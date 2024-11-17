package com.ssvs.seguro_salud_vida_sana.controllers;

import com.ssvs.seguro_salud_vida_sana.models.Calificacion;
import com.ssvs.seguro_salud_vida_sana.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificacion")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    // Obtener todas las calificaciones
    @GetMapping
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionService.getCalificaciones();
    }

    // Crear una nueva calificación
    @PostMapping
    public Calificacion crearCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionService.saveCalificacion(calificacion);
    }

    // Obtener calificaciones por rango de estrellas
    @GetMapping("/rango")
    public List<Calificacion> obtenerCalificacionesPorRango(@RequestParam int min, @RequestParam int max) {
        return calificacionService.getCalificacionesPorRango(min, max);
    }

    // Contar calificaciones por número de estrellas
    @GetMapping("/contador/{estrella}")
    public ResponseEntity<Long> contarCalificacionesPorEstrella(@PathVariable int estrella) {
        Long conteo = calificacionService.contarCalificacionesPorEstrella(estrella);
        return ResponseEntity.ok(conteo);
    }

    // Eliminar una calificación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCalificacion(@PathVariable Long id) {
        calificacionService.deleteCalificacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Calificacion> buscarCalificacion(
        @RequestParam Long aseguradoId,
        @RequestParam Long medicoId
    ) {
        Calificacion calificacion = calificacionService.buscarCalificacionPorAseguradoYMedico(aseguradoId, medicoId);
        if (calificacion != null) {
            return ResponseEntity.ok(calificacion);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    // Actualizar una calificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> actualizarCalificacion(
            @PathVariable Long id,
            @RequestBody Calificacion calificacionActualizada) {
        Calificacion calificacion = calificacionService.getCalificacionById(id);

        if (calificacion != null) {
            calificacion.setEstrella(calificacionActualizada.getEstrella());
            calificacionService.saveCalificacion(calificacion);
            return ResponseEntity.ok(calificacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
