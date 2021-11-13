package fr.tao.studentcourse.service;

import java.util.List;

import fr.tao.studentcourse.dto.CourseDto;

public interface CourseService {
	
	public List<CourseDto> getAllCourses();
	public CourseDto addCourse(CourseDto courseDto);
	public CourseDto updateCourse(Integer id, CourseDto courseDto);
	public String deleteCourse(Integer id);
}
