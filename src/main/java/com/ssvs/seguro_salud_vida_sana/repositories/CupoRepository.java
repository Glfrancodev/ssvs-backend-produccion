package com.ssvs.seguro_salud_vida_sana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;

@Repository
public interface CupoRepository extends JpaRepository<Cupo, Long> {
    // Puedes agregar métodos de búsqueda personalizados si es necesario
}
