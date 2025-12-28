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
public class AlexTest {

    @Mock
    private Feline feline;
    private Alex alex;

    @Before
    public void setUp() throws Exception {
        // Arrange: инициализируем тестируемый объект (создаём Алекса с мок Feline)
         alex = new Alex(feline);
    }
    @Test
    public void shouldCreateAlexSuccessfullyAndHaveMane() throws Exception {
        // Act: вызываем метод тестируемого класса (проверяем, есть ли грива: Alex всегда "Самец")
        boolean hasMane = alex.doesHaveMane();

        // Assert: проверяем возвращаемый результат (у самца должна быть грива)
        assertTrue(hasMane);
    }

    @Test
    public void shouldReturnFriendsList() throws Exception {

        // Act: вызываем метод тестируемого класса (получаем список друзей)
        List<String> friends = alex.getFriends();

        // Assert: проверяем возвращаемый результат (список из 3 друзей в нужном порядке)
        assertEquals(List.of("Марти", "Глория", "Мелман"), friends);
    }

    @Test
    public void shouldReturnPlaceOfLiving() throws Exception {

        // Act: вызываем метод тестируемого класса (получаем место проживания)
        String place = alex.getPlaceOfLiving();

        // Assert: проверяем возвращаемый результат (место проживания Нью-Йоркский зоопарк)
        assertEquals("Нью-Йоркский зоопарк", place);
    }

    @Test
    public void shouldReturnZeroKittens() throws Exception {
        // Act: вызываем метод тестируемого класса (получаем количество котят)
        int kittens = alex.getKittens();

        // Assert: проверяем возвращаемый результат (у Алекса нет львят)
        assertEquals(0, kittens);
    }

    @Test
    public void shouldNotInteractWithFelineWhenGetKittensCalled() throws Exception {

        // Act: вызываем метод тестируемого класса
        alex.getKittens();

        // Assert: проверяем отсутствие взаимодействий с зависимостью
        Mockito.verifyNoInteractions(feline);
    }

    @Test
    public void shouldReturnFoodList() throws Exception {

        // Arrange: подготавливаем тестовые данные и поведение мока
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);

        // Act: вызываем метод тестируемого класса
        List<String> food = alex.getFood();

        // Assert: проверяем возвращаемый результат
        assertEquals(expectedFood, food);
    }

    @Test
    public void shouldCallFelineGetFoodWithPredator() throws Exception {

        // Arrange: настраиваем поведение мока
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные"));

        // Act: вызываем метод тестируемого класса
        alex.getFood();

        // Assert: проверяем вызов метода зависимости с ожидаемым аргументом
        Mockito.verify(feline).getFood("Хищник");
    }
}

