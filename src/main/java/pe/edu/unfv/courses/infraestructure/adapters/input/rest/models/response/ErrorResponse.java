package pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.enums.ErrorType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String code;
	private ErrorType errorType;	//FUNCTIONAL, SYSTEM
	private String genericMessage;
	private List<String> details;
	private String timestamp;
}
