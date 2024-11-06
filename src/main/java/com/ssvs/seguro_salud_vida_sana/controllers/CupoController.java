package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;
import com.ssvs.seguro_salud_vida_sana.services.CupoService;

@RestController
@RequestMapping("/api/cupo")
public class CupoController {

    @Autowired
    private CupoService cupoService;

    // Obtener todos los cupos
    @GetMapping
    public List<Cupo> obtenerCupos() {
        return cupoService.getCupos();
    }

    // Obtener un cupo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cupo> obtenerCupoPorId(@PathVariable Long id) {
        Optional<Cupo> cupo = cupoService.getCupoById(id);
        return cupo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo cupo
    @PostMapping
    public Cupo crearCupo(@RequestBody Cupo cupo) {
        return cupoService.saveCupo(cupo);
    }

    // Actualizar un cupo
    @PutMapping("/{id}")
    public ResponseEntity<Cupo> actualizarCupo(@PathVariable Long id, @RequestBody Cupo cupo) {
        Optional<Cupo> cupoOptional = cupoService.getCupoById(id);
        if (cupoOptional.isPresent()) {
            Cupo cupoActualizado = cupoOptional.get();
            cupoActualizado.setNumero(cupo.getNumero());
            cupoActualizado.setEstado(cupo.getEstado());
            cupoActualizado.setAsegurado(cupo.getAsegurado());
            cupoActualizado.setHorario(cupo.getHorario());
            return ResponseEntity.ok(cupoService.saveCupo(cupoActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un cupo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCupo(@PathVariable Long id) {
        Optional<Cupo> cupo = cupoService.getCupoById(id);
        if (cupo.isPresent()) {
            cupoService.deleteCupo(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
