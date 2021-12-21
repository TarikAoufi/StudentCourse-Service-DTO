package fr.tao.studentcourse.dto;

import java.util.Set;

import fr.tao.studentcourse.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
	
	private Integer id;
	private String name;
	private Set<Student> students;

}
