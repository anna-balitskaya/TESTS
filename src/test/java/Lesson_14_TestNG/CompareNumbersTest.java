package Lesson_14NG;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompareNumbersTest {
    private CompareNumbers compareNumbers;

    @BeforeMethod
    public void setUp() {
        compareNumbers = new CompareNumbers();
    }

    @Test(priority = 0, description = "a>b, true")
    public void testA_more_B_true() {
        boolean a_more_b = compareNumbers.a_more_b(10, 5);
        Assert.assertTrue(a_more_b);
    }

    @Test(priority = 1, description = "a>b, false")
    public void testA_more_B_false() {
        boolean a_more_b = compareNumbers.a_more_b(4, 5);
        Assert.assertFalse(a_more_b);
    }

    @Test(priority = 2, description = "a<b, true")
    public void testA_less_B_true() {
        boolean a_less_b = compareNumbers.a_less_b(4, 5);
        Assert.assertTrue(a_less_b);
    }

    @Test(priority = 3, description = "a<b, false")
    public void testA_less_B_false() {
        boolean a_less_b = compareNumbers.a_less_b(5, 4);
        Assert.assertFalse(a_less_b);
    }

    @Test(priority = 4, description = "a=b, true")
    public void testA_equal_B_true() {
        boolean a_equal_b = compareNumbers.a_equal_b(4, 4);
        Assert.assertTrue(a_equal_b);
    }

    @Test(priority = 5, description = "a=b, false")
    public void testA_equal_B_false() {
        boolean a_equal_b = compareNumbers.a_equal_b(5, 4);
        Assert.assertFalse(a_equal_b);
    }
}
