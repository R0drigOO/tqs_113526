package hw1.services;

import hw1.entities.Reservation;
import hw1.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Long mealId, LocalDate date, String studentName) {
        String generatedCode = UUID.randomUUID().toString().substring(0, 8);

        Reservation reservation = new Reservation();
        reservation.setReservationCode(generatedCode);
        reservation.setMealId(mealId);
        reservation.setDate(date);
        reservation.setStudentName(studentName);
        reservation.setUsed(false);

        logger.info("Creating reservation for mealId={}, date={}, studentName={}, code={}", 
                     mealId, date, studentName, generatedCode);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservationByCode(String reservationCode) {
        logger.debug("Fetching reservation with code={}", reservationCode);
        return reservationRepository.findByReservationCode(reservationCode);
    }

    public boolean cancelReservation(String reservationCode) {
        logger.info("Attempting to cancel reservation with code={}", reservationCode);
        Reservation reservation = reservationRepository.findByReservationCode(reservationCode);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            logger.info("Reservation code={} canceled successfully", reservationCode);
            return true;
        }
        logger.warn("Reservation code={} not found. Cancel failed", reservationCode);
        return false;
    }

    public boolean useReservation(String reservationCode) {
        logger.info("Checking-in (use) reservation with code={}", reservationCode);
        Reservation reservation = reservationRepository.findByReservationCode(reservationCode);
        if (reservation != null && !reservation.isUsed()) {
            reservation.setUsed(true);
            reservationRepository.save(reservation);
            logger.info("Reservation code={} marked as used", reservationCode);
            return true;
        }
        logger.warn("Reservation code={} is invalid or already used", reservationCode);
        return false;
    }
}
