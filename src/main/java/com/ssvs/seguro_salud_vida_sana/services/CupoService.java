package com.ssvs.seguro_salud_vida_sana.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;
import com.ssvs.seguro_salud_vida_sana.repositories.CupoRepository;

@Service
public class CupoService {

    @Autowired
    private CupoRepository cupoRepository;

    // Crear un nuevo cupo
    public Cupo saveCupo(Cupo cupo) {
        return cupoRepository.save(cupo);
    }

    // Obtener todos los cupos
    public List<Cupo> getCupos() {
        return cupoRepository.findAll();
    }

    // Buscar cupo por ID
    public Optional<Cupo> getCupoById(Long id) {
        return cupoRepository.findById(id);
    }

    // Eliminar cupo
    public void deleteCupo(Long id) {
        cupoRepository.deleteById(id);
    }

    public List<Cupo> findCuposByHorarioId(Long horarioId) {
        return cupoRepository.findByHorarioId(horarioId);
    }

    // Obtener cupos ocupados por horario ID
    public List<Cupo> findCuposOcupadosByHorarioId(Long horarioId) {
        return cupoRepository.findByHorarioIdAndEstado(horarioId, "Ocupado");
    }

    public List<Cupo> obtenerCuposPorAsegurado(Long aseguradoId) {
        return cupoRepository.findByAseguradoId(aseguradoId);
    }

    public boolean existeCupoParaFechaActual(Long aseguradoId, LocalDate fecha) {
        return cupoRepository.existsByAseguradoIdAndFechaReservado(aseguradoId, fecha);
    }


}
