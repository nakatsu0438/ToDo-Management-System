package com.dmm.task.entity;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Task {
	@Id
	 private Long id;
	 private String title;
	 private boolean done;
	 private LocalDate date;

}