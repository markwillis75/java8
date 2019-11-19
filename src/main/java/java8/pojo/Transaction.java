package java8.pojo;

import lombok.Data;

/**
 * Created by Mark on 19/11/2019.
 */
@Data
public class Transaction {
    private final Trader trader;
    private int year;
    private int value;

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
