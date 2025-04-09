package hw1.functional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReservationUITest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testRestaurantSelectionAndMealListing() throws InterruptedException {
        driver.get("http://localhost:8080/restaurants");

        WebElement restaurantIdInput = driver.findElement(By.id("restaurantId"));
        restaurantIdInput.sendKeys("1");

        WebElement startDateInput = driver.findElement(By.id("startDate"));
        startDateInput.sendKeys("04-01-2025");

        WebElement endDateInput = driver.findElement(By.id("endDate"));
        endDateInput.sendKeys("04-05-2025");

        WebElement verRefeicoesButton = driver.findElement(By.xpath("//button[text()='Ver refeições']"));
        verRefeicoesButton.click();

        Thread.sleep(1000);

        assertTrue(driver.getCurrentUrl().contains("/meals?restaurantId=1"));
        WebElement table = driver.findElement(By.tagName("table"));
        assertNotNull(table);
    }
}
