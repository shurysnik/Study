package org.example.repository;

import org.example.model.Manufacturer;
import org.example.model.SportCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class SportCarReprositoryTest {
    private SportCarReprository target;
    private SportCar sportCar;

    @BeforeEach
    void setUp() {
        target = new SportCarReprository();
        sportCar = createSimpleSportCar();
        target.save(sportCar);
    }

    private SportCar createSimpleSportCar() {
        return new SportCar("Model", Manufacturer.BMW, BigDecimal.ZERO, 0.0, 0.0);
    }

    @Test
    void getById_notFind() {
        final SportCar acutalNull = target.getById(null);
        Assertions.assertNull(acutalNull);
        final SportCar acutal = target.getById("1234");
        Assertions.assertNull(acutal);


    }

    @Test
    void getById_OneId() {
        final SportCar acutalSportCar = target.getById(sportCar.getId());
        Assertions.assertNotNull(acutalSportCar);
        Assertions.assertEquals(acutalSportCar.getId(), sportCar.getId());
    }

    @Test
    void getById_manySportCar() {
        final SportCar actualSportCar = target.getById(sportCar.getId());
        Assertions.assertNotNull(actualSportCar);
        final SportCar notFoundObject = createSimpleSportCar();
        Assertions.assertNotNull(notFoundObject);
        Assertions.assertEquals(actualSportCar.getId(), sportCar.getId());
        Assertions.assertNotEquals(notFoundObject.getId(), sportCar.getId());
    }


    @Test
    void getAll() {
        final int valueSize = 1;
        final List<SportCar> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(valueSize, actual.size());
    }

    @Test
    void save_success_notChangePrice() {
        sportCar.setPrice(BigDecimal.ONE);
        final boolean checkStatus = target.save(sportCar);

        Assertions.assertTrue(checkStatus);

        final SportCar actual = target.getById(sportCar.getId());

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(BigDecimal.ONE, actual.getPrice());

    }

    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void save_success_changePrice() {
        final boolean checkStatus = target.save(sportCar);
        Assertions.assertTrue(checkStatus);
        final SportCar actual = target.getById(sportCar.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(BigDecimal.valueOf(-1), actual.getPrice());

    }

    @Test
    void saveAll_null() {
        final boolean actual = target.saveAll(null);
        Assertions.assertFalse(actual);

    }


    @Test
    void saveAll() {
        final boolean actual = target.saveAll(List.of(createSimpleSportCar()));
        Assertions.assertTrue(actual);
    }

    @Test
    void saveAll_emptyList() {
        final boolean actual = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(actual);
    }

    @Test
    void update_null() {
        final SportCar actual=createSimpleSportCar();
        final  boolean actualCheck=target.update(actual);
        Assertions.assertFalse(actualCheck);
    }

    @Test
    void update() {
        sportCar.setPrice(BigDecimal.TEN);
        final  boolean actualCheck=target.update(sportCar);
        Assertions.assertTrue(actualCheck);
        final  SportCar actualValue= target.getById(sportCar.getId());
        Assertions.assertEquals(actualValue.getPrice(),sportCar.getPrice());
    }

    @Test
    void updateNumberOfPatients() {
        final SportCar actual=createSimpleSportCar();
        actual.setNumberOfRacesWon(10.0);
        actual.setMaxSpeed(150);
        final boolean otherSportCar=target.updateNumberOfRacesWon(sportCar.getNumberOfRacesWon(),actual);
        Assertions.assertTrue(otherSportCar);
        final  SportCar findSportCar=target.getById(sportCar.getId());
        Assertions.assertNotEquals(10.0,findSportCar.getNumberOfRacesWon());
        Assertions.assertEquals(150,findSportCar.getMaxSpeed());
    }

    @Test
    void delete() {
         final  boolean actual=target.delete(sportCar.getId());
         Assertions.assertTrue(actual);
    }

    @Test
    void delete_fail() {
        final boolean actual=target.delete(null);
        Assertions.assertFalse(actual);

    }
}
