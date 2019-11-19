package java8.streams;

import java8.pojo.Trader;
import java8.pojo.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by Mark on 19/11/2019.
 */
public class TraderApp {
    private List<Transaction> transactions;

    public TraderApp(List<Transaction> transactions){
        this.transactions = transactions;
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

    public List<Trader> getAllTradersFromCitySortedByNameAsString(String city){
        return transactions
                .stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equalsIgnoreCase(city))
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

    public boolean getAreAnyTradersBasedInCity(String city){
        return transactions
                .stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> trader.getCity().equalsIgnoreCase(city));
    }

    public Optional<Transaction> getHighestValueTransation(){
        return transactions
                .stream()
                .max(Comparator.comparing(Transaction::getValue));
    }

    public Optional<Transaction> getLowestValueTransation(){
        return transactions
                .stream()
                .min(comparing(Transaction::getValue));
    }
}
