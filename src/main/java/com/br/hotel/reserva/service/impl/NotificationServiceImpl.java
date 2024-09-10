package com.br.hotel.reserva.service.impl;

import com.br.hotel.reserva.model.Reservation;
import com.br.hotel.reserva.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void notifyUser(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservation.getCustomerEmail());
        message.setSubject("Confirmação da Reserva");
        message.setText("""
                Olá %s,

                Sua reserva no hotel %s foi confirmada.
                Detalhes da reserva:
                Data de Check-in: %s
                Data de Check-out: %s

                Obrigado por escolher nosso hotel!
                """.formatted(
                        reservation.getCustomerName(),
                        reservation.getHotel().getName(),
                        reservation.getCheckInDate(),
                        reservation.getCheckOutDate()
                )
        );

        mailSender.send(message);
    }

    public void notifyCancellation(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservation.getCustomerEmail());
        message.setSubject("Cancelamento de Reserva");
        message.setText("""
                Olá %s,

                Sua reserva no hotel %s foi cancelada.
                Data de Check-in: %s
                Data de Check-out: %s

                Lamentamos o cancelamento e esperamos vê-lo em breve!
                """.formatted(
                        reservation.getCustomerName(),
                        reservation.getHotel().getName(),
                        reservation.getCheckInDate(),
                        reservation.getCheckOutDate()
                )
        );

        mailSender.send(message);
    }
}
