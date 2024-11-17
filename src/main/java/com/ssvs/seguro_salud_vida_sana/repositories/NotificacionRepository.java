package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssvs.seguro_salud_vida_sana.models.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Obtener todas las notificaciones de un asegurado
    List<Notificacion> findByAseguradoId(Long aseguradoId);

    // Obtener notificaciones no leídas de un asegurado
    List<Notificacion> findByAseguradoIdAndLeidoFalse(Long aseguradoId);

    // Marcar todas las notificaciones de un asegurado como leídas
    default void marcarTodasComoLeidas(Long aseguradoId, JpaRepository<Notificacion, Long> repo) {
        List<Notificacion> notificaciones = findByAseguradoIdAndLeidoFalse(aseguradoId);
        notificaciones.forEach(notificacion -> notificacion.setLeido(true));
        repo.saveAll(notificaciones);
    }
}
