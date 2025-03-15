package Lesson_14NG;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArithmeticOperationsT {

    private ArithmeticOperations arithmeticOperations;

    @BeforeMethod
    public void setUp() {
        arithmeticOperations = new ArithmeticOperations();
    }

    @Test(priority = 0, description = "Сумма")
    public void testSum() {
        int sum = arithmeticOperations.sum(10, 5);
        Assert.assertEquals(sum, 15);
    }

    @Test(priority = 1, description = "Вычитание")
    public void testMinus() {
        int minus = arithmeticOperations.minus(10, 5);
        Assert.assertEquals(minus, 5);
    }

    @Test (priority = 2,description = "Умножение")
    public void testMultiply() {
        int multiply = arithmeticOperations.multiply(10, 5);
        Assert.assertEquals(multiply, 50);
    }

    @Test(priority = 3,description = "Деление")
    public void testDivide() {
        int divide = arithmeticOperations.divide(10, 5);
        Assert.assertEquals(divide, 2);
    }

    @Test(priority = 4, description = "Деление на ноль")
    public void testDivideByZero() {
        Assert.expectThrows(ArithmeticException.class, () -> arithmeticOperations.divide(10, 0));
    }
}
