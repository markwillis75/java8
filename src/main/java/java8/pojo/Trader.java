package java8.pojo;

import lombok.Data;

/**
 * Created by Mark on 19/11/2019.
 */
@Data
public class Trader {
    private String name;
    private String city;

    public Trader (String name, String city){
        this.name = name;
        this.city = city;
    }
}
