package hw1.services;

import hw1.entities.Meal;
import hw1.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getMeals(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        return mealRepository.findByRestaurantIdAndDateBetween(restaurantId, startDate, endDate);
    }

}
