package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Horario;
import com.ssvs.seguro_salud_vida_sana.repositories.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Crear un nuevo horario
    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Obtener todos los horarios
    public List<Horario> getHorarios() {
        return horarioRepository.findAll();
    }

    // Buscar horario por ID
    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    // Eliminar horario
    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
