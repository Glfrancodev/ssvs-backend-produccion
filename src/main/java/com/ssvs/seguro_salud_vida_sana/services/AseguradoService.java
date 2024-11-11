package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Asegurado;
import com.ssvs.seguro_salud_vida_sana.models.HistoriaClinica;
import com.ssvs.seguro_salud_vida_sana.repositories.AseguradoRepository;
import com.ssvs.seguro_salud_vida_sana.repositories.HistoriaClinicaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AseguradoService {

    @Autowired
    private AseguradoRepository aseguradoRepository;
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    // Crear un nuevo asegurado
    public Asegurado saveAsegurado(Asegurado asegurado) {
        return aseguradoRepository.save(asegurado);
    }

    public Asegurado createAsegurado(Asegurado asegurado) {
        // Crear una nueva Historia Clínica
        HistoriaClinica nuevaHistoriaClinica = new HistoriaClinica();
        nuevaHistoriaClinica = historiaClinicaRepository.save(nuevaHistoriaClinica);

        // Asignar la Historia Clínica al Asegurado
        asegurado.setHistoriaClinica(nuevaHistoriaClinica);

        // Guardar el Asegurado con la Historia Clínica vinculada
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

    public Asegurado obtenerAseguradoPorCorreo(String correo) {
        return aseguradoRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new EntityNotFoundException("Asegurado no encontrado con el correo: " + correo));
    }

}
