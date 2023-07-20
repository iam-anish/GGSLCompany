package com.ggsl.services;

import com.ggsl.entities.Employee;
import com.ggsl.entities.PageableResponse;

import java.util.List;

public interface EmployeeServices {

    Employee addEmployee(Employee employee);

    PageableResponse<Employee> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    String deleteEmployee(String EmployeeId);

    String updateEmployee(String EmployeeId,Employee employee);

    Employee nthLevelEmployee(String EmployeeId,int nthLevel);
}
