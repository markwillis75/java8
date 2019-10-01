package java8.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleFactory {

    private static AppleColor getAppleColor(){
        if (new Random().nextInt(3) % 2 == 0)
            return AppleColor.GREEN;
        else
            return AppleColor.RED;
    }

    private static int getWeight(){
        Random r = new Random();
        int low = 1;
        int high = 10;
        return r.nextInt(high-low) + low;
    }

    public static List<Apple> makeAppleList(int len){
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < len; i++){
            Apple apple = new Apple();
            apple.setWeight(getWeight());
            apple.setColor(getAppleColor());
            apples.add (apple);
        }
        return apples;
    }
}
