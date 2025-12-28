package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FelineTest {

    private Feline feline;

    @Before
    public void setUp() {
        // Given: есть объект Feline
        feline = new Feline();
    }

    @Test
    public void shouldReturnPredatorFoodWhenEatMeatCalled() throws Exception {

        // When: вызываем eatMeat() Feline вызывает getFood("Хищник")
        List<String> food = feline.eatMeat();

        // Then: возвращается список еды хищника
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @Test
    public void shouldReturnFelineFamilyWhenGetFamilyCalled() {
        // When: вызываем getFamily()
        String family = feline.getFamily();

        // Then: возвращается "Кошачьи"
        assertEquals("Кошачьи", family);
    }

    @Test
    public void shouldReturnOneKittenWhenGetKittensCalled() {
        // When: вызываем getKittens() без параметров (внутри вызывает getKittens(1))
        int kittens = feline.getKittens();

        // Then: по умолчанию возвращается 1
        assertEquals(1, kittens);
    }

}
