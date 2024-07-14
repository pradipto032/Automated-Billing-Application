package com.craftMarine.emailSender.RequestBody;

import lombok.Data;

@Data
public class CustomerRequestBody {

    private String custName;

    private String custPhone;

    private String custMail;

    private AddressRequestBody custShippingAddress;

    private AddressRequestBody custBillingAddress;

}
