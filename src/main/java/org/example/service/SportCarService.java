package org.example.service;

import org.example.model.Manufacturer;
import org.example.model.SportCar;
import org.example.repository.SportCarReprository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SportCarService {
    private final SportCarReprository sportCarService;
    private static final Random RANDOM = new Random();

    public SportCarService(SportCarReprository sportCarReprository) {
        this.sportCarService = sportCarReprository;
    }

    public List<SportCar> createAndSaveSportCars(int count) {
        List<SportCar> results = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final SportCar sportCar = new SportCar(
                    "Model" + RANDOM.nextDouble(1000.0),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    RANDOM.nextDouble(1000.0),
                    RANDOM.nextDouble(5.0));

            results.add(sportCar);
            sportCarService.save(sportCar);
        }
        return results;

    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] manufacturers = Manufacturer.values();
        final int index = RANDOM.nextInt(manufacturers.length);
        return manufacturers[index];
    }

    public void saveSportCar(List<SportCar> sportCars) {
        sportCarService.saveAll(sportCars);
    }

    public void printAll() {
        for (SportCar sportCar : sportCarService.getAll()) {
            System.out.println(sportCar);
        }
    }
}

