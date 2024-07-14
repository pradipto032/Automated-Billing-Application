package com.craftMarine.emailSender.Model;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddressModel {

    @NonNull
    private String address;

    @NonNull
    private String state;

    @NonNull
    private String pinCode;

}
