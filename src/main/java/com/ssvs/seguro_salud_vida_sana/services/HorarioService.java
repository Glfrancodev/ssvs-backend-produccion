package com.ssvs.seguro_salud_vida_sana.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Cupo;
import com.ssvs.seguro_salud_vida_sana.models.Horario;
import com.ssvs.seguro_salud_vida_sana.repositories.CupoRepository;
import com.ssvs.seguro_salud_vida_sana.repositories.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Prueba Agregado para generar cupos segun horario
    @Autowired
    private CupoRepository cupoRepository;
    public Horario crearHorario(Horario horario) {
        // Guardar el horario
        Horario nuevoHorario = horarioRepository.save(horario);

        // Calcular los cupos
        generarCupos(nuevoHorario);

        return nuevoHorario;
    }

    private void generarCupos(Horario horario) {
        int cantidadCupos = horario.getCantidadCupos();
        LocalTime horaInicio = horario.getHoraInicio();
        LocalTime horaFinal = horario.getHoraFinal();

        // Calcular la duración en minutos del horario
        long duracionTotal = java.time.Duration.between(horaInicio, horaFinal).toMinutes();

        // Calcular la duración de cada cupo en minutos
        long duracionCupo = duracionTotal / cantidadCupos;

        // Crear cupos y asignarles la hora de atención
        for (int i = 0; i < cantidadCupos; i++) {
            Cupo cupo = new Cupo();
            cupo.setNumero(i + 1); // Número del cupo
            cupo.setEstado("Libre");
            cupo.setHorario(horario);

            // Calcular la hora específica para el cupo
            LocalTime horaCupo = horaInicio.plusMinutes(duracionCupo * i);
            cupo.setHora(horaCupo);

            // Guardar el cupo
            cupoRepository.save(cupo);
        }
    }



    // Crear un nuevo horario
    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Obtener todos los horarios
    public List<Horario> getHorarios() {
        return horarioRepository.findAll();
    }

    // Buscar horario por ID
    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    // Eliminar horario
    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }

    public List<Horario> findHorariosByMedicoEspecialidadId(Long medicoEspecialidadId) {
        return horarioRepository.findByMedicoEspecialidadId(medicoEspecialidadId);
    }

    public List<Horario> obtenerHorariosPorMedicoYFecha(Long medicoId, LocalDate fecha) {
        return horarioRepository.findHorariosByMedicoAndFechaAfter(medicoId, fecha);
    }

    public List<Horario> guardarHorarios(List<Horario> horarios) {
        return horarioRepository.saveAll(horarios);
    }

}
