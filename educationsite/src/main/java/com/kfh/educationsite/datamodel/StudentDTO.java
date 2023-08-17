package com.kfh.educationsite.datamodel;

import com.kfh.educationsite.entity.Course;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    @NotEmpty(message = "FullNameEnglish Name should not be null or empty")
    private String fullNameEnglish;
    private String fullNameArabic;
    @NotEmpty(message = "EmailAddress should not be null or empty")
    private String emailAddress;
    private String telephoneNumber;
    private String address;

    private List<Course> courses;
}
