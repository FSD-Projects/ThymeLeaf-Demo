package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		employeeRepository.save(theEmployee);
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		Employee employee;
		Optional<Employee> result = employeeRepository.findById(id);
		if(result.isPresent()) {
			employee = result.get();
		}
		else {
			throw new EmployeeNotFoundException("Employee not found with such id!");
		}
		return employee;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Employee employee;
		Optional<Employee> result = employeeRepository.findById(id);
		if(result.isPresent()) {
			employee = result.get();
		}
		else {
			throw new EmployeeNotFoundException("Employee not found with such id!");
		}
		employeeRepository.delete(employee);
	}

	@Override
	public List<Employee> searchBy(String theFirstName, String theLastName) {
		// TODO Auto-generated method stub
		return employeeRepository.findByFirstNameContainsAndLastNameContainsAllIgnoreCase(theFirstName, theLastName);
	}

}
