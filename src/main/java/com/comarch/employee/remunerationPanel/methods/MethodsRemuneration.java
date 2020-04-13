package com.comarch.employee.remunerationPanel.methods;

import com.comarch.employee.remunerationPanel.model.Remuneration;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MethodsRemuneration {
    Remuneration findRemunerationByEmployeeNumber(Long employeeNumber, UUID idRemuneration);
}
