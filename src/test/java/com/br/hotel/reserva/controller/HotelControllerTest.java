package com.br.hotel.reserva.controller;


import com.br.hotel.reserva.dto.HotelSearchDTO;
import com.br.hotel.reserva.model.Hotel;
import com.br.hotel.reserva.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchHotels_Success() {
        HotelSearchDTO searchDTO = new HotelSearchDTO();
        searchDTO.setLocation("Test Location");

        Hotel hotel = new Hotel();

        List<Hotel> hotels = Arrays.asList(hotel);
        when(hotelService.findHotelsByLocation("Test Location")).thenReturn(new ResponseEntity<>(hotels, HttpStatus.OK));

        ResponseEntity<List<Hotel>> response = hotelController.searchHotels(searchDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(hotels);
    }

    @Test
    public void testSearchHotels_NoHotelsFound() {
        HotelSearchDTO searchDTO = new HotelSearchDTO();
        searchDTO.setLocation("Unknown Location");

        when(hotelService.findHotelsByLocation("Unknown Location")).thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND));

        ResponseEntity<List<Hotel>> response = hotelController.searchHotels(searchDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    public void testGetHotelById_Success() {
        Long hotelId = 1L;
        Hotel hotel = new Hotel();

        when(hotelService.findHotelById(hotelId)).thenReturn(new ResponseEntity<>(hotel, HttpStatus.OK));

        ResponseEntity<Hotel> response = hotelController.getHotelById(hotelId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(hotel);
    }

    @Test
    public void testGetHotelById_NotFound() {
        Long hotelId = 1L;

        when(hotelService.findHotelById(hotelId)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<Hotel> response = hotelController.getHotelById(hotelId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }
}