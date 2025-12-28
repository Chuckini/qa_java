package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    private Animal animal;

    @Before
    public void setUp() {
        // Given: есть Animal
        animal = new Animal();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenAnimalKindIsUnknown() throws Exception {
        // Given: неизвестный вид
        String unknownKind = "Птица";

        // When: запрашиваем еду по неизвестному виду
        animal.getFood(unknownKind);

        // Then: ожидаем Exception (проверяется аннотацией @Test(expected = Exception.class))
    }

    @Test
    public void shouldReturnFamilyWhenGetFamilyCalled() {

        // When: запрашиваем семейство
        String family = animal.getFamily();

        // Then: возвращается строка с перечислением семейств
        assertEquals(
                "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи",
                family
        );
    }
}
