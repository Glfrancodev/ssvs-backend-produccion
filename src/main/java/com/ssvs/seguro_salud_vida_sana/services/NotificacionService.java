package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Notificacion;
import com.ssvs.seguro_salud_vida_sana.models.Asegurado;
import com.ssvs.seguro_salud_vida_sana.repositories.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // Crear una nueva notificación
    public Notificacion saveNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    // Obtener todas las notificaciones de un asegurado
    public List<Notificacion> getNotificacionesByAseguradoId(Long aseguradoId) {
        return notificacionRepository.findByAseguradoId(aseguradoId);
    }

    // Obtener notificaciones no leídas de un asegurado
    public List<Notificacion> getNotificacionesNoLeidas(Long aseguradoId) {
        return notificacionRepository.findByAseguradoIdAndLeidoFalse(aseguradoId);
    }

    // Marcar todas las notificaciones como leídas para un asegurado
    public void marcarNotificacionesComoLeidas(Long aseguradoId) {
        List<Notificacion> noLeidas = getNotificacionesNoLeidas(aseguradoId);
        noLeidas.forEach(notificacion -> notificacion.setLeido(true));
        notificacionRepository.saveAll(noLeidas);
    }

    // Crear notificaciones masivas para asegurados con cupos afectados
    public void crearNotificacionesPorCuposMovidos(List<Asegurado> asegurados, String mensaje) {
        asegurados.forEach(asegurado -> {
            Notificacion notificacion = new Notificacion(mensaje, asegurado);
            notificacionRepository.save(notificacion);
        });
    }
}
