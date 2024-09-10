package com.br.hotel.reserva.repository;

import com.br.hotel.reserva.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
}
