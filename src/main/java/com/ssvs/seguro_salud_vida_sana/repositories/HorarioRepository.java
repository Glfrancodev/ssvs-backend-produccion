package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    // Puedes agregar métodos de búsqueda personalizados si es necesario
    List<Horario> findByMedicoEspecialidadId(Long medicoEspecialidadId);
}
