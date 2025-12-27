package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AnimalParametrizedTest {

    // Входной параметр: вид животного
    private final String animalKind;

    // Ожидаемый результат: список еды
    private final List<String> expectedFood;

    // Given: наборы данных для двух успешных веток (без exception)
    @Parameterized.Parameters(name = "animalKind={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения")},
                {"Хищник", List.of("Животные", "Птицы", "Рыба")}
        });
    }

    // Конструктор получает параметры из data()
    public AnimalParametrizedTest(String animalKind, List<String> expectedFood) {
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
    }

    @Test
    public void shouldReturnFood_whenAnimalKindIsValid() throws Exception {
        // Given: есть Animal
        Animal animal = new Animal();

        // When: запрашиваем еду по виду
        List<String> actualFood = animal.getFood(animalKind);

        // Then: возвращается ожидаемый список
        assertEquals(expectedFood, actualFood);
    }
}
