package java8.pojo;

import lombok.Data;

/**
 * Created by Mark on 21/10/2019.
 */
@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type{ MEAT, FISH, OTHER};
}
