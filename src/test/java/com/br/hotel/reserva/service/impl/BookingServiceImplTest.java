package com.br.hotel.reserva.service.impl;

import com.br.hotel.reserva.dto.BookingDTO;
import com.br.hotel.reserva.exception.CustomException;
import com.br.hotel.reserva.model.Reservation;
import com.br.hotel.reserva.repository.ReservationRepository;
import com.br.hotel.reserva.repository.RoomRepository;
import com.br.hotel.reserva.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookRoom_RoomNotFound() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setRoomId(1L);

        when(roomRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        CustomException thrown = assertThrows(CustomException.class, () -> {
            bookingService.bookRoom(bookingDTO);
        });

        assertEquals("Quarto não encontrado: 1", thrown.getMessage());
    }

    @Test
    void testCancelReservation_Success() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setCancelled(false);

        when(reservationRepository.findById(any(Long.class))).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<String> response = bookingService.cancelReservation(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reserva cancelada com sucesso!", response.getBody());
        verify(notificationService, times(1)).notifyCancellation(reservation);
    }

    @Test
    void testCancelReservation_NotFound() {
        when(reservationRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        CustomException thrown = assertThrows(CustomException.class, () -> {
            bookingService.cancelReservation(1L);
        });

        assertEquals("Reserva não encontrada: 1", thrown.getMessage());
    }

    @Test
    void testCancelReservation_AlreadyCancelled() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setCancelled(true);

        when(reservationRepository.findById(any(Long.class))).thenReturn(Optional.of(reservation));

        CustomException thrown = assertThrows(CustomException.class, () -> {
            bookingService.cancelReservation(1L);
        });

        assertEquals("A reserva já foi cancelada.", thrown.getMessage());
    }
}