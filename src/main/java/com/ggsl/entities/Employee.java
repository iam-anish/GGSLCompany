package com.ggsl.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeDetails")
public class Employee{

    @Id
    private String employeeId;

    private String employeeName;

    private String phoneNumber;

    private String email;

    private String reportsTo;

    private String profileImage;
}
