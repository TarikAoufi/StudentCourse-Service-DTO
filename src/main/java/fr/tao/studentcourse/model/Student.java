package fr.tao.studentcourse.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "STUDENT")
public class Student {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, 
			inverseJoinColumns = {@JoinColumn(name = "COURSE_ID") })
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	
	public Student(String name) {
		this.name = name;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	public void removeCourse(Course course) {
        this.getCourses().remove(course);
        course.getStudents().remove(this);
    }
	
	public void removeCourses() {
        for (Course course : new HashSet<>(courses)) {
            removeCourse(course);
        }
    }
}
