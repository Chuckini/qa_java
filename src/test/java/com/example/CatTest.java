package com.example;

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

    @Test
    public void shouldReturnMeow_whenGetSoundCalled() {
        // Given: кот, которому передали зависимость
        Cat cat = new Cat(feline);

        // When: вызываем метод getSound()
        String sound = cat.getSound();

        // Then: кот должен сказать "Мяу"
        assertEquals("Мяу", sound);
    }

    @Test
    public void shouldReturnFood_whenGetFoodCalled() throws Exception {
        // Given: кот, которому передали зависимость
        Cat cat = new Cat(feline);

        // Given: зависимость Feline при вызове eatMeat() вернёт список еды
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        // When: вызываем метод getFood()
        List<String> actualFood = cat.getFood();

        // Then: кот возвращает то, что вернула зависимость
        assertEquals(expectedFood, actualFood);

        // Then: проверяем, что кот действительно обратился к зависимости
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
        Mockito.verifyNoMoreInteractions(feline);
    }

    @Test(expected = Exception.class)
    public void shouldThrowException_whenPredatorThrowsException() throws Exception {
        // Given: есть кот и зависимость настроена на исключение
        Cat cat = new Cat(feline);

        // Given: зависимость выбрасывает Exception при попытке получить еду
        Mockito.when(feline.eatMeat()).thenThrow(
                new Exception("Не удалось получить еду")
        );

        // When: вызываем getFood()
        cat.getFood();
        // Then @Test(expected = Exception.class)
    }
}
