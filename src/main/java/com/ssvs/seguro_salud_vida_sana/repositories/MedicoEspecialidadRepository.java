package com.ssvs.seguro_salud_vida_sana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.MedicoEspecialidad;

@Repository
public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {
    // Puedes agregar métodos de búsqueda personalizados si es necesario

    MedicoEspecialidad findByEspecialidadIdAndMedicoId(Long especialidadId, Long medicoId);

}
