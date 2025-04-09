package hw1.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "weather_cache_entry")
public class WeatherCacheEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate forecastDay;
    private Double temperature;
    private String description;

    private LocalDateTime expiresAt;

    // Construtores
    public WeatherCacheEntry() {
    }

    public WeatherCacheEntry(LocalDate forecastDay, Double temperature, String description, LocalDateTime expiresAt) {
        this.forecastDay = forecastDay;
        this.temperature = temperature;
        this.description = description;
        this.expiresAt = expiresAt;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public LocalDate getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(LocalDate forecastDay) {
        this.forecastDay = forecastDay;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
