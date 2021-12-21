package fr.tao.studentcourse.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tao.studentcourse.dto.CourseDto;
import fr.tao.studentcourse.model.Course;
import fr.tao.studentcourse.model.Student;
import fr.tao.studentcourse.repository.CourseRepository;
import fr.tao.studentcourse.repository.StudentRepository;
import fr.tao.studentcourse.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courseDtos = new ArrayList<>();
		List<Course> courses = courseRepository.findAll();
		courses.stream().forEach(course -> {
			CourseDto courseDto = mapEntityToDto(course);
			courseDtos.add(courseDto);
		});
		return courseDtos;
	}

	@Override
	public CourseDto addCourse(CourseDto courseDto) {
		Course course = new Course();
		mapDtoToEntity(courseDto, course);
		Course savedCourse = courseRepository.save(course);
		return mapEntityToDto(savedCourse);
	}

	@Override
	public CourseDto updateCourse(Integer id, CourseDto courseDto) {
		Optional<Course> course = courseRepository.findById(id);
		if (course.isPresent()) {
			course.get().getStudents().clear();
			mapDtoToEntity(courseDto, course.get());
			Course entity = courseRepository.save(course.get());
			return mapEntityToDto(entity);
		}
		return null;
	}

	@Override
	public String deleteCourse(Integer id) {
		Optional<Course> course = courseRepository.findById(id);
		// Remove the related courses from student entity.
		if (course.isPresent()) {
			course.get().removeStudents();
			courseRepository.deleteById(course.get().getId());
			return "Course with id: " + id + " deleted successfully!";
		}
		return null;
	}

	private CourseDto mapEntityToDto(Course course) {
		if (course == null) {
            return null;
        }
		return CourseDto.builder()
				.id(course.getId())
				.name(course.getName())
				.students(course.getStudents().stream().collect(Collectors.toSet()))
				.build();
	}

	private void mapDtoToEntity(CourseDto courseDto, Course course) {
		course.setName(courseDto.getName());
		if (null == course.getStudents()) {
			course.setStudents(new HashSet<>());
		}
		courseDto.getStudents().stream().forEach(studentInDto -> {
			Student student = studentRepository.findByName(studentInDto.getName());
			if (null == student) {
				student = new Student();
				student.setCourses(new HashSet<>());
			}
			student.setName(studentInDto.getName());
			course.addStudent(student);
		});

	}

}
