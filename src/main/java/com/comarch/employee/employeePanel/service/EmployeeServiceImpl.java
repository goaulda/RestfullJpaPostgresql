package com.comarch.employee.employeePanel.service;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.employeePanel.repository.EmployeeRepo;
import com.comarch.employee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    //Adding constructor
    public EmployeeServiceImpl() {
    }

    //Method adding new employee to database
    @Override
    public Long addNewEmployee(@Valid Employee employee) {
        Long employeeNumber = employeeRepo.save(employee).getEmployeeNumber();
        return employeeNumber;
    }

    //Edit employee method by employeeNumber
    @Override
    public Employee editEmployeeByEmployeeNumber(Long employeeNumber, @Valid Employee employee) {

        //Select employee by employeeNumber if employee dosen't exist throw Exception
        Employee employeeToUpdate = employeeRepo.findByEmployeeNumber(employeeNumber).orElseThrow(() -> new EmployeeNotFoundException("Employee dosen't exist"));

        //Passing employee field to employeeUpdate
        employeeToUpdate.setBirthdate(employee.getBirthdate());
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());
        employeeToUpdate.setPesel(employee.getPesel());
        employeeToUpdate.setEmployeeNumber(employee.getEmployeeNumber());
        return employeeRepo.save(employeeToUpdate);
    }


    //Getting all employee method with pageable
    @Override
    public Page<Employee> getAllEmployee(Pageable pageable) {
        Page<Employee> allEmployee = employeeRepo.findAll(pageable);
        return allEmployee;
    }
}
