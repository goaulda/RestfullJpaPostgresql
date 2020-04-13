package com.comarch.employee.remunerationPanel.methods;

import com.comarch.employee.employeePanel.methods.MethodsEmployee;
import com.comarch.employee.exception.NoEqualException;
import com.comarch.employee.exception.RemunerationNotFoundException;
import com.comarch.employee.remunerationPanel.model.Remuneration;
import com.comarch.employee.remunerationPanel.repo.RemunerationRepo;
import com.sun.jdi.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MethodsRemunerationImpl implements MethodsRemuneration {

    @Autowired
    RemunerationRepo remunerationRepo;

    @Autowired
    MethodsEmployee methodsEmployee;


    @Override
    public Remuneration findRemunerationByEmployeeNumber(Long employeeNumber, UUID idRemuneration) {

        UUID presentIdEmployee = methodsEmployee.findIdByIdEmployeeNumber(employeeNumber);
        Remuneration remuneration = remunerationRepo.findById(idRemuneration).orElseThrow(()-> new RemunerationNotFoundException("Salary not found"));
        UUID inDatabaseIDEmployee = remuneration.getIdEmployee();
        checkIf2FieldsEqual(presentIdEmployee, inDatabaseIDEmployee);
        return remuneration;
    }

    private void checkIf2FieldsEqual(Object presentIdEmployee, Object inDatabaseIDEmployee) {

        if (!presentIdEmployee.equals(inDatabaseIDEmployee)){
            throw new NoEqualException();
        }

    }
}
