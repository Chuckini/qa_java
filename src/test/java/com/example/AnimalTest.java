package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test(expected = Exception.class)
    public void shouldThrowException_whenAnimalKindIsUnknown() throws Exception {
        // Given: есть Animal и неизвестный вид
        Animal animal = new Animal();
        String unknownKind = "Птица";

        // When: запрашиваем еду по неизвестному виду
        animal.getFood(unknownKind);

        // Then: ожидаем Exception (проверяется аннотацией @Test(expected = Exception.class))
    }

    @Test
    public void shouldReturnFamily_whenGetFamilyCalled() {
        // Given: есть Animal
        Animal animal = new Animal();

        // When: запрашиваем семейство
        String family = animal.getFamily();

        // Then: возвращается строка с перечислением семейств
        assertEquals(
                "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи",
                family
        );
    }
}
