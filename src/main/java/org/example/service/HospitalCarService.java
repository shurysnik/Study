package org.example.service;


import org.example.model.HospitalCar;
import org.example.model.Manufacturer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.example.repository.HospitalCarReprository;

public class HospitalCarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalCar.class);
    private static final Random RANDOM = new Random();
    private final HospitalCarReprository hospitalCarService;

    public HospitalCarService(HospitalCarReprository hospitalCarReprository) {
        this.hospitalCarService = hospitalCarReprository;
    }

    public List<HospitalCar> createAndSaveHospitalCar(int count) {
        List<HospitalCar> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final HospitalCar hospitalCar = new HospitalCar(
                    "Model -" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    RANDOM.nextInt(5)
            );

            result.add(hospitalCar);
            hospitalCarService.save(hospitalCar);
            LOGGER.debug("Created auto {}", hospitalCar.getId());

        }
        return result;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] manufacturers = Manufacturer.values();
        final int index = RANDOM.nextInt(manufacturers.length);
        return manufacturers[index];
    }

    public void saveAutos(List<HospitalCar> hospitalCars) {
        hospitalCarService.saveAll(hospitalCars);

    }

    public void printAll() {
        for (HospitalCar hospitalCar : hospitalCarService.getAll()) {
            System.out.println(hospitalCar);
        }
    }
}
