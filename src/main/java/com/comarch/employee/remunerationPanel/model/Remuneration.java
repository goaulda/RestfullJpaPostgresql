package com.comarch.employee.remunerationPanel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Remuneration {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true)
    @JsonProperty(value = "id")
    private UUID idRemuneration;

    @JsonIgnore
    private UUID idEmployee;

    private Double salary;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(value = "salary_date")
    private LocalDate salaryDate;

}
