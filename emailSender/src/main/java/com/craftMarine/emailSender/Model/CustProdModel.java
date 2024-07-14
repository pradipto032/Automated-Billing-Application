package com.craftMarine.emailSender.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@Document("Customer_Billing_Details")
public class CustProdModel {

    public int invoiceId;

    @NonNull
    public String custName;

    @NonNull
    public String custMail;

    public List<ProductModel> productDetails;

    public String orderDate;

}
