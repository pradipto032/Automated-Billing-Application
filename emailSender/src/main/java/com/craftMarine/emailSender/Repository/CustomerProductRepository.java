package com.craftMarine.emailSender.Repository;

import com.craftMarine.emailSender.Model.CustProdModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProductRepository extends MongoRepository<CustProdModel, String> {

    @Query(value = "{invoiceId : ?0 , custMail : ?1}", delete = true)
    void deleteCustomerBill(int invoiceId, String custMail);

    @Query(value = "{invoiceId : ?0 , custMail : ?1}")
    CustProdModel getCustomerBill(int invoiceId, String custMail);


}
