package com.br.hotel.reserva.service;

import com.br.hotel.reserva.model.Hotel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {
    ResponseEntity<List<Hotel>> findHotelsByLocation(String location);
    ResponseEntity<Hotel> findHotelById(Long id);
}
