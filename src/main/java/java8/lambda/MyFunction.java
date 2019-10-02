package java8.lambda;

import java.util.*;
import java.util.function.Function;

public class MyFunction {

    public <T, R> List<R> map (List<T> items, Function<T, R> function){
        List<R> mapped = new ArrayList<>();

        for (T item:items){
            mapped.add(function.apply(item));
        }
        return mapped;
    }
}
