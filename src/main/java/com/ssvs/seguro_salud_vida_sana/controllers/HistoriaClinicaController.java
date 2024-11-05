package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.HistoriaClinica;
import com.ssvs.seguro_salud_vida_sana.services.HistoriaClinicaService;

@RestController
@RequestMapping("/api/historia_clinica")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    // Obtener todas las historias clínicas
    @GetMapping
    public List<HistoriaClinica> obtenerHistoriasClinicas() {
        return historiaClinicaService.getHistoriasClinicas();
    }

    // Obtener una historia clínica por ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtenerHistoriaClinicaPorId(@PathVariable Long id) {
        Optional<HistoriaClinica> historiaClinica = historiaClinicaService.getHistoriaClinicaById(id);
        return historiaClinica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva historia clínica
    @PostMapping
    public HistoriaClinica crearHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        return historiaClinicaService.saveHistoriaClinica(historiaClinica);
    }

    // Actualizar una historia clínica
    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> actualizarHistoriaClinica(@PathVariable Long id, @RequestBody HistoriaClinica historiaClinica) {
        Optional<HistoriaClinica> historiaClinicaOptional = historiaClinicaService.getHistoriaClinicaById(id);
        if (historiaClinicaOptional.isPresent()) {
            HistoriaClinica historiaClinicaActualizada = historiaClinicaOptional.get();
            // Actualiza los atributos de historiaClinica según sea necesario
            return ResponseEntity.ok(historiaClinicaService.saveHistoriaClinica(historiaClinicaActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una historia clínica por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistoriaClinica(@PathVariable Long id) {
        Optional<HistoriaClinica> historiaClinica = historiaClinicaService.getHistoriaClinicaById(id);
        if (historiaClinica.isPresent()) {
            historiaClinicaService.deleteHistoriaClinica(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
