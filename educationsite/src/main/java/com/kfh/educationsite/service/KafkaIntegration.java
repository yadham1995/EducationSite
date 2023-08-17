package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaIntegration implements IntegrationMethod {
    @Override
    public String getType() {
        return IntegrationModes.KAFKA.value();
    }

    @Override
    public List<CourseDTO> consume(GenericRequestDTO message) {
        System.out.println("Sending Message To Kafka Queue: " + message);
        return null;
    }
}
