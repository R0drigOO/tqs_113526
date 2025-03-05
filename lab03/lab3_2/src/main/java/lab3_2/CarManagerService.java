package lab3_2;

import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
    //@Autowired
    private CarRepository carRepository;

    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id) {
        return carRepository.findById(id);
    }
    
    public Optional<Car> findSuitableReplacement(Car original) {
        List<Car> allCars = carRepository.findAll();
        for (Car car : allCars) {
            if (!car.getId().equals(original.getId()) 
            && car.getMaker().equals(original.getMaker())
            && car.getModel().equals(original.getModel())) {
                return Optional.of(car);
            }
        }
        
        return Optional.empty();
    }

}
