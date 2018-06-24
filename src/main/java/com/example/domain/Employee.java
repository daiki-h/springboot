package com.example.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
@SequenceGenerator(name="ID_GENERATOR",
					sequenceName="id",
					initialValue=1,
					allocationSize=1)
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "position", nullable = false)
	private String position;
	
	@Column(name = "hire_date", nullable = false)
	private LocalDateTime hiredate;

}
