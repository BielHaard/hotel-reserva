package com.br.hotel.reserva.service.impl;

import com.br.hotel.reserva.model.Hotel;
import com.br.hotel.reserva.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

class NotificationServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotifyUser() {
        Reservation reservation = new Reservation();
        reservation.setCustomerEmail("customer@example.com");
        reservation.setCustomerName("John Doe");
        Hotel hotel = new Hotel();
        hotel.setName("Grand Hotel");
        reservation.setHotel(hotel);
        reservation.setCheckInDate(LocalDateTime.of(2024, 9, 10, 14, 0));
        reservation.setCheckOutDate(LocalDateTime.of(2024, 9, 15, 11, 0));

        notificationService.notifyUser(reservation);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo("customer@example.com");
        expectedMessage.setSubject("Confirmação da Reserva");
        expectedMessage.setText("""
                Olá John Doe,

                Sua reserva no hotel Grand Hotel foi confirmada.
                Detalhes da reserva:
                Data de Check-in: 2024-09-10T14:00
                Data de Check-out: 2024-09-15T11:00

                Obrigado por escolher nosso hotel!
                """);
    }

    @Test
    void testNotifyCancellation() {
        Reservation reservation = new Reservation();
        reservation.setCustomerEmail("customer@example.com");
        reservation.setCustomerName("Jane Doe");
        Hotel hotel = new Hotel();
        hotel.setName("Grand Hotel");
        reservation.setHotel(hotel);
        reservation.setCheckInDate(LocalDateTime.of(2024, 10, 1, 14, 0));
        reservation.setCheckOutDate(LocalDateTime.of(2024, 10, 7, 11, 0));

        notificationService.notifyCancellation(reservation);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo("customer@example.com");
        expectedMessage.setSubject("Cancelamento de Reserva");
        expectedMessage.setText("""
                Olá Jane Doe,

                Sua reserva no hotel Grand Hotel foi cancelada.
                Data de Check-in: 2024-10-01T14:00
                Data de Check-out: 2024-10-07T11:00

                Lamentamos o cancelamento e esperamos vê-lo em breve!
                """);

    }
}