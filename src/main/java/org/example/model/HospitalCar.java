package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HospitalCar extends Vehicle {
    private int numberOfPatients;

    public HospitalCar(String model, Manufacturer manufacturer, BigDecimal price, int numberOfPatients) {
        super(model, manufacturer, price);
        this.numberOfPatients = numberOfPatients;
    }

    @Override
    public String toString() {
        return "HospitalCar{" +
                "numberOfPatients=" + numberOfPatients +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
