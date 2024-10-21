package pe.edu.unfv.courses.infraestructure.adapters.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseEntity;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoursePersistenceMapper {
	
	Course toCourse(CourseEntity entity);	
	
	CourseEntity toCourseEntity(Course course);
	
	List<Course> toCourses(List<CourseEntity> entities);
}
