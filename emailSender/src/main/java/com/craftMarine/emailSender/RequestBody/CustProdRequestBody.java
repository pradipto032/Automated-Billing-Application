package com.craftMarine.emailSender.RequestBody;

import com.craftMarine.emailSender.Model.CustomerModel;
import com.craftMarine.emailSender.Model.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class CustProdRequestBody {

    @NonNull
    public CustomerModel custDetails;

    @NonNull
    public List<ProductModel> productDetails;

    public int advancePaid;

}
