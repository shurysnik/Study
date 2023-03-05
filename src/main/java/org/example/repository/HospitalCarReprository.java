package org.example.repository;

import org.example.model.HospitalCar;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class HospitalCarReprository implements CrudRepository<HospitalCar> {
    List<HospitalCar> hospitalCars;

    public HospitalCarReprository() {
        hospitalCars = new LinkedList<>();
    }

    @Override
    public HospitalCar getById(String id) {
        for (HospitalCar hospitalCar : hospitalCars) {
            if (hospitalCar.getId().equals(id)) {
                return hospitalCar;
            }

        }
        return null;
    }

    @Override
    public List<HospitalCar> getAll() {
        return hospitalCars;
    }

    @Override
    public boolean save(HospitalCar hospitalCar) {
        if (hospitalCar == null) {
            throw new IllegalArgumentException("Auto must not be null");
        }
        if (hospitalCar.getPrice().equals(BigDecimal.ZERO)) {
            hospitalCar.setPrice(BigDecimal.valueOf(-1));
        }
        return hospitalCars.add(hospitalCar);
    }

    @Override
    public boolean saveAll(List<HospitalCar> hospitalCar) {
        if (hospitalCar == null) {
            return false;
        }

        return hospitalCars.addAll(hospitalCar);
    }

    @Override
    public boolean update(HospitalCar hospitalCar) {
        final HospitalCar foundedHospitalCar = getById(hospitalCar.getId());
        if (foundedHospitalCar != null) {
            HospitalCarsCopy.copy(hospitalCar, foundedHospitalCar);
            return true;
        }
        return false;
    }

    public boolean updateNumberOfPatients(int numberOfPatients, HospitalCar copyFrom) {
        for (HospitalCar hospitalCar : hospitalCars) {
            if (hospitalCar.getNumberOfPatients() == numberOfPatients) {
                HospitalCarsCopy.copy(copyFrom, hospitalCar);
            }
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        return hospitalCars.removeIf(hospitalCar -> hospitalCar.getId().equals(id));
    }

    private static class HospitalCarsCopy {
        static void copy(final HospitalCar from, final HospitalCar to) {
            to.setModel(from.getModel());
            to.setPrice(from.getPrice());
            to.setNumberOfPatients(from.getNumberOfPatients());
        }
    }
}
