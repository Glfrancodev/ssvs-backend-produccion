package com.ssvs.seguro_salud_vida_sana.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssvs.seguro_salud_vida_sana.models.Usuario;
import com.ssvs.seguro_salud_vida_sana.services.UsuarioService;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getUsuarios();
    }

    // Obtener el perfil del usuario autenticado
    @GetMapping("/perfil")
    public ResponseEntity<Usuario> obtenerPerfil(Authentication authentication) {
        String email = authentication.getName(); // Extrae el email del token JWT automáticamente
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorCorreo(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setContrasena(null); // Ocultar la contraseña antes de devolver la respuesta
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }
    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);
        if (usuarioOptional.isPresent()) {
          Usuario usuarioActualizado = usuarioOptional.get();
          usuarioActualizado.setNombre(usuario.getNombre());
          usuarioActualizado.setApellido(usuario.getApellido());
          usuarioActualizado.setCorreo(usuario.getCorreo());
          usuarioActualizado.setRol(usuario.getRol());
          usuarioActualizado.setEstaActivo(usuario.getEstaActivo());
          usuarioActualizado.setRol(usuario.getRol());
          return ResponseEntity.ok(usuarioService.updateUsuario(usuarioActualizado));
        } else {
          return ResponseEntity.notFound().build();
        }
    }

    // Desactivar un usuario (soft delete)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateUsuario(@PathVariable Long id) {
        usuarioService.toggleUsuarioStatus(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener usuarios por rol
    @GetMapping("/rol/{roleId}")
    public List<Usuario> obtenerUsuariosPorRol(@PathVariable Long roleId) {
        return usuarioService.getUsuariosByRol(roleId);
    }

    @GetMapping("/rol/{roleId}/sin-medico")
    public List<Usuario> obtenerUsuariosSinMedico(@PathVariable Long roleId) {
        return usuarioService.getUsuariosSinMedicoByRol(roleId);
    }

    @GetMapping("/rol/{roleId}/sin-asegurado")
    public ResponseEntity<List<Usuario>> obtenerUsuariosSinAsegurado(@PathVariable Long roleId) {
        List<Usuario> usuarios = usuarioService.getUsuariosSinAseguradoByRol(roleId);
        return ResponseEntity.ok(usuarios);
    }

}