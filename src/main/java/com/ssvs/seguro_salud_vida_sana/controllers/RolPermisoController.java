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

import com.ssvs.seguro_salud_vida_sana.models.RolPermiso;
import com.ssvs.seguro_salud_vida_sana.services.RolPermisoService;;

@RestController
@RequestMapping("/api/rol-permiso")
@CrossOrigin(origins = "*")
public class RolPermisoController {
  @Autowired
  private RolPermisoService rolPermisoService;

  // Obtener todos los rolpermisos
  @GetMapping
  public List<RolPermiso> getAllRolPermisos() {
    return rolPermisoService.getAllRolpermisos();
  }

  // Obtener un rolPermiso por ID
  @GetMapping("/{id}")
  public Optional<RolPermiso> getRolPermisoById(@PathVariable Long id) {
    return rolPermisoService.getRolPermisoById(id);
  }

    // Obtener las relaciones entre rol y permiso por ID del rol
    @GetMapping("/rol/{rolId}")
    public List<RolPermiso> obtenerRelacionesRolPermisoPorRolId(@PathVariable Long rolId) {
        return rolPermisoService.obtenerRelacionesRolPermisoPorRolId(rolId);
    }
  // Crear un nuevo rolPermiso
  @PostMapping
  public RolPermiso createRolPermiso(@RequestBody RolPermiso rolPermiso) {
    return rolPermisoService.saveRolPermiso(rolPermiso);
  }

  // Actualizar un rolPermiso existente
  @PutMapping("/{id}")
  public ResponseEntity<RolPermiso> updateRolPermiso(@PathVariable Long id, @RequestBody RolPermiso rolPermiso){
    Optional<RolPermiso> rolPermisoOptional = rolPermisoService.getRolPermisoById(id);
    if(rolPermisoOptional.isPresent()){
      RolPermiso updatedRolPermiso = rolPermisoOptional.get();
      return ResponseEntity.ok(rolPermisoService.saveRolPermiso(updatedRolPermiso));
    }else{
      return ResponseEntity.notFound().build();
    }
  }
  // Eliminar un rol
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRolPermiso(@PathVariable Long id){
    Optional<RolPermiso> rolPermiso = rolPermisoService.getRolPermisoById(id);
    if(rolPermiso.isPresent()){
      rolPermisoService.deleteRolPermiso(id);
      return ResponseEntity.noContent().build();
    } else{
      return ResponseEntity.notFound().build();
    }
  }
}