package com.craftMarine.emailSender.Repository;

import com.craftMarine.emailSender.Model.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository extends MongoRepository<CustomerModel, String> {

    @Query("{'custMail' : ?0}")
    CustomerModel getCustomer(String gmail);

    @Query(value = "{custMail : ?0}", delete = true)
    CustomerModel deleteCustomer(String gmail);
}
