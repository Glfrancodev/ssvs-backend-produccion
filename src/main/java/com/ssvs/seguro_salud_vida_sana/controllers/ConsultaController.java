package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Consulta;
import com.ssvs.seguro_salud_vida_sana.services.ConsultaService;

@RestController
@RequestMapping("/api/consulta")
// @CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    // Obtener todas las consultas
    @GetMapping
    public List<Consulta> obtenerConsultas() {
        return consultaService.getConsultas();
    }

    // Obtener una consulta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.getConsultaById(id);
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva consulta
    @PostMapping
    public Consulta crearConsulta(@RequestBody Consulta consulta) {
        return consultaService.saveConsulta(consulta);
    }

    // Actualizar una consulta
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        Optional<Consulta> consultaOptional = consultaService.getConsultaById(id);
        if (consultaOptional.isPresent()) {
            Consulta consultaActualizada = consultaOptional.get();
            consultaActualizada.setFechaConsulta(consulta.getFechaConsulta());
            consultaActualizada.setMotivoConsulta(consulta.getMotivoConsulta());
            consultaActualizada.setDiagnostico(consulta.getDiagnostico());
            consultaActualizada.setNota(consulta.getNota());
            return ResponseEntity.ok(consultaService.saveConsulta(consultaActualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una consulta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.getConsultaById(id);
        if (consulta.isPresent()) {
            consultaService.deleteConsulta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener todas las consultas por historia clínica ID
    @GetMapping("/historia/{historiaClinicaId}")
    public List<Consulta> getConsultasByHistoriaClinicaId(@PathVariable Long historiaClinicaId) {
        return consultaService.findConsultasByHistoriaClinicaId(historiaClinicaId);
    }
    
}
