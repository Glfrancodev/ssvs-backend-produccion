package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Receta;
import com.ssvs.seguro_salud_vida_sana.services.RecetaService;

@RestController
@RequestMapping("/api/receta")
@CrossOrigin(origins = "http://localhost:4200/login")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    // Obtener todas las recetas
    @GetMapping
    public List<Receta> obtenerRecetas() {
        return recetaService.getRecetas();
    }

    // Obtener una receta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerRecetaPorId(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.getRecetaById(id);
        return receta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva receta
    @PostMapping
    public Receta crearReceta(@RequestBody Receta receta) {
        return recetaService.saveReceta(receta);
    }

    // Actualizar una receta
    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizarReceta(@PathVariable Long id, @RequestBody Receta receta) {
        Optional<Receta> recetaOptional = recetaService.getRecetaById(id);
        if (recetaOptional.isPresent()) {
            Receta recetaActualizada = recetaOptional.get();
            recetaActualizada.setFrecuencia(receta.getFrecuencia());
            recetaActualizada.setFechaInicio(receta.getFechaInicio());
            recetaActualizada.setFechaFinal(receta.getFechaFinal());
            return ResponseEntity.ok(recetaService.saveReceta(recetaActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una receta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.getRecetaById(id);
        if (receta.isPresent()) {
            recetaService.deleteReceta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
