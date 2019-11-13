package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int id;
	@Column(name = "first_name")
	@NotBlank(message = "First name required")
	private String firstName;
	@Column(name = "last_name")
	@NotBlank(message = "Last name required")
	private String lastName;
	@Column(name = "corporate_email")
	@NotBlank(message = "Email required")
	private String email;

}
