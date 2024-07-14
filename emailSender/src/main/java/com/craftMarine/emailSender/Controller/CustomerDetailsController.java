package com.craftMarine.emailSender.Controller;

import com.craftMarine.emailSender.Model.CustomerModel;
import com.craftMarine.emailSender.RequestBody.CustomerRequestBody;
import com.craftMarine.emailSender.RequestBody.DeleteRequestBody;
import com.craftMarine.emailSender.Service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerDetailsController {

	@Autowired
	private CustomerDataService customerDataService;

	@PostMapping("/customer/register")
	public ResponseEntity<String> customerRegistration(@RequestBody CustomerRequestBody customerRequestBody){
		return new ResponseEntity<>(customerDataService.customerregister(customerRequestBody), HttpStatus.CREATED);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerModel>> showcustomer() {
		return new ResponseEntity<>(customerDataService.showcustomers(), HttpStatus.OK);
	}

	@DeleteMapping("/customer/delete")
	public ResponseEntity<String> deleteCustomerDetails(@RequestBody DeleteRequestBody custDetails){
		return new ResponseEntity<>(customerDataService.custDelete(custDetails), HttpStatus.OK);
	}

	@PutMapping("/customer/update")
	public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerRequestBody customerRequestBody){//, @RequestBody AddressRequestBody shippingAddressRequestBody,@RequestBody AddressRequestBody billingAddressRequestBody){
		return new ResponseEntity<>(customerDataService.custUpdate(customerRequestBody), HttpStatus.OK);
	}

}
