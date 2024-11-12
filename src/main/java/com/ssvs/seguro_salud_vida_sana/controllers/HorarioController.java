package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Horario;
import com.ssvs.seguro_salud_vida_sana.services.HorarioService;

@RestController
@RequestMapping("/api/horario")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // Obtener todos los horarios
    @GetMapping
    public List<Horario> obtenerHorarios() {
        return horarioService.getHorarios();
    }

    // Obtener un horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerHorarioPorId(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorarioById(id);
        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo horario
    @PostMapping
    public Horario crearHorario(@RequestBody Horario horario) {
        return horarioService.crearHorario(horario);
    }

    // Actualizar un horario
    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizarHorario(@PathVariable Long id, @RequestBody Horario horario) {
        Optional<Horario> horarioOptional = horarioService.getHorarioById(id);
        if (horarioOptional.isPresent()) {
            Horario horarioActualizado = horarioOptional.get();
            horarioActualizado.setFecha(horario.getFecha());
            horarioActualizado.setHoraInicio(horario.getHoraInicio());
            horarioActualizado.setHoraFinal(horario.getHoraFinal());
            horarioActualizado.setMedicoEspecialidad(horario.getMedicoEspecialidad());
            return ResponseEntity.ok(horarioService.saveHorario(horarioActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un horario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorarioById(id);
        if (horario.isPresent()) {
            horarioService.deleteHorario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/medico-especialidad/{medicoEspecialidadId}")
    public ResponseEntity<List<Horario>> getHorariosByMedicoEspecialidad(@PathVariable Long medicoEspecialidadId) {
        List<Horario> horarios = horarioService.findHorariosByMedicoEspecialidadId(medicoEspecialidadId);
        return ResponseEntity.ok(horarios);
    }

}
