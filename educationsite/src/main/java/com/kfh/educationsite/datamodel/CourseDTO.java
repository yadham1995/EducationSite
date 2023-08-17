package com.kfh.educationsite.datamodel;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;

    @NotEmpty(message = "Course Name should not be null or empty")
    private String name;
}
