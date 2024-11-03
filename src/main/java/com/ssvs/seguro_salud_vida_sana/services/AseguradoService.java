package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Asegurado;
import com.ssvs.seguro_salud_vida_sana.repositories.AseguradoRepository;

@Service
public class AseguradoService {

    @Autowired
    private AseguradoRepository aseguradoRepository;

    // Crear un nuevo asegurado
    public Asegurado saveAsegurado(Asegurado asegurado) {
        return aseguradoRepository.save(asegurado);
    }

    // Obtener todos los asegurados
    public List<Asegurado> getAsegurados() {
        return aseguradoRepository.findAll();
    }

    // Buscar asegurado por ID
    public Optional<Asegurado> getAseguradoById(Long id) {
        return aseguradoRepository.findById(id);
    }


    // Eliminar asegurado
    public void deleteAsegurado(Long id) {
        aseguradoRepository.deleteById(id);
    }
}
