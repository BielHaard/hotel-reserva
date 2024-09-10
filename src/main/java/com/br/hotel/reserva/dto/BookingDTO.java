package com.br.hotel.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long roomId;
    private String customerName;
    private String customerEmail;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
