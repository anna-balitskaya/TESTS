package Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CompareNumbersTest {
    private CompareNumbers compareNumbers;

    @BeforeEach
    public void SetUp() {
        compareNumbers = new CompareNumbers();
    }

    @DisplayName("a>b, true")
    @Test
    public void testA_more_B_true() {
        boolean a_more_b = compareNumbers.a_more_b(10, 5);
        Assertions.assertTrue(a_more_b);
}

    @DisplayName("a>b, false")
    @Test
    public void testA_more_B_false() {
        boolean a_more_b = compareNumbers.a_more_b(5, 6);
        Assertions.assertFalse(a_more_b);
    }

    @DisplayName("a<b, true")
    @Test
    public void testA_less_B_true() {
        boolean a_less_b = compareNumbers.a_less_b(1,2);
        Assertions.assertTrue(a_less_b);
    }

    @DisplayName("a<b, false")
    @Test
    public void testA_less_B_false() {
        boolean a_less_b = compareNumbers.a_less_b(10, 10);
        Assertions.assertFalse(a_less_b);
    }

    @DisplayName("a=b, true")
    @Test
    public void testA_equal_B_true() {
        boolean a_equal_b = compareNumbers.a_equal_b(5, 5);
        Assertions.assertTrue(a_equal_b);
    }

    @DisplayName("a=b, false")
    @Test
    public void testA_equal_B_false() {
        boolean a_equal_b = compareNumbers.a_equal_b(4, 5);
        Assertions.assertFalse(a_equal_b);
    }
}
