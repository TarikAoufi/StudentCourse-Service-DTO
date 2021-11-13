package fr.tao.studentcourse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.tao.studentcourse.model.Course;
import fr.tao.studentcourse.model.Student;
import fr.tao.studentcourse.repository.CourseRepository;
import fr.tao.studentcourse.repository.StudentRepository;

@SpringBootApplication
public class StudentCourseServiceDtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseServiceDtoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(StudentRepository studentRepository, 
			CourseRepository courseRepository) {
		return args -> {
			Student student1 = new Student("Nigel Poulton");
			// Create courses
			Course course1 = new Course("The Kubernetes Book");
			Course course2 = new Course("Quick Start Kubernetes");
			Course course3 = new Course("Docker Deep Dive");
			// Add courses references in the student
			student1.getCourses().add(course1);
			student1.getCourses().add(course2);
			student1.getCourses().add(course3);
			// Add student reference in the courses
			course1.getStudents().add(student1);
			course2.getStudents().add(student1);
			course3.getStudents().add(student1);
			// Save student
			studentRepository.save(student1);
			
			Student student2 = new Student("Ali Heckler");		
			// Create courses
			Course course4 = new Course("JavaFX 9 by Example"); 
			Course course5 = new Course("Java 13 Revealed");
			Course course6 = new Course("Spring Boot: Up and Running"); 
			// Add courses references in the student	
			student2.getCourses().add(course4);
			student2.getCourses().add(course5);
			student2.getCourses().add(course6);
			// Add student reference in the courses
			course4.getStudents().add(student2);
			course5.getStudents().add(student2);
			course6.getStudents().add(student2);
			// Save student
			studentRepository.save(student2);
	
		};
	}

}
