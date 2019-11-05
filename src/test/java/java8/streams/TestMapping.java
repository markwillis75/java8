package java8.streams;

import java8.pojo.Dish;
import java8.pojo.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mark on 05/11/2019.
 */
public class TestMapping {
    private List<Dish> dishes;

    @Before
    public void buildDishes(){
        dishes = new Menu().getDishes();
    }


    @Test
    public void testMap(){
        List<String> dishNames =
                dishes.
                        stream().
                        map(Dish::getName).
                        collect(Collectors.toList());

        assertTrue(dishNames.size() == dishes.size());

        int counter = 0;
        for (String dishName:dishNames){
            assertTrue(dishes.get(counter++).getName().equals(dishName));
        }
    }

    @Test
    public void testFlatMap(){
        List<String> words = Arrays.asList(new String[]{"hello", "world"});

        String uniqueChars =
            words.
                stream().
                map(word -> word.split("")).  // produces String[]
                flatMap(Arrays::stream).            // now stream the array to produce a stream of strings
                distinct().
                collect(Collectors.joining());

        assertTrue(uniqueChars.length() == 7);
        assertTrue(uniqueChars.equals("helowrd"));
    }
}
