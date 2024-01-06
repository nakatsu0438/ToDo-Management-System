package com.dmm.task.entity;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Tasks {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @Column
    private String name;
    
    @Column
    private boolean done;
    
    @Column
    private LocalDate date;
}
