package fr.tao.studentcourse.dto;

import java.util.HashSet;
import java.util.Set;

import fr.tao.studentcourse.model.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
	
	private Integer id;
	private String name;
	private Set<Course> courses = new HashSet<>();

}
