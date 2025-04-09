package hw1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hw1.entities.WeatherCacheEntry;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherCacheEntryRepository extends JpaRepository<WeatherCacheEntry, Long> {
    Optional<WeatherCacheEntry> findByForecastDay(LocalDate forecastDay);
}
