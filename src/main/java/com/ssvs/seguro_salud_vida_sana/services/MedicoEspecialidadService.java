package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.MedicoEspecialidad;
import com.ssvs.seguro_salud_vida_sana.repositories.MedicoEspecialidadRepository;

@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;

    // Crear una nueva relación MedicoEspecialidad
    public MedicoEspecialidad saveMedicoEspecialidad(MedicoEspecialidad medicoEspecialidad) {
        return medicoEspecialidadRepository.save(medicoEspecialidad);
    }

    // Obtener todas las relaciones MedicoEspecialidad
    public List<MedicoEspecialidad> getMedicosEspecialidades() {
        return medicoEspecialidadRepository.findAll();
    }

    // Buscar relación MedicoEspecialidad por ID
    public Optional<MedicoEspecialidad> getMedicoEspecialidadById(Long id) {
        return medicoEspecialidadRepository.findById(id);
    }

    // Eliminar relación MedicoEspecialidad
    public void deleteMedicoEspecialidad(Long id) {
        medicoEspecialidadRepository.deleteById(id);
    }
}
