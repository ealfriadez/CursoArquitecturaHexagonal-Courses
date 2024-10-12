package pe.edu.unfv.courses.application.ports.input;

import java.util.List;

import pe.edu.unfv.courses.domain.models.Course;

public interface CourseInputPort {

	Course findById(Long id);
	List<Course> findAll();
	Course save(Course course);
	Course update(Long id, Course course);
	void deleteById(Long id);
}
