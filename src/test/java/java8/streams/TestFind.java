package java8.streams;

import java8.pojo.Dish;
import java8.pojo.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mark on 05/11/2019.
 */
public class TestFind {
    private List<Dish> dishes;

    @Before
    public void makeDishes(){
        dishes = new Menu().getDishes();
    }

    /**
     * Find first is more resource intensive when processing with parallelStream.
     * If order is not important and using parallelStream, use findAny()
     */

    @Test
    public void testFindAny(){
        Optional<Dish> dish =
                dishes
                .stream()
                .filter(Dish::isVegetarian)
                .findAny();

        assertTrue(dish.isPresent());
        assertTrue(dish.get().isVegetarian());
    }

    @Test
    public void testFindFirst(){
        Optional<Dish> dish =
                dishes
                        .stream()
                        .filter(Dish::isVegetarian)
                        .findFirst();

        assertTrue(dish.isPresent());
        assertTrue(dish.get().isVegetarian());
    }
}
