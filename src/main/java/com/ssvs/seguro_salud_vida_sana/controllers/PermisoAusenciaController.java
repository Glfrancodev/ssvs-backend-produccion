package com.ssvs.seguro_salud_vida_sana.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Horario;
import com.ssvs.seguro_salud_vida_sana.models.PermisoAusencia;
import com.ssvs.seguro_salud_vida_sana.services.HorarioService;
import com.ssvs.seguro_salud_vida_sana.services.PermisoAusenciaService;

@RestController
@RequestMapping("/api/permiso-ausencia")
@CrossOrigin(origins = "http://localhost:4200/login")
public class PermisoAusenciaController {

    @Autowired
    private PermisoAusenciaService permisoAusenciaService;
    @Autowired
    private HorarioService horarioService;

    // Obtener todos los permisos de ausencia
    @GetMapping
    public List<PermisoAusencia> obtenerPermisosAusencia() {
        return permisoAusenciaService.getPermisosAusencia();
    }

    // Obtener un permiso de ausencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<PermisoAusencia> obtenerPermisoAusenciaPorId(@PathVariable Long id) {
        Optional<PermisoAusencia> permisoAusencia = permisoAusenciaService.getPermisoAusenciaById(id);
        return permisoAusencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo permiso de ausencia
    @PostMapping
    public PermisoAusencia crearPermisoAusencia(@RequestBody PermisoAusencia permisoAusencia) {
        return permisoAusenciaService.savePermisoAusencia(permisoAusencia);
    }

    // Actualizar permiso de ausencia
        @PutMapping("/{id}")
    public ResponseEntity<PermisoAusencia> actualizarPermisoAusencia(
            @PathVariable Long id, @RequestBody PermisoAusencia permisoAusencia) {

        Optional<PermisoAusencia> permisoAusenciaOptional = permisoAusenciaService.getPermisoAusenciaById(id);
        if (permisoAusenciaOptional.isPresent()) {
            PermisoAusencia permisoExistente = permisoAusenciaOptional.get();
            permisoExistente.setFechaPermiso(permisoAusencia.getFechaPermiso());
            permisoExistente.setDescripcion(permisoAusencia.getDescripcion());
            permisoExistente.setEstado(permisoAusencia.getEstado());
            permisoExistente.setMedico(permisoAusencia.getMedico());

            // Verificamos si el estado del permiso se ha actualizado a "Aprobado"
            if ("Aprobado".equalsIgnoreCase(permisoAusencia.getEstado())) {
                moverHorarios(permisoAusencia.getMedico().getId(), permisoAusencia.getFechaPermiso());
            }

            PermisoAusencia permisoActualizado = permisoAusenciaService.savePermisoAusencia(permisoExistente);
            return ResponseEntity.ok(permisoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para mover horarios un día hacia adelante
    private void moverHorarios(Long medicoId, LocalDate fechaPermiso) {
        // Obtener los horarios posteriores a la fecha del permiso
        List<Horario> horarios = horarioService.obtenerHorariosPorMedicoYFecha(medicoId, fechaPermiso);

        // Incrementar en un día la fecha de cada horario
        for (Horario horario : horarios) {
            horario.setFecha(horario.getFecha().plusDays(1));
        }

        // Guardar los horarios actualizados
        horarioService.guardarHorarios(horarios);
    }


    // Eliminar un permiso de ausencia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPermisoAusencia(@PathVariable Long id) {
        Optional<PermisoAusencia> permisoAusencia = permisoAusenciaService.getPermisoAusenciaById(id);
        if (permisoAusencia.isPresent()) {
            permisoAusenciaService.deletePermisoAusencia(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
