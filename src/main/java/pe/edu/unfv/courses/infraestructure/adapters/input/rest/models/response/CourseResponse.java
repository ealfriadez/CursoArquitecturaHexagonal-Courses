package pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.unfv.courses.domain.models.Student;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

	private Long id;
	private String name;
	private List<Student> students;
	private String timestamp;
}
