package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineParametrizedTest {

    // Входной параметр: сколько котят передаём в getKittens(int)
    private final int kittensCount;

    // Given: наборы данных для параметризованного теста
    @Parameterized.Parameters(name = "kittensCount={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0},
                {1},
                {3},
                {10}
        });
    }
    // конструктор
    public FelineParametrizedTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Test
    public void shouldReturnSameKittensCount_whenGetKittensWithParamCalled() {
        // Given: есть объект Feline
        Feline feline = new Feline();

        // When: вызываем getKittens(int) с параметром
        int actualKittens = feline.getKittens(kittensCount);

        // Then: возвращается то же число, которое мы передали
        assertEquals(kittensCount, actualKittens);
    }
}

