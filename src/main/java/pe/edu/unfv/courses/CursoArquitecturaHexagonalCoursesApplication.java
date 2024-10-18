package pe.edu.unfv.courses;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.RequiredArgsConstructor;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseEntity;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models.CourseStudent;
import pe.edu.unfv.courses.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;

@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class CursoArquitecturaHexagonalCoursesApplication implements CommandLineRunner{

	private final CourseJpaRepository courseJpaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoArquitecturaHexagonalCoursesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CourseStudent cs1 = new  CourseStudent();
		cs1.setStudentId(1L);
		
		CourseStudent cs2 = new  CourseStudent();
		cs1.setStudentId(2L);
		
		CourseStudent cs3 = new  CourseStudent();
		cs1.setStudentId(3L);
		
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName("Spring");
		courseEntity.addCourseStudent(cs1);
		courseEntity.addCourseStudent(cs2);
		
		CourseEntity courseEntity2 = new CourseEntity();
		courseEntity2.setName("Node JS");
		courseEntity2.addCourseStudent(cs3);
		
		courseJpaRepository.saveAll(Arrays.asList(courseEntity, courseEntity2));
	}

	
}
