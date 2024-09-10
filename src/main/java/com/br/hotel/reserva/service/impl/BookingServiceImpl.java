package com.br.hotel.reserva.service.impl;


import com.br.hotel.reserva.dto.BookingDTO;
import com.br.hotel.reserva.exception.CustomException;
import com.br.hotel.reserva.model.Reservation;
import com.br.hotel.reserva.model.Room;
import com.br.hotel.reserva.repository.ReservationRepository;
import com.br.hotel.reserva.repository.RoomRepository;
import com.br.hotel.reserva.service.BookingService;
import com.br.hotel.reserva.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final NotificationService notificationService;

    @Autowired
    public BookingServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, NotificationService notificationService) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
    }

    public ResponseEntity<Reservation> bookRoom(BookingDTO bookingDTO) {
        Room room = roomRepository.findById(bookingDTO.getRoomId()).orElseThrow(() -> new CustomException("Quarto não encontrado: " + bookingDTO.getRoomId()));

        Reservation reservation = Reservation.builder()
                        .room(room)
                        .hotel(room.getHotel())
                        .customerName(bookingDTO.getCustomerName())
                        .customerEmail(bookingDTO.getCustomerEmail())
                        .checkInDate(bookingDTO.getCheckIn())
                        .checkOutDate(bookingDTO.getCheckOut())
                        .confirmed(true)
                        .build();
        reservationRepository.save(reservation);

        notificationService.notifyUser(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    public ResponseEntity<String> cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException("Reserva não encontrada: " + reservationId));

        if (reservation.isCancelled()) {
            throw new CustomException("A reserva já foi cancelada.");
        }

        reservation.setCancelled(true);
        reservation.setConfirmed(false);
        reservationRepository.save(reservation);

        notificationService.notifyCancellation(reservation);

        return new ResponseEntity<>("Reserva cancelada com sucesso!", HttpStatus.OK);
    }
}

