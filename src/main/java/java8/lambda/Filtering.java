package java8.lambda;

import java8.pojo.Apple;
import java8.pojo.AppleColor;
import java8.pojo.AppleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Created by Mark Willis on 24/09/2019.
 */
public class Filtering {

    private static final int HEAVY = 5;
    public static void main(String[] args){
        new Filtering().filter();
    }

    private void filter(){
        List<Apple> apples = AppleFactory.makeAppleList(10);
        System.out.println("APPLES    " + apples);

        System.out.println("RED       " + filterApples(apples, (Apple a) -> a.getColor().equals(AppleColor.RED)));
        System.out.println("HEAVY RED " + filterApples(apples, (Apple a) -> a.getColor().equals(AppleColor.RED) && a.getWeight() > HEAVY));
        System.out.println("GREEN     " + filterApples(apples, (Apple a) -> a.getColor().equals(AppleColor.GREEN)));
        System.out.println("LIGHT     " + filterApples(apples, (Apple a) -> a.getWeight() < HEAVY));

        System.out.println("============");

        System.out.println("RED       " + filterAnything(apples, (Apple a) -> a.getColor().equals(AppleColor.RED)));
        System.out.println("HEAVY RED " + filterAnything(apples, (Apple a) -> a.getColor().equals(AppleColor.RED) && a.getWeight() > HEAVY));
        System.out.println("GREEN     " + filterAnything(apples, (Apple a) -> a.getColor().equals(AppleColor.GREEN)));
        System.out.println("LIGHT     " + filterAnything(apples, (Apple a) -> a.getWeight() < HEAVY));
    }

    private List<Apple> filterApples(List<Apple> apples, Predicate<Apple> appleFilter){
        List<Apple> filtered = new ArrayList<>();
        for (Apple apple:apples) {
            if (appleFilter.test(apple))
                filtered.add(apple);
        }
        return filtered;
    }

    private <T> List<T> filterAnything(List<T> allItems, Predicate<T> theFilter){
        List<T> filtered = new ArrayList<>();
        for (T item:allItems) {
            if (theFilter.test(item))
                filtered.add(item);
        }
        return filtered;
    }
}
