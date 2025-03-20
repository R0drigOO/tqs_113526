package test_containers;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeFlywayIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private EmployeeRepository employeeRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    @Order(1)
    void testInitialDataLoaded() {
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(4, employees.size(), "Should have loaded 4 employees with Flyway");
    }
    
    @Test
    @Order(2)
    void testInsertEmployee() {
        Employee employee = new Employee();
        employee.setName("Charlie");

        Employee result = employeeRepository.save(employee);
        assertNotNull(result.getId());
        assertEquals("Charlie", result.getName());
    }

    @Test
    @Order(3)
    void testRetrieveEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        assertFalse(employees.isEmpty());
        assertEquals(5, employees.size());
        assertEquals("Charlie", employees.get(4).getName());
    }
    
    @Test
    @Order(4)
    void testUpdateEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        Employee employee = employees.get(0);
        employee.setName("John Doe Updated");

        Employee result = employeeRepository.save(employee);
        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals("John Doe Updated", result.getName());
    }
    
    @Test
    @Order(5)
    void testRetrieveAfterUpdates() {
        List<Employee> employees = employeeRepository.findAll();
        assertFalse(employees.isEmpty());
        assertEquals(5, employees.size());
        assertEquals("John Doe Updated", employees.get(4).getName());
    }
}
