package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Calificacion;
import com.ssvs.seguro_salud_vida_sana.repositories.CalificacionRepository;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    // Crear una nueva calificación
    public Calificacion saveCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    // Obtener todas las calificaciones
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    // Obtener calificaciones por rango de estrellas
    public List<Calificacion> getCalificacionesPorRango(int min, int max) {
        return calificacionRepository.findByEstrellaBetween(min, max);
    }

    // Contar calificaciones por número de estrellas
    public Long contarCalificacionesPorEstrella(int estrella) {
        return calificacionRepository.countByEstrella(estrella);
    }

    // Eliminar calificación por ID
    public void deleteCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    public Calificacion buscarCalificacionPorAseguradoYMedico(Long aseguradoId, Long medicoId) {
        return calificacionRepository.findByAseguradoIdAndMedicoId(aseguradoId, medicoId);
    }

}
