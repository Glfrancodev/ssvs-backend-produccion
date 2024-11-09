package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
  Optional<Usuario> findByCorreo(String correo);
  // Consulta para obtener usuarios según el rol
  List<Usuario> findByRolId(Long roleId);
}
