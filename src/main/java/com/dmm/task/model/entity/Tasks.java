package com.dmm.task.model.entity;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence_generator")
    @SequenceGenerator(name = "tasks_sequence_generator", sequenceName = "tasks_sequence", allocationSize = 1)
    private Long id;
    
//    @ManyToOne
//    @JoinColumn(name = "user_name")　リレーションの定義
//    private Users userName;
    private String title;
    private String name;
    private String text;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean done;
}