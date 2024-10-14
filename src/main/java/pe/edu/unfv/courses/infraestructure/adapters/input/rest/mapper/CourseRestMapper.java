package pe.edu.unfv.courses.infraestructure.adapters.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.infraestructure.adapters.input.rest.models.request.CourseCreateRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {

	Course toCourse(CourseCreateRequest request);
}
