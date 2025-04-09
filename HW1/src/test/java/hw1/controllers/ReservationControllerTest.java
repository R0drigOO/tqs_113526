package hw1.controllers;

import hw1.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testCreateReservation() throws Exception {
        reservationRepository.deleteAll();

        String jsonBody = """
                {
                  "mealId": 10,
                  "date": "2025-04-15",
                  "studentName": "Joao"
                }
                """;

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationCode").exists())
                .andExpect(jsonPath("$.mealId").value(10))
                .andExpect(jsonPath("$.studentName").value("Joao"));
    }

    @Test
    void testGetReservation_NotFound() throws Exception {
        mockMvc.perform(get("/api/reservations/INVALID_CODE"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
