package Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FactorialTest {

    @DisplayName("Факториал 0")
    @Test
    public void TestFactorialZero() {
        int result = Factorial.factorial(0);
        Assertions.assertEquals(1, result);
    }

    @DisplayName("Факториал 1")
    @Test
    public void TestFactorialOne() {
        int result = Factorial.factorial(1);
        Assertions.assertEquals(1, result);
    }

    @DisplayName("Фактриал положительного числа")
    @Test
    public void TestFactorial() {
        int result = Factorial.factorial(5);
        Assertions.assertEquals(120, result);
    }

    @DisplayName("Факториал отрицательного числа")
    @Test
    public void TestFactorialNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Factorial.factorial(-1);
        });
    }
}
