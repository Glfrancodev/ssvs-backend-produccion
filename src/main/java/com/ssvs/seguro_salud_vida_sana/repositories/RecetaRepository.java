package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssvs.seguro_salud_vida_sana.models.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByTratamientoId(Long tratamientoId);
}
