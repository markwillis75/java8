package java8;

import java8.lambda.Filtering;
import java8.pojo.*;
import org.junit.*;

import java.util.*;
import java.util.function.Predicate;

public class TestFiltering {

    private static List<Apple> apples;
    private static Filtering filter;

    @BeforeClass
    public static void init(){
        apples = new ArrayList<>();
        apples.add(new Apple(AppleColor.RED, 90));
        apples.add(new Apple(AppleColor.RED, 10));
        apples.add(new Apple(AppleColor.GREEN, 90));
        apples.add(new Apple(AppleColor.GREEN, 25));

        filter = new Filtering();
    }

    @Test
    public void testFilterByColor(){
        List<Apple> redApples = filterByColor(AppleColor.RED);
        Assert.assertTrue(redApples.size() == 2);
        assertColor(redApples, AppleColor.RED);

        List<Apple> greenApples = filterByColor(AppleColor.GREEN);
        Assert.assertTrue(greenApples.size() == 2);
        assertColor(greenApples, AppleColor.GREEN);

        Assert.assertNotEquals(redApples, greenApples);
    }

    @Test
    public void testFilterByWeight(){
        List<Apple> w10 = filterByWeight(10);
        Assert.assertTrue(w10.size() == 1);

        List<Apple> w25 = filterByWeight(25);
        Assert.assertTrue(w25.size() == 1);

        List<Apple> w90 = filterByWeight(90);
        Assert.assertTrue(w90.size() == 2);

        List<Apple> w99 = filterByWeight(99);
        Assert.assertTrue(w99.size() == 0);
    }

    @Test
    public void testFilterByWeightAndColor(){
        List<Apple> red90  = filterByWeightAndColor(90, AppleColor.RED);
        Assert.assertTrue(red90.size() == 1);
        Assert.assertTrue(red90.get(0).getColor().equals(AppleColor.RED) && red90.get(0).getWeight() == 90);

        List<Apple> green90 = filterByWeightAndColor(90, AppleColor.GREEN);
        Assert.assertTrue(green90.size() == 1);
        Assert.assertTrue(green90.get(0).getColor().equals(AppleColor.GREEN) && red90.get(0).getWeight() == 90);

        List<Apple> green10 = filterByWeightAndColor(10, AppleColor.GREEN);
        Assert.assertTrue(green10.isEmpty());
    }

    @Test
    public void compareGenerics(){
        Predicate<Apple> pp = (Apple a) -> a.getWeight() == 90 && a.getColor().equals(AppleColor.RED);

        List<Apple> filterApples = filter.filterApples(apples, pp);
        List<Apple> filterGeneric = filter.filterAnything(apples, pp);

        Assert.assertEquals(filterApples, filterGeneric);
    }

    private void assertColor(List<Apple> apples, AppleColor color){
        for (Apple apple:apples){
            Assert.assertTrue(apple.getColor().equals(color));
        }
    }

    private List<Apple> filterByColor(AppleColor color){
        return filter.filterApples(apples, (Apple a) -> a.getColor().equals(color));
    }

    private List<Apple> filterByWeight(int weight){
        return filter.filterApples(apples, (Apple a) -> a.getWeight() == weight);
    }

    private List<Apple> filterByWeightAndColor(int weight, AppleColor color){
        return filter.filterApples(apples, (Apple a) -> a.getWeight() == weight && a.getColor().equals(color));
    }
}
