package com.craftMarine.emailSender.RequestBody;

import lombok.Data;

@Data
public class DeleteBillRequestBody {

    private int invoiceId;

    private String custMail;

}
