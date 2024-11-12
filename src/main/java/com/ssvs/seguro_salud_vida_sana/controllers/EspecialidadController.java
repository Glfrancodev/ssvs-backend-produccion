package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Especialidad;
import com.ssvs.seguro_salud_vida_sana.services.EspecialidadService;

@RestController
@RequestMapping("/api/especialidad")
// @CrossOrigin(origins = "*")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    // Obtener todas las especialidades
    @GetMapping
    public List<Especialidad> obtenerEspecialidades() {
        return especialidadService.getEspecialidades();
    }

    // Obtener una especialidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtenerEspecialidadPorId(@PathVariable Long id) {
        Optional<Especialidad> especialidad = especialidadService.getEspecialidadById(id);
        return especialidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva especialidad
    @PostMapping
    public Especialidad crearEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadService.saveEspecialidad(especialidad);
    }

    // Actualizar especialidad
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizarEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad) {
        Optional<Especialidad> especialidadOptional = especialidadService.getEspecialidadById(id);
        if (especialidadOptional.isPresent()) {
            Especialidad especialidadActualizada = especialidadOptional.get();
            especialidadActualizada.setNombre(especialidad.getNombre());
            especialidadActualizada.setDescripcion(especialidad.getDescripcion());
            return ResponseEntity.ok(especialidadService.saveEspecialidad(especialidadActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una especialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable Long id) {
        Optional<Especialidad> especialidad = especialidadService.getEspecialidadById(id);
        if (especialidad.isPresent()) {
            especialidadService.deleteEspecialidad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener especialidad por nombre
    @GetMapping("/nombre/{nombre}")
    public Optional<Especialidad> obtenerEspecialidadPorNombre(@PathVariable String nombre) {
        return especialidadService.getEspecialidadByNombre(nombre);
    }

}
