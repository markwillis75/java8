package java8.streams;

import java8.pojo.Trader;
import java8.pojo.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by Mark on 19/11/2019.
 */
public class TestTradersAndTransactions {

    private static TraderApp traderApp;

    @BeforeClass
    public static void build(){
        traderApp = new TraderApp();
    }


    @Test
    public void testll2011TransationsLowToHigh(){
        List<Transaction> transactions = traderApp.getAll2011TransationsLowToHigh();

        assertTrue(transactions.stream().allMatch(transaction -> transaction.getYear() == 2011));

        int start = -100;
        for (Transaction transaction:transactions){
            assertTrue(transaction.getValue() > start);
            start = transaction.getValue();
        }
    }

    @Test
    public void testUniqueCities(){
        List<String> uniqueCities = traderApp.getUniqueCities();
        assertTrue(uniqueCities.size() == 2);

        assertTrue(uniqueCities.stream().filter(city -> city.toLowerCase().equals("cambridge")).findAny().isPresent());
        assertTrue(uniqueCities.stream().filter(city -> city.toLowerCase().equals("milan")).findAny().isPresent());
    }

    @Test
    public void testAllTradersFromCambridgeSortedByName(){
        List<Trader> traders = traderApp.getAllTradersFromCambridgeSortedByNameAsString();

        assertTrue(traders.size() == 3);
        assertTrue(traders.stream().allMatch(trader -> trader.getCity().equalsIgnoreCase("cambridge")));
    }

    @Test
    public void testAllNamesAsStringSortedAlphabetically(){
        String str = traderApp.getAllTraderNamesSortedAlphabetically();

        assertEquals("Alan, Brian, Mario, Raoul", str);
    }
}
