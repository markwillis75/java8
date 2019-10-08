package java8.pojo;

import lombok.Data;


/**
 * Created by Mark Willis on 24/09/2019.
 */
public @Data  class Apple {

    private AppleColor color;
    private int weight;

    public Apple(){
    }

    public Apple(AppleColor color, int weight){
        this.color = color;
        this.weight = weight;
    }
}
