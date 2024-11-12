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

import com.ssvs.seguro_salud_vida_sana.models.Permiso;
import com.ssvs.seguro_salud_vida_sana.services.PermisoService;

@RestController
@RequestMapping("/api/permiso")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class PermisoController {
  @Autowired
  private PermisoService permisoService;

  // Obtener todos los permisos
  @GetMapping
  public List<Permiso> getAllPermisos() {
    return permisoService.getAllPermisos();
  }
  // Obtener un permiso por ID
  @GetMapping("/{id}")
  public Optional<Permiso> getPermisoById(@PathVariable Long id) {
    return permisoService.getPermisoById(id);
  }

  // Crear un nuevo permiso
  @PostMapping
  public Permiso createPermiso(@RequestBody Permiso permiso) {
    return permisoService.savePermiso(permiso);
  }

  // Actualizar un permiso existente
  @PutMapping("/{id}")
  public ResponseEntity<Permiso> updatePermiso(@PathVariable Long id, @RequestBody Permiso permiso){
    Optional<Permiso> permisoOptional = permisoService.getPermisoById(id);
    if(permisoOptional.isPresent()){
      Permiso updatedPermiso = permisoOptional.get();
      updatedPermiso.setNombre(permiso.getNombre());
      updatedPermiso.setDescripcion(permiso.getDescripcion());
      return ResponseEntity.ok(permisoService.savePermiso(updatedPermiso));
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  // Eliminar un permiso existente
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePermiso(@PathVariable Long id){
    Optional<Permiso> permisoOptional = permisoService.getPermisoById(id);
    if(permisoOptional.isPresent()){
      permisoService.deletePermiso(id);
      return ResponseEntity.noContent().build();
    }else{
      return ResponseEntity.notFound().build();
    }
  }
}