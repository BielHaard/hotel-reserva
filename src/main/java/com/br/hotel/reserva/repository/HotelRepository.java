package com.br.hotel.reserva.repository;

import com.br.hotel.reserva.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("""
    SELECT h FROM Hotel h Where h.location = ?1
    """)
    List<Hotel> findByLocation(String location);

}
