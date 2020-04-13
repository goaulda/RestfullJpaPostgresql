package com.comarch.employee.remunerationPanel.service;

import com.comarch.employee.employeePanel.repository.EmployeeRepo;
import com.comarch.employee.employeePanel.methods.MethodsEmployee;
import com.comarch.employee.remunerationPanel.methods.ConvertIntToObjectMoney;
import com.comarch.employee.remunerationPanel.methods.MethodsRemuneration;
import com.comarch.employee.remunerationPanel.model.Average;
import com.comarch.employee.remunerationPanel.model.ObjectMoney;
import com.comarch.employee.remunerationPanel.model.Remuneration;
import com.comarch.employee.remunerationPanel.repo.RemunerationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class RemunerationServiceImpl implements RemunerationService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RemunerationRepo remunerationRepo;

    @Autowired
    MethodsEmployee methodsEmployee;

    @Autowired
    MethodsRemuneration methodsRemuneration;

    @Autowired
    ConvertIntToObjectMoney convertIntToObjectMoney;

    @Override
    public Remuneration editSalaryByEmployeeNumber(Long employeeNumber, UUID idRemuneration, @Valid Remuneration remuneration) {
        Remuneration remunerationToUpdate = methodsRemuneration.findRemunerationByEmployeeNumber(employeeNumber, idRemuneration);
        remunerationToUpdate.setSalary(remuneration.getSalary());
        return remunerationRepo.save(remunerationToUpdate);
    }

    @Override
    public Page<Remuneration> getAllSalary(Pageable pageable) {
        Page<Remuneration> allSalaries = remunerationRepo.findAll(pageable);
        return allSalaries;
    }

    @Override
    public Remuneration addSalaryToEmployeeByEmployeeNumber(Long employeeNumber, @Valid Remuneration remuneration) {
        UUID idEmployee = methodsEmployee.findIdByIdEmployeeNumber(employeeNumber);
        remuneration.setIdEmployee(idEmployee);
        return remunerationRepo.save(remuneration);
    }

    @Override
    public Page<Remuneration> getSalariesByEmployeeNumber(Long employeeNumber, Pageable pageable) { ;
        UUID idEmployeeNumber = methodsEmployee.findIdByIdEmployeeNumber(employeeNumber);
        Page<Remuneration> remunerationPage = remunerationRepo.findAllByIdEmployee(idEmployeeNumber, pageable);
        return remunerationPage;
    }

    @Override
    public Average getLast12MountsSalaryByEmployeeNumber(Long employeeNumber) {

       // Pageable top12 = new PageRequest(0, 12);

        Average average = new Average();
        UUID idEmployee = methodsEmployee.findIdByIdEmployeeNumber(employeeNumber);
        //List<Remuneration> averageList = remunerationRepo.findAllByIdEmployeeOrderBySalaryDateDesc(idEmployee, top12);
        List<Remuneration> averageList = remunerationRepo.findFirst10ByIdEmployeeOrderBySalaryDateDesc(idEmployee);

        int size = averageList.size();
        Double sumSalaryDouble = 0.00;
        for (int i = 0; i < size; i++) {
            sumSalaryDouble =+ averageList.get(i).getSalary();
        }
        final Double averageDouble = sumSalaryDouble/size;
        DecimalFormat df2 = new DecimalFormat("#.##");
        average.setAverage(df2.format(averageDouble) + " zł");
        return average;
    }

    @Override
    public Average getAverageSalariesByEmployeeNumber(Long employeeNumber, String firstPeriod, String lastPeriod, Pageable pageable) {
        Average average = new Average();
        UUID idEmployee = methodsEmployee.findIdByIdEmployeeNumber(employeeNumber);
        String sum = convertAverage(idEmployee, firstPeriod, lastPeriod );
        average.setAverage(sum + " zł");
        return average;
    }

    private String convertAverage(UUID idEmployee, String firstPeriod, String lastPeriod) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fPeriod = LocalDate.parse(firstPeriod, formatter);
        LocalDate lPeriod = LocalDate.parse(lastPeriod, formatter);

        List<Remuneration> averageList = remunerationRepo.findAll(new Specification<Remuneration>() {
            @Nullable
            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<Remuneration> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
                predicates.add(
                        criteriaBuilder.equal(root.get("idEmployee"), idEmployee));
                predicates.add(
                        criteriaBuilder.between(root.get("salaryDate"), fPeriod, lPeriod));

                return criteriaBuilder.and(predicates.toArray(
                        new javax.persistence.criteria.Predicate[predicates.size()]));
            }
        });

        int size = averageList.size();
        Double sumSalaryDouble = 0.00;
        for (int i = 0; i < size; i++) {
            sumSalaryDouble =+ averageList.get(i).getSalary();
        }
        final Double average = sumSalaryDouble/size;
        DecimalFormat df2 = new DecimalFormat("#.##");


        return df2.format(average);
    }


}
