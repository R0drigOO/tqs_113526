**3.1 Employee management (getting started example)**
a) Tests that use AssertThat from AssertJ:
- All tests from A_EmployeeRepositoryTest.java
- ALl tests from B_EmployeeService_UnitTest.java
- whenValidInput_thenCreateEmployee test from D_EmployeeRestControllerIT.java
- All tests from E_EmployeeRestControllerTemplateIT

b) REVER!
- @Transactional: This annotation ensures that each test method is run within a transaction that is rolled back after the test completes. This helps to keep the database in a consistent state between tests.
- @AutoConfigureTestDatabase: This annotation configures an embedded test database to be used instead of the application’s real database. This helps to isolate tests from the production database.
- @AutoConfigureTestEntityManager: Provides an alternative to the standard JPA EntityManager specifically designed for tests.

c)
An example of mocking the EmployeeRepository to avoid using a real database is in the @BeforeEach method of B_EmployeeService_UnitTest. For instance:
This tells the repository to return a mock Employee object (instead of accessing a real database).

d)
- @Mock (from Mockito) is used to create a mock object in plain unit tests, without needing a Spring context.
- @MockBean (from Spring Boot) creates a mock bean within the Spring application context, often used in tests that rely on Spring’s auto-configuration or application context.

e)
The application-integrationtest.properties file provides environment-specific properties for integration tests, usually activated by the "integrationtest" profile. When you run tests with that profile (@ActiveProfiles("integrationtest") or a similar mechanism), Spring will load properties from that file instead of (or alongside) the default application.properties.

f)
C (Controller-only tests with @WebMvcTest):
Loads a minimal web context focusing on the controller layer.
Dependencies (like services) are mocked (@MockBean).
Uses MockMvc to simulate requests.

D (Integration test with full Spring Boot context, MockMvc:
Loads the entire application context (@SpringBootTest).
Routes requests through the real service and repository layers.
Still uses MockMvc for a server-side testing API.

E (Integration test with full Spring Boot context, TestRestTemplate):
Loads the entire application context (@SpringBootTest).
Uses an actual client (TestRestTemplate) to send real HTTP requests.
Tests the complete request-response cycle, including serialization and deserialization.