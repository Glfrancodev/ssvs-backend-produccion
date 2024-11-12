package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.seguro_salud_vida_sana.models.Rol;
import com.ssvs.seguro_salud_vida_sana.services.RolService;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*")
public class RolController {
  @Autowired
  private RolService rolService;

  // Obtener todos los roles
  @GetMapping
  public List<Rol> getAllRoles(){
    return rolService.getAllRoles();
  }
  // Obtener un rol por ID
  @GetMapping("/{id}")
  public Optional<Rol> getRolById(@PathVariable Long id){
    return rolService.getRolById(id);
  }

  // Crear un nuevo rol
  @PostMapping
  public Rol createRol(@RequestBody Rol rol){
    return rolService.saveRol(rol);
  }

  // Actualizar un rol existente
  @PutMapping("/{id}")
  public Optional<Rol> updateRol(@PathVariable Long id, @RequestBody Rol rolDetails){
    Optional<Rol> rol = rolService.getRolById(id);
    if(rol.isPresent()){
      Rol updatedRol = rol.get();
      updatedRol.setNombre(rolDetails.getNombre());
      return Optional.of(rolService.saveRol(updatedRol));
    } else {
      return Optional.empty();
    }
  }

  // Eliminar un rol
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRol(@PathVariable Long id){
    Optional<Rol> rol = rolService.getRolById(id);
    if(rol.isPresent()){
      rolService.deleteRol(id);
      return ResponseEntity.noContent().build();
    } else{
      return ResponseEntity.notFound().build();
    }
  }
}
