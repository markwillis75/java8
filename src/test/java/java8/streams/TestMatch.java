package java8.streams;

import java8.pojo.Dish;
import java8.pojo.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by Mark on 05/11/2019.
 */
public class TestMatch {
    private List<Dish> dishes;

    @Before
    public void makeDishes(){
        dishes = new Menu().getDishes();
    }

    @Test
    public void testAnyMatch(){
        assertTrue(dishes.stream().anyMatch(Dish::isVegetarian));
        assertFalse(dishes.stream().anyMatch(dish -> dish.getCalories() > 10000));
    }

    @Test
    public void testAllMatch(){
        assertTrue(dishes.stream().allMatch(dish -> dish.getName() != null));
        assertFalse(dishes.stream().allMatch(Dish::isVegetarian));
    }

    @Test
    public void testNoneMatch(){
        assertTrue(dishes.stream().noneMatch(dish -> dish.getCalories() < 1));
    }
}
