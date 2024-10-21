package pe.edu.unfv.courses.infraestructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseEntity;

public interface CourseJpaRepository extends CrudRepository<CourseEntity, Long>{

	@Modifying
	@Transactional
	@Query("DELETE FROM CourseStudent cs WHERE cs.studentId = ?1")	
	void deleteCourseStudentByStudentById(Long studentId);
}
