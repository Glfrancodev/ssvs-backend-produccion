package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.PermisoAusencia;
import com.ssvs.seguro_salud_vida_sana.services.PermisoAusenciaService;

@RestController
@RequestMapping("/api/permiso-ausencia")
public class PermisoAusenciaController {

    @Autowired
    private PermisoAusenciaService permisoAusenciaService;

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
    public ResponseEntity<PermisoAusencia> actualizarPermisoAusencia(@PathVariable Long id, @RequestBody PermisoAusencia permisoAusencia) {
        Optional<PermisoAusencia> permisoAusenciaOptional = permisoAusenciaService.getPermisoAusenciaById(id);
        if (permisoAusenciaOptional.isPresent()) {
            PermisoAusencia permisoAusenciaActualizado = permisoAusenciaOptional.get();
            permisoAusenciaActualizado.setFechaPermiso(permisoAusencia.getFechaPermiso());
            permisoAusenciaActualizado.setDescripcion(permisoAusencia.getDescripcion());
            permisoAusenciaActualizado.setEstado(permisoAusencia.getEstado());
            permisoAusenciaActualizado.setMedico(permisoAusencia.getMedico());
            return ResponseEntity.ok(permisoAusenciaService.savePermisoAusencia(permisoAusenciaActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
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
