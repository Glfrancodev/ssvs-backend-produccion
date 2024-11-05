package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.HistoriaClinica;
import com.ssvs.seguro_salud_vida_sana.repositories.HistoriaClinicaRepository;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    // Crear una nueva historia clínica
    public HistoriaClinica saveHistoriaClinica(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    // Obtener todas las historias clínicas
    public List<HistoriaClinica> getHistoriasClinicas() {
        return historiaClinicaRepository.findAll();
    }

    // Buscar historia clínica por ID
    public Optional<HistoriaClinica> getHistoriaClinicaById(Long id) {
        return historiaClinicaRepository.findById(id);
    }

    // Eliminar historia clínica
    public void deleteHistoriaClinica(Long id) {
        historiaClinicaRepository.deleteById(id);
    }
}
