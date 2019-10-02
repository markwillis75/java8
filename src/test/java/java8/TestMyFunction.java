package java8;

import java8.lambda.MyFunction;
import java8.pojo.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class TestMyFunction {

    private static List<Apple> apples;

    @BeforeClass
    public static void makeApples(){
        apples = new ArrayList<>();
        apples.add(new Apple(AppleColor.RED, 10));
        apples.add(new Apple(AppleColor.GREEN, 20));
    }

    @Test
    public void testStringLength(){
        List<Integer> ints = new MyFunction().map(apples, (Apple a) ->  a.getColor().toString().length() + a.getWeight());

        Assert.assertTrue(ints.get(0) == 13);
        Assert.assertTrue(ints.get(1) == 25);
    }

    @Test
    public void testStringConcat(){
        List<String> strings = new MyFunction().map(apples, (Apple a) -> a.getColor() + ":" + a.getWeight());

        Assert.assertEquals(strings.get(0), "RED:10");
        Assert.assertEquals(strings.get(1), "GREEN:20");
    }

    @Test
    public void testAppleColor(){
        List<AppleColor> colors = new MyFunction().map(apples, (Apple a) -> a.getColor());

        Assert.assertEquals(colors.get(0), AppleColor.RED);
        Assert.assertEquals(colors.get(1), AppleColor.GREEN);
    }
}
