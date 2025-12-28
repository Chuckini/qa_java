package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    // Мок зависимости: Cat внутри хранит Predator, а Feline реализует Predator
    @Mock
    private Feline feline;

    private Cat cat;

    @Before
    public void setUp() {
        // Given: кот, которому передали зависимость
        cat = new Cat(feline);
    }

    @Test
    public void shouldReturnMeowWhenGetSoundCalled() {

        // When: вызываем метод getSound()
        String sound = cat.getSound();

        // Then: кот должен сказать "Мяу"
        assertEquals("Мяу", sound);
    }

    @Test
    public void shouldReturnFoodWhenGetFoodCalled() throws Exception {

        // Given: зависимость возвращает список еды
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        // When: вызываем getFood()
        List<String> actualFood = cat.getFood();

        // Then: возвращается ожидаемый результат
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void shouldCallEatMeatWhenGetFoodCalled() throws Exception {

        // Given: зависимость настроена
        Mockito.when(feline.eatMeat()).thenReturn(List.of("Животные"));

        // When: вызываем getFood()
        cat.getFood();

        // Then: кот обращается к зависимости
        Mockito.verify(feline).eatMeat();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenPredatorThrowsException() throws Exception {

        // Given: зависимость выбрасывает Exception при попытке получить еду
        Mockito.when(feline.eatMeat()).thenThrow(
                new Exception("Не удалось получить еду")
        );

        // When: вызываем getFood()
        cat.getFood();
        // Then @Test(expected = Exception.class)
    }
}
