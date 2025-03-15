package Lesson_14NG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {

    @Test(priority = 0, description = "Факториал 0")
    public void TestFactorialZero() {
        int result = Factorial.factorial(0);
        Assert.assertEquals(result, 1);
    }


    @Test(priority = 1, description = "Факториал 1")
    public void TestFactorialOne() {
        int result = Factorial.factorial(1);
        Assert.assertEquals(result, 1);
    }

    @Test(priority = 2, description = "Фактриал положительного числа")
    public void TestFactorial() {
        int result = Factorial.factorial(5);
        Assert.assertEquals(result, 120);
    }

    @Test(priority = 3, description = "Факториал отрицательного числа")
    public void TestFactorialNegative() {
        Assert.expectThrows(IllegalArgumentException.class, () -> {
            Factorial.factorial(-1);
        });
    }
}
