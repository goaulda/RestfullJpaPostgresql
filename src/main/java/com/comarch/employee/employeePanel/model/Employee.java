package com.comarch.employee.employeePanel.model;


import com.comarch.employee.remunerationPanel.model.Remuneration;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true)
    @JsonIgnore
    private UUID idEmployee;

    @JsonProperty(value = "employee_number")
    @Column(unique = true)
    private Long employeeNumber;

    @Column(nullable = false)
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(nullable = false)
    @JsonProperty(value = "last_name")
    private String lastName;

    @PESEL
    private String pesel;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEmployee", referencedColumnName = "idEmployee")
    Set<Remuneration> remunerations = new HashSet<>();


}
