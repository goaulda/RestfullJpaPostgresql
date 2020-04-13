package com.comarch.employee.employeePanel.controller;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.employeePanel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    ResponseEntity<?> addNewEmployee (
            @Valid @RequestBody Employee employee
    ){
        Long employeeNumber = employeeService.addNewEmployee(employee);
        return new ResponseEntity<>(employeeNumber, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{employeeNumber}", method = RequestMethod.PUT)
    ResponseEntity<Employee> editEmployeeByEmployeeNumber (
            @Valid @RequestBody Employee employee,
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber
    ){
        Employee updateEmployee = employeeService.editEmployeeByEmployeeNumber(employeeNumber, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/all", method = RequestMethod.GET)
    ResponseEntity<Page<Employee>> getAllEmployee(
            Pageable pageable
    ){
        Page<Employee> allEmployee = employeeService.getAllEmployee(pageable);
        return new ResponseEntity<Page<Employee>>(allEmployee,HttpStatus.OK);
    }
}
