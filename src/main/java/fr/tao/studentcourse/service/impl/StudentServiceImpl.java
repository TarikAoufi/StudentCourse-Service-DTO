package fr.tao.studentcourse.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tao.studentcourse.dto.StudentDto;
import fr.tao.studentcourse.model.Course;
import fr.tao.studentcourse.model.Student;
import fr.tao.studentcourse.repository.CourseRepository;
import fr.tao.studentcourse.repository.StudentRepository;
import fr.tao.studentcourse.service.StudentService;

@Service 
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student = new Student();
		mapDtoToEntity(studentDto, student);
		Student savedStudent = studentRepository.save(student);
		return mapEntityToDto(savedStudent);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        students.stream().forEach(student -> {
            StudentDto studentDto = mapEntityToDto(student);
            studentDtos.add(studentDto);
        });
        return studentDtos;
	}
	
	@Transactional
	@Override
	public StudentDto updateStudent(Integer id, StudentDto studentDto) {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			student.get().getCourses().clear();
			mapDtoToEntity(studentDto, student.get());
			Student entity = studentRepository.save(student.get());
			return mapEntityToDto(entity);
		}
		return null;
	}

	@Override
    public String deleteStudent(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        //Remove the related courses from student entity.
        if(student.isPresent()) {
            student.get().removeCourses();
            studentRepository.deleteById(student.get().getId());
            return "Student with id: " + id + " deleted successfully!";
        }
        return null;
    }
	
	private void mapDtoToEntity(StudentDto studentDto, Student student) {
		student.setName(studentDto.getName());
		if (null == student.getCourses()) {
            student.setCourses(new HashSet<>());
        }
		studentDto.getCourses().stream().forEach(courseInDto -> {
            Course course = courseRepository.findByName(courseInDto.getName());
            if (null == course) {
                course = new Course();
                course.setStudents(new HashSet<>());
            }
            course.setName(courseInDto.getName());
            student.addCourse(course);
        });		
	}
	
	private StudentDto mapEntityToDto(Student student) {
		if (student == null) {
            return null;
        }
		return StudentDto.builder()
				.name(student.getName())
				.id(student.getId())
				.courses(student.getCourses().stream().collect(Collectors.toSet()))
				.build();
	}

}
