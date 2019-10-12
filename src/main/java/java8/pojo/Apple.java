package java8.pojo;

import lombok.Data;


/**
 * Created by Mark Willis on 24/09/2019.
 */
public @Data  class Apple {

    private AppleColor color;
    private int weight;
    private String country;

    public Apple(){
    }

    public Apple(AppleColor color, int weight){
        this (color, weight, "");
    }

    public Apple(AppleColor color, int weight, String country){
        this.color = color;
        this.weight = weight;
        this.country = country;
    }
}
