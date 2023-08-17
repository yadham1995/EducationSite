package com.kfh.educationsite.controller;

import com.kfh.educationsite.datamodel.CourseDTO;
import com.kfh.educationsite.datamodel.CourseResponse;
import com.kfh.educationsite.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.kfh.educationsite.utilities.CommonConstants.PaginationConstants.*;

@Api(value = "CRUD REST APIs for Course end point")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "addCourse REST API")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDto) {
        return new ResponseEntity<>(courseService.addCourse(courseDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateCourse")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable(value = "id") Long courseId,
                                                  @Valid @RequestBody CourseDTO courseDto) {
        CourseDTO updatedCourse = courseService.updateCourse(courseId, courseDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @ApiOperation(value = "deleteCourse")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable(name = "id") Long id){
        courseService.deleteCourseById(id);
        return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
    }

    @ApiOperation(value = "getCourseById")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllCoursesWithPagination")
    @GetMapping("/pagination")
    public CourseResponse getAllCoursesWithPagination(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return courseService.getAllCourses(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "getAllCourses")
    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.getCourses();
    }
}
