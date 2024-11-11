package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Receta;
import com.ssvs.seguro_salud_vida_sana.repositories.RecetaRepository;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    // Crear o guardar una nueva receta
    public Receta saveReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    // Obtener todas las recetas
    public List<Receta> getRecetas() {
        return recetaRepository.findAll();
    }

    // Buscar receta por ID
    public Optional<Receta> getRecetaById(Long id) {
        return recetaRepository.findById(id);
    }

    // Eliminar receta
    public void deleteReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    public List<Receta> getRecetasByTratamientoId(Long tratamientoId) {
        return recetaRepository.findByTratamientoId(tratamientoId);
    }

}
