package fr.tao.studentcourse.service;

import java.util.List;

import fr.tao.studentcourse.dto.StudentDto;

public interface StudentService {
	
	public StudentDto addStudent(StudentDto studentDto);
    public List<StudentDto> getAllStudents();
    public StudentDto updateStudent(Integer id, StudentDto student);
    public String deleteStudent(Integer id);

}
