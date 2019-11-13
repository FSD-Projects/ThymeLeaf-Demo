package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employeeList = employeeService.getEmployeeList();
		theModel.addAttribute("employeeList", employeeList);
		return "employees/employee-list";
	}

	@GetMapping("/showFormForAdd")
	public String showEmployeeForm(Model theModel) {
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@PostMapping("/saveForm")
	public String saveEmployeeForm(@Valid@ModelAttribute("employee") Employee theEmployee, BindingResult result) {
		if(result.hasErrors()) {
			return "employees/employee-form";
		}
		else {
			employeeService.save(theEmployee);
		}
		return "redirect:/api/employees/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showEmployeeForm(@RequestParam("employeeId") int id, Model theModel) {
		Employee theEmployee = employeeService.findById(id);
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@PostMapping("/deleteEmployee")
	public String deleteEmployeeRecord(@RequestParam("employeeId") int id) {
		employeeService.delete(id);
		return "redirect:/api/employees/list";
	}
	@PostMapping("/search")
	public String search(@RequestParam("firstName") String theFirstName,
						 @RequestParam("lastName") String theLastName,
						 Model theModel) {
		
		// check names, if both are empty then just give list of all employees

		if (theFirstName.trim().isEmpty() && theLastName.trim().isEmpty()) {
			return "redirect:/employees/list";
		}
		else {
			// else, search by first name and last name
			List<Employee> theEmployees =
							employeeService.searchBy(theFirstName, theLastName);
			
			// add to the spring model
			theModel.addAttribute("employeeList", theEmployees);
			
			// send to list-employees
			return "employees/employee-list";
		}
		
	}
}
