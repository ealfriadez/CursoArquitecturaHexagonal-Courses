package pe.edu.unfv.courses.application.ports.output;

import java.util.Optional;

import pe.edu.unfv.courses.domain.models.Student;

public interface StudentOuputPort {

	Optional<Student> addStudentToCourse(Long courseId, Long studentId);
	Optional<Student> removeStudentFromCourse(Long courseId, Long studentId);
	void removeStudentFromCollection(Long stundentId);
}
