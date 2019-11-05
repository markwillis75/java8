package java8.streams;

import java8.pojo.Dish;
import java8.pojo.Menu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mark on 05/11/2019.
 */
public class TestFilterAndSlice {

    private List<Dish> dishes;

    @Before
    public void getDishes() {
        dishes = new Menu().getDishes();
    }

    @Test
    public void testFilter(){
        List<Dish> veg =
                dishes.
                    stream().
                    filter(Dish::isVegetarian).
                    collect(Collectors.toList());

        assertTrue(veg.stream().allMatch(dish -> dish.isVegetarian()));
    }

    @Test
    public void testFilterDistinct(){
        List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 1, 2});

        List<Integer>
                distinct =
                    numbers.
                    stream().
                    distinct().
                    collect(Collectors.toList());

        assertTrue(numbers.size() > distinct.size());
        assertTrue(distinct.size() == 5);
        assertTrue(new HashSet<Integer>(distinct).size() == 5);
    }

    @Test
    public void testLimit(){
        List<Dish> twoMeat =
                dishes.
                        stream().
                        filter(dish -> dish.getType().equals(Dish.Type.MEAT)).
                        limit(2).
                        collect(Collectors.toList());

        assertTrue(twoMeat.size() == 2);
        assertTrue(twoMeat.stream().allMatch(dish -> dish.getType().equals(Dish.Type.MEAT)));
    }

    @Test
    public void testSkip(){
        List<Dish> oneMeat =
                dishes.
                        stream().
                        filter(dish -> dish.getType().equals(Dish.Type.MEAT)).
                        skip(2).
                        collect(Collectors.toList());

        assertTrue(oneMeat.size() == 1);
        assertTrue(oneMeat.stream().allMatch(dish -> dish.getType().equals(Dish.Type.MEAT)));
    }
}
