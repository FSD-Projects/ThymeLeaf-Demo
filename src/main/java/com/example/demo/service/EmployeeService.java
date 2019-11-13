package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public List<Employee> getEmployeeList();

	public void save(Employee theEmployee);

	public Employee findById(int id);

	public void delete(int id);

	public List<Employee> searchBy(String theFirstName, String theLastName);
}
