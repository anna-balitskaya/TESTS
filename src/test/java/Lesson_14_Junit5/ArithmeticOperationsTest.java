package Lesson_14;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArithmeticOperationsTest {

    private ArithmeticOperations arithmeticOperations;

    @BeforeEach
    public void SetUp() {
        arithmeticOperations = new ArithmeticOperations();
    }

    @DisplayName("Сумма")
    @Test
    public void testSum () {
        int sum = arithmeticOperations.sum(10,5);
        Assertions.assertEquals(15, sum);
    }

    @DisplayName("Вычитание")
    @Test
    public void testMinus () {
        int minus = arithmeticOperations.minus(10,5);
        Assertions.assertEquals(5, minus);
    }

    @DisplayName("Умножение")
    @Test
    public void testMultiply () {
        int multiply = arithmeticOperations.multiply(10,5);
        Assertions.assertEquals(50, multiply);
    }

    @DisplayName("Деление")
    @Test
    public void testDivide () {
        int divide = arithmeticOperations.divide(10,5);
        Assertions.assertEquals(2, divide);
    }

    @DisplayName("Деление на ноль")
    @Test
    public void testDivide_byZero () {
        Assertions.assertThrows(ArithmeticException.class, () -> arithmeticOperations.divide(10,0));
    }
}
