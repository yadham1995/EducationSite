package com.kfh.educationsite.controller;

import com.kfh.educationsite.datamodel.CourseDTO;
import com.kfh.educationsite.datamodel.StudentDTO;
import com.kfh.educationsite.service.GenericRequestDTO;
import com.kfh.educationsite.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Student EndPoint")
@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ApiOperation(value = "allocateStudentWithSelectedCourse")
    @PostMapping("/course/{courseId}/students")
    public ResponseEntity<StudentDTO> allocateStudentWithSelectedCourse(@PathVariable(value = "courseId") Long courseId,
                                                                   @Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.allocateStudentWithSelectedCourse(courseId, studentDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "getAllCoursesByConsumingAPI")
    @GetMapping("/courses")
    public List<CourseDTO> getAllCoursesByConsumingAPI(@Valid @RequestBody GenericRequestDTO genericRequestDTO) {
        return studentService.getAllCoursesByConsumingAPI(genericRequestDTO);
    }

    @ApiOperation(value = "getStudentsBySelectedCourse")
    @GetMapping("/course/{courseId}/students")
    public List<StudentDTO> getStudentsBySelectedCourse(@PathVariable(value = "courseId") Long courseId) {
        return studentService.getStudentsBySelectedCourse(courseId);
    }

    @ApiOperation(value = "updateStudentCourses")
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudentCourses(@PathVariable(value = "studentId") Long studentId,
                                                  @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @ApiOperation(value = "deleteStudent")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
    }
}
