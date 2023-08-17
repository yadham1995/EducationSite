package com.kfh.educationsite.service;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class GenericRequestDTO {
    @NotEmpty
    @Pattern(regexp = "^(KAFKA|RABBIT|REST|SOAP)$", message = "invalid IntegrationFramework accepted list is {KAFKA,RABBIT,REST,SOAP}")
    private String integrationFramework;
}
