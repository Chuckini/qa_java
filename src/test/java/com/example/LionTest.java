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
    public void shouldThrowExceptionWhenSexIsInvalid() throws Exception {
        // Given: невалидное значение пола
        String invalidSex = "Кошечка";

        // When: создаём льва с невалидным полом
        new Lion(invalidSex, feline);

        // Then: ожидаем Exception (проверяется аннотацией @Test(expected = Exception.class))
    }

    @Test
    public void shouldReturnKittensCountWhenGetKittensCalled() throws Exception {
        // Given: есть лев и зависимость настроена вернуть количество котят
        Mockito.when(feline.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getKittens()
        int kittens = lion.getKittens();

        // Then: лев возвращает то число, которое вернула зависимость
        assertEquals(3, kittens);
    }

    @Test
    public void shouldCallFelineGetKittensWhenGetKittensCalled() throws Exception {
        // Given: лев и зависимость настроена
        Mockito.when(feline.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getKittens()
        lion.getKittens();

        // Then: проверяем вызов метода зависимости
        Mockito.verify(feline).getKittens();
    }

    @Test
    public void shouldReturnFoodWhenGetFoodCalled() throws Exception {
        // Given: есть лев и зависимость должна вернуть список еды
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getFood()
        List<String> actualFood = lion.getFood();

        // Then: лев возвращает список, который вернула зависимость
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void shouldCallFelineGetFoodWithPredatorWhenGetFoodCalled() throws Exception {
        // Given: лев и зависимость настроена
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные"));
        Lion lion = new Lion("Самец", feline);

        // When: вызываем getFood()
        lion.getFood();

        // Then: проверяем вызов метода зависимости с ожидаемым аргументом
        Mockito.verify(feline).getFood("Хищник");
    }
}
