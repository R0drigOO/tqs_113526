package lab3_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

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
}