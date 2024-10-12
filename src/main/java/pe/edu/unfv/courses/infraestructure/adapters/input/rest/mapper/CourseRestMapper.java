package pe.edu.unfv.courses.infraestructure.adapters.input.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.request.CourseCreateRequest;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.response.CourseResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {

	Course toCourse(CourseCreateRequest request);
	
	CourseResponse toCourseResponse(Course course);
	
	List<CourseResponse> toCourseResponses(List<Course> courses);
}
