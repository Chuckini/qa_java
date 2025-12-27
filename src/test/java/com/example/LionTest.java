package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    // Мок зависимости: Lion получает Feline через инъекцию зависимости (DI)
    @Mock
    private Feline feline;

    @Test(expected = Exception.class)
    public void shouldThrowException_whenSexIsInvalid() throws Exception {
        // Given: невалидное значение пола
        String invalidSex = "Котик";

        // When: создаём льва с невалидным полом
        new Lion(invalidSex, feline);

        // Then: ожидаем Exception (проверяется аннотацией @Test(expected = Exception.class))
    }

    @Test
    public void shouldReturnKittensCount_whenGetKittensCalled() throws Exception {
        // Given: есть лев и зависимость настроена вернуть количество котят
        Mockito.when(feline.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getKittens()
        int kittens = lion.getKittens();

        // Then: лев возвращает то число, которое вернула зависимость
        assertEquals(3, kittens);

        // Then: проверяем, что метод зависимости действительно был вызван
        Mockito.verify(feline, Mockito.times(1)).getKittens();
        Mockito.verifyNoMoreInteractions(feline);
    }

    @Test
    public void shouldReturnFood_whenGetFoodCalled() throws Exception {
        // Given: есть лев и зависимость должна вернуть список еды
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getFood()
        List<String> actualFood = lion.getFood();

        // Then: лев возвращает список, который вернула зависимость
        assertEquals(expectedFood, actualFood);

        // Then: проверяем, что зависимость была вызвана с правильным аргументом
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
        Mockito.verifyNoMoreInteractions(feline);
    }
}
