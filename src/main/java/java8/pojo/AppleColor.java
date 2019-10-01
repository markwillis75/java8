package java8.pojo;

import java.awt.*;

/**
 * Created by Mark Willis on 24/09/2019.
 */
public enum AppleColor {


    RED (Color.red),
    GREEN (Color.green);

    private Color color;
    private AppleColor (Color color){
        this.color = color;
    }

}
