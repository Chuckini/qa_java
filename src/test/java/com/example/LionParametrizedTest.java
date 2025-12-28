package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionParametrizedTest {

    // Входные параметры: пол и ожидаемое наличие гривы
    private final String sex;
    private final boolean expectedHasMane;

    // Given: наборы данных для двух успешных веток конструктора
    @Parameterized.Parameters(name = "sex={0}, expectedHasMane={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true},
                {"Самка", false}
        });
    }

    public LionParametrizedTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    @Test
    public void shouldReturnCorrectManeValueWhenLionCreatedWithValidSex() throws Exception {
        // Given: создаём льва с валидным полом
        // реальный Feline, потому что doesHaveMane() не зависит от него
        Feline feline = new Feline();
        Lion lion = new Lion(sex, feline);

        // When: проверяем наличие гривы
        boolean hasMane = lion.doesHaveMane();

        // Then: значение соответствует полу
        assertEquals(expectedHasMane, hasMane);
    }
}
