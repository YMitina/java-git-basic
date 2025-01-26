package ru.otus.java.basic.homeworks;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {
    private Array arr;

    @BeforeEach
    public void setUp() {
        arr = new Array();
    }

    @Test
    public void testGetArrayAfterTheLastUnitThrows() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            arr.getArrayAfterTheLastUnit(new int[]{2, 2, 2, 2});
        }, "Должно выброситься исключение RuntimeException");
    }

    @Test
    public void testGetArrayAfterTheLastUnitIsNotNull() {
        Assertions.assertNotNull(arr.getArrayAfterTheLastUnit(new int[]{1, 2, 1, 2, 2}));
    }

    @Test
    public void testCheckArrayOnlyOneAndTwoFalse() {
        Assertions.assertFalse(arr.checkArrayOnlyOneAndTwo(new int[]{2, 2}));

    }

    @ParameterizedTest
    @MethodSource("testDataTrue")
    public void testCheckArrayOnlyOneAndTwoEqualsTrue(int[] a) {
        Assertions.assertEquals(true, arr.checkArrayOnlyOneAndTwo(a));
    }

    @MethodSource
    public static Stream<Arguments> testDataTrue() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}),
                Arguments.of(new int[]{1, 2, 2, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("testDataFalse")
    public void testCheckArrayOnlyOneAndTwoEqualsFalse(int[] a) {
        Assertions.assertEquals(false, arr.checkArrayOnlyOneAndTwo(a));
    }

    @MethodSource
    public static Stream<Arguments> testDataFalse() {
        return Stream.of(
                Arguments.of(new int[]{1, 3}),
                Arguments.of(new int[]{1, 1})
        );
    }

}