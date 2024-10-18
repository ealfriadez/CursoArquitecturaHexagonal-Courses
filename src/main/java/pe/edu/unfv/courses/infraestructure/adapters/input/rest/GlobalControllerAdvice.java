package pe.edu.unfv.courses.infraestructure.adapters.input.rest;

import static pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.enums.ErrorType.SYSTEM;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.COURSE_BAD_PARAMETERS;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.COURSE_NOT_FOUND;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.INTERNAL_SERVER_ERROR;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.NON_ENROLLED_STUDENT;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.STUDENT_NOT_FOUND;
import static pe.edu.unfv.courses.infraestructure.utils.CourseErrorCatalog.WEB_CLIENT_ERROR;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.courses.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.courses.domain.exceptions.NonEnrolledStudentException;
import pe.edu.unfv.courses.domain.exceptions.StudentNotFoundException;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

	private final String ERROR_LOG_MESSAGE = "Error -> code: {}, type: {}, message: {}";
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CourseNotFoundException.class)
	public ErrorResponse handleCourseNotFoundException() {
		
		log.error(ERROR_LOG_MESSAGE, COURSE_NOT_FOUND.getCode(), FUNCTIONAL, COURSE_NOT_FOUND.getMessage());
		
		return ErrorResponse.builder()
				.code(COURSE_NOT_FOUND.getCode())
				.errorType(FUNCTIONAL)
				.genericMessage(COURSE_NOT_FOUND.getMessage())
				.timestamp(LocalDate.now().toString())
				.build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(StudentNotFoundException.class)
	public ErrorResponse handleStudentNotFoundException() {
		
		log.error(ERROR_LOG_MESSAGE, STUDENT_NOT_FOUND.getCode(), FUNCTIONAL, STUDENT_NOT_FOUND.getMessage());
		
		return ErrorResponse.builder()
				.code(STUDENT_NOT_FOUND.getCode())
				.errorType(FUNCTIONAL)
				.genericMessage(STUDENT_NOT_FOUND.getMessage())
				.timestamp(LocalDate.now().toString())
				.build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NonEnrolledStudentException.class)
	public ErrorResponse handleNonEnrolledStudentException(NonEnrolledStudentException e){
		
		log.error(ERROR_LOG_MESSAGE, NON_ENROLLED_STUDENT.getCode(), FUNCTIONAL, NON_ENROLLED_STUDENT.getMessage());
		
		return ErrorResponse.builder()
				.code(NON_ENROLLED_STUDENT.getCode())
				.errorType(FUNCTIONAL)
				.genericMessage(NON_ENROLLED_STUDENT.getMessage())
				.details(Collections.singletonList(e.getMessage()))
				.timestamp(LocalDate.now().toString())
				.build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		BindingResult bindingResult = e.getBindingResult();
		
		log.error(ERROR_LOG_MESSAGE, COURSE_BAD_PARAMETERS.getCode(), FUNCTIONAL, COURSE_BAD_PARAMETERS.getMessage());
		
		return ErrorResponse.builder()
				.code(COURSE_BAD_PARAMETERS.getCode())
				.errorType(FUNCTIONAL)
				.genericMessage(COURSE_BAD_PARAMETERS.getMessage())
				.details(bindingResult.getFieldErrors().stream()						
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
						.toList())
				.timestamp(LocalDate.now().toString())
				.build();
	}	
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handleFeignException(FeignException e){
		
		log.error(ERROR_LOG_MESSAGE, WEB_CLIENT_ERROR.getCode(), FUNCTIONAL, WEB_CLIENT_ERROR.getMessage());
		
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(WEB_CLIENT_ERROR.getCode())
				.errorType(FUNCTIONAL)
				.genericMessage(WEB_CLIENT_ERROR.getMessage())
				.details(Collections.singletonList(e.getMessage()))
				.timestamp(LocalDate.now().toString())
				.build();
		
		return ResponseEntity.status(e.status())
				.body(errorResponse);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleWebClientErrorException(Exception e) {
		
		log.error(ERROR_LOG_MESSAGE, INTERNAL_SERVER_ERROR.getCode(), SYSTEM, INTERNAL_SERVER_ERROR.getMessage());
		
		return ErrorResponse.builder()
				.code(INTERNAL_SERVER_ERROR.getCode())
				.errorType(SYSTEM)
				.genericMessage(INTERNAL_SERVER_ERROR.getMessage())
				.details(Collections.singletonList(e.getMessage()))
				.timestamp(LocalDate.now().toString())
				.build();
	}	
}
