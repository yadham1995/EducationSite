package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqIntegration implements IntegrationMethod {

    @Override
    public String getType() {
        return IntegrationModes.RABBIT.value();
    }
    @Override
    public List<CourseDTO> consume(GenericRequestDTO message) {
        System.out.println("Sending Message To RabbitMq Queue: " + message);
        return null;
    }
}
