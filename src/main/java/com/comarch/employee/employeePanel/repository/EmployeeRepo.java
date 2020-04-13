package com.comarch.employee.employeePanel.repository;

import com.comarch.employee.employeePanel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmployeeNumber(Long employeeNumber);
}
