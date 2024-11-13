package com.ssvs.seguro_salud_vida_sana.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.seguro_salud_vida_sana.models.Bitacora;
import com.ssvs.seguro_salud_vida_sana.services.BitacoraService;

@RestController
@RequestMapping("/api/bitacora")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class BitacoraController {

    @Autowired
    private BitacoraService bitacoraService;

    // Crear un nuevo registro en la bitácora
    @PostMapping
    public ResponseEntity<Bitacora> crearRegistroBitacora(@RequestBody Bitacora bitacora) {
        Bitacora nuevoRegistro = bitacoraService.saveBitacora(bitacora);
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    // Obtener todos los registros de bitácora
    @GetMapping
    public List<Bitacora> obtenerTodosLosRegistros() {
        return bitacoraService.getAllBitacora();
    }

    // Obtener registros de bitácora por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Bitacora>> obtenerBitacoraPorCorreo(@PathVariable String correo) {
        List<Bitacora> registros = bitacoraService.getBitacoraByCorreo(correo);
        return registros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(registros);
    }

    // Obtener registros de bitácora por fecha exacta
    @GetMapping("/fecha")
    public ResponseEntity<List<Bitacora>> obtenerBitacoraPorFecha(@RequestParam LocalDate fecha) {
        List<Bitacora> registros = bitacoraService.getBitacoraByFecha(fecha);
        return registros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(registros);
    }

    // Obtener registros de bitácora en un rango de fechas
    @GetMapping("/fecha/rango")
    public ResponseEntity<List<Bitacora>> obtenerBitacoraPorRangoDeFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        List<Bitacora> registros = bitacoraService.getBitacoraByFechaRange(inicio, fin);
        return registros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(registros);
    }

    // Obtener registros de bitácora por correo y fecha exacta
    @GetMapping("/correo/{correo}/fecha")
    public ResponseEntity<List<Bitacora>> obtenerBitacoraPorCorreoYFecha(@PathVariable String correo, @RequestParam LocalDate fecha) {
        List<Bitacora> registros = bitacoraService.getBitacoraByCorreoAndFecha(correo, fecha);
        return registros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(registros);
    }
}
