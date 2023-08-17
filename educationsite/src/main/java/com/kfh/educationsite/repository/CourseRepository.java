package com.kfh.educationsite.repository;

import com.kfh.educationsite.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
