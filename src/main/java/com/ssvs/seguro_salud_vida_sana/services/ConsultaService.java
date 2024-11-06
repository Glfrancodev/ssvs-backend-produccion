package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Consulta;
import com.ssvs.seguro_salud_vida_sana.repositories.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    // Crear o guardar una nueva consulta
    public Consulta saveConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    // Obtener todas las consultas
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    // Buscar consulta por ID
    public Optional<Consulta> getConsultaById(Long id) {
        return consultaRepository.findById(id);
    }

    // Eliminar consulta
    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
