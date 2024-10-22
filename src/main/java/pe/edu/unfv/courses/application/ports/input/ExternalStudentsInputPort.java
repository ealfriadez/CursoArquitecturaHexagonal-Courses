package pe.edu.unfv.courses.application.ports.input;

import pe.edu.unfv.courses.domain.models.Student;

public interface ExternalStudentsInputPort {

	Student addStudentToCourse(Long courseId, Long studentId);
	Student removeStudentFromCourse(Long courseId, Long studentId);
	void removeStudentFromCollection(Long studentId);
}
