import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void calculateLiftTicks_test() throws Exception {
        int[] weights = {60, 80, 40};
        int[] destinedFloors = {2, 3, 2};
        int floorCount = 5;
        int maxLiftPeopleCount = 2;
        int maxLiftWeight = 200;

        int result = 15;

        Assert.assertEquals(result, Main.calculateLiftTicks(weights, destinedFloors, floorCount, maxLiftPeopleCount, maxLiftWeight));
    }

    @Test
    public void calculateLiftTicks_testEmpty() throws Exception {
        int[] weights = {};
        int[] destinedFloors = {};
        int floorCount = 5;
        int maxLiftPeopleCount = 2;
        int maxLiftWeight = 200;

        int result = 0;

        Assert.assertEquals(result, Main.calculateLiftTicks(weights, destinedFloors, floorCount, maxLiftPeopleCount, maxLiftWeight));
    }

}
