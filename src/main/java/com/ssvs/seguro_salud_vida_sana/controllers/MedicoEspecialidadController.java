package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Medico;
import com.ssvs.seguro_salud_vida_sana.models.MedicoEspecialidad;
import com.ssvs.seguro_salud_vida_sana.services.MedicoEspecialidadService;

@RestController
@RequestMapping("/api/medico-especialidad")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class MedicoEspecialidadController {

    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    // Obtener todas las relaciones MedicoEspecialidad
    @GetMapping
    public List<MedicoEspecialidad> obtenerMedicoEspecialidades() {
        return medicoEspecialidadService.getMedicosEspecialidades();
    }

    // Obtener una relación MedicoEspecialidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicoEspecialidad> obtenerMedicoEspecialidadPorId(@PathVariable Long id) {
        Optional<MedicoEspecialidad> medicoEspecialidad = medicoEspecialidadService.getMedicoEspecialidadById(id);
        return medicoEspecialidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva relación MedicoEspecialidad
    @PostMapping
    public MedicoEspecialidad crearMedicoEspecialidad(@RequestBody MedicoEspecialidad medicoEspecialidad) {
        return medicoEspecialidadService.saveMedicoEspecialidad(medicoEspecialidad);
    }

    // Actualizar relación MedicoEspecialidad
    @PutMapping("/{id}")
    public ResponseEntity<MedicoEspecialidad> actualizarMedicoEspecialidad(@PathVariable Long id, @RequestBody MedicoEspecialidad medicoEspecialidad) {
        Optional<MedicoEspecialidad> medicoEspecialidadOptional = medicoEspecialidadService.getMedicoEspecialidadById(id);
        if (medicoEspecialidadOptional.isPresent()) {
            MedicoEspecialidad medicoEspecialidadActualizada = medicoEspecialidadOptional.get();
            // Actualiza los atributos según sea necesario
            medicoEspecialidadActualizada.setMedico(medicoEspecialidad.getMedico());
            medicoEspecialidadActualizada.setEspecialidad(medicoEspecialidad.getEspecialidad());
            return ResponseEntity.ok(medicoEspecialidadService.saveMedicoEspecialidad(medicoEspecialidadActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una relación MedicoEspecialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicoEspecialidad(@PathVariable Long id) {
        Optional<MedicoEspecialidad> medicoEspecialidad = medicoEspecialidadService.getMedicoEspecialidadById(id);
        if (medicoEspecialidad.isPresent()) {
            medicoEspecialidadService.deleteMedicoEspecialidad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/especialidad/{especialidadId}/medico/{medicoId}")
    public ResponseEntity<MedicoEspecialidad> getMedicoEspecialidadByEspecialidadAndMedico(
            @PathVariable Long especialidadId,
            @PathVariable Long medicoId) {
        MedicoEspecialidad medicoEspecialidad = medicoEspecialidadService
                .findByEspecialidadAndMedico(especialidadId, medicoId);
        return medicoEspecialidad != null 
                ? ResponseEntity.ok(medicoEspecialidad) 
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/medico/especialidad/{especialidadId}")
    public List<Medico> obtenerMedicosPorEspecialidad(@PathVariable Long especialidadId) {
        return medicoEspecialidadService.obtenerMedicosPorEspecialidad(especialidadId);
    }

}
