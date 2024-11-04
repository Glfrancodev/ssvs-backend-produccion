package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Especialidad;
import com.ssvs.seguro_salud_vida_sana.repositories.EspecialidadRepository;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    // Crear una nueva especialidad
    public Especialidad saveEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    // Obtener todas las especialidades
    public List<Especialidad> getEspecialidades() {
        return especialidadRepository.findAll();
    }

    // Buscar especialidad por ID
    public Optional<Especialidad> getEspecialidadById(Long id) {
        return especialidadRepository.findById(id);
    }

    // Buscar especialidad por nombre
    public Optional<Especialidad> getEspecialidadByNombre(String nombre) {
        return especialidadRepository.findByNombre(nombre);
    }

    // Eliminar especialidad
    public void deleteEspecialidad(Long id) {
        especialidadRepository.deleteById(id);
    }
}
