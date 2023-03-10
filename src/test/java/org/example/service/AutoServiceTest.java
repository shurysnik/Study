package org.example.service;

import org.example.model.Auto;
import org.example.model.Manufacturer;
import org.example.repository.AutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class AutoServiceTest {
    private AutoService target;
    private AutoRepository autoRepository;

    private Auto createSimpleAuto() {
        return new Auto("Model", Manufacturer.BMW, BigDecimal.ZERO, "Type");
    }

    @BeforeEach
    void setUp() {
        autoRepository = Mockito.mock(AutoRepository.class);
        target = new AutoService(autoRepository);
    }

    @Test
    void createAndSaveAutosNegativeCountBySize() {
        final List<Auto> actual = target.createAndSaveAutos(-1);
        Assertions.assertEquals(0, actual.size());

    }

    @Test
    void createAndSaveAutosNegativeCountByIllegalArgumentException() {
        String message = "cannot be less than 0 ";
        assertThrows(IllegalArgumentException.class,
                () -> target.createAndSaveAutos(-1), message
        );
    }

    @Test
    void createAndSaveAutosZeroCountBySize() {
        final List<Auto> actual = target.createAndSaveAutos(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createAndSaveAutosZeroCountByEMPTY_LIST() {
        List<Auto> expected = Collections.EMPTY_LIST;
        List<Auto> actual = target.createAndSaveAutos(0);
        assertEquals(expected, actual);

    }

    @Test
    void createAndSaveAutos() {
        final List<Auto> actual = target.createAndSaveAutos(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(autoRepository, Mockito.times(5))
                .save(Mockito.any());
    }


    @Test
    void saveAuto_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            target.save(null);
        });
    }
    @Test
    void findOneByIdNull() {
        final Auto expected = createSimpleAuto();
        Mockito.when(autoRepository.getById("")).thenReturn(expected);
        final Auto actual = target.findOneById(null);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void findOneByIdNullByCaptorCreateSimpleAuto() {
        final Auto expected = createSimpleAuto();
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findOneById(null);
        Mockito.verify(autoRepository).getById(captor.capture());
    }

    @Test
    void findOneByIdNullByCaptor() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        target.findOneById("404");
        verify(autoRepository).getById(stringArgumentCaptor.capture());
        assertEquals("404", stringArgumentCaptor.getValue());
    }

    @Test
    void findOneByIdNullByMatcher() {
        ArgumentMatcher<String> argumentMatcher = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String s) {
                return s.startsWith("404");
            }
        };
        Auto actual = target.findOneById(null);
        verify(autoRepository).getById(Mockito.argThat(argumentMatcher));
        Assertions.assertNull(actual);
    }

    @Test
    void printAll() {
        final List<Auto> actual = List.of(createSimpleAuto(), createSimpleAuto());
        Mockito.when(autoRepository.getAll()).thenReturn(actual);
        target.printAll();
    }
}