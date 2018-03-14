package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrianglePositiveTests {

    private final String MESSAGE0 = "";
    private final int TR_EQUILATERAL = 1;
    private final int TR_ISOSCELES = 2;
    private final int TR_ORDYNARY = 4;
    private final int TR_RECTANGULAR = 8;

    private Triangle triangle;

    @DataProvider(name = "validTriangleProvider")
    public Object[][] createValidTriangleProviderData() {
        return new Object[][]
                {
                        {5.0, 6.5, 7.0, MESSAGE0},
                };
    }

    @DataProvider(name = "detectTriangleProvider")
    public Object[][] createDetectTriangleData() {
        return new Object[][]
                {
                        {3.0, 4.0, 5.0, TR_RECTANGULAR},
                        {5.0, 3.0, 4.0, TR_RECTANGULAR},
                        {3.0, 5.0, 4.0, TR_RECTANGULAR},
                        {3.5, 3.5, 4.0, TR_ISOSCELES},
                        {3.0, 4.0, 4.0, TR_ISOSCELES},
                        {3.5, 4.0, 3.5, TR_ISOSCELES},
                        {3.5, 3.5, 3.5, TR_ISOSCELES | TR_EQUILATERAL},
                        {3.0, 3.0, Math.sqrt(3.0*3.0 + 3.0*3.0), TR_RECTANGULAR | TR_ISOSCELES},
                        {Math.sqrt(3.0*3.0 + 3.0*3.0), 3.0, 3.0, TR_RECTANGULAR | TR_ISOSCELES},
                        {3.0, Math.sqrt(3.0*3.0 + 3.0*3.0), 3.0, TR_RECTANGULAR | TR_ISOSCELES},
                        {6.5, 5.0, 7.3, TR_ORDYNARY},
                };
    }

    @Test(dataProvider = "validTriangleProvider")
    public void tstCheckTriangle(double a, double b, double c, String expected_message) {
        this.triangle = new Triangle(a, b, c);
        Assert.assertTrue(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), expected_message);
    }

    @Test(dataProvider = "detectTriangleProvider")
    public void tstDetectTriangle(double a, double b, double c, int expectedDetection) {
        this.triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), expectedDetection, 0.0001);
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "validTriangleProvider")
    public void tstGetSquare(double a, double b, double c, String expected_message) {
        this.triangle = new Triangle(a, b, c);
        double p = (a + b + c)/2;
        double expected_square = Math.sqrt(p*(p - a)*(p - b)*(p - c));
        Assert.assertEquals(triangle.getSquare(), expected_square, 0.0001);
    }
}
