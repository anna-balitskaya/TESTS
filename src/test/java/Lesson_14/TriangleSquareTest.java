package Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TriangleSquareTest {

    private TriangleSquare triangleSquare;

    @BeforeEach
    public void SetUp() {
        triangleSquare = new TriangleSquare();
    }

    @DisplayName("Площадь треугольника")
    @Test
    public void testTriangleSquare () {
        int result = triangleSquare.triangleSquare(10,5);
        Assertions.assertEquals(25, result);
    }

    @DisplayName("Площадь треугольника с высотой 0")
    @Test
    public void testTriangleSquare_HightZero() {
        int result = triangleSquare.triangleSquare(0,5);
        Assertions.assertEquals(0, result);
    }

    @DisplayName("Площадь треугольника с основанием 0")
    @Test
    public void testTriangleSquare_SideAZero() {
        int result = triangleSquare.triangleSquare(10,0);
        Assertions.assertEquals(0, result);
    }
}
