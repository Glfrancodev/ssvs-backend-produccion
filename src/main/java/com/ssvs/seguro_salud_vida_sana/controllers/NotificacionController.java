package com.ssvs.seguro_salud_vida_sana.controllers;

import com.ssvs.seguro_salud_vida_sana.models.Notificacion;
import com.ssvs.seguro_salud_vida_sana.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacion")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Crear una nueva notificación
    @PostMapping
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.saveNotificacion(notificacion);
    }

    // Obtener notificaciones por asegurado
    @GetMapping("/asegurado/{aseguradoId}")
    public List<Notificacion> obtenerNotificacionesPorAsegurado(@PathVariable Long aseguradoId) {
        return notificacionService.getNotificacionesByAseguradoId(aseguradoId);
    }

    // Obtener notificaciones no leídas por asegurado
    @GetMapping("/asegurado/{aseguradoId}/no-leidas")
    public List<Notificacion> obtenerNotificacionesNoLeidas(@PathVariable Long aseguradoId) {
        return notificacionService.getNotificacionesNoLeidas(aseguradoId);
    }

    // Marcar todas las notificaciones como leídas
    @PatchMapping("/asegurado/{aseguradoId}/marcar-leidas")
    public ResponseEntity<Void> marcarNotificacionesComoLeidas(@PathVariable Long aseguradoId) {
        notificacionService.marcarNotificacionesComoLeidas(aseguradoId);
        return ResponseEntity.noContent().build();
    }

    // Crear notificaciones masivas
    @PostMapping("/masivas")
    public ResponseEntity<Void> crearNotificacionesMasivas(@RequestBody List<Notificacion> notificaciones) {
        notificaciones.forEach(notificacionService::saveNotificacion);
        return ResponseEntity.noContent().build();
    }
}
