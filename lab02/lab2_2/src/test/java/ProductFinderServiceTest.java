

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import lab2_2.*;

public class ProductFinderServiceTest {

    @Test
    void whenValidId_thenReturnsProduct() throws IOException {
        ISimpleHttpClient httpClientMock = mock(ISimpleHttpClient.class);
        ProductFinderService service = new ProductFinderService(httpClientMock);

        String mockJsonForId3 = "{ \"id\": 3, \"title\": \"Mens Cotton Jacket\", \"price\": 50.0 }";
        when(httpClientMock.doHttpGet(anyString())).thenReturn(mockJsonForId3);

        Optional<Product> result = service.findProductDetails(3);

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getId());
        assertEquals("Mens Cotton Jacket", result.get().getTitle());
    }

    @Test
    void whenInvalidId_thenReturnsEmptyOptional() throws IOException {
        ISimpleHttpClient httpClientMock = mock(ISimpleHttpClient.class);
        ProductFinderService service = new ProductFinderService(httpClientMock);

        when(httpClientMock.doHttpGet(anyString())).thenReturn("{}");

        Optional<Product> result = service.findProductDetails(300);

        assertFalse(result.isPresent());
    }
}