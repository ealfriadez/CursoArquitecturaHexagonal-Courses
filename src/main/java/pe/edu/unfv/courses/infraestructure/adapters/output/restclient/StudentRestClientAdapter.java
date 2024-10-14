package pe.edu.unfv.courses.infraestructure.adapters.output.restclient;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.application.ports.output.StudentOuputPort;
import pe.edu.unfv.courses.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.courses.domain.models.Student;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseStudent;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;
import pe.edu.unfv.courses.infraestructure.adapters.output.restclient.client.StudentFeignClient;

@Component
@RequiredArgsConstructor
public class StudentRestClientAdapter implements StudentOuputPort{

	private final StudentFeignClient feignClient;
	private final CourseJpaRepository courseJpaRepository;
	
	@Override
	public Student addStudentToCourse(Long courseId, Long studentId) {		
		return courseJpaRepository.findById(courseId)
				.map(courseEntity -> {
					Student student = feignClient.findById(studentId);
					CourseStudent courseStudent = new CourseStudent();
					courseStudent.setStudentId(student.getId());
					courseEntity.addCourseStudent(courseStudent);
					courseJpaRepository.save(courseEntity);
					return student;
				}).orElseThrow(CourseNotFoundException::new);
	}

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
		
		/*
		return courseJpaRepository.findById(courseId)
				.map(courseEntity -> {
					Student student = feignClient.findById(studentId);
					List<Long> studentIds = courseEntity.getCourseStudentList()
							.stream()
							.map(CourseStudent::getStudentId)
							.toList();					
					boolean estaMatriculado = studentIds.stream()
							.anyMatch(id -> id.equals(student.getId()));
					
					if (estaMatriculado) {
						CourseStudent courseStudent = new CourseStudent();
						courseStudent.setStudentId(student.getId());
						courseEntity.removeCourseStudent(courseStudent);
						courseJpaRepository.save(courseEntity);
					}
					
					throw new RuntimeException("");
				})
		*/
	}

	@Override
	public void removeStudentFromCollection(Long stundentId) {
		courseJpaRepository.deleteCourseStudentByStudentById(stundentId);
	}
}
