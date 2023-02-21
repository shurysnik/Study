package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SportCar  extends  Vehicle{
    private  double maxSpeed;
    private  double numberOfRacesWon;

    public SportCar(String model, Manufacturer manufacturer, BigDecimal price,double maxSpeed,double numberOfRacesWon) {
        super(model, manufacturer, price);
        this.maxSpeed=maxSpeed;
        this.numberOfRacesWon=numberOfRacesWon;
    }


    @Override
    public String toString() {
        return "SportCar{" +
                "maxSpeed=" + maxSpeed +
                ", numberOfRacesWon=" + numberOfRacesWon +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
