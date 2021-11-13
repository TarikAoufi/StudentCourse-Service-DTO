package fr.tao.studentcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.tao.studentcourse.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	public Course findByName(String courseName);
}
