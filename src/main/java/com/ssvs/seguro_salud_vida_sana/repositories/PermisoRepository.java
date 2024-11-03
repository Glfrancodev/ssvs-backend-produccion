package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso,Long> {
  // Métodos personalizados si se necesitan
  Optional<Permiso> findByNombre(String nombre);

}
