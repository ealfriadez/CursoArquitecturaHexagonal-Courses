package pe.edu.unfv.courses.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.application.ports.input.CourseInputPort;
import pe.edu.unfv.courses.application.ports.input.StudentsInputPort;
import pe.edu.unfv.courses.application.ports.output.CoursePersistencePort;
import pe.edu.unfv.courses.application.ports.output.StudentOuputPort;
import pe.edu.unfv.courses.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.domain.models.Student;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseInputPort, StudentsInputPort{

	private final CoursePersistencePort coursePersistencePort;
	private final StudentOuputPort studentOuputPort;
	
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
	public Optional<Student> addStudentToCourse(Long courseId, Long studentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Student> removeStudentFromCourse(Long courseId, Long studentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void removeStudentFromCollection(Long stundentId) {
		// TODO Auto-generated method stub
		
	}
}
