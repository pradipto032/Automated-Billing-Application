package com.craftMarine.emailSender.Service;

import com.craftMarine.emailSender.Model.AddressModel;
import com.craftMarine.emailSender.Model.CustomerModel;
import com.craftMarine.emailSender.Repository.CustomerDetailsRepository;
import com.craftMarine.emailSender.RequestBody.CustomerRequestBody;
import com.craftMarine.emailSender.RequestBody.DeleteRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDataService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    public boolean checkCustomerAvail(String gmail) {
        CustomerModel a = customerDetailsRepository.getCustomer(gmail);
        return a == null;
    }

    public String customerregister(CustomerRequestBody customerRequestBody){
        if (checkCustomerAvail(customerRequestBody.getCustMail())) {
            CustomerModel custmodel = new CustomerModel();
            custmodel.setCustName(customerRequestBody.getCustName());
            custmodel.setCustMail(customerRequestBody.getCustMail());
            custmodel.setCustPhone(customerRequestBody.getCustPhone());
            AddressModel ship = new AddressModel(customerRequestBody.getCustShippingAddress().getAddress(), customerRequestBody.getCustShippingAddress().getState(), customerRequestBody.getCustShippingAddress().getPinCode());
            AddressModel bill = new AddressModel(customerRequestBody.getCustBillingAddress().getAddress(), customerRequestBody.getCustBillingAddress().getState(), customerRequestBody.getCustBillingAddress().getPinCode());
            custmodel.setCustShippingAddress(ship);
            custmodel.setCustBillingAddress(bill);
                customerDetailsRepository.save(custmodel);

            return "Customer details saved";
        } else {
            return "Customer already exists";
        }
    }

    public List<CustomerModel> showcustomers()
    {
        return customerDetailsRepository.findAll();
    }

    public String custDelete(DeleteRequestBody custDetails) {
        if (!checkCustomerAvail(custDetails.getCustMail())) {
            customerDetailsRepository.deleteCustomer(custDetails.getCustMail());
            return "Customer details deleted";
        } else {
            return "No such customer to delete. Please give correct gmailId";
        }
    }

    public String custUpdate(CustomerRequestBody customerRequestBody){//, AddressRequestBody shippingAddressRequestBody, AddressRequestBody billingAddressRequestBody) {
        if (!checkCustomerAvail(customerRequestBody.getCustMail())) {
            customerDetailsRepository.deleteCustomer(customerRequestBody.getCustMail());
            CustomerModel custmodel = new CustomerModel();
            custmodel.setCustName(customerRequestBody.getCustName());
            custmodel.setCustMail(customerRequestBody.getCustMail());
            custmodel.setCustPhone(customerRequestBody.getCustPhone());
            AddressModel ship = new AddressModel(customerRequestBody.getCustShippingAddress().getAddress(), customerRequestBody.getCustShippingAddress().getState(), customerRequestBody.getCustShippingAddress().getPinCode());
            AddressModel bill = new AddressModel(customerRequestBody.getCustBillingAddress().getAddress(), customerRequestBody.getCustBillingAddress().getState(), customerRequestBody.getCustBillingAddress().getPinCode());
            custmodel.setCustShippingAddress(ship);//shippingAddressModel);
            custmodel.setCustBillingAddress(bill);
            customerDetailsRepository.save(custmodel);
            return "Customer details updated";
        } else {
            return "No such customer present. Please give correct gmailId";
        }
    }
}
