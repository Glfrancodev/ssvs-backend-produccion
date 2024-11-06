package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Tratamiento;
import com.ssvs.seguro_salud_vida_sana.repositories.TratamientoRepository;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    // Crear o guardar un nuevo tratamiento
    public Tratamiento saveTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    // Obtener todos los tratamientos
    public List<Tratamiento> getTratamientos() {
        return tratamientoRepository.findAll();
    }

    // Buscar tratamiento por ID
    public Optional<Tratamiento> getTratamientoById(Long id) {
        return tratamientoRepository.findById(id);
    }

    // Eliminar tratamiento
    public void deleteTratamiento(Long id) {
        tratamientoRepository.deleteById(id);
    }
}
