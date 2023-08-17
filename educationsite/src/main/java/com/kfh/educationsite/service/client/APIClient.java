package com.kfh.educationsite.service.client;

import com.kfh.educationsite.datamodel.CourseDTO;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8080", value = "SENSOR-DEVICE-SERVICE")
public interface APIClient {

    @GetMapping
    @Retry(name = "${spring.application.name}", fallbackMethod = "alert")
    List<CourseDTO> getAllCourses();

    default ResponseEntity<String> alert(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>("failed to consume end point", HttpStatus.OK);
    }
}
