package com.craftMarine.employeeManagement.Model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document("Employee")
@AllArgsConstructor
@Data
public class EmployeeModel {
	
	@Id
	private String id;
	
	private String gmailId;

	@NonNull
	private String title;

	@NonNull
	private String uname;
	
	@NonNull
	private String upiId;

	@NonNull
	private int salary;
	
	@NonNull
	private String phoneNo;
	
	@NonNull
	private String paidOrNot;

	@NonNull
	private String date;
}
