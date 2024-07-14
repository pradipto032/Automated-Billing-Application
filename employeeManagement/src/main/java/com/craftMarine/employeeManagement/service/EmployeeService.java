package com.craftMarine.employeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.craftMarine.employeeManagement.EmployeeRequest.DeleteRequestbody;
import com.craftMarine.employeeManagement.EmployeeRequest.EmployeeRequestbody;
import com.craftMarine.employeeManagement.Model.EmployeeModel;
import com.craftMarine.employeeManagement.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean checkEmail(String gmailId) {
		EmployeeModel emp = employeeRepository.findbyGmailId(gmailId);
		return emp != null;
	}

	// This function is for saving the EmployeeDetails
	public String employeeRegister(EmployeeRequestbody empDetails) {
		if (!this.checkEmail(empDetails.getGmailId())) {
			EmployeeModel empModel = new EmployeeModel(null, empDetails.getGmailId(), empDetails.getTitle(), empDetails.getUname(),
					empDetails.getUpiId(), empDetails.getSalary(), empDetails.getPhoneNo(), empDetails.getPaidOrNot(),
					empDetails.getDate());
			employeeRepository.save(empModel);
			return "Employee details registered";
		}
		return "Email already saved. Please log in";
	}

	// This function is for deleting the employeeDetails
	public String employeeDelete(DeleteRequestbody empDetails) {
			if (checkEmail(empDetails.getGmailId())) {
				employeeRepository.deleteDetails(empDetails.getGmailId());
				return "Employee details deleted";
			} else {
				return "No such user to delete. Please give correct gmailId";
			}
		}

	// This function is for updating the employeeDetails
	public String employeeUpdate(EmployeeRequestbody empDetails) {
	    EmployeeModel findbyGmailId = employeeRepository.findbyGmailId(empDetails.getGmailId());
			if (checkEmail(findbyGmailId.getGmailId())) {
				employeeRepository.delete(findbyGmailId);
				EmployeeModel empModel = new EmployeeModel(null, empDetails.getGmailId(), empDetails.getTitle(), empDetails.getUname(),
						empDetails.getUpiId(), empDetails.getSalary(), empDetails.getPhoneNo(),
						empDetails.getPaidOrNot(), empDetails.getDate());
				employeeRepository.save(empModel);
				return "Employee details updated";
			} else {
				return "No such user to update. Please give correct gmailId";
			}
	}

	public List<EmployeeModel> showemployees()
	{
		return employeeRepository.findAll();
	}
}
