package com.ssvs.seguro_salud_vida_sana.controllers;

import com.ssvs.seguro_salud_vida_sana.models.*;
import com.ssvs.seguro_salud_vida_sana.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/seeder")
// @CrossOrigin(origins = "https://stylo-store-git-master-gabriels-projects-9c5cda58.vercel.app")
public class SeederController{

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void run(String... args) throws Exception {
        seedPermisos();
        seedRoles();
        seedRolPermisos();
        seedUsuarios();
    }

    private void seedPermisos() {
        if (permisoRepository.count() == 0) {
            Permiso p1 = new Permiso();
            p1.setNombre("crear_rol");
            p1.setDescripcion("Permite crear los datos del rol");

            Permiso p2 = new Permiso();
            p2.setNombre("ver_rol");
            p2.setDescripcion("Permite ver los datos del rol");

            Permiso p3 = new Permiso();
            p3.setNombre("editar_rol");
            p3.setDescripcion("Permite editar los datos del rol");

            Permiso p4 = new Permiso();
            p4.setNombre("eliminar_rol");
            p4.setDescripcion("Permite eliminar los datos del rol");

            Permiso p6 = new Permiso();
            p6.setNombre("crear_especialidad");
            p6.setDescripcion("Permite crear los datos de la especialidad");

            Permiso p7 = new Permiso();
            p7.setNombre("ver_especialidad");
            p7.setDescripcion("Permite ver los datos de la especialidad");

            Permiso p8 = new Permiso();
            p8.setNombre("editar_especialidad");
            p8.setDescripcion("Permite editar los datos de la especialidad");

            Permiso p9 = new Permiso();
            p9.setNombre("eliminar_especialidad");
            p9.setDescripcion("Permite eliminar los datos de la especialidad");


            permisoRepository.saveAll(Arrays.asList(p1, p2, p3, p4,p6,p7,p8,p9));
        }
    }

    private void seedRoles() {
        if (rolRepository.count() == 0) {
            Rol r1 = new Rol();
            r1.setNombre("SuperUsuario");

            Rol r2 = new Rol();
            r2.setNombre("Asegurado");

            Rol r3 = new Rol();
            r3.setNombre("Medico");

            rolRepository.saveAll(Arrays.asList(r1, r2, r3));
        }
    }

    private void seedRolPermisos() {
        if (rolPermisoRepository.count() == 0) {
            // Obtener roles y permisos
            Optional<Rol> superUsuario = rolRepository.findByNombre("SuperUsuario");
            Optional<Rol> asegurado = rolRepository.findByNombre("Asegurado");
            Optional<Rol> medico = rolRepository.findByNombre("Medico");

            Optional<Permiso> crearRol = permisoRepository.findByNombre("crear_rol");
            Optional<Permiso> verRol = permisoRepository.findByNombre("ver_rol");
            Optional<Permiso> editarRol = permisoRepository.findByNombre("editar_rol");
            Optional<Permiso> eliminarRol = permisoRepository.findByNombre("eliminar_rol");
            Optional<Permiso> crearEspecialidad = permisoRepository.findByNombre("crear_especialidad");
            Optional<Permiso> verEspecialidad = permisoRepository.findByNombre("ver_especialidad");
            Optional<Permiso> editarEspecialidad = permisoRepository.findByNombre("editar_especialidad");
            Optional<Permiso> eliminarEspecialidad = permisoRepository.findByNombre("eliminar_especialidad");

            if (superUsuario.isPresent() && asegurado.isPresent() && medico.isPresent()
                    && crearRol.isPresent() && verRol.isPresent()
                    && editarRol.isPresent() && eliminarRol.isPresent()) {

                LocalDate fechaAsignacion = LocalDate.of(2024, 11, 02);

                RolPermiso rp1 = new RolPermiso();
                rp1.setFechaAsignacion(fechaAsignacion);
                rp1.setRol(superUsuario.get());
                rp1.setPermiso(crearRol.get());

                RolPermiso rp2 = new RolPermiso();
                rp2.setFechaAsignacion(fechaAsignacion);
                rp2.setRol(superUsuario.get());
                rp2.setPermiso(verRol.get());

                RolPermiso rp3 = new RolPermiso();
                rp3.setFechaAsignacion(fechaAsignacion);
                rp3.setRol(superUsuario.get());
                rp3.setPermiso(editarRol.get());

                RolPermiso rp4 = new RolPermiso();
                rp4.setFechaAsignacion(fechaAsignacion);
                rp4.setRol(superUsuario.get());
                rp4.setPermiso(eliminarRol.get());

                RolPermiso rp7 = new RolPermiso();
                rp7.setFechaAsignacion(fechaAsignacion);
                rp7.setRol(superUsuario.get());
                rp7.setPermiso(crearEspecialidad.get());

                RolPermiso rp8 = new RolPermiso();
                rp8.setFechaAsignacion(fechaAsignacion);
                rp8.setRol(superUsuario.get());
                rp8.setPermiso(verEspecialidad.get());

                RolPermiso rp9 = new RolPermiso();
                rp9.setFechaAsignacion(fechaAsignacion);
                rp9.setRol(superUsuario.get());
                rp9.setPermiso(editarEspecialidad.get());

                RolPermiso rp10 = new RolPermiso();
                rp10.setFechaAsignacion(fechaAsignacion);
                rp10.setRol(superUsuario.get());
                rp10.setPermiso(eliminarEspecialidad.get());

                RolPermiso rp5 = new RolPermiso();
                rp5.setFechaAsignacion(fechaAsignacion);
                rp5.setRol(asegurado.get());
                rp5.setPermiso(verEspecialidad.get());

                RolPermiso rp6 = new RolPermiso();
                rp6.setFechaAsignacion(fechaAsignacion);
                rp6.setRol(medico.get());
                rp6.setPermiso(verEspecialidad.get());

                rolPermisoRepository.saveAll(Arrays.asList(rp1, rp2, rp3, rp4, rp5, rp6, rp7, rp8, rp9, rp10));
            }
        }
    }

    private void seedUsuarios() {
        if (usuarioRepository.count() == 0) {
            Optional<Rol> superUsuario = rolRepository.findByNombre("SuperUsuario");
            Optional<Rol> asegurado = rolRepository.findByNombre("Asegurado");
            Optional<Rol> medico = rolRepository.findByNombre("Medico");

            if (superUsuario.isPresent() && asegurado.isPresent() && medico.isPresent()) {
                Usuario u1 = new Usuario();
                u1.setCorreo("superusuario1@gmail.com");
                u1.setContrasena(passwordEncoder.encode("Superusuario1pass"));
                u1.setNombre("Super");
                u1.setApellido("Usuario");
                u1.setSexo('M');
                u1.setEstaActivo(true);
                u1.setRol(superUsuario.get());

                Usuario u2 = new Usuario();
                u2.setCorreo("asegurado1@gmail.com");
                u2.setContrasena(passwordEncoder.encode("asegurado1pass"));
                u2.setNombre("Asegurado");
                u2.setApellido("Pro");
                u2.setSexo('M');
                u2.setEstaActivo(true);
                u2.setRol(asegurado.get());

                Usuario u3 = new Usuario();
                u3.setCorreo("medico1@gmail.com");
                u3.setContrasena(passwordEncoder.encode("medico1pass"));
                u3.setNombre("Medico");
                u3.setApellido("Vip");
                u3.setSexo('F');
                u3.setEstaActivo(true);
                u3.setRol(medico.get());

                Usuario u4 = new Usuario();
                u4.setCorreo("gabrielLopezmedico@gmail.com");
                u4.setContrasena(passwordEncoder.encode("medico1pass"));
                u4.setNombre("Gabriel");
                u4.setApellido("López");
                u4.setSexo('M');
                u4.setEstaActivo(true);
                u4.setRol(medico.get());

                Usuario u5 = new Usuario();
                u5.setCorreo("jackmedico@gmail.com");
                u5.setContrasena(passwordEncoder.encode("medico1pass"));
                u5.setNombre("Jack");
                u5.setApellido("López");
                u5.setSexo('M');
                u5.setEstaActivo(true);
                u5.setRol(medico.get());

                usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
            }
        }
    }
}