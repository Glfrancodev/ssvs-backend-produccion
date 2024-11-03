package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.seguro_salud_vida_sana.models.Asegurado;
import com.ssvs.seguro_salud_vida_sana.services.AseguradoService;

@RestController
@RequestMapping("/api/asegurado")
public class AseguradoController {

    @Autowired
    private AseguradoService aseguradoService;

    // Obtener todos los asegurados
    @GetMapping
    public List<Asegurado> obtenerAsegurados() {
        return aseguradoService.getAsegurados();
    }

    // Obtener un asegurado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asegurado> obtenerAseguradoPorId(@PathVariable Long id) {
        Optional<Asegurado> asegurado = aseguradoService.getAseguradoById(id);
        return asegurado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo asegurado
    @PostMapping
    public Asegurado crearAsegurado(@RequestBody Asegurado asegurado) {
        return aseguradoService.saveAsegurado(asegurado);
    }

    // Actualizar asegurado
    @PutMapping("/{id}")
    public ResponseEntity<Asegurado> actualizarAsegurado(@PathVariable Long id, @RequestBody Asegurado asegurado) {
        Optional<Asegurado> aseguradoOptional = aseguradoService.getAseguradoById(id);
        if (aseguradoOptional.isPresent()) {
            Asegurado aseguradoActualizado = aseguradoOptional.get();
            aseguradoActualizado.setTipoSangre(asegurado.getTipoSangre());
            aseguradoActualizado.setSexo(asegurado.getSexo());
            aseguradoActualizado.setFechaNacimiento(asegurado.getFechaNacimiento());
            aseguradoActualizado.setUsuario(asegurado.getUsuario());
            return ResponseEntity.ok(aseguradoService.saveAsegurado(aseguradoActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un asegurado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsegurado(@PathVariable Long id) {
        Optional<Asegurado> asegurado = aseguradoService.getAseguradoById(id);
        if (asegurado.isPresent()) {
            aseguradoService.deleteAsegurado(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
