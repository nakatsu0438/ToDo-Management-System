package com.dmm.task.model.entity;

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
    
//    @ManyToOne
//    @JoinColumn(name = "user_name")　リレーションの定義
//    private Users userName;
    private String title;
    private String name;
    private String text;
    private LocalDate date;
    private boolean done;
}
