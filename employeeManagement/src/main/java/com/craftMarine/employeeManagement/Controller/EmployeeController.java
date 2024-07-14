package com.craftMarine.employeeManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.craftMarine.employeeManagement.EmployeeRequest.DeleteRequestbody;
import com.craftMarine.employeeManagement.EmployeeRequest.EmployeeRequestbody;
import com.craftMarine.employeeManagement.Model.EmployeeModel;
import com.craftMarine.employeeManagement.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/save")
	public String saveEmployeeDetails(@RequestBody EmployeeRequestbody empDetails){
		String resp = employeeService.employeeRegister(empDetails);
		return resp;
	}
	
	@DeleteMapping("/employee/delete")
	public String deleteEmployeeDetails(@RequestBody DeleteRequestbody empDetails){
		String resp = employeeService.employeeDelete(empDetails);
		return resp;
	}
	
	@PutMapping("/employee/update")
	public String updateEmployeeDetails(@RequestBody EmployeeRequestbody empDetails){
		String resp = employeeService.employeeUpdate(empDetails);
		return resp;
	}

	@GetMapping("/employee")
	public List<EmployeeModel> showemployee() {
		return employeeService.showemployees();
	}
}
