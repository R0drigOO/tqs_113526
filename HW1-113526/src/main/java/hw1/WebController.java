package hw1;

import hw1.entities.Meal;
import hw1.entities.Reservation;
import hw1.services.MealService;
import hw1.services.ReservationService;
import hw1.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MealService mealService;
    
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ReservationService reservationService;

    // ========== 1) Página inicial (/restaurants) ==========
    /**
     * GET /restaurants
     * Mostra um formulário para selecionar o ID do restaurante,
     * data inicial e data final.
     */
    @GetMapping("/restaurants")
    public String showRestaurantSelection() {
        // "index" é o nome do template (index.html)
        return "index";
    }

    /**
     * POST /restaurants
     * Recebe o ID do restaurante e datas, redireciona para a lista de refeições
     */
    @PostMapping("/restaurants")
    public String processRestaurantSelection(
            @RequestParam Long restaurantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        // Redireciona para /meals?restaurantId=...&startDate=...&endDate=...
        return "redirect:/meals?restaurantId=" + restaurantId
                + "&startDate=" + startDate
                + "&endDate=" + endDate;
    }

    // ========== 2) Página de refeições e tempo (/meals) ==========
    /**
     * GET /meals?restaurantId=1&startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
     */
    @GetMapping("/meals")
    public String showMeals(
            @RequestParam Long restaurantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        // Busca as refeições
        List<Meal> meals = mealService.getMeals(restaurantId, startDate, endDate);
        // Para cada dia do intervalo, poderíamos mostrar a previsão
        // (depende de como você quer estruturar).
        // Vamos supor que para cada Meal (com data X) pegamos a previsão de X:
        // Ou, se quiser, exiba apenas a data de cada meal + weather do dia.

        model.addAttribute("meals", meals);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        // Retorna o template meals.html
        return "meals";
    }

    // ========== 3) Página de reserva (formulário) ==========
    /**
     * GET /reservations/new
     * Exibe o formulário de reserva.
     */
    @GetMapping("/reservations/new")
    public String showReservationForm() {
        // Retorna o template reservation-form.html
        return "reservation-form";
    }

    /**
     * POST /reservations/new
     * Cria a reserva.
     */
    @PostMapping("/reservations/new")
    public String createReservation(
            @RequestParam Long mealId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String studentName,
            Model model
    ) {
        Reservation reservation = reservationService.createReservation(mealId, date, studentName);
        // Podemos redirecionar para a página de detalhes da reserva
        return "redirect:/reservations/" + reservation.getReservationCode();
    }

    // ========== 4) Página de detalhes da reserva ==========
    /**
     * GET /reservations/{code}
     * Mostra detalhes de uma reserva (e dá a opção de cancelar).
     */
    @GetMapping("/reservations/{reservationCode}")
    public String showReservationDetails(@PathVariable String reservationCode, Model model) {
        Reservation reservation = reservationService.getReservationByCode(reservationCode);
        model.addAttribute("reservation", reservation);
        return "reservation-details";
    }

    /**
     * POST /reservations/{code}/cancel
     * Cancela a reserva (opcional).
     */
    @PostMapping("/reservations/{reservationCode}/cancel")
    public String cancelReservation(@PathVariable String reservationCode) {
        reservationService.cancelReservation(reservationCode);
        // Redireciona para alguma página (p. ex. a inicial)
        return "redirect:/restaurants";
    }

    // ========== 5) Página de verificação (staff) ==========
    /**
     * GET /reservations/check
     * Exibe o form para inserir o código da reserva e marcar como usada.
     */
    @GetMapping("/reservations/check")
    public String showCheckReservation() {
        return "check-reservation";
    }

    /**
     * POST /reservations/check
     * Recebe o código, marca como usado (ou não) e exibe o resultado.
     */
    @PostMapping("/reservations/check")
    public String processCheckReservation(@RequestParam String reservationCode, Model model) {
        boolean used = reservationService.useReservation(reservationCode);
        model.addAttribute("reservationCode", reservationCode);
        model.addAttribute("used", used);
        return "check-reservation";
    }

}
