package com.kfh.educationsite.repository;

import com.kfh.educationsite.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByCourseId(long courseId);

}
