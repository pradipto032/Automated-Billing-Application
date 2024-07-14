package com.craftMarine.employeeManagement.EmployeeRequest;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmployeeRequestbody {
	@NonNull
	private String gmailId;
	
	@NonNull
	private String uname;

	@NonNull
	private String title;

	@NonNull
	private String upiId;
	
	private int salary;
	
	@NonNull
	private String phoneNo;

	@NonNull
	private String paidOrNot;

	@NonNull
	private String date;
}
