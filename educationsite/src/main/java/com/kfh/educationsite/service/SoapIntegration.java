package com.kfh.educationsite.service;

import com.kfh.educationsite.datamodel.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoapIntegration implements IntegrationMethod {

    @Override
    public String getType() {
        return IntegrationModes.SOAP.value();
    }

    @Override
    public List<CourseDTO> consume(GenericRequestDTO message) {

        System.out.println("Sending Soap Request: " + message);
        return null;
    }
}
