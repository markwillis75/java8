package java8.methodreference;

import java8.pojo.Apple;
import java8.pojo.AppleColor;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ConstructorReference {

    public static void main (String[] args){
        new ConstructorReference().exec();
    }

    private void exec(){
        // Default constructor matches the Supplier interface; takes no args and returns a result
        Supplier<Apple> supplier = Apple::new;
        Apple apple1 = supplier.get();
        System.out.println(apple1);

        // 2 arg constructor fits the BiFunction interface; takes 2 args and returns a result
        BiFunction<AppleColor, Integer, Apple> factory = Apple::new;
        Apple apple2 = factory.apply(AppleColor.GREEN, new Integer(10));
        System.out.println(apple2);

        /*
            If apple had a 3-arg constructor, where the 3rd arg was country, it would be possible to create a Trifunction
            interface and use it to create apples

            public interface Trifunction <T, U, V, R>{
                R apply (T t, U u, V v);
            }

            Trifunction <AppleColor, Integer weight, Country country> factory = Apple::new;
            Apple apple3 = factory.apply(AppleColor.GREEN, 10, FRANCE);
         */
    }
}
