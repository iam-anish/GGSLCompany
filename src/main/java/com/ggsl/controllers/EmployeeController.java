package com.ggsl.controllers;

import com.ggsl.entities.Employee;
import com.ggsl.entities.PageableResponse;
import com.ggsl.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController{

    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
       Employee savedEmployee  = employeeServices.addEmployee(employee);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<Employee>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "employeeName",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<Employee> employeeList = employeeServices.getAll(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId){
        String data = employeeServices.deleteEmployee(employeeId);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId,@RequestBody Employee employee){
        String data = employeeServices.updateEmployee(employeeId,employee);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/level/{employeeId}/{nthLevel}")
    public ResponseEntity<Employee> nthLevelEmployee(@PathVariable String employeeId,@PathVariable int nthLevel){
        Employee employee = employeeServices.nthLevelEmployee(employeeId,nthLevel);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
