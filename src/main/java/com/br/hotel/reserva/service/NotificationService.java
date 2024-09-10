package com.br.hotel.reserva.service;

import com.br.hotel.reserva.model.Reservation;

public interface NotificationService {
    void notifyUser(Reservation reservation);

    void notifyCancellation(Reservation reservation);
}
