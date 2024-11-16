package com.ssvs.seguro_salud_vida_sana.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssvs.seguro_salud_vida_sana.services.ArchivoService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = {"http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/"})
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @PostMapping("/subir")
    public ResponseEntity<Map<String, String>> subirArchivo(@RequestParam("archivo") MultipartFile archivo,
                                                            @RequestParam("consultaId") Long consultaId) {
        try {
            // Validar el tamaño del archivo (50MB = 50 * 1024 * 1024 bytes)
            if (archivo.getSize() > 50 * 1024 * 1024) {
                return ResponseEntity.status(413).body(Map.of("error", "El archivo supera el tamaño máximo permitido de 50MB"));
            }
    
            // Subir el archivo a MinIO y obtener la URL
            String url = archivoService.subirArchivo(archivo.getOriginalFilename(),
                    archivo.getInputStream(), archivo.getContentType());
    
            // Guardar metadatos del archivo en la base de datos
            archivoService.registrarArchivoEnDB(archivo.getOriginalFilename(),
                    archivo.getContentType(), archivo.getSize(), url, consultaId);
    
            // Crear respuesta JSON con la URL
            Map<String, String> response = Map.of("url", url);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al subir el archivo: " + e.getMessage()));
        }
    }
    

    @GetMapping("/descargar/{nombreArchivo}")
    public ResponseEntity<?> descargarArchivo(@PathVariable String nombreArchivo) {
        try {
            InputStream archivoStream = archivoService.descargarArchivo(nombreArchivo);
    
            // Tipo MIME genérico. Puedes ajustar esto si tienes el tipo real del archivo.
            String tipo = "application/octet-stream";
    
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"")
                    .contentType(org.springframework.http.MediaType.parseMediaType(tipo))
                    .body(new InputStreamResource(archivoStream));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Archivo no encontrado: " + e.getMessage());
        }
    }
    


    @DeleteMapping("/eliminar/{nombreArchivo}")
    public ResponseEntity<String> eliminarArchivo(@PathVariable String nombreArchivo) {
        try {
            archivoService.eliminarArchivo(nombreArchivo);
            return ResponseEntity.ok("Archivo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el archivo: " + e.getMessage());
        }
    }
    
    // Endpoint para listar archivos por consulta
    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<Map<String, String>>> listarArchivosPorConsulta(@PathVariable Long consultaId) {
        List<Map<String, String>> archivos = archivoService.listarArchivosPorConsulta(consultaId);
        return ResponseEntity.ok(archivos);
    }

}
