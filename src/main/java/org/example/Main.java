package org.example;

import org.example.model.Auto;
import org.example.model.HospitalCar;
import org.example.model.SportCar;
import org.example.repository.HospitalCarReprository;
import org.example.repository.SportCarReprository;
import org.example.service.AutoService;
import org.example.service.HospitalCarService;
import org.example.service.SportCarService;
import java.util.List;

import org.example.repository.AutoRepository;

public class Main {
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final HospitalCarService HOSPITAL_CAR_SERVICE = new HospitalCarService(new HospitalCarReprository());

    private static final SportCarService SPORT_CAR_SERVICE= new SportCarService(new SportCarReprository());

    public static void main(String[] args) {
        final List<Auto> autos = AUTO_SERVICE.createAndSaveAutos(10);
        AUTO_SERVICE.saveAutos(autos);
        AUTO_SERVICE.printAll();

        final List<HospitalCar>hospitalCars=HOSPITAL_CAR_SERVICE.createAndSaveHospitalCar(10);
        HOSPITAL_CAR_SERVICE.saveAutos(hospitalCars);
        HOSPITAL_CAR_SERVICE.printAll();

        final  List<SportCar>sportCars=SPORT_CAR_SERVICE.createAndSaveSportCars(10);
        SPORT_CAR_SERVICE.saveSportCar(sportCars);
        SPORT_CAR_SERVICE.printAll();
    }
}