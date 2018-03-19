package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleNegativeTests {

    private final String MESSAGE0 = "a<=0";
    private final String MESSAGE1 = "b<=0";
    private final String MESSAGE2 = "c<=0";
    private final String MESSAGE3 = "a+b<=c";
    private final String MESSAGE4 = "b+c<=a";
    private final String MESSAGE5 = "a+c<=b";
    private final String MESSAGE6 = "[a] is Not a Number";
    private final String MESSAGE7 = "[b] is Not a Number";
    private final String MESSAGE8 = "[c] is Not a Number";
    private final String MESSAGE9 = "[a] is Infinite";
    private final String MESSAGE10 = "[b] is Infinite";
    private final String MESSAGE11 = "[c] is Infinite";

    private Triangle triangle;

    @DataProvider(name = "negativeTestsProvider")
    public Object[][] createNegativeTestsData() {
        return new Object[][]
                {
                        {-2.8, 2.5, 4.3, MESSAGE0},
                        {3.0, -2.5, 3.8, MESSAGE1},
                        {3.0, 3.5, -4.2, MESSAGE2},
                        {1.5, 2.1, 4.8, MESSAGE3},
                        {1.5, 2.1, 3.6, MESSAGE3},
                        {7.5, 2.1, 4.8, MESSAGE4},
                        {6.9, 2.1, 4.6, MESSAGE4},
                        {1.5, 6.1, 2.8, MESSAGE5},
                        {1.5, 3.5, 2.0, MESSAGE5},
                        {Double.NaN, 3.6, 8.0, MESSAGE6},
                        {4.D, Double.NaN, 8.0, MESSAGE7},
                        {3.2, 3.6, Double.NaN, MESSAGE8},
                        {Double.POSITIVE_INFINITY, 2.3, 2.0, MESSAGE9},
                        {4.5, Double.POSITIVE_INFINITY, 2.0, MESSAGE10},
                        {2.8, 2.3, Double.POSITIVE_INFINITY, MESSAGE11},
                };
    }

    @Test(dataProvider = "negativeTestsProvider")
    public void tstCheckTriangle(double a, double b, double c, String expected_message) {
        this.triangle = new Triangle(a, b, c);
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), expected_message, "[Message \"" + triangle.getMessage() + "\" is " +
                "not correct. Triangle sides: a=" + a + " b=" + b + " c=" + c + "]");
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "negativeTestsProvider")
    public void tstDetectTriangle(double a, double b, double c, String expected_message) {
        this.triangle = new Triangle(a, b, c);
        triangle.detectTriangle();
        Assert.fail("[No exception have been thrown when detecting invalid triangle with sides: a=" + a + " b=" + b + " c=" + c + ". " +
                "Exception need to be added to method detectTriangle().]");
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "negativeTestsProvider")
    public void tstGetSquare(double a, double b, double c, String expected_message) {
        this.triangle = new Triangle(a, b, c);
        triangle.getSquare();
        Assert.fail("No exception have been thrown when getting square of invalid triangle with sides: a=" + a + " b=" + b + " c=" + c + "" +
                "Exception need to be added to method getSquare().]");
    }

}
