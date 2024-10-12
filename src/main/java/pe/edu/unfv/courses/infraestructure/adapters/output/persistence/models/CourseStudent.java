package pe.edu.unfv.courses.infraestructure.adapters.output.persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "courses_students")
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "student_id")
	private Long studentId;
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}		
		
		if (!(object instanceof CourseStudent courseStudent)) {
			return false;
		}
		
		return this.studentId != null && this.studentId.equals(courseStudent.studentId);
	}	
}
