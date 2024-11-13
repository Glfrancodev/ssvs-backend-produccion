package com.ssvs.seguro_salud_vida_sana.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssvs.seguro_salud_vida_sana.models.Bitacora;
import com.ssvs.seguro_salud_vida_sana.repositories.BitacoraRepository;

@Service
public class BitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    // Guardar un nuevo registro de bitácora
    public Bitacora saveBitacora(Bitacora bitacora) {
        return bitacoraRepository.save(bitacora);
    }

    // Obtener todos los registros de bitácora
    public List<Bitacora> getAllBitacora() {
        return bitacoraRepository.findAll();
    }

    // Buscar bitácora por ID
    public Optional<Bitacora> getBitacoraById(Long id) {
        return bitacoraRepository.findById(id);
    }

    // Buscar registros de bitácora por correo electrónico
    public List<Bitacora> getBitacoraByCorreo(String correo) {
        return bitacoraRepository.findByCorreo(correo);
    }

    // Buscar registros de bitácora por fecha exacta
    public List<Bitacora> getBitacoraByFecha(LocalDate fecha) {
        return bitacoraRepository.findByFecha(fecha);
    }

    // Buscar registros de bitácora por correo y fecha exacta
    public List<Bitacora> getBitacoraByCorreoAndFecha(String correo, LocalDate fecha) {
        return bitacoraRepository.findByCorreoAndFecha(correo, fecha);
    }

    // Buscar registros de bitácora en un rango de fechas
    public List<Bitacora> getBitacoraByFechaRange(LocalDate startDate, LocalDate endDate) {
        return bitacoraRepository.findByFechaBetween(startDate, endDate);
    }

    // Eliminar un registro de bitácora
    public void deleteBitacora(Long id) {
        bitacoraRepository.deleteById(id);
    }
}
