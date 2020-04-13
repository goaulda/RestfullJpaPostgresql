package com.comarch.employee.employeePanel.service;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.employeePanel.repository.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface EmployeeService {


    Long addNewEmployee(@Valid Employee employee);

    Employee editEmployeeByEmployeeNumber(Long employeeNumber, @Valid Employee employee);

    Page<Employee> getAllEmployee(Pageable pageable);
}
