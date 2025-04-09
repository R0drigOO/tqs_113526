package hw1.services;

import hw1.entities.WeatherCacheEntry;
import hw1.repositories.WeatherCacheEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Mock
    private WeatherCacheEntryRepository weatherCacheEntryRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherForDay_CacheHit() {
        LocalDate day = LocalDate.of(2025, 4, 20);
        WeatherCacheEntry cached = new WeatherCacheEntry(day, 20.0, "Sunny", LocalDateTime.now().plusDays(1));

        when(weatherCacheEntryRepository.findByForecastDay(day)).thenReturn(Optional.of(cached));

        WeatherCacheEntry result = weatherService.getWeatherForDay(day);

        assertEquals(cached, result);
        assertEquals(1, weatherService.getCacheHits());
        assertEquals(0, weatherService.getCacheMisses());

        verify(weatherCacheEntryRepository, never()).save(any(WeatherCacheEntry.class));
    }

    @Test
    void testGetWeatherForDay_CacheMiss() {
        LocalDate day = LocalDate.of(2025, 4, 20);

        when(weatherCacheEntryRepository.findByForecastDay(day)).thenReturn(Optional.empty());

        WeatherCacheEntry result = weatherService.getWeatherForDay(day);

        assertNotNull(result);
        assertEquals(0, weatherService.getCacheHits());
        assertEquals(1, weatherService.getCacheMisses());

        verify(weatherCacheEntryRepository, times(1)).save(any(WeatherCacheEntry.class));
    }
}
