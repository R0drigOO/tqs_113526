package hw1.controllers;

import hw1.entities.WeatherCacheEntry;
import hw1.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{day}")
    public WeatherCacheEntry getWeather(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day) {
        return weatherService.getWeatherForDay(day);
    }

    @GetMapping("/cache")
    public Map<String, Integer> getCacheStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("hits", weatherService.getCacheHits());
        stats.put("misses", weatherService.getCacheMisses());
        return stats;
    }
}
