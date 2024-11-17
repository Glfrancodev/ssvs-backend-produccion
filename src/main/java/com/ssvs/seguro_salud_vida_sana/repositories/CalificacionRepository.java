package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ssvs.seguro_salud_vida_sana.models.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    // Obtener todas las calificaciones por un rango de estrellas
    List<Calificacion> findByEstrellaBetween(int min, int max);

    // Contar calificaciones por número de estrellas
    Long countByEstrella(int estrella);

    @Query("SELECT AVG(c.estrella) FROM Calificacion c WHERE c.medico.id = :medicoId")
    Double obtenerPromedioCalificacionPorMedicoId(@Param("medicoId") Long medicoId);

    @Query("SELECT c FROM Calificacion c WHERE c.asegurado.id = :aseguradoId AND c.medico.id = :medicoId")
    Calificacion findByAseguradoIdAndMedicoId(@Param("aseguradoId") Long aseguradoId, @Param("medicoId") Long medicoId);
    

}
