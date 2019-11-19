package java8.streams;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Using .reduce(...) rather than simple iteration over a collection abstracts away the iteration and allows for parallelism
 * behind the scenes.  With reduce, the collection can be partitioned, farmed out to different threads for processing and
 * the results of processing each partition merged together on the coordinating thread.
 *
 * Simply use collection.parallelStream() instead of collection.stream() to avail of parallelism
 */
public class TestReduce {

    private static List<Integer> numbers;

    @BeforeClass
    public static void build(){
        numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    }

    @Test
    public void testReduceSumIntegerStartingValueof10(){
        // reduce by lambda
        Integer summedValue = numbers.stream().reduce(10, (a, b) -> a + b);
        assertEquals(Integer.valueOf(20), summedValue);

        // reduce by method reference
        summedValue = numbers.stream().reduce(10 ,Integer::sum);
        assertEquals(Integer.valueOf(20), summedValue);
    }

    @Test
    public void testReduceSumIntegersNoStartingValue(){
        // Note that return type is Optional; caters for scenario where the Collection contains no elements

        // reduce by lambda
        Optional<Integer> optional = numbers.stream().reduce((a, b) -> a + b);
        assertEquals(Integer.valueOf(10), optional.get());

        // reduce by method reference
        optional = numbers.stream().reduce(Integer::sum);
        assertEquals(Integer.valueOf(10), optional.get());
    }

    @Test
    public void testMinAndMax(){
        assertEquals(Integer.valueOf(1), numbers.stream().reduce((a, b) -> Integer.min(a, b)).get());
        assertEquals(Integer.valueOf(1), numbers.stream().reduce(Integer::min).get());

        assertEquals(Integer.valueOf(4), numbers.stream().reduce((a, b) -> Integer.max(a, b)).get());
        assertEquals(Integer.valueOf(4), numbers.stream().reduce(Integer::max).get());
    }

    @Test
    public void testCountingWithMapAndReduce(){
        // map every element in the list to the number 1.  Use reduce to add 1 to the running total
        int count = numbers
                .stream()
                .map(num -> 1)
                .reduce(0, (a, b) -> a+b);

        assertEquals(4, count);

        // use built-in map-reduce method
        assertEquals(4, numbers.stream().count());


    }
}
