package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Especialidad;
import com.ssvs.seguro_salud_vida_sana.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
      @Query("SELECT me.especialidad FROM MedicoEspecialidad me JOIN me.medico m WHERE m.usuario.correo = :correo")
    List<Especialidad> findEspecialidadesByCorreo(@Param("correo") String correo);

    Optional<Medico> findByUsuarioCorreo(String correo);
}
