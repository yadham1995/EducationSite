package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import com.kfh.educationsite.service.client.APIClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestIntegration implements IntegrationMethod {

    private APIClient apiClient;

    @Override
    public String getType() {
        return IntegrationModes.REST.value();
    }

    @Override
    public List<CourseDTO> consume(GenericRequestDTO message) {
        List<CourseDTO> response = apiClient.getAllCourses();
        return response;
    }


}
