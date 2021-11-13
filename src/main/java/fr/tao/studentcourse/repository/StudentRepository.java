package fr.tao.studentcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.tao.studentcourse.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findByName(String name);
}
