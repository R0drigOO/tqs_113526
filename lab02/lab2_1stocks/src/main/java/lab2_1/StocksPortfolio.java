package lab2_1;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double value = 0.0;
        for (Stock stock : stocks) {
            value += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return value;
    }

    /**
     * @param topN the number of most valuable stocks to return
     * @return a list with the topN most valuable stocks in the portfolio
     */
    public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
            .sorted(
                Comparator.comparingDouble((Stock s) ->
                    stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity()
                ).reversed()
            )
            .limit(topN)
            .collect(Collectors.toList());
    }
}