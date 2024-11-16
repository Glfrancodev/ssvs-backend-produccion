package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssvs.seguro_salud_vida_sana.models.Archivo;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    // Obtener todos los archivos relacionados con una consulta específica
    List<Archivo> findByConsultaId(Long consultaId);

    // Eliminar todos los archivos relacionados con una consulta específica
    void deleteByConsultaId(Long consultaId);
}
