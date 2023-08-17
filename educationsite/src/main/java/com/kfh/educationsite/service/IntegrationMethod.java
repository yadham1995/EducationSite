package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;

import java.util.List;

public interface IntegrationMethod {

    String getType();
    List<CourseDTO> consume(GenericRequestDTO message);
}
