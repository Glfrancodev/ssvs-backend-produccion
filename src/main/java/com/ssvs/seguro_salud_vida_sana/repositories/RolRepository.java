package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long>{
  // Medotodos personalizados si se necesitan
  Optional<Rol> findByNombre(String nombre);

}
