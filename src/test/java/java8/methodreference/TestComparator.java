package java8.methodreference;

import java8.pojo.Apple;
import java8.pojo.AppleColor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingDouble;
import static org.junit.Assert.*;

import static java.util.Comparator.comparing;

/**
 * Created by Mark on 12/10/2019.
 */
public class TestComparator {

    private List<Apple> apples;

    @Before
    public void init() {
        apples = getApples();
    }

    @Test
    public void testSortingByWeightWithMethodReference(){
        // Comparator.comparing(Function ff) returns a Comparator which calls the function by which items will be compared
        // Comparator is a functional interface with signature (item1, item2) -> int
        new Comparator().sort(apples, comparing(Apple::getWeight));

        testApplesByWeight();
    }

    @Test
    public void testSortingByWeightWithLambdaExpression(){
        new Comparator().sort(apples, (a1, a2) -> a1.getWeight() - a2.getWeight());

        testApplesByWeight();
    }

    @Test
    public void testSortingByWeightAndCountry(){
        new Comparator().sort(apples, comparing(Apple::getWeight)
                                      .thenComparing(Apple::getCountry));

        testApplesByWeight();

        apples.removeIf((Apple a) -> a.getWeight() != 10);
        assertTrue(apples.get(0).getCountry().equals("Australia"));
        assertTrue(apples.get(1).getCountry().equals("France"));
    }

    public void testApplesByWeight(){
        int lastWeight = 0;
        for (Apple apple:apples){
            assertTrue(apple.getWeight() >= lastWeight);
            lastWeight = apple.getWeight();
        }
    }

    private List<Apple> getApples(){
        return new ArrayList(Arrays.asList(
                new Apple(AppleColor.RED, 10, "France"),
                new Apple(AppleColor.GREEN, 4),
                new Apple(AppleColor.RED, 20),
                new Apple(AppleColor.RED, 5),
                new Apple(AppleColor.GREEN, 10, "Australia")));
    }
}
