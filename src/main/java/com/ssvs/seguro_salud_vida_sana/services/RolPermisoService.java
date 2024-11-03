package com.ssvs.seguro_salud_vida_sana.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Permiso;
import com.ssvs.seguro_salud_vida_sana.models.RolPermiso;
import com.ssvs.seguro_salud_vida_sana.repositories.RolPermisoRepository;

import jakarta.transaction.Transactional;


@Service
public class RolPermisoService {
  @Autowired
  RolPermisoRepository rolPermisoRepository;

  // Obtener todos los rolpermisos
  public List<RolPermiso> getAllRolpermisos() {
    return rolPermisoRepository.findAll();
  }

  // Obtener un rolpermiso por ID
  public Optional<RolPermiso> getRolPermisoById(Long id) {
    return rolPermisoRepository.findById(id);
  }

  public List<Permiso> obtenerPermisosPorRolId(Long rolId) {
    List<RolPermiso> rolPermisos = rolPermisoRepository.findByRolId(rolId);
    return rolPermisos.stream()
            .map(RolPermiso::getPermiso)  // Obtener los permisos relacionados
            .collect(Collectors.toList());
  }
  // Guardar o actualizar un rolpermiso
  public RolPermiso saveRolPermiso(RolPermiso rolPermiso) {
    return rolPermisoRepository.save(rolPermiso);
  }
    // Método para obtener las relaciones rol-permiso por rolId
    public List<RolPermiso> obtenerRelacionesRolPermisoPorRolId(Long rolId) {
        return rolPermisoRepository.findByRolId(rolId);
    }
    @Transactional
    public void deleteRolPermiso(Long id) {
        rolPermisoRepository.deleteById(id);
    }
}
