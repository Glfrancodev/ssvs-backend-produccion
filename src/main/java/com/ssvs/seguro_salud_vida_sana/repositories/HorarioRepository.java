package com.ssvs.seguro_salud_vida_sana.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByMedicoEspecialidadId(Long medicoEspecialidadId);

    @Query("SELECT h FROM Horario h WHERE h.medicoEspecialidad.medico.id = :medicoId AND h.fecha >= :fecha")
    List<Horario> findHorariosByMedicoAndFechaAfter(@Param("medicoId") Long medicoId, @Param("fecha") LocalDate fecha);

    @Query("SELECT h FROM Horario h WHERE h.fecha >= :fechaActual")
    List<Horario> findAllValidHorarios(@Param("fechaActual") LocalDate fechaActual);
}

