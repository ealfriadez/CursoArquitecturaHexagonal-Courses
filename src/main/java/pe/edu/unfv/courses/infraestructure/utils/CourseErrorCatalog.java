package pe.edu.unfv.courses.infraestructure.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseErrorCatalog {
	
	COURSE_NOT_FOUND("COURSE_ERR_001", "Course not found."),
	STUDENT_NOT_FOUND("COURSE_ERR_002", "Studente not found."),
	COURSE_BAD_PARAMETERS("COURSE_ERR_003", "Invalid parameters for creation course."),
	NON_ENROLLED_STUDENT("COURSE_ERR_004", "Nom enrolled student."),
	WEB_CLIENT_ERROR("COURSE_ERR_005", "Error in connection with ms-student-service."),
	INTERNAL_SERVER_ERROR("GENERIC_ERR_MS_006", "Internal server error.");
	
	private final String code;
	private final String message;
}
