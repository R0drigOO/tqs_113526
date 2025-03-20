package test_containers;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class EmployeeIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private EmployeeRepository employeeRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    @Order(1)
    void testInsertEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        Employee result = employeeRepository.save(employee);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("John Doe", result.getName());
    }
    
    @Test
    @Order(2)
    void testRetrieveEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    @Order(3)
    void testUpdatemployee() {
        List<Employee> employees = employeeRepository.findAll();
        Employee employee = employees.get(0);
        employee.setName("John Doe Updated");

        Employee result = employeeRepository.save(employee);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals("John Doe Updated", result.getName());
    }

    @Test
    @Order(4)
    void testRetrieveAfterUpdateEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
        assertEquals("John Doe Updated", employees.get(0).getName());
    }
}