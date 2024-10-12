package pe.edu.unfv.courses.infraestructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.application.ports.output.CoursePersistencePort;
import pe.edu.unfv.courses.domain.models.Course;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.mapper.CoursePersistenceMapper;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseEntity;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;

@Component
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePersistencePort{

	private final CourseJpaRepository courseJpaRepository;
	private final CoursePersistenceMapper persistenceMapper;
	
	@Override
	public Optional<Course> findById(Long id) {		
		return courseJpaRepository.findById(id)
				.map(persistenceMapper::toCourse);
	}

	@Override
	public List<Course> findAll() {		
		return persistenceMapper.toCourses(
				(List<CourseEntity>)courseJpaRepository.findAll());
	}

	@Override
	public Course save(Course course) {		
		return persistenceMapper.toCourse(
				courseJpaRepository.save(persistenceMapper.toCourseEntity(course)));
	}

	@Override
	public void deleteById(Long id) {
		courseJpaRepository.deleteById(id);		
	}

}
