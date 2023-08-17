package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import com.kfh.educationsite.datamodel.StudentDTO;
import com.kfh.educationsite.entity.Course;
import com.kfh.educationsite.entity.Student;
import com.kfh.educationsite.exception.ResourceNotFoundException;
import com.kfh.educationsite.repository.CourseRepository;
import com.kfh.educationsite.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final Map<String, IntegrationMethod> integrationMethodMap;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, List<IntegrationMethod> integrationMethodList) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.integrationMethodMap = integrationMethodList.stream().collect(Collectors.toMap(IntegrationMethod::getType, Function.identity()));
    }

    @Transactional
    public StudentDTO allocateStudentWithSelectedCourse(Long courseId, StudentDTO studentDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Course", "id", courseId));
        Student student = mapToEntity(studentDTO);
        student.getCourse().add(course);
        Student newStudent = studentRepository.save(student);
        return mapToDto(newStudent);
    }

    public List<StudentDTO> getStudentsBySelectedCourse(Long courseId) {
        List<Student> students = studentRepository.findByCourseId(courseId);
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteStudentById(Long studentId) {
        Student studentToBeDeleted = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        studentRepository.delete(studentToBeDeleted);
    }

    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student oldStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        Student updatedStudent = mapToEntity(studentDTO);
        updatedStudent.setId(oldStudent.getId());
        //if (studentDTO.getCourses() != null && !studentDTO.getCourses().isEmpty()) {
        //studentDTO.getCourses().forEach(s -> s.setStudents(updatedStudent));
        //updatedPlot.setIrrigationSlots(plotDto.getIrrigationSlots());
        //}
        return mapToDto(studentRepository.save(updatedStudent));
    }

    private Student mapToEntity(StudentDTO studentDTO) {
        if (studentDTO == null)
            return null;
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFullNameEnglish(studentDTO.getFullNameEnglish());
        student.setFullNameArabic(studentDTO.getFullNameArabic());
        student.setEmailAddress(studentDTO.getEmailAddress());
        student.setTelephoneNumber(studentDTO.getTelephoneNumber());
        student.setAddress(student.getAddress());
        if (studentDTO.getCourses() != null && !studentDTO.getCourses().isEmpty())
            student.setCourse(studentDTO.getCourses());
        return student;
    }

    private StudentDTO mapToDto(Student student) {
        if (student == null)
            return null;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFullNameEnglish(student.getFullNameEnglish());
        studentDTO.setFullNameArabic(student.getFullNameArabic());
        studentDTO.setEmailAddress(student.getEmailAddress());
        studentDTO.setTelephoneNumber(student.getTelephoneNumber());
        studentDTO.setAddress(student.getAddress());
        if (student.getCourse() != null && !student.getCourse().isEmpty())
            studentDTO.setCourses(student.getCourse());
        return studentDTO;
    }

    public List<CourseDTO> getAllCoursesByConsumingAPI(GenericRequestDTO genericRequestDTO) {
        IntegrationModes integrationMode = IntegrationModes.fromValue(genericRequestDTO.getIntegrationFramework());
        IntegrationMethod integrationMethodService = integrationMethodMap.get(integrationMode.value());
        return integrationMethodService.consume(genericRequestDTO);
    }
}
