package lab3_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.ResponseEntity;

public class CarControllerTest {

    @Mock
    private CarManagerService carManagerService;

    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carController = new CarController();
        ReflectionTestUtils.setField(carController, "carManagerService", carManagerService);
    }

    @Test
    void testCreateCar() {
        Car car = new Car("TestBrand", "TestModel");
        when(carManagerService.save(car)).thenReturn(car);

        ResponseEntity<Car> response = carController.createCar(car);

        assertEquals(car, response.getBody());
    }

    @Test
    void testGetAllCars() {
        when(carManagerService.getAllCars()).thenReturn(Collections.emptyList());

        assertEquals(0, carController.getAllCars().size());
    }

    @Test
    void testGetCarById() {
        Car car = new Car("TestBrand", "TestModel");
        when(carManagerService.getCarDetails(1L)).thenReturn(Optional.of(car));

        ResponseEntity<Car> response = carController.getCarById(1L);

        assertEquals(car, response.getBody());
    }
}