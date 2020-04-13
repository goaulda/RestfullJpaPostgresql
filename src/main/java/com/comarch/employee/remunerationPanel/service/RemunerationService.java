package com.comarch.employee.remunerationPanel.service;

import com.comarch.employee.remunerationPanel.model.Average;
import com.comarch.employee.remunerationPanel.model.Remuneration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@Service
public interface RemunerationService {

    Remuneration editSalaryByEmployeeNumber(Long employeeNumber, UUID idRemuneration, @Valid Remuneration remuneration);

    Page<Remuneration> getAllSalary(Pageable pageable);

    Remuneration addSalaryToEmployeeByEmployeeNumber(Long employeeNumber, @Valid Remuneration remuneration);

    Page<Remuneration> getSalariesByEmployeeNumber(Long employeeNumber, Pageable pageable);

    Average getLast12MountsSalaryByEmployeeNumber(Long employeeNumber);

    Average getAverageSalariesByEmployeeNumber(Long employeeNumber, String firstPeriod, String lastPeriod, Pageable pageable);
}
