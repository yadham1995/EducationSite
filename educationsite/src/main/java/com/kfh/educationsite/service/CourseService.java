package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import com.kfh.educationsite.datamodel.CourseResponse;
import com.kfh.educationsite.entity.Course;
import com.kfh.educationsite.exception.ResourceNotFoundException;
import com.kfh.educationsite.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseDTO addCourse(CourseDTO courseDto) {
        Course course = courseRepository.save(mapToEntity(courseDto));
        return mapToDto(course);
    }

    public CourseDTO updateCourse(Long courseId, CourseDTO courseDto) {
        Course oldCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        Course updatedCourse = mapToEntity(courseDto);
        updatedCourse.setId(oldCourse.getId());
        return mapToDto(courseRepository.save(updatedCourse));
    }

    public void deleteCourseById(Long courseId) {
        Course courseToBeDeleted = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        courseRepository.delete(courseToBeDeleted);
    }

    public CourseResponse getAllCourses(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Course> courses = courseRepository.findAll(pageable);

        List<Course> listOfCourses = courses.getContent();

        List<CourseDTO> content = listOfCourses.stream().map(this::mapToDto).collect(Collectors.toList());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setContent(content);
        courseResponse.setPageNo(courses.getNumber());
        courseResponse.setPageSize(courses.getSize());
        courseResponse.setTotalElements(courses.getTotalElements());
        courseResponse.setTotalPages(courses.getTotalPages());
        courseResponse.setLast(courses.isLast());

        return courseResponse;
    }

    public List<CourseDTO> getCourses() {
       return courseRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        return mapToDto(course);
    }

    public Course mapToEntity(CourseDTO courseDTO) {
        if (courseDTO == null)
            return null;
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        return course;
    }

    public CourseDTO mapToDto(Course course) {
        if (course == null)
            return null;
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        return courseDTO;
    }

}
