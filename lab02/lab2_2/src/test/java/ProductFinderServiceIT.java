import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import lab2_2.*;

public class ProductFinderServiceIT {

    @Test
    void whenValidId_thenReturnsProduct() throws IOException {
        ISimpleHttpClient realHttpClient = new TqsBasicHttpClient();
        ProductFinderService service = new ProductFinderService(realHttpClient);
    
        Optional<Product> result = service.findProductDetails(3);

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getId());
        assertEquals("Mens Cotton Jacket", result.get().getTitle());
    }

    @Test
    void whenInvalidId_thenReturnsEmptyOptional() throws IOException {
        ISimpleHttpClient realHttpClient = new TqsBasicHttpClient();
        ProductFinderService service = new ProductFinderService(realHttpClient);

        Optional<Product> result = service.findProductDetails(300);

        assertFalse(result.isPresent());
    }
}