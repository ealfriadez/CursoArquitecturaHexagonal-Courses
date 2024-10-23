package pe.edu.unfv.courses.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.courses.application.ports.input.CourseInputPort;
import pe.edu.unfv.courses.application.ports.input.ExternalStudentsInputPort;
import pe.edu.unfv.courses.application.ports.output.CoursePersistencePort;
import pe.edu.unfv.courses.application.ports.output.ExternalStudentOuputPort;
import pe.edu.unfv.courses.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.domain.models.Student;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseService implements CourseInputPort, ExternalStudentsInputPort{

	private final CoursePersistencePort coursePersistencePort;
	private final ExternalStudentOuputPort studentOuputPort;
	
	@Override
	public Course findById(Long id) {		
		return coursePersistencePort.findById(id)
				.orElseThrow(CourseNotFoundException::new);
	}

	@Override
	public List<Course> findAll() {		
		return coursePersistencePort.findAll();
	}

	@Override
	public Course save(Course course) {		
		return coursePersistencePort.save(course);
	}

	@Override
	public Course update(Long id, Course course) {		
		return coursePersistencePort.findById(id)
				.map(courseDb -> {
					courseDb.setName(course.getName());
					return coursePersistencePort.save(courseDb);
				}).orElseThrow(CourseNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		if (coursePersistencePort.findById(id).isEmpty()) {
			throw new CourseNotFoundException();
		}
		
		coursePersistencePort.deleteById(id);		
	}
	
	@Override
	public Student addStudentToCourse(Long courseId, Long studentId) {		
		return studentOuputPort.addStudentToCourse(courseId, studentId);
	}

	@Override
	public Student removeStudentFromCourse(Long courseId, Long studentId) {		
		return studentOuputPort.removeStudentFromCourse(courseId, studentId);
	}

	@Override
	public void removeStudentFromCollection(Long studentId) {
		studentOuputPort.removeStudentFromCollection(studentId);
	}
}
