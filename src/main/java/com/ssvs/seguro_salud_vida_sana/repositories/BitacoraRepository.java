package com.ssvs.seguro_salud_vida_sana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Bitacora;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    
    List<Bitacora> findByCorreo(String correo);

    List<Bitacora> findByFecha(LocalDate fecha);

    List<Bitacora> findByCorreoAndFecha(String correo, LocalDate fecha);
    
    List<Bitacora> findByFechaBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT b FROM Bitacora b WHERE b.fecha BETWEEN :startDate AND :endDate")
    List<Bitacora> findBitacoraByFechaRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
