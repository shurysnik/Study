package org.example.repository;

import org.example.model.HospitalCar;
import org.example.model.SportCar;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class SportCarReprository implements CrudRepository<SportCar> {
    List<SportCar> sportCars;

    public SportCarReprository() {
        sportCars = new LinkedList<>();
    }

    @Override
    public SportCar getById(String id) {
        for (SportCar sportCar : sportCars) {
            if (sportCar.getId().equals(id)) {
                return sportCar;
            }
        }
        return null;
    }

    @Override
    public List<SportCar> getAll() {
        return sportCars;
    }

    @Override
    public boolean save(SportCar sportCar) {
        if (sportCar==null)
        {
            throw new IllegalArgumentException("Auto must not be null");
        }
        if (sportCar.getPrice().equals(BigDecimal.ZERO))
        {
            sportCar.setPrice(BigDecimal.valueOf(-1));
        }
        return sportCars.add(sportCar);
    }

    @Override
    public boolean saveAll(List<SportCar> sportCarsList) {
        if (sportCarsList==null)
        {
            return  false;
        }
        return sportCars.addAll(sportCarsList);
    }

    @Override
    public boolean update(SportCar sportCar) {
        final SportCar foundedSportsCar = getById(sportCar.getId());
        if (foundedSportsCar != null) {
            SportCarsCopy.copy(sportCar, foundedSportsCar);
            return true;
        }
        return false;
    }
    public boolean updateNumberOfRacesWon(double numberOfRacesWon, SportCar copyFrom) {
        for (SportCar sportCar : sportCars) {
            if (sportCar.getNumberOfRacesWon() == numberOfRacesWon) {
               SportCarReprository.SportCarsCopy.copy( copyFrom,sportCar);
            }
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        return sportCars.removeIf(sportCar -> sportCar.getId().equals(id));
    }

    private static class SportCarsCopy {
        static void copy(  SportCar from,SportCar to) {
            to.setModel(from.getModel());
            to.setPrice(from.getPrice());
           // to.setNumberOfRacesWon(from.getNumberOfRacesWon());
            to.setMaxSpeed(from.getMaxSpeed());
        }
    }
}
