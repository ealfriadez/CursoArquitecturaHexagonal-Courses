package pe.edu.unfv.courses.infraestructure.adapters.input.rest;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.application.ports.input.CourseInputPort;
import pe.edu.unfv.courses.application.ports.input.StudentsInputPort;
import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.mapper.CourseRestMapper;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.request.CourseCreateRequest;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.response.CourseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseRestAdapter {

	private final CourseInputPort courseInputPort;
	private final StudentsInputPort studentsInputPort;
	private final CourseRestMapper courseRestMapper;
	
	@GetMapping
	public List<CourseResponse> findAll(){
		return courseRestMapper.toCourseResponses(courseInputPort.findAll());		
	}
	
	@GetMapping("/{id}")
	public CourseResponse findById(@PathVariable Long id) {
		return courseRestMapper.toCourseResponse(courseInputPort.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CourseResponse> save(@Valid @RequestBody CourseCreateRequest request){
		Course course = courseInputPort.save(courseRestMapper.toCourse(request));
		return ResponseEntity.created(URI.create("/courses".concat(course.getId().toString())))
				.body(courseRestMapper.toCourseResponse(course));
	}
	
	@PutMapping("/{id}")
	public CourseResponse update(@PathVariable Long id, @Valid @RequestBody CourseCreateRequest request) {
		return courseRestMapper.toCourseResponse(courseInputPort.update(id, courseRestMapper.toCourse(request)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		courseInputPort.deleteById(id);
	}
	
}
