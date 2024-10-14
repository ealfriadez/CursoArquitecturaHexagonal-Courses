package pe.edu.unfv.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CursoArquitecturaHexagonalCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoArquitecturaHexagonalCoursesApplication.class, args);
	}

}
