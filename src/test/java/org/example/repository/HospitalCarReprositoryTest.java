package org.example.repository;


import org.example.model.HospitalCar;
import org.example.model.Manufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class HospitalCarReprositoryTest {

    private HospitalCarReprository target;
    private HospitalCar hospitalCar;

    @BeforeEach
    void setUp() {
        target = new HospitalCarReprository();
        hospitalCar = createSimpleHospitalCar();
        target.save(hospitalCar);
    }

    public HospitalCar createSimpleHospitalCar() {
        return new HospitalCar("Model", Manufacturer.BMW, BigDecimal.ZERO, 0);
    }

    @Test
    void getById_OneId() {
        final HospitalCar actual = target.getById(hospitalCar.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.getId(), hospitalCar.getId());
    }

    @Test
    void getById_notFind() {
        final HospitalCar actual = target.getById("1234");
        Assertions.assertNull(actual);
    }

    @Test
    void getById_manyHospitalCar() {
        final HospitalCar otherHospitalCar = createSimpleHospitalCar();
        target.save(otherHospitalCar);
        final HospitalCar actual = target.getById(hospitalCar.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.getId(), hospitalCar.getId());
        Assertions.assertNotEquals(actual.getId(), otherHospitalCar.getId());
    }

    @Test
    void getAll() {
        final List<HospitalCar> actual = target.getAll();
        final int expectedSize = 1;
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedSize, actual.size());
    }

    @Test
    void save_success_notChangePrice() {
        hospitalCar.setPrice(BigDecimal.ONE);

        final boolean actual = target.save(hospitalCar);
        Assertions.assertTrue(actual);
        final HospitalCar otherHospitalCar = target.getById(hospitalCar.getId());
        Assertions.assertEquals(BigDecimal.ONE, otherHospitalCar.getPrice());
    }


    @Test
    void save_success_changePrice() {
        target.save(hospitalCar);
        final HospitalCar actual = target.getById(hospitalCar.getId());
        Assertions.assertEquals(BigDecimal.valueOf(-1), actual.getPrice());
    }
    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void saveAll_null() {
        final boolean actual = target.saveAll(null);
        Assertions.assertFalse(actual);
    }

    @Test
    void saveAll() {
        final boolean actual = target.saveAll(List.of(createSimpleHospitalCar()));
        Assertions.assertTrue(actual);
    }

    @Test
    void saveAll_emptyList() {
        final boolean actual = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(actual);
    }

    @Test
    void update_null() {
        final HospitalCar actual = createSimpleHospitalCar();
        //final boolean actualSaveHospitalCar=target.save(actual); if we want to have not null we need save our object
        final boolean actualUpdateHospitalCar = target.update(actual);

        Assertions.assertFalse(actualUpdateHospitalCar);
    }

    @Test
    void update() {
        hospitalCar.setPrice(BigDecimal.TEN); //we add our new value to our base(
        final boolean actual = target.update(hospitalCar);// we can uptade this value
        Assertions.assertTrue(actual); // we can check our update
        final HospitalCar actualHospitalCarById = target.getById(hospitalCar.getId());// we found this object with our new  price
        Assertions.assertEquals(hospitalCar.getPrice(), actualHospitalCarById.getPrice());

    }

    @Test
    void updateNumberOfPatients() {
        final HospitalCar otherHospitalCar = createSimpleHospitalCar();
        otherHospitalCar.setManufacturer(Manufacturer.KIA);//we do it to update and check the test in work
        otherHospitalCar.setPrice(BigDecimal.TEN);//we do it to get null(bc we dont have this update)
        final boolean actualHospitalCar = target.updateNumberOfPatients(hospitalCar.getNumberOfPatients(), otherHospitalCar);
        Assertions.assertTrue(actualHospitalCar);
        final HospitalCar actualHospitalCarById = target.getById(hospitalCar.getId());
        Assertions.assertEquals(Manufacturer.BMW, actualHospitalCarById.getManufacturer());
        Assertions.assertEquals(BigDecimal.TEN, actualHospitalCarById.getPrice());
    }

    @Test
    void delete() {
        target.save(hospitalCar);
        final boolean actual = target.delete(hospitalCar.getId());
        Assertions.assertTrue(actual);
    }

    @Test
    void delete_fail() {

        final boolean actual = target.delete(null);
        Assertions.assertFalse(actual);
    }
}