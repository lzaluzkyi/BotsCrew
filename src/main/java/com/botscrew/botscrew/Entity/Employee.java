package com.botscrew.botscrew.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@ToString(of = "id")
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leader")
    private List<Department> leader;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "worker")
    private List<Department> workIn;

    private Degree degree;

    private BigDecimal salary;
}
