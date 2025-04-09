package hw1.services;

import hw1.entities.WeatherCacheEntry;
import hw1.repositories.WeatherCacheEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherCacheEntryRepository weatherCacheEntryRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private AtomicInteger cacheHits = new AtomicInteger(0);
    private AtomicInteger cacheMisses = new AtomicInteger(0);

    private static final long CACHE_TTL_MINUTES = 60;

    public WeatherCacheEntry getWeatherForDay(LocalDate day) {
        Optional<WeatherCacheEntry> optEntry = weatherCacheEntryRepository.findByForecastDay(day);

        if (optEntry.isPresent()) {
            WeatherCacheEntry entry = optEntry.get();
            if (LocalDateTime.now().isBefore(entry.getExpiresAt())) {
                cacheHits.incrementAndGet();
                logger.debug("Cache HIT for day={}. ExpiresAt={}", day, entry.getExpiresAt());
                return entry;
            } else {
                logger.debug("Cache EXPIRED for day={}. Removing entry...", day);
                weatherCacheEntryRepository.delete(entry);
            }
        }
        cacheMisses.incrementAndGet();
        logger.debug("Cache MISS for day={}. Will fetch from external API...", day);

        WeatherCacheEntry freshEntry = callExternalWeatherApi(day);
        weatherCacheEntryRepository.save(freshEntry);
        logger.info("Saved new weather cache entry for day={}. ExpiresAt={}", day, freshEntry.getExpiresAt());

        return freshEntry;
    }

    private WeatherCacheEntry callExternalWeatherApi(LocalDate day) {
        logger.info("Fetching weather data from external API for day={}", day);

        return new WeatherCacheEntry(day, 20.0, "Sunny", LocalDateTime.now().plusMinutes(CACHE_TTL_MINUTES));
    }

    public int getCacheHits() {
        return cacheHits.get();
    }

    public int getCacheMisses() {
        return cacheMisses.get();
    }
}
