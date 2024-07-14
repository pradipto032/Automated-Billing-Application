package com.craftMarine.emailSender.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(force = true)
public class ProductModel {

    @NonNull
    private String productName;

    @NonNull
    public int quantity;

    @NonNull
    public int productPrice;

    @NonNull
    public String orderStatus;

    public int totalPrice;

    public String rgb;

    //functions
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        updateRgb();
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        updateTotalPrice();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        totalPrice = quantity * productPrice;
    }

    public void updateRgb() {
        String p = "";
        switch (this.getOrderStatus()) {
            case "Yet to start":
                p="Red";
                break;
            case "Yet to dispatch":
                p="Yellow";
                break;
            case "Yet to deliver":
                p="Cyan";
                break;
            case "Delivered":
                p="Lime";
                break;
            default:
                p="White";
        }
        rgb = p;
    }
}
