package java8.lambda;

import java8.pojo.*;

import java.util.*;
import java.util.function.Consumer;

public class MyConsumer {

    public <T> void consume (List<T> consumables, Consumer<T> consumer){
        for (T consumable:consumables){
            consumer.accept(consumable);
        }
    }

    public static void main(String[] args){
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(AppleColor.GREEN, 10));
        apples.add(new Apple(AppleColor.RED, 50));

        MyConsumer consumer = new MyConsumer();

        consumer.consume(apples, (Apple a) ->
                System.out.println(consumer.buildString(a)));

        consumer.consume(apples, (Apple a) ->
                System.out.println(new StringBuffer(consumer.buildString(a)).reverse()));
    }

    private String buildString(Apple apple){
        return String.format("Apple color: %s Apple weight: %d", apple.getColor(), apple.getWeight());
    }
}
