package com.br.hotel.reserva.controller;

import com.br.hotel.reserva.dto.HotelSearchDTO;
import com.br.hotel.reserva.model.Hotel;
import com.br.hotel.reserva.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Pesquisar hotéis", description = "Retorna uma lista de hotéis com base na localização.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de hotéis retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum hotel encontrado para a localização fornecida")
    })
    @PostMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(@RequestBody HotelSearchDTO searchDTO) {
        return hotelService.findHotelsByLocation(searchDTO.getLocation());
    }

    @Operation(summary = "Obter detalhes de um hotel", description = "Retorna os detalhes de um hotel específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes do hotel retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.findHotelById(id);
    }
}