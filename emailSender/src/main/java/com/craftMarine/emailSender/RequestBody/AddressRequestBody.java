package com.craftMarine.emailSender.RequestBody;

import lombok.Data;

@Data
public class AddressRequestBody {

    private String address;

    private String state;

    private String pinCode;
}
