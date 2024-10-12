package pe.edu.unfv.courses.application.ports.input;

import java.util.Optional;

import pe.edu.unfv.courses.domain.models.Student;

public interface StudentsInputPort {

	Optional<Student> addStudentToCourse(Long courseId, Long studentId);
	Optional<Student> removeStudentFromCourse(Long courseId, Long studentId);
	void removeStudentFromCollection(Long stundentId);
}
