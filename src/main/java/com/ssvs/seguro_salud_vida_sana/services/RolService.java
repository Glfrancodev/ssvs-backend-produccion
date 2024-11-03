package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Rol;
import com.ssvs.seguro_salud_vida_sana.repositories.RolRepository;;

@Service
public class RolService {
  @Autowired
  RolRepository rolRepository;

  // Obtener todos los roles
  public List<Rol> getAllRoles() {
    return rolRepository.findAll();
  }

  // Obtener un rol por ID
  public Optional<Rol> getRolById(Long id) {
    return rolRepository.findById(id);
  }

  // Guardar o actualizar un rol
  public Rol saveRol(Rol rol) {
    return rolRepository.save(rol);
  }

  // Eliminar un rol
  public void deleteRol(Long id) {
    rolRepository.deleteById(id);
  }
}
