package org.example.repository;

import org.example.model.Auto;
import org.example.model.Manufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AutoRepositoryTest {

    private AutoRepository target;

    private Auto auto;
    @BeforeEach
    void setUp() {
        target = new AutoRepository();
        auto= createSimpleAuto();
        target.save(auto);
    }
    private  Auto createSimpleAuto(){
        return new Auto("Model-", Manufacturer.BMW, BigDecimal.ZERO,"Type");
    }
    @Test
    void getById_findOne() {

        final Auto actual=target.getById(auto.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(auto.getId(),actual.getId());
    }
    @Test
    void getById_findOne_manyAutos() {
        final Auto otherAuto=createSimpleAuto();
        target.save(otherAuto);
        final Auto actual=target.getById(auto.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(auto.getId(),actual.getId());
        Assertions.assertNotEquals(otherAuto.getId(),actual.getId());
    }
    @Test
    void getAll() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}