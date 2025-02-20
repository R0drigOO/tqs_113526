package lab2_2;

import java.util.Optional;

public class ProductFinderService {
    private String API_PRODUCTS;
    private ISimpleHttpClient httpClient;

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(int id) {
        return null;
    }
}