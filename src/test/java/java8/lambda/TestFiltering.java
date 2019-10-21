package java8.lambda;

import java8.pojo.*;
import org.junit.*;
import static org.junit.Assert.*;

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
        assertTrue(greenApples.size() == 2);
        assertColor(greenApples, AppleColor.GREEN);

        assertNotEquals(redApples, greenApples);
    }

    @Test
    public void testFilterByWeight(){
        List<Apple> w10 = filterByWeight(10);
        assertTrue(w10.size() == 1);

        List<Apple> w25 = filterByWeight(25);
        assertTrue(w25.size() == 1);

        List<Apple> w90 = filterByWeight(90);
        assertTrue(w90.size() == 2);

        List<Apple> w99 = filterByWeight(99);
        assertTrue(w99.size() == 0);
    }

    @Test
    public void testFilterByWeightAndColor(){
        List<Apple> red90  = filterByWeightAndColor(90, AppleColor.RED);
        assertTrue(red90.size() == 1);
        assertTrue(red90.get(0).getColor().equals(AppleColor.RED) && red90.get(0).getWeight() == 90);

        List<Apple> green90 = filterByWeightAndColor(90, AppleColor.GREEN);
        assertTrue(green90.size() == 1);
        assertTrue(green90.get(0).getColor().equals(AppleColor.GREEN) && red90.get(0).getWeight() == 90);

        List<Apple> green10 = filterByWeightAndColor(10, AppleColor.GREEN);
        assertTrue(green10.isEmpty());
    }

    @Test
    public void compareGenerics(){
        Predicate<Apple> pp = (Apple a) -> a.getWeight() == 90 && a.getColor().equals(AppleColor.RED);

        List<Apple> filterApples = filter.filterApples(apples, pp);
        List<Apple> filterGeneric = filter.filterAnything(apples, pp);

        assertEquals(filterApples, filterGeneric);
    }

    @Test
    public void testNegatedPredicate(){
        Predicate<Apple> pp = (a) -> a.getWeight() > 50;
        List<Apple> lessThan50 = filter.filterApples(apples,
                pp.negate());

        assertTrue(lessThan50.size() == 2);

        for(Apple apple: lessThan50){
            assertTrue(apple.getWeight() < 50);
        }
    }

    @Test
    public void tetLogicalOr(){
        Predicate<Apple> weight10 = (a) -> a.getWeight() == 10;
        Predicate<Apple> weight25 = (a) -> a.getWeight() == 25;

        List<Apple> weight10or25 = filter.filterApples(apples, weight10.or(weight25));

        assertTrue(weight10or25.size() == 2);

        for (Apple apple: weight10or25){
            assertTrue(apple.getWeight() == 10 || apple.getWeight() == 25);
        }
    }

    @Test
    public void testAnd(){
        Predicate<Apple>  red = (a) -> a.getColor().equals(AppleColor.RED);
        Predicate<Apple>  heavierThan10 = (a) -> a.getWeight() > 10;

        List<Apple> redAndHeavy = filter.filterApples(apples, red.and(heavierThan10));

        assertTrue(redAndHeavy.size() == 1);
        assertTrue(redAndHeavy.get(0).getWeight() > 10 && redAndHeavy.get(0).getColor().equals(AppleColor.RED));
    }


    private void assertColor(List<Apple> apples, AppleColor color){
        for (Apple apple:apples){
            assertTrue(apple.getColor().equals(color));
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
