package pe.edu.unfv.courses.application.ports.output;

import java.util.List;
import java.util.Optional;

import pe.edu.unfv.courses.domain.models.Course;

public interface CoursePersistencePort {

	Optional<Course> findById(Long id);
	List<Course> findAll();
	Course save(Course course);
	void deleteById(Long id);
}
