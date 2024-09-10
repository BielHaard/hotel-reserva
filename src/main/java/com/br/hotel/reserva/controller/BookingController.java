package com.br.hotel.reserva.controller;

import com.br.hotel.reserva.dto.BookingDTO;
import com.br.hotel.reserva.model.Reservation;
import com.br.hotel.reserva.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Reserva um quarto", description = "Cria uma nova reserva de quarto no sistema para um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados da requisição")
    })
    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserveRoom(@RequestBody BookingDTO bookingDTO) {
        return bookingService.bookRoom(bookingDTO);
    }

    @Operation(summary = "Cancela uma reserva", description = "Cancela uma reserva existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva cancelada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        return bookingService.cancelReservation(id);
    }
}
