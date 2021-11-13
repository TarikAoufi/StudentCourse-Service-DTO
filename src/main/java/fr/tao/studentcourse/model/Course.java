package fr.tao.studentcourse.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "COURSE")
public class Course {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence")
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private Set<Student> students = new HashSet<>();
	
	public Course(String name) {
		this.name = name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
		student.getCourses().add(this);
	}
	
	public void removeStudent(Student student) {
        this.getStudents().remove(student);
        student.getCourses().remove(this);
    }

	public void removeStudents() {
		for (Student student : new HashSet<>(students)) {
			removeStudent(student);
		}

	}

}
