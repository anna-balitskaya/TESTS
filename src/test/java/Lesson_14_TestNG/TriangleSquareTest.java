package Lesson_14NG;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TriangleSquareTest {

    private TriangleSquare triangleSquare;

    @BeforeMethod
    public void SetUp() {
        triangleSquare = new TriangleSquare();
    }

    @Test(priority = 0, description = "Площадь треугольника")
    public void testTriangleSquare() {
        int result = triangleSquare.triangleSquare(10, 5);
        Assert.assertEquals(result,25);
    }

    @Test(priority = 1, description = "Площадь треугольника с высотой 0")
    public void testTriangleSquare_HightZero() {
        int result = triangleSquare.triangleSquare(0, 5);
        Assert.assertEquals(result,0);
    }

    @Test(priority = 2, description = "Площадь треугольника с основанием 0")
    public void testTriangleSquare_SideAZero() {
        int result = triangleSquare.triangleSquare(10, 0);
        Assert.assertEquals(result, 0);
    }
}
