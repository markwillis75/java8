package java8.streams;

import java8.pojo.Trader;
import java8.pojo.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark on 19/11/2019.
 */
public class TestTradersAndTransactions {

    private static TraderApp traderApp;

    @BeforeClass
    public static void build(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan  = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions =
                        Arrays.asList(
                                new Transaction(brian, 2011, 300),
                                new Transaction(raoul, 2012, 1000),
                                new Transaction(raoul, 2011, 400),
                                new Transaction(mario, 2012, 710),
                                new Transaction(mario, 2012, 700),
                                new Transaction(alan, 2012, 950)
                        );

        traderApp = new TraderApp(transactions);
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
        List<Trader> traders = traderApp.getAllTradersFromCitySortedByNameAsString("cambridge");

        assertTrue(traders.size() == 3);
        assertTrue(traders.stream().allMatch(trader -> trader.getCity().equalsIgnoreCase("cambridge")));

        traders = traderApp.getAllTradersFromCitySortedByNameAsString("las vegas");
        assertTrue(traders.isEmpty());
    }

    @Test
    public void testAllNamesAsStringSortedAlphabetically(){
        String str = traderApp.getAllTraderNamesSortedAlphabetically();

        assertEquals("Alan, Brian, Mario, Raoul", str);
    }

    @Test
    public void testTraderBasedInCity(){
        assertTrue(traderApp.getAreAnyTradersBasedInCity("milan"));
        assertFalse(traderApp.getAreAnyTradersBasedInCity("las vegas"));
    }

    @Test
    public void testHighestValueOfAllTransations(){
        Optional<Transaction> highestValue = traderApp.getHighestValueTransation();
        assertTrue(highestValue.isPresent() && highestValue.get().getValue() == 1000);
    }

    @Test
    public void testLowestValueOfAllTransations(){
        Optional<Transaction> lowestValue = traderApp.getLowestValueTransation();
        assertTrue(lowestValue.isPresent() && lowestValue.get().getValue() == 300);
    }
}
