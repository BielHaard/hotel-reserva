package com.br.hotel.reserva.controller;

import com.br.hotel.reserva.dto.BookingDTO;
import com.br.hotel.reserva.model.Reservation;
import com.br.hotel.reserva.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReserveRoom_Success() {
        BookingDTO bookingDTO = new BookingDTO();

        Reservation reservation = new Reservation();

        when(bookingService.bookRoom(any(BookingDTO.class)))
                .thenReturn(new ResponseEntity<>(reservation, HttpStatus.OK));

        ResponseEntity<Reservation> response = bookingController.reserveRoom(bookingDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(reservation);
    }

    @Test
    public void testReserveRoom_BadRequest() {
        BookingDTO bookingDTO = new BookingDTO();

        when(bookingService.bookRoom(any(BookingDTO.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        ResponseEntity<Reservation> response = bookingController.reserveRoom(bookingDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testCancelReservation_Success() {
        Long reservationId = 1L;

        when(bookingService.cancelReservation(anyLong()))
                .thenReturn(new ResponseEntity<>("Reserva cancelada com sucesso", HttpStatus.OK));

        ResponseEntity<String> response = bookingController.cancelReservation(reservationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Reserva cancelada com sucesso");
    }

    @Test
    public void testCancelReservation_NotFound() {
        Long reservationId = 1L;

        when(bookingService.cancelReservation(anyLong()))
                .thenReturn(new ResponseEntity<>("Reserva não encontrada", HttpStatus.NOT_FOUND));

        ResponseEntity<String> response = bookingController.cancelReservation(reservationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Reserva não encontrada");
    }

}