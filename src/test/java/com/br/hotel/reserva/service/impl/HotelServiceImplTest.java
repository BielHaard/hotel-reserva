package com.br.hotel.reserva.service.impl;

import com.br.hotel.reserva.exception.CustomException;
import com.br.hotel.reserva.model.Hotel;
import com.br.hotel.reserva.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindHotelsByLocationSuccess() {
        String location = "Paris";
        Hotel hotel1 = new Hotel(1L, "Hotel Paris", location, null);
        Hotel hotel2 = new Hotel(2L, "Hotel Deluxe Paris", location, null);
        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);
        when(hotelRepository.findByLocation(location)).thenReturn(hotels);

        ResponseEntity<List<Hotel>> response = hotelService.findHotelsByLocation(location);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotels, response.getBody());
    }

    @Test
    void testFindHotelsByLocationThrowsException() {
        String location = "Unknown Location";
        when(hotelRepository.findByLocation(location)).thenReturn(Arrays.asList());

        CustomException thrown = assertThrows(CustomException.class, () -> hotelService.findHotelsByLocation(location));
        assertEquals("Nenhum hotel encontrado para a localização: " + location, thrown.getMessage());
    }

    @Test
    void testFindHotelByIdSuccess() {
        Long hotelId = 1L;
        Hotel hotel = new Hotel(hotelId, "Hotel Paris", "Paris", null);
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        ResponseEntity<Hotel> response = hotelService.findHotelById(hotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotel, response.getBody());
    }

    @Test
    void testFindHotelByIdThrowsException() {
        Long hotelId = 1L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        CustomException thrown = assertThrows(CustomException.class, () -> hotelService.findHotelById(hotelId));
        assertEquals("Hotel não encontrado: " + hotelId, thrown.getMessage());
    }
}
