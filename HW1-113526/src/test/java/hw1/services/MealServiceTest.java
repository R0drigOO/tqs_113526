package hw1.services;

import hw1.entities.Meal;
import hw1.repositories.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMealsWithinDateRange() {
        Long restaurantId = 1L;
        LocalDate start = LocalDate.of(2025, 4, 1);
        LocalDate end = LocalDate.of(2025, 4, 5);

        Meal meal1 = new Meal();
        meal1.setId(100L);
        meal1.setName("Meal1");
        meal1.setDate(LocalDate.of(2025, 4, 1));

        Meal meal2 = new Meal();
        meal2.setId(101L);
        meal2.setName("Meal2");
        meal2.setDate(LocalDate.of(2025, 4, 3));

        when(mealRepository.findByRestaurantIdAndDateBetween(restaurantId, start, end))
                .thenReturn(Arrays.asList(meal1, meal2));

        List<Meal> result = mealService.getMeals(restaurantId, start, end);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Meal1", result.get(0).getName());
        verify(mealRepository, times(1))
                .findByRestaurantIdAndDateBetween(restaurantId, start, end);
    }
}
