package hw1.services;

import hw1.entities.Reservation;
import hw1.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        Long mealId = 10L;
        LocalDate date = LocalDate.of(2025, 4, 15);
        String studentName = "Joao";

        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(inv -> inv.getArgument(0));

        Reservation r = reservationService.createReservation(mealId, date, studentName);

        assertNotNull(r.getReservationCode());
        assertEquals(mealId, r.getMealId());
        assertEquals(date, r.getDate());
        assertFalse(r.isUsed());

        verify(reservationRepository, times(1)).save(captor.capture());
        Reservation saved = captor.getValue();
        assertEquals(studentName, saved.getStudentName());
    }

    @Test
    void testUseReservation() {
        String code = "ABC123";

        Reservation existing = new Reservation();
        existing.setReservationCode(code);
        existing.setUsed(false);

        when(reservationRepository.findByReservationCode(code)).thenReturn(existing);

        boolean result = reservationService.useReservation(code);

        assertTrue(result);
        assertTrue(existing.isUsed());
        verify(reservationRepository, times(1)).save(existing);
    }

    @Test
    void testCancelReservation_NotFound() {
        String code = "NOTFOUND";
        when(reservationRepository.findByReservationCode(code)).thenReturn(null);

        boolean result = reservationService.cancelReservation(code);

        assertFalse(result);
        verify(reservationRepository, never()).delete(any(Reservation.class));
    }
}
