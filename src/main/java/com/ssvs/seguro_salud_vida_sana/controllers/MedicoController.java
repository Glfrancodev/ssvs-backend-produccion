package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.seguro_salud_vida_sana.models.Especialidad;
import com.ssvs.seguro_salud_vida_sana.models.Medico;
import com.ssvs.seguro_salud_vida_sana.services.MedicoService;

@RestController
@RequestMapping("/api/medico")
@CrossOrigin(origins = "https://ssvs-frontend-produccion-production.up.railway.app")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Obtener todos los medicos
    @GetMapping
    public List<Medico> obtenerMedicos() {
        return medicoService.getMedicos();
    }

    // Obtener un medico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo medico
    @PostMapping
    public Medico crearMedico(@RequestBody Medico medico) {
        return medicoService.saveMedico(medico);
    }

    // Actualizar medico
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
        Optional<Medico> medicoOptional = medicoService.getMedicoById(id);
        if (medicoOptional.isPresent()) {
            Medico medicoActualizado = medicoOptional.get();
            medicoActualizado.setItem(medico.getItem());
            medicoActualizado.setUsuario(medico.getUsuario());
            return ResponseEntity.ok(medicoService.saveMedico(medicoActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un medico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        if (medico.isPresent()) {
            medicoService.deleteMedico(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener especialidades por correo del médico
    @GetMapping("/especialidades/{correo}")
    public List<Especialidad> obtenerEspecialidadesPorCorreo(@PathVariable String correo) {
        return medicoService.obtenerEspecialidadesPorCorreo(correo);
    }

    // Obtener médico por correo
    @GetMapping("/correo/{correo}")
    public Medico obtenerMedicoPorCorreo(@PathVariable String correo) {
        return medicoService.obtenerMedicoPorCorreo(correo);
    }

}