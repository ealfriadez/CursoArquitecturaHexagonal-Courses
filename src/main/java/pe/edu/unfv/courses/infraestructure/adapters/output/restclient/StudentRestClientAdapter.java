package pe.edu.unfv.courses.infraestructure.adapters.output.restclient;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.application.ports.output.ExternalStudentOuputPort;
import pe.edu.unfv.courses.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.courses.domain.exceptions.NonEnrolledStudentException;
import pe.edu.unfv.courses.domain.models.Student;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseStudent;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;
import pe.edu.unfv.courses.infraestructure.adapters.output.restclient.client.StudentFeignClient;

@Component
@RequiredArgsConstructor
public class StudentRestClientAdapter implements ExternalStudentOuputPort{

	private final StudentFeignClient feignClient;
	private final CourseJpaRepository courseJpaRepository;
	
	@Override
	public Student addStudentToCourse(Long courseId, Long studentId) {		
		return courseJpaRepository.findById(courseId)
				.map(courseEntity -> {
					Student student = feignClient.findById(studentId);	// FeignException -> 404, 400, 500
					CourseStudent courseStudent = new CourseStudent();
					courseStudent.setStudentId(student.getId());
					courseEntity.addCourseStudent(courseStudent);
					courseJpaRepository.save(courseEntity);
					return student;
				}).orElseThrow(CourseNotFoundException::new);
	}

	/*
	@Override
	public Student removeStudentFromCourse(Long courseId, Long studentId) {		
		return courseJpaRepository.findById(courseId)
				.map(courseEntity -> {
					Student student = feignClient.findById(studentId);
					CourseStudent courseStudent = new CourseStudent();
					courseStudent.setId(student.getId());
					courseEntity.removeCourseStudent(courseStudent);
					courseJpaRepository.save(courseEntity);
					return student;
				}).orElseThrow(CourseNotFoundException::new);
	}
	*/
	
	@Override
	public Student removeStudentFromCourse(Long courseId, Long studentId) {		
		return courseJpaRepository.findById(courseId)
				.map(courseEntity -> {
					Student student = feignClient.findById(studentId);
					boolean isEnrolled = courseEntity.getCourseStudentList()
						.stream()
						.anyMatch(cs -> cs.getStudentId().equals(studentId));
					if (isEnrolled) {
						CourseStudent courseStudent = new CourseStudent();
						courseStudent.setStudentId(student.getId());
						courseEntity.removeCourseStudent(courseStudent);
						courseJpaRepository.save(courseEntity);
						return student;
					}
					throw new NonEnrolledStudentException(studentId);
				}).orElseThrow(CourseNotFoundException::new);
	}

	@Override
	public void removeStudentFromCollection(Long studentId) {
		courseJpaRepository.deleteCourseStudentByStudentById(studentId);
	}
}
