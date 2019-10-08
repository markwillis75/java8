package java8.methodreference;

import java8.pojo.*;
import java.util.*;
import static java.util.Comparator.comparing;

public class Comparator {

    public static void main(String[] args){
        new Comparator().exec();
    }

    private void exec(){
        // Comparator.comparing(Function ff) returns a Comparator which calls the function by which items will be compared
        // Comparator is a functional interface with signature (item1, item2) -> int
        sort(comparing(Apple::getWeight), "Method Reference");

        // we can write our own lambda function which satisfies the Comparator functional interface.  Requires a little more code
        sort((a1, a2) -> a1.getWeight() - a2.getWeight(), "Lambda Function");
    }

    private void sort (java.util.Comparator<Apple> comparator, String method){
        List<Apple> apples = getApples();

        System.out.println(method + "\n\tBefore: " + apples);
        apples.sort(comparator);

        System.out.println("\tAfter:  " + apples);
    }

    private List<Apple> getApples(){
        return Arrays.asList(
                new Apple(AppleColor.RED, 10),
                new Apple(AppleColor.GREEN, 4),
                new Apple(AppleColor.RED, 20),
                new Apple(AppleColor.RED, 5));
    }
}