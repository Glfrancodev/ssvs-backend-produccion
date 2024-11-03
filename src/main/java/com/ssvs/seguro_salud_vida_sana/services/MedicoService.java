package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Medico;
import com.ssvs.seguro_salud_vida_sana.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // Crear un nuevo medico
    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // Obtener todos los medicos
    public List<Medico> getMedicos() {
        return medicoRepository.findAll();
    }

    // Buscar medico por ID
    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    // Buscar medico por item
    public Optional<Medico> obtenerMedicoPorItem(String item) {
        return medicoRepository.findByItem(item);
    }

    // Eliminar medico
    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}
