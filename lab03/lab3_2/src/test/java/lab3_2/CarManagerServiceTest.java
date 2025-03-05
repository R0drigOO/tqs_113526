package lab3_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarManagerServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarManagerService carManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carManagerService = new CarManagerService(carRepository); 
    }

    @Test
    void testSaveCar() {
        Car car = new Car("Tesla", "Model S");
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Car savedCar = carManagerService.save(car);

        assertEquals("Tesla", savedCar.getMaker());
        assertEquals("Model S", savedCar.getModel());
        verify(carRepository).save(car);
    }

    @Test
    void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(0, carManagerService.getAllCars().size());
    }

    @Test
    void testGetCarById() {
        Car car = new Car("BMW", "i8");
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Optional<Car> foundCar = carManagerService.getCarDetails(1L);

        assertEquals("BMW", foundCar.get().getMaker());
        assertEquals("i8", foundCar.get().getModel());
    }

    @Test
    void testFindSuitableReplacement() {
        Car car1 = new Car("BMW", "i8");
        car1.setId(1L);
        Car car2 = new Car("Tesla", "Model S");
        car2.setId(2L);
        Car car3 = new Car("BMW", "i8");
        car3.setId(3L);

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2, car3));

        Optional<Car> replacement = carManagerService.findSuitableReplacement(car1);
        assertTrue(replacement.isPresent());
        assertEquals("BMW", replacement.get().getMaker());
        assertEquals("i8", replacement.get().getModel());
        assertNotEquals(car1.getId(), replacement.get().getId());
    }
}