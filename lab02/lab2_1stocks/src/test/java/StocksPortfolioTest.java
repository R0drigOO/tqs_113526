import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import lab2_1.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {
    @Mock
    private IStockmarketService stockmarketService;

    @InjectMocks
    private StocksPortfolio portfolio;

    /*
    // Another way to initialize the mock
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        portfolio = new StocksPortfolio(stockmarketService);
    }
    */

    @Test
    void testTotalValue() {
        Stock stock1 = new Stock("Apple", 50);
        Stock stock2 = new Stock("NVidia", 30);

        portfolio.addStock(stock1);
        portfolio.addStock(stock2);

        when(stockmarketService.lookUpPrice("Apple")).thenReturn(150.0);
        when(stockmarketService.lookUpPrice("NVidia")).thenReturn(120.0);
        //when(stockmarketService.lookUpPrice("AMD")).thenReturn(80.0); // mockito error due to not being used
        //when(stockmarketService.lookUpPrice("Intel")).thenReturn(30.0); // mockito error due to not being used

        double totalValue = portfolio.totalValue();
        assertEquals(150.0 * 50 + 120.0 * 30, totalValue);

        verify(stockmarketService).lookUpPrice("Apple");
        verify(stockmarketService).lookUpPrice("NVidia");
    }

    @Test
    void testTotalValueHamcrest() {
        Stock stock1 = new Stock("Apple", 50);
        Stock stock2 = new Stock("NVidia", 30);
        Stock stock3 = new Stock("AMD", 20);
        Stock stock4 = new Stock("Intel", 45);

        portfolio.addStock(stock1);
        portfolio.addStock(stock2);
        portfolio.addStock(stock3);
        portfolio.addStock(stock4);

        when(stockmarketService.lookUpPrice("Apple")).thenReturn(150.0);
        when(stockmarketService.lookUpPrice("NVidia")).thenReturn(120.0);
        when(stockmarketService.lookUpPrice("AMD")).thenReturn(80.0);
        when(stockmarketService.lookUpPrice("Intel")).thenReturn(30.0);

        double totalValue = portfolio.totalValue();
        assertThat(totalValue, equalTo(150.0 * 50 + 120.0 * 30 + 80.0 * 20 + 30.0 * 45));

        verify(stockmarketService).lookUpPrice("Apple");
        verify(stockmarketService).lookUpPrice("NVidia");
        verify(stockmarketService).lookUpPrice("AMD");
        verify(stockmarketService).lookUpPrice("Intel");
    }

    @Test
    void testMostValuableStocks() {
        Stock stock1 = new Stock("Apple", 50);
        Stock stock2 = new Stock("NVidia", 30);
        Stock stock3 = new Stock("AMD", 20);
        Stock stock4 = new Stock("Intel", 45);

        when(stockmarketService.lookUpPrice("Apple")).thenReturn(150.0);
        when(stockmarketService.lookUpPrice("NVidia")).thenReturn(120.0);
        when(stockmarketService.lookUpPrice("AMD")).thenReturn(80.0);
        when(stockmarketService.lookUpPrice("Intel")).thenReturn(30.0);

        // Testing with an empty portfolio
        assertEquals(new ArrayList<Stock>(), portfolio.mostValuableStocks(5));
        verify(stockmarketService, times(0)).lookUpPrice("Apple");
        verify(stockmarketService, times(0)).lookUpPrice("NVidia");
        verify(stockmarketService, times(0)).lookUpPrice("AMD");
        verify(stockmarketService, times(0)).lookUpPrice("Intel");

        portfolio.addStock(stock1);
        portfolio.addStock(stock2);
        portfolio.addStock(stock3);
        portfolio.addStock(stock4);

        List<Stock> mostValuableStocks = new ArrayList<Stock>();
        mostValuableStocks.add(stock1);
        mostValuableStocks.add(stock2);
        mostValuableStocks.add(stock3);

        // Testing with a portfolio with 4 stocks
        assertEquals(mostValuableStocks, portfolio.mostValuableStocks(3));
        verify(stockmarketService).lookUpPrice("Apple");
        verify(stockmarketService, times(2)).lookUpPrice("NVidia");
        verify(stockmarketService, times(2)).lookUpPrice("AMD");
        verify(stockmarketService).lookUpPrice("Intel");

        // Testing with a portfolio with 4 stocks and asking for 0 most valuable stocks
        assertEquals(new ArrayList<Stock>(), portfolio.mostValuableStocks(0));
        verify(stockmarketService, times(2)).lookUpPrice("Apple");
        verify(stockmarketService, times(4)).lookUpPrice("NVidia");
        verify(stockmarketService, times(4)).lookUpPrice("AMD");
        verify(stockmarketService, times(2)).lookUpPrice("Intel");

        mostValuableStocks.add(stock4);
        // Testing with a portfolio with 4 stocks and asking for 6 most valuable stocks
        assertEquals(mostValuableStocks, portfolio.mostValuableStocks(6));
        verify(stockmarketService, times(3)).lookUpPrice("Apple");
        verify(stockmarketService, times(6)).lookUpPrice("NVidia");
        verify(stockmarketService, times(6)).lookUpPrice("AMD");
        verify(stockmarketService, times(3)).lookUpPrice("Intel");
    }
}