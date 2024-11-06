package com.ssvs.seguro_salud_vida_sana.controllers;

import com.ssvs.seguro_salud_vida_sana.models.*;
import com.ssvs.seguro_salud_vida_sana.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;
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
    private MedicoRepository medicoRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private AseguradoRepository aseguradoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private CupoRepository cupoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void run(String... args) throws Exception {
        seedPermisos();
        seedRoles();
        seedRolPermisos();
        seedUsuarios();
        seedMedico();
        seedHistoriaClinica();
        seedAsegurado();
        seedEspecialidad();
        seedMedicoEspecialidad();
        seedHorario();
        seedCupo();
        seedConsulta();
        seedTratamiento();
        seedReceta();
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

                RolPermiso rp1 = new RolPermiso();
                rp1.setRol(superUsuario.get());
                rp1.setPermiso(crearRol.get());

                RolPermiso rp2 = new RolPermiso();
                rp2.setRol(superUsuario.get());
                rp2.setPermiso(verRol.get());

                RolPermiso rp3 = new RolPermiso();
                rp3.setRol(superUsuario.get());
                rp3.setPermiso(editarRol.get());

                RolPermiso rp4 = new RolPermiso();
                rp4.setRol(superUsuario.get());
                rp4.setPermiso(eliminarRol.get());

                RolPermiso rp7 = new RolPermiso();
                rp7.setRol(superUsuario.get());
                rp7.setPermiso(crearEspecialidad.get());

                RolPermiso rp8 = new RolPermiso();
                rp8.setRol(superUsuario.get());
                rp8.setPermiso(verEspecialidad.get());

                RolPermiso rp9 = new RolPermiso();
                rp9.setRol(superUsuario.get());
                rp9.setPermiso(editarEspecialidad.get());

                RolPermiso rp10 = new RolPermiso();
                rp10.setRol(superUsuario.get());
                rp10.setPermiso(eliminarEspecialidad.get());

                RolPermiso rp5 = new RolPermiso();
                rp5.setRol(asegurado.get());
                rp5.setPermiso(verEspecialidad.get());

                RolPermiso rp6 = new RolPermiso();
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
                u1.setCi("1234567");
                u1.setCorreo("superusuario1@gmail.com");
                u1.setContrasena(passwordEncoder.encode("Superusuario1pass"));
                u1.setNombre("Super");
                u1.setApellido("Usuario");
                u1.setEstaActivo(true);
                u1.setRol(superUsuario.get());

                Usuario u2 = new Usuario();
                u2.setCi("7654321");
                u2.setCorreo("asegurado1@gmail.com");
                u2.setContrasena(passwordEncoder.encode("asegurado1pass"));
                u2.setNombre("Asegurado");
                u2.setApellido("Pro");
                u2.setEstaActivo(true);
                u2.setRol(asegurado.get());

                Usuario u3 = new Usuario();
                u3.setCi("1111111");
                u3.setCorreo("medico1@gmail.com");
                u3.setContrasena(passwordEncoder.encode("medico1pass"));
                u3.setNombre("Medico");
                u3.setApellido("Vip");
                u3.setEstaActivo(true);
                u3.setRol(medico.get());

                Usuario u4 = new Usuario();
                u4.setCi("2222222");
                u4.setCorreo("gabrielLopezmedico@gmail.com");
                u4.setContrasena(passwordEncoder.encode("medico1pass"));
                u4.setNombre("Gabriel");
                u4.setApellido("López");
                u4.setEstaActivo(true);
                u4.setRol(medico.get());

                Usuario u5 = new Usuario();
                u5.setCi("33333333");
                u5.setCorreo("jackmedico@gmail.com");
                u5.setContrasena(passwordEncoder.encode("medico1pass"));
                u5.setNombre("Jack");
                u5.setApellido("López");
                u5.setEstaActivo(true);
                u5.setRol(medico.get());
                
                Usuario u6 = new Usuario();
                u6.setCi("4444444");
                u6.setCorreo("asegurado2@gmail.com");
                u6.setContrasena(passwordEncoder.encode("asegurado1pass"));
                u6.setNombre("Asegurado 2");
                u6.setApellido("Prueba");
                u6.setEstaActivo(true);
                u6.setRol(asegurado.get());

                Usuario u7 = new Usuario();
                u7.setCi("5555555");
                u7.setCorreo("asegurado3@gmail.com");
                u7.setContrasena(passwordEncoder.encode("asegurado1pass"));
                u7.setNombre("Asegurado 3");
                u7.setApellido("Prueba");
                u7.setEstaActivo(true);
                u7.setRol(asegurado.get());

                usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7));
            }
        }
    }
    private void seedMedico() {
        if (medicoRepository.count() == 0) {
            Optional<Usuario> usuario1 = usuarioRepository.findByCorreo("medico1@gmail.com");
            Optional<Usuario> usuario2 = usuarioRepository.findByCorreo("gabrielLopezmedico@gmail.com");
            Optional<Usuario> usuario3 = usuarioRepository.findByCorreo("jackmedico@gmail.com");
    
            if (usuario1.isPresent() && usuario2.isPresent() && usuario3.isPresent()) {
                Medico m1 = new Medico();
                m1.setItem("M001");
                m1.setUsuario(usuario1.get());

                Medico m2 = new Medico();
                m2.setItem("M002");
                m2.setUsuario(usuario2.get());
    
                Medico m3 = new Medico();
                m3.setItem("M003");
                m3.setUsuario(usuario3.get());
    
                medicoRepository.saveAll(Arrays.asList(m1, m2, m3));
            }
        }
    }
    
    private void seedHistoriaClinica() {
        if (historiaClinicaRepository.count() == 0) {

    
                HistoriaClinica hc1 = new HistoriaClinica();
    
                HistoriaClinica hc2 = new HistoriaClinica();

                HistoriaClinica hc3 = new HistoriaClinica();
    
                historiaClinicaRepository.saveAll(Arrays.asList(hc1, hc2, hc3));
        }
    }
    
    private void seedAsegurado() {
        if (aseguradoRepository.count() == 0) {
            Optional<Usuario> usuario1 = usuarioRepository.findByCorreo("asegurado1@gmail.com");
            Optional<Usuario> usuario2 = usuarioRepository.findByCorreo("asegurado2@gmail.com");
            Optional<Usuario> usuario3 = usuarioRepository.findByCorreo("asegurado3@gmail.com");
            Optional<HistoriaClinica> historiaclinica1 = historiaClinicaRepository.findById(1L);
            Optional<HistoriaClinica> historiaclinica2 = historiaClinicaRepository.findById(2L);
            Optional<HistoriaClinica> historiaclinica3 = historiaClinicaRepository.findById(3L);
    
            if (usuario1.isPresent() && usuario2.isPresent() && usuario3.isPresent()) {
                Asegurado a1 = new Asegurado();
                a1.setTipoSangre("O+");
                a1.setSexo('M');
                a1.setFechaNacimiento(LocalDate.of(1990, 5, 20));
                a1.setUsuario(usuario1.get());
                a1.setHistoriaClinica(historiaclinica1.get());
    
                Asegurado a2 = new Asegurado();
                a2.setTipoSangre("A-");
                a2.setSexo('F');
                a2.setFechaNacimiento(LocalDate.of(1985, 8, 15));
                a2.setUsuario(usuario2.get());
                a2.setHistoriaClinica(historiaclinica2.get());
    
                Asegurado a3 = new Asegurado();
                a3.setTipoSangre("B+");
                a3.setSexo('M');
                a3.setFechaNacimiento(LocalDate.of(2000, 12, 5));
                a3.setUsuario(usuario3.get());
                a3.setHistoriaClinica(historiaclinica3.get());
    
                aseguradoRepository.saveAll(Arrays.asList(a1, a2, a3));
            }
        }
    }
    
    private void seedEspecialidad() {
        if (especialidadRepository.count() == 0) {
            Especialidad e1 = new Especialidad();
            e1.setNombre("Cardiología");
            e1.setDescripcion("Especialidad en el tratamiento de enfermedades del corazón");
    
            Especialidad e2 = new Especialidad();
            e2.setNombre("Neurología");
            e2.setDescripcion("Especialidad en el tratamiento de enfermedades del sistema nervioso");
    
            Especialidad e3 = new Especialidad();
            e3.setNombre("Pediatría");
            e3.setDescripcion("Especialidad en el cuidado de la salud de los niños");
    
            especialidadRepository.saveAll(Arrays.asList(e1, e2, e3));
        }
    }

    private void seedMedicoEspecialidad() {
        if (medicoEspecialidadRepository.count() == 0) {
            Optional<Medico> medico1 = medicoRepository.findById(1L);
            Optional<Medico> medico2 = medicoRepository.findById(2L);
            Optional<Medico> medico3 = medicoRepository.findById(3L);
    
            Optional<Especialidad> cardiologia = especialidadRepository.findByNombre("Cardiología");
            Optional<Especialidad> neurologia = especialidadRepository.findByNombre("Neurología");
            Optional<Especialidad> pediatria = especialidadRepository.findByNombre("Pediatría");
    
            if (medico1.isPresent() && medico2.isPresent() && medico3.isPresent() &&
                cardiologia.isPresent() && neurologia.isPresent() && pediatria.isPresent()) {
    
                MedicoEspecialidad me1 = new MedicoEspecialidad();
                me1.setMedico(medico1.get());
                me1.setEspecialidad(cardiologia.get());
    
                MedicoEspecialidad me2 = new MedicoEspecialidad();
                me2.setMedico(medico2.get());
                me2.setEspecialidad(neurologia.get());
    
                MedicoEspecialidad me3 = new MedicoEspecialidad();
                me3.setMedico(medico3.get());
                me3.setEspecialidad(pediatria.get());
    
                medicoEspecialidadRepository.saveAll(Arrays.asList(me1, me2, me3));
            }
        }
    }        

    private void seedHorario() {
        if (horarioRepository.count() == 0) {
            Optional<MedicoEspecialidad> medicoEspecialidad1 = medicoEspecialidadRepository.findById(1L);
            Optional<MedicoEspecialidad> medicoEspecialidad2 = medicoEspecialidadRepository.findById(2L);
            Optional<MedicoEspecialidad> medicoEspecialidad3 = medicoEspecialidadRepository.findById(3L);
            Horario h1 = new Horario();
            h1.setFecha(LocalDate.of(2024, 1, 15));
            h1.setHoraInicio(LocalTime.of(9, 0));
            h1.setHoraFinal(LocalTime.of(17, 0));
            h1.setMedicoEspecialidad(medicoEspecialidad1.get());
    
            Horario h2 = new Horario();
            h2.setFecha(LocalDate.of(2024, 1, 16));
            h2.setHoraInicio(LocalTime.of(10, 0));
            h2.setHoraFinal(LocalTime.of(18, 0));
            h2.setMedicoEspecialidad(medicoEspecialidad2.get());
    
            Horario h3 = new Horario();
            h3.setFecha(LocalDate.of(2024, 1, 17));
            h3.setHoraInicio(LocalTime.of(8, 0));
            h3.setHoraFinal(LocalTime.of(16, 0));
            h3.setMedicoEspecialidad(medicoEspecialidad3.get());
    
            horarioRepository.saveAll(Arrays.asList(h1, h2, h3));
        }
    }
    
    private void seedCupo() {
        if (cupoRepository.count() == 0) {
            Optional<Horario> horario1 = horarioRepository.findById(1L);
            Optional<Horario> horario2 = horarioRepository.findById(2L);
            Optional<Horario> horario3 = horarioRepository.findById(3L);
    
            Optional<Asegurado> asegurado1 = aseguradoRepository.findById(1L);
            Optional<Asegurado> asegurado2 = aseguradoRepository.findById(2L);
    
            if (horario1.isPresent() && horario2.isPresent() && horario3.isPresent()) {
                Cupo c1 = new Cupo();
                c1.setNumero(1);
                c1.setFechaReservado(LocalDate.now());
                c1.setHora(LocalTime.of(9, 0));
                c1.setEstado("Reservado");
                c1.setHorario(horario1.get());
                c1.setAsegurado(asegurado1.orElse(null));
    
                Cupo c2 = new Cupo();
                c2.setNumero(1);
                c2.setFechaReservado(LocalDate.now());
                c2.setHora(LocalTime.of(10, 0));
                c2.setEstado("Reservado");
                c2.setHorario(horario2.get());
                c2.setAsegurado(asegurado2.orElse(null));
    
                Cupo c3 = new Cupo();
                c3.setNumero(1);
                c3.setHora(LocalTime.of(8, 0));
                c3.setEstado("Libre");
                c3.setHorario(horario3.get());
                cupoRepository.saveAll(Arrays.asList(c1, c2, c3));
            }
        }
    }

    private void seedConsulta() {
        if (consultaRepository.count() == 0) {
            Optional<Cupo> cupo1 = cupoRepository.findById(1L);
            Optional<Cupo> cupo2 = cupoRepository.findById(2L);
            Optional<HistoriaClinica> historia1 = historiaClinicaRepository.findById(1L);
            Optional<HistoriaClinica> historia2 = historiaClinicaRepository.findById(2L);
    
            if (cupo1.isPresent() && cupo2.isPresent() && historia1.isPresent() && historia2.isPresent()) {
                Consulta consulta1 = new Consulta();
                consulta1.setFechaConsulta(LocalDate.of(2024, 3, 15));
                consulta1.setMotivoConsulta("Revisión general");
                consulta1.setDiagnostico("Buena salud");
                consulta1.setNota("Sin observaciones adicionales");
                consulta1.setCupo(cupo1.get());
                consulta1.setHistoriaClinica(historia1.get());
    
                Consulta consulta2 = new Consulta();
                consulta2.setFechaConsulta(LocalDate.of(2024, 4, 20));
                consulta2.setMotivoConsulta("Dolor de cabeza");
                consulta2.setDiagnostico("Migraña leve");
                consulta2.setNota("Recomendar descanso y tratamiento adicional");
                consulta2.setCupo(cupo2.get());
                consulta2.setHistoriaClinica(historia2.get());
    
                consultaRepository.saveAll(Arrays.asList(consulta1, consulta2));
            }
        }
    }
    
    private void seedTratamiento() {
        if (tratamientoRepository.count() == 0) {
            Optional<Consulta> consulta1 = consultaRepository.findById(1L);
            Optional<Consulta> consulta2 = consultaRepository.findById(2L);
    
            if (consulta1.isPresent() && consulta2.isPresent()) {
                Tratamiento tratamiento1 = new Tratamiento();
                tratamiento1.setFecha(LocalDate.of(2024, 3, 15));
                tratamiento1.setConsulta(consulta1.get());
    
                Tratamiento tratamiento2 = new Tratamiento();
                tratamiento2.setFecha(LocalDate.of(2024, 4, 20));
                tratamiento2.setConsulta(consulta2.get());
    
                tratamientoRepository.saveAll(Arrays.asList(tratamiento1, tratamiento2));
            }
        }
    }
    
    private void seedReceta() {
        if (recetaRepository.count() == 0) {
            Optional<Tratamiento> tratamiento1 = tratamientoRepository.findById(1L);
            Optional<Tratamiento> tratamiento2 = tratamientoRepository.findById(2L);
    
            if (tratamiento1.isPresent() && tratamiento2.isPresent()) {
                Receta receta1 = new Receta();
                receta1.setMedicamento("Paracetamol");
                receta1.setFrecuencia("Cada 8 horas");
                receta1.setFechaInicio(LocalDate.of(2024, 3, 16));
                receta1.setFechaFinal(LocalDate.of(2024, 3, 22));
                receta1.setTratamiento(tratamiento1.get());
    
                Receta receta2 = new Receta();
                receta2.setMedicamento("Ibuprofeno");
                receta2.setFrecuencia("Cada 12 horas");
                receta2.setFechaInicio(LocalDate.of(2024, 4, 21));
                receta2.setFechaFinal(LocalDate.of(2024, 4, 27));
                receta2.setTratamiento(tratamiento2.get());
    
                recetaRepository.saveAll(Arrays.asList(receta1, receta2));
            }
        }
    }
    
    
}