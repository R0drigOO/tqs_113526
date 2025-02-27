package lab3_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    public ResponseEntity<Car> createCar(Car car) {
        return ResponseEntity.ok(carManagerService.save(car));
    }

    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    public ResponseEntity<Car> getCarById(Long id) {
        return ResponseEntity.ok(carManagerService.getCarDetails(id).get());
    }

}
