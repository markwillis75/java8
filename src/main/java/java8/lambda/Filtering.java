package java8.lambda;

import java8.pojo.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Mark Willis on 24/09/2019.
 */
public class Filtering {

    public List<Apple> filterApples(List<Apple> apples, Predicate<Apple> appleFilter){
        List<Apple> filtered = new ArrayList<>();
        for (Apple apple:apples) {
            if (appleFilter.test(apple))
                filtered.add(apple);
        }
        return filtered;
    }

    public  <T> List<T> filterAnything(List<T> allItems, Predicate<T> theFilter){
        List<T> filtered = new ArrayList<>();
        for (T item:allItems) {
            if (theFilter.test(item))
                filtered.add(item);
        }
        return filtered;
    }
}
