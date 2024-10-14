package pe.edu.unfv.courses.infraestructure.adapters.output.restclient.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.unfv.courses.domain.models.Student;

@FeignClient(name = "students-service", url = "http://localhost:8080")
public interface StudentFeignClient {

	@GetMapping("/students/{id}")
	Student findById(@PathVariable Long Id);
	
	@GetMapping("/students/find-by-ids")
	List<Student> findByIds(@RequestParam List<Long> ids);
}
