package com.craftMarine.employeeManagement.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.craftMarine.employeeManagement.Model.EmployeeModel;


@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, String>{

	@Query("{'gmailId' : ?0}")
 	EmployeeModel findbyGmailId(String gmailId);
	
	@Query(value="{'gmailId' : ?0}", delete=true)
	EmployeeModel deleteDetails(String gmailId);
	
}
