package com.ssvs.seguro_salud_vida_sana.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;
import com.ssvs.seguro_salud_vida_sana.services.CupoService;

@RestController
@RequestMapping("/api/cupo")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
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

    @GetMapping("/horario/{horarioId}")
    public List<Cupo> getCuposByHorario(@PathVariable Long horarioId) {
        return cupoService.findCuposByHorarioId(horarioId);
    }

    // Obtener cupos ocupados por horario ID
    @GetMapping("/horario/{horarioId}/ocupado")
    public List<Cupo> getCuposOcupadosPorHorario(@PathVariable Long horarioId) {
        return cupoService.findCuposOcupadosByHorarioId(horarioId);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<Cupo> actualizarEstadoCupo(@PathVariable Long id, @RequestBody String estado) {
        Optional<Cupo> cupoOptional = cupoService.getCupoById(id);
        if (cupoOptional.isPresent()) {
            Cupo cupo = cupoOptional.get();
            cupo.setEstado(estado);
            Cupo cupoActualizado = cupoService.saveCupo(cupo);
            return ResponseEntity.ok(cupoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/reservar/{id}")
    public ResponseEntity<Cupo> reservarCupo(@PathVariable Long id, @RequestBody Cupo cupo) {
        Optional<Cupo> cupoOptional = cupoService.getCupoById(id);
        if (cupoOptional.isPresent()) {
            Cupo cupoExistente = cupoOptional.get();
            Long aseguradoId = cupo.getAsegurado().getId();
            LocalDate fechaActual = LocalDate.now();
            
            // Verificar si el asegurado ya tiene un cupo reservado para hoy
            if (cupoService.existeCupoParaFechaActual(aseguradoId, fechaActual)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            
            // Actualizar solo los campos necesarios
            cupoExistente.setFechaReservado(cupo.getFechaReservado());
            cupoExistente.setEstado(cupo.getEstado());
            cupoExistente.setAsegurado(cupo.getAsegurado());
            
            // Guardar el cupo actualizado
            Cupo cupoActualizado = cupoService.saveCupo(cupoExistente);
            return ResponseEntity.ok(cupoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/asegurado/{aseguradoId}")
    public ResponseEntity<List<Cupo>> obtenerCuposPorAsegurado(@PathVariable Long aseguradoId) {
        List<Cupo> cupos = cupoService.obtenerCuposPorAsegurado(aseguradoId);
        return ResponseEntity.ok(cupos);
    }

    @PutMapping("/no/reservar/{id}")
    public ResponseEntity<Cupo> quitarCupo(@PathVariable Long id, @RequestBody Cupo cupo) {
        Optional<Cupo> cupoOptional = cupoService.getCupoById(id);
        if (cupoOptional.isPresent()) {
            Cupo cupoExistente = cupoOptional.get();
            
            // Actualizar solo los campos necesarios
            cupoExistente.setFechaReservado(cupo.getFechaReservado());
            cupoExistente.setEstado(cupo.getEstado());
            cupoExistente.setAsegurado(cupo.getAsegurado());
            
            // Guardar el cupo actualizado
            Cupo cupoActualizado = cupoService.saveCupo(cupoExistente);
            return ResponseEntity.ok(cupoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
