package com.comarch.employee.remunerationPanel.repo;

import com.comarch.employee.employeePanel.model.Employee;
import com.comarch.employee.remunerationPanel.model.Remuneration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Repository
public interface RemunerationRepo extends JpaRepository<Remuneration, UUID>, JpaSpecificationExecutor<Remuneration> {


    Page<Remuneration> findAllByIdEmployee(UUID idEmployee, Pageable pageable);

    List<Remuneration> findAllByIdEmployeeOrderBySalaryDateDesc(UUID uuid, Pageable pageable);

    List<Remuneration> findFirst10ByIdEmployeeOrderBySalaryDateDesc(UUID uuid);


}
