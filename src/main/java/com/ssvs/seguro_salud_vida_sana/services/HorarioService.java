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
        Horario nuevoHorario = horarioRepository.save(horario);
        generarCupos(nuevoHorario);
        return nuevoHorario;
    }

    private void generarCupos(Horario horario) {
        int cantidadCupos = horario.getCantidadCupos();
        LocalTime horaInicio = horario.getHoraInicio();
        LocalTime horaFinal = horario.getHoraFinal();

        long duracionTotal = java.time.Duration.between(horaInicio, horaFinal).toMinutes();
        long duracionCupo = duracionTotal / cantidadCupos;

        for (int i = 0; i < cantidadCupos; i++) {
            Cupo cupo = new Cupo();
            cupo.setNumero(i + 1);
            cupo.setEstado("Libre");
            cupo.setHorario(horario);
            LocalTime horaCupo = horaInicio.plusMinutes(duracionCupo * i);
            cupo.setHora(horaCupo);
            cupoRepository.save(cupo);
        }
    }

    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Obtener todos los horarios con filtro por fecha
    public List<Horario> getHorarios() {
        return horarioRepository.findAll().stream()
                .filter(horario -> !horario.getFecha().isBefore(LocalDate.now()))
                .toList();
    }

    // Buscar horario por ID
    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id)
                .filter(horario -> !horario.getFecha().isBefore(LocalDate.now()));
    }

    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }

    public List<Horario> findHorariosByMedicoEspecialidadId(Long medicoEspecialidadId) {
        return horarioRepository.findByMedicoEspecialidadId(medicoEspecialidadId).stream()
                .filter(horario -> !horario.getFecha().isBefore(LocalDate.now()))
                .toList();
    }

    public List<Horario> obtenerHorariosPorMedicoYFecha(Long medicoId, LocalDate fecha) {
        return horarioRepository.findHorariosByMedicoAndFechaAfter(medicoId, fecha).stream()
                .filter(horario -> !horario.getFecha().isBefore(LocalDate.now()))
                .toList();
    }

    public List<Horario> guardarHorarios(List<Horario> horarios) {
        return horarioRepository.saveAll(horarios);
    }

}
