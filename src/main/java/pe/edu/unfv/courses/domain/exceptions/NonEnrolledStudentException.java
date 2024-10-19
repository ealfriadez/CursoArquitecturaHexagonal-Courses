package pe.edu.unfv.courses.domain.exceptions;

public class NonEnrolledStudentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonEnrolledStudentException(Long id) {
		super("The student with the id: " + id + ", is not enrolled in the course.");
	}
}
