package java8.pojo;

import java.util.*;

/**
 * Created by Mark on 21/10/2019.
 */
public class Menu {
    private List<Dish> dishes = new ArrayList<>();

    public Menu (){
        dishes.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        dishes.add(new Dish("beef", false, 700, Dish.Type.MEAT));
        dishes.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        dishes.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        dishes.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        dishes.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        dishes.add(new Dish("pizza", true, 550, Dish.Type.OTHER));
        dishes.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        dishes.add(new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public List<Dish> getDishes(){
        return dishes;
    }
}
