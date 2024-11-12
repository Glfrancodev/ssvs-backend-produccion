package com.ssvs.seguro_salud_vida_sana.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;

@Repository
public interface CupoRepository extends JpaRepository<Cupo, Long> {
    // Puedes agregar métodos de búsqueda personalizados si es necesario
    List<Cupo> findByHorarioId(Long horarioId);
    List<Cupo> findByHorarioIdAndEstado(Long horarioId, String estado);
    List<Cupo> findByAseguradoId(Long aseguradoId);
    boolean existsByAseguradoIdAndFechaReservado(Long aseguradoId, LocalDate fechaReservado);
}
