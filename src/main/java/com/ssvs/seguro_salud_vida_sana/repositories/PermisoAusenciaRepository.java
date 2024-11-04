package com.ssvs.seguro_salud_vida_sana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.PermisoAusencia;

@Repository
public interface PermisoAusenciaRepository extends JpaRepository<PermisoAusencia, Long> {
}
