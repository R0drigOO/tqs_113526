package lab3_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testSaveAndFindById() {
        Car car = new Car("Fiat", "Punto");
        Car savedCar = carRepository.save(car);

        Optional<Car> foundCar = carRepository.findById(savedCar.getId());
        assertEquals("Fiat", foundCar.get().getMaker());
        assertEquals("Punto", foundCar.get().getModel());
    }
}