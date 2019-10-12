package java8.methodreference;

import java.util.*;

public class Comparator {

    public <T> void sort (List<T> apples, java.util.Comparator<T> comparator){
        apples.sort(comparator);
    }
}