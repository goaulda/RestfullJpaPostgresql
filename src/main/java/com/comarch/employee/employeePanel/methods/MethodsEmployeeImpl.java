package com.comarch.employee.employeePanel.methods;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.employeePanel.repository.EmployeeRepo;
import com.comarch.employee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MethodsEmployeeImpl implements MethodsEmployee {

    @Autowired
    private EmployeeRepo employeeRepo;

    public UUID findIdByIdEmployeeNumber(Long employeeNumber){
        Employee employee = employeeRepo.findByEmployeeNumber(employeeNumber).orElseThrow(()-> new EmployeeNotFoundException("Employee doesn't exist"));
        UUID idEmployee = employee.getIdEmployee();
        return idEmployee;
    }



}
