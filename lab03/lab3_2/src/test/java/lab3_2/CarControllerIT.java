package lab3_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CarControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetCar() {
        Car carToCreate = new Car("Audi", "A3");
        ResponseEntity<Car> createResponse = restTemplate.postForEntity("/cars", carToCreate, Car.class);

        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Car createdCar = createResponse.getBody();
        System.out.println("\n\n\nCREATED CAR: " + createdCar + "\n\n\n");
        assertNotNull(createdCar);
        assertNotNull(createdCar.getId());

        ResponseEntity<Car[]> allCarsResponse = restTemplate.getForEntity("/cars", Car[].class);
        assertEquals(HttpStatus.OK, allCarsResponse.getStatusCode());
        List<Car> carList = List.of(allCarsResponse.getBody());
        assertNotNull(carList);

        boolean found = false;
        for (Car car : carList) {
            if ("Audi".equals(car.getMaker()) && "A3".equals(car.getModel())) {
                found = true;
                break;
            }
        }

        assertEquals(true, found, "Car should be in the list");
    }
}