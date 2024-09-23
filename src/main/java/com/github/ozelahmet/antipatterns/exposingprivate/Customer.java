package com.github.ozelahmet.antipatterns.exposingprivate;

public class Customer {
    private CustomerStatus status = CustomerStatus.Regular;

    public void promote() {
        this.status = CustomerStatus.Preferred;
    }

    public double getDiscount() {
        return status == CustomerStatus.Preferred ? 0.05 : 0;
    }
}

enum CustomerStatus {
    Regular,
    Preferred
}