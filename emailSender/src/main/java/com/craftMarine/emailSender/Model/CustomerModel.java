package com.craftMarine.emailSender.Model;

import com.craftMarine.emailSender.RequestBody.AddressRequestBody;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(force = true)
@Document("Customer")
public class CustomerModel {

    @NonNull
    private String custName;

    @NonNull
    private String custPhone;

    @NonNull
    private String custMail;

    @NonNull
    private AddressModel custShippingAddress;

    @NonNull
    private AddressModel custBillingAddress;

}
