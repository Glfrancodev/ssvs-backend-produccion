package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Especialidad;
import com.ssvs.seguro_salud_vida_sana.models.Medico;
import com.ssvs.seguro_salud_vida_sana.repositories.CalificacionRepository;
import com.ssvs.seguro_salud_vida_sana.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

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

    // Eliminar medico
    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    // Obtener especialidades por correo del médico
    public List<Especialidad> obtenerEspecialidadesPorCorreo(String correo) {
        return medicoRepository.findEspecialidadesByCorreo(correo);
    }

    // Obtener médico por correo
    public Medico obtenerMedicoPorCorreo(String correo) {
        Optional<Medico> medico = medicoRepository.findByUsuarioCorreo(correo);
        return medico.orElse(null);
    }

    // Obtener promedio de calificaciones por ID de médico
    public Double obtenerPromedioCalificacionPorMedicoId(Long medicoId) {
        return calificacionRepository.obtenerPromedioCalificacionPorMedicoId(medicoId);
    }

}
