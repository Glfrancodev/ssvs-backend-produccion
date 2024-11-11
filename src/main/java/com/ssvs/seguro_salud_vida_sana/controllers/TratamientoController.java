package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Tratamiento;
import com.ssvs.seguro_salud_vida_sana.services.TratamientoService;

@RestController
@RequestMapping("/api/tratamiento")
@CrossOrigin(origins = "https://ssvs-frontend-produccion-production.up.railway.app")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    // Obtener todos los tratamientos
    @GetMapping
    public List<Tratamiento> obtenerTratamientos() {
        return tratamientoService.getTratamientos();
    }

    // Obtener un tratamiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamientoPorId(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.getTratamientoById(id);
        return tratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo tratamiento
    @PostMapping
    public Tratamiento crearTratamiento(@RequestBody Tratamiento tratamiento) {
        return tratamientoService.saveTratamiento(tratamiento);
    }

    // Actualizar un tratamiento
    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> actualizarTratamiento(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        Optional<Tratamiento> tratamientoOptional = tratamientoService.getTratamientoById(id);
        if (tratamientoOptional.isPresent()) {
            Tratamiento tratamientoActualizado = tratamientoOptional.get();
            tratamientoActualizado.setFecha(tratamiento.getFecha());
            return ResponseEntity.ok(tratamientoService.saveTratamiento(tratamientoActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un tratamiento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.getTratamientoById(id);
        if (tratamiento.isPresent()) {
            tratamientoService.deleteTratamiento(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Tratamiento> getTratamientoByConsultaId(@PathVariable Long consultaId) {
        Optional<Tratamiento> tratamiento = tratamientoService.getTratamientoByConsultaId(consultaId);
        return tratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
