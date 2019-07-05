package com.botscrew.botscrew.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(of = "id")
public class Department {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee leader;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Employee> worker;


}
