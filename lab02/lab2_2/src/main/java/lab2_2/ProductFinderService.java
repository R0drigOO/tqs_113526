package lab2_2;

import java.io.IOException;
import java.util.Optional;
import org.json.JSONObject;

public class ProductFinderService {
    private String API_PRODUCTS = "https://fakestoreapi.com/products";
    private ISimpleHttpClient httpClient;

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(int id) throws IOException {
        String jsonResponse = httpClient.doHttpGet(API_PRODUCTS + "/" + id);

        if (jsonResponse == null || jsonResponse.trim().isEmpty()) return Optional.empty();

        try {
            JSONObject obj = new JSONObject(jsonResponse);
            int fetchedId = obj.optInt("id", -1);

            if (fetchedId != id) return Optional.empty();

            String title = obj.optString("title", "");
            double price = obj.optDouble("price", 0.0);

            Product product = new Product(fetchedId, null, null, title, null, price);
            return Optional.of(product);
            
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}