package com.dmm.task.entity;



//import javax.persistence.Column;



import javax.persistence.Entity;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@Entity
@ToString
public class Tasks {
	@Id
	 private Long id;
	 private String user;
	 private String name;
	 private boolean done;
	 private LocalDate date;
}