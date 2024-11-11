package com.ssvs.seguro_salud_vida_sana.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Horario;
import com.ssvs.seguro_salud_vida_sana.models.PermisoAusencia;
import com.ssvs.seguro_salud_vida_sana.repositories.HorarioRepository;
import com.ssvs.seguro_salud_vida_sana.repositories.PermisoAusenciaRepository;

@Service
public class PermisoAusenciaService {

    @Autowired
    private PermisoAusenciaRepository permisoAusenciaRepository;

    // Crear un nuevo permiso de ausencia
    public PermisoAusencia savePermisoAusencia(PermisoAusencia permisoAusencia) {
        return permisoAusenciaRepository.save(permisoAusencia);
    }

    // Obtener todos los permisos de ausencia
    public List<PermisoAusencia> getPermisosAusencia() {
        return permisoAusenciaRepository.findAll();
    }

    // Buscar permiso de ausencia por ID
    public Optional<PermisoAusencia> getPermisoAusenciaById(Long id) {
        return permisoAusenciaRepository.findById(id);
    }

    // Eliminar permiso de ausencia
    public void deletePermisoAusencia(Long id) {
        permisoAusenciaRepository.deleteById(id);
    }

}
