package com.ssvs.seguro_salud_vida_sana.services;

import com.ssvs.seguro_salud_vida_sana.models.Permiso;
import com.ssvs.seguro_salud_vida_sana.models.RolPermiso;
import com.ssvs.seguro_salud_vida_sana.models.Usuario;
import com.ssvs.seguro_salud_vida_sana.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);
        Usuario usuario = usuarioOptional.orElseThrow(() ->
            new UsernameNotFoundException("Usuario no encontrado con correo: " + correo)
        );
 
        if (!usuario.getEstaActivo()) {
            throw new DisabledException("El usuario no está activo");
        }

        // Construir las autoridades del usuario
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Añadir el rol del usuario como autoridad
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombre())); // Ejemplo: "ROLE_SuperUsuario"

        // Opcional: Añadir los permisos asociados al rol del usuario
        for (RolPermiso rolPermiso : usuario.getRol().getRolPermisos()) {
            Permiso permiso = rolPermiso.getPermiso();
            authorities.add(new SimpleGrantedAuthority(permiso.getNombre())); // Ejemplo: "ver_empresa"
        }

        // Log para verificar las autoridades
        System.out.println("Autoridades para el usuario " + usuario.getCorreo() + ": " + authorities);

        return new User(usuario.getCorreo(), usuario.getContrasena(), authorities);
    }
}
