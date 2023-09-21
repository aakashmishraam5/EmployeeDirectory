package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService=theEmployeeService;
	}
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		//get emplyee from db
		List<Employee> theEmployees=employeeService.findAll();



		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		//create model attribute to bind the data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee",theEmployee);

		return "employees/employee-form";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		//get employee from service
		Employee theEmployee = employeeService.findById(theId);

		//set employee in the mdoel to prepoluate the form
		theModel.addAttribute("employee",theEmployee);

		//send over to our form
		return "employees/employee-form";


	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//save the employee
		employeeService.save(theEmployee);

		//use direct to avoid duplicate
		return "redirect:/employees/list";

	}
	@GetMapping("/delete")
		public String delete(@RequestParam("employeeId") int theId){

		//delete the employee
		employeeService.deleteById(theId);

		//redirect to home
		return "redirect:/employees/list";

		}





}









