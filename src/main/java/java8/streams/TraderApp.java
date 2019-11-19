package java8.streams;

import java8.pojo.Trader;
import java8.pojo.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by Mark on 19/11/2019.
 */
public class TraderApp {

    private Trader raoul = new Trader("Raoul", "Cambridge");
    private Trader mario = new Trader("Mario", "Milan");
    private Trader alan  = new Trader("Alan", "Cambridge");
    private Trader brian = new Trader("Brian", "Cambridge");


    private List<Transaction> transactions;

    public TraderApp(){
        transactions=
            Arrays.asList(
                    new Transaction(brian, 2011, 300),
                    new Transaction(raoul, 2012, 1000),
                    new Transaction(raoul, 2011, 400),
                    new Transaction(mario, 2012, 710),
                    new Transaction(mario, 2012, 700),
                    new Transaction(alan, 2012, 950)
            );
    }

    public List<Transaction> getAll2011TransationsLowToHigh(){
        return transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    public List<String> getUniqueCities(){
        return transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Trader> getAllTradersFromCambridgeSortedByNameAsString(){
        return transactions
                .stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    public String getAllTraderNamesSortedAlphabetically() {
        return transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((a, b) -> a + ", " + b)
                .get();
    }
}
