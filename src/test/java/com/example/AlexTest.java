package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AlexTest {

    @Mock
    private Feline feline;

    @Test
    public void shouldCreateAlexSuccessfully_andHaveMane() throws Exception {
        // Arrange: создаём Алекса с мок Feline
        Alex alex = new Alex(feline);

        // Act: проверяем, есть ли грива (Alex всегда "Самец")
        boolean hasMane = alex.doesHaveMane();

        // Assert: у самца должна быть грива
        assertTrue(hasMane);
    }

    @Test
    public void shouldReturnFriendsList() throws Exception {
        // Arrange: создаём Алекса с мок Feline
        Alex alex = new Alex(feline);

        // Act: получаем список друзей
        List<String> friends = alex.getFriends();

        // Assert: список из 3 друзей в нужном порядке
        assertEquals(List.of("Марти", "Глория", "Мелман"), friends);
    }

    @Test
    public void shouldReturnPlaceOfLiving() throws Exception {
        // Arrange: создаём Алекса с мок Feline
        Alex alex = new Alex(feline);

        // Act: получаем место проживания
        String place = alex.getPlaceOfLiving();

        // Assert: место проживания Нью-Йоркский зоопарк
        assertEquals("Нью-Йоркский зоопарк", place);
    }

    @Test
    public void shouldReturnZeroKittens() throws Exception {
        // Arrange: создаём Алекса с мок Feline
        Alex alex = new Alex(feline);

        // Act: получаем количество котят
        int kittens = alex.getKittens();

        // Assert: у Алекса нет львят
        assertEquals(0, kittens);

        // Assert: зависимость не трогали, потому что Alex переопределяет метод
        Mockito.verifyNoInteractions(feline);
    }

    @Test
    public void shouldGetFoodViaFeline_whenAlexGetFoodCalled() throws Exception {
        // Arrange: создаём Алекса с мок Feline
        Alex alex = new Alex(feline);

        // Arrange: настраиваем мок (Lion.getFood() вызывает feline.getFood("Хищник"))
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);

        // Act: получаем еду
        List<String> food = alex.getFood();

        // Assert: вернулся ожидаемый список
        assertEquals(expectedFood, food);

        // Assert: был вызван нужный метод зависимости с нужным аргументом
        Mockito.verify(feline).getFood("Хищник");
        Mockito.verifyNoMoreInteractions(feline);
    }
}

