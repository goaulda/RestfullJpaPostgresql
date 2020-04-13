package com.comarch.employee.remunerationPanel.controller;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.remunerationPanel.model.Average;
import com.comarch.employee.remunerationPanel.model.Remuneration;
import com.comarch.employee.remunerationPanel.repo.RemunerationRepo;
import com.comarch.employee.remunerationPanel.service.RemunerationService;
import com.comarch.employee.remunerationPanel.service.RemunerationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class RemunerationController {

    @Autowired
    public RemunerationService remunerationService;

    @RequestMapping(value = "/salary/{employeeNumber}", method = RequestMethod.POST)
    ResponseEntity<Remuneration> addSalaryToEmployeeByEmployeeNumber (
            @Valid @RequestBody Remuneration remuneration,
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber
    ){
        Remuneration newSalary = remunerationService.addSalaryToEmployeeByEmployeeNumber(employeeNumber, remuneration);
        return new ResponseEntity<>(newSalary, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/salary/{employeeNumber}/edit/{idRemuneration}", method = RequestMethod.PUT)
    ResponseEntity<Remuneration> editSalaryByEmployeeNumber (
            @Valid @RequestBody Remuneration remuneration,
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber,
            @PathVariable(value = "idRemuneration", required = true) UUID idRemuneration

    ){
        Remuneration updateSalary = remunerationService.editSalaryByEmployeeNumber(employeeNumber,idRemuneration, remuneration);
        return new ResponseEntity<>(updateSalary, HttpStatus.OK);
    }

    @RequestMapping(value = "/salaries/all/employees", method = RequestMethod.GET)
    ResponseEntity<Page<Remuneration>> getSalariesForAllEmployees(
            Pageable pageable
    ){
        Page<Remuneration> salariesForAllEmployees = remunerationService.getAllSalary(pageable);
        return new ResponseEntity<Page<Remuneration>>(salariesForAllEmployees,HttpStatus.OK);
    }

    @RequestMapping(value = "/salary/{employeeNumber}", method = RequestMethod.GET)
    ResponseEntity<Page<Remuneration>> getSalariesByEmployeeNumber(
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber,
            Pageable pageable
    ){
        Page<Remuneration> salariesByEmployeeNumber = remunerationService.getSalariesByEmployeeNumber(employeeNumber, pageable);
        return new ResponseEntity<Page<Remuneration>>(salariesByEmployeeNumber,HttpStatus.OK);
    }

    @RequestMapping(value = "/salary/{employeeNumber}/average", method = RequestMethod.GET)
    ResponseEntity<Average> getAverageSalariesByEmployeeNumber(
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber,
            @RequestParam(name = "firstPeriod", required = false) String firstPeriod,
            @RequestParam(name = "lastPeriod", required = false) String lastPeriod,
            Pageable pageable
    ){
        Average salariesByEmployeeNumber = remunerationService.getAverageSalariesByEmployeeNumber(employeeNumber, firstPeriod, lastPeriod, pageable);
        return new ResponseEntity<Average>(salariesByEmployeeNumber,HttpStatus.OK);
    }

    @RequestMapping(value = "/salary/{employeeNumber}/average12mounts", method = RequestMethod.GET)
    ResponseEntity<Average> getLast12MountsSalaryByEmployeeNumber(
            @PathVariable(value = "employeeNumber", required = true) Long employeeNumber
    ){
        Average last12MountsSalaryByEmployeeNumber = remunerationService.getLast12MountsSalaryByEmployeeNumber(employeeNumber);
        return new ResponseEntity<Average>(last12MountsSalaryByEmployeeNumber,HttpStatus.OK);
    }

}
