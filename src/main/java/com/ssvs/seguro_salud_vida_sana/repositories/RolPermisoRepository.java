package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.RolPermiso;


@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso,Long> {
  // Métodos personalizados si se necesitan
  List<RolPermiso> findByRolId(Long rolId);
  
  @Modifying
  @Query("DELETE FROM RolPermiso rp WHERE rp.id = :id")
  void deleteById(@Param("id") Long id);

}
