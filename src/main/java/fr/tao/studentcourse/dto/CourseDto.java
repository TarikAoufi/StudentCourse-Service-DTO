package fr.tao.studentcourse.dto;

import java.util.HashSet;
import java.util.Set;

import fr.tao.studentcourse.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
	
	private Integer id;
	private String name;
	private Set<Student> students = new HashSet<>();

}
