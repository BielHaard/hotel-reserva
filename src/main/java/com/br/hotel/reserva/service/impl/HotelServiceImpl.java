package com.br.hotel.reserva.service.impl;

import com.br.hotel.reserva.exception.CustomException;
import com.br.hotel.reserva.model.Hotel;
import com.br.hotel.reserva.repository.HotelRepository;
import com.br.hotel.reserva.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public ResponseEntity<List<Hotel>> findHotelsByLocation(String location) {
        List<Hotel> hotels = hotelRepository.findByLocation(location);

        if (hotels.isEmpty()) {
            throw new CustomException("Nenhum hotel encontrado para a localização: " + location);
        }

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    public ResponseEntity<Hotel> findHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new CustomException("Hotel não encontrado: " + id));
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
}