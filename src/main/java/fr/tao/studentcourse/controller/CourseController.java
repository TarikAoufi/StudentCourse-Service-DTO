package fr.tao.studentcourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.studentcourse.dto.CourseDto;
import fr.tao.studentcourse.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDto>> getAllCourses() {
		List<CourseDto> courseDtos = courseService.getAllCourses();
		return new ResponseEntity<>(courseDtos, HttpStatus.OK);
	}
	
	@PostMapping("/course")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
		CourseDto crs = courseService.addCourse(courseDto);
        return new ResponseEntity<>(crs, HttpStatus.CREATED);
    }
	
	@PutMapping("/course/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable(name = "id") Integer id,
            @RequestBody CourseDto courseDto) {
		CourseDto crs = courseService.updateCourse(id, courseDto);
        return new ResponseEntity<>(crs, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable(name = "id") Integer id) {
        String message = courseService.deleteCourse(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
