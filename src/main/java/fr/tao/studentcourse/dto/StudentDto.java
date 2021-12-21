package fr.tao.studentcourse.dto;

import java.util.Set;

import fr.tao.studentcourse.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @Builder @AllArgsConstructor
public class StudentDto {
	
	private Integer id;
	private String name;
	private Set<Course> courses;

}
