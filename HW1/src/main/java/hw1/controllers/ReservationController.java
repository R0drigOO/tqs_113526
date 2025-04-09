package hw1.controllers;

import hw1.dtos.CreateReservationRequest;
import hw1.entities.Reservation;
import hw1.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody CreateReservationRequest request) {
        return reservationService.createReservation(
                request.getMealId(),
                request.getDate(),
                request.getStudentName()
        );
    }

    @GetMapping("/{reservationCode}")
    public Reservation getReservation(@PathVariable String reservationCode) {
        return reservationService.getReservationByCode(reservationCode);
    }

    @PatchMapping("/{reservationCode}/use")
    public String useReservation(@PathVariable String reservationCode) {
        boolean used = reservationService.useReservation(reservationCode);
        return used ? "Reserva usada com sucesso!" : "Reserva inválida ou já utilizada.";
    }

    @DeleteMapping("/{reservationCode}")
    public String cancelReservation(@PathVariable String reservationCode) {
        boolean canceled = reservationService.cancelReservation(reservationCode);
        return canceled ? "Reserva cancelada com sucesso!" : "Reserva não encontrada.";
    }
}
