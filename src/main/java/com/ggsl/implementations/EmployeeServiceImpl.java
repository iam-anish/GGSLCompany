package com.ggsl.implementations;

import com.ggsl.Helper.helper;
import com.ggsl.entities.Employee;
import com.ggsl.entities.PageableResponse;
import com.ggsl.repositories.EmployeeRepositories;
import com.ggsl.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeServices{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmployeeRepositories employeeRepositories;

    @Override
    public Employee addEmployee(Employee employee){
        String employeeId = UUID.randomUUID().toString();
        employee.setEmployeeId(employeeId);
        Employee savedEmployee = employeeRepositories.save(employee);
        Employee ReportEmployee = employeeRepositories.findById(savedEmployee.getReportsTo()).orElseThrow(()->new RuntimeException("Employee not found"));

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(ReportEmployee.getEmail());
        mailMessage.setSubject("New Employee Added");
        mailMessage.setText(employee.getEmployeeName() + " will now work under you. Mobile number is "+employee.getPhoneNumber()+" and Email is "+employee.getEmail()+".");
        mailMessage.setFrom("21ce071@charusat.edu.in");
        javaMailSender.send(mailMessage);

        return savedEmployee;
    }

    @Override
    public PageableResponse<Employee> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("decs")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize,sort);
        Page<Employee> page = employeeRepositories.findAll(pageable);

        PageableResponse<Employee> pageableResponse = helper.getPageableResponse(page,Employee.class);
        return pageableResponse;
    }

    @Override
    public String deleteEmployee(String EmployeeId){
        Employee employee = employeeRepositories.findById(EmployeeId).orElseThrow(()->new RuntimeException("Employee not found"));
        employeeRepositories.delete(employee);
        return "Deleted";
    }

    @Override
    public String updateEmployee(String EmployeeId,Employee employee){
        Employee employee1 = employeeRepositories.findById(EmployeeId).orElseThrow(()->new RuntimeException("Employee not found"));
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setEmail(employee.getEmail());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setProfileImage(employee.getProfileImage());
        employee1.setReportsTo(employee.getReportsTo());
        employeeRepositories.save(employee1);
        return "Updated";
    }

    @Override
    public Employee nthLevelEmployee(String EmployeeId, int nthLevel){
        Employee employee = new Employee();
        for (int i=0;i<=nthLevel;i++){
            employee = employeeRepositories.findById(EmployeeId).orElseThrow(()->new RuntimeException("Employee not found"));
            EmployeeId = employee.getReportsTo();
        }
        return employee;
    }
}
