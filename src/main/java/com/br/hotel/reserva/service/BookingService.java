package com.br.hotel.reserva.service;

import com.br.hotel.reserva.dto.BookingDTO;
import com.br.hotel.reserva.model.Reservation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface BookingService {
    ResponseEntity<Reservation> bookRoom(BookingDTO bookingDTO);

    ResponseEntity<String> cancelReservation(Long id);
}
