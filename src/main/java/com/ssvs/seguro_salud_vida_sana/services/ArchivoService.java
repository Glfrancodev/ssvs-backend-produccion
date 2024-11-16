package com.ssvs.seguro_salud_vida_sana.services;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ssvs.seguro_salud_vida_sana.models.Archivo;
import com.ssvs.seguro_salud_vida_sana.models.Consulta;
import com.ssvs.seguro_salud_vida_sana.repositories.ArchivoRepository;
import com.ssvs.seguro_salud_vida_sana.repositories.ConsultaRepository;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArchivoService {

    @Value("${minio.public.endpoint}")
    private String publicEndpoint;


    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    private final String bucket = "archivos-consultas";

    public String subirArchivo(String nombreArchivo, InputStream archivoStream, String tipo) throws Exception {
        // Subir el archivo al bucket
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(nombreArchivo)
                .contentType(tipo)
                .stream(archivoStream, archivoStream.available(), -1)
                .build());
    
        // Construir manualmente la URL pública del archivo
        String urlArchivo = String.format("%s/%s/%s", publicEndpoint, bucket, nombreArchivo);
        return urlArchivo;
    }
    
    public InputStream descargarArchivo(String nombreArchivo) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(nombreArchivo)
                .build());
    }

    public void eliminarArchivo(String nombreArchivo) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(nombreArchivo)
                .build());
    }


    public void registrarArchivoEnDB(String nombre, String tipo, Long tamanio, String url, Long consultaId) {
        // Verificar si la consulta existe
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new IllegalArgumentException("Consulta no encontrada con ID: " + consultaId));

        // Crear la entidad Archivo y guardar en la base de datos
        Archivo archivo = new Archivo();
        archivo.setNombre(nombre);
        archivo.setTipo(tipo);
        archivo.setTamanio(tamanio);
        archivo.setUrl(url);
        archivo.setConsulta(consulta);

        archivoRepository.save(archivo);
    }

    // Método para listar los archivos de una consulta (solo nombre y tipo)
    public List<Map<String, String>> listarArchivosPorConsulta(Long consultaId) {
        return archivoRepository.findByConsultaId(consultaId).stream()
                .map(archivo -> {
                    Map<String, String> archivoInfo = new HashMap<>();
                    archivoInfo.put("nombre", archivo.getNombre());
                    archivoInfo.put("tipo", archivo.getTipo());
                    return archivoInfo;
                })
                .collect(Collectors.toList());
    }

}
