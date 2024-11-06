package com.ssvs.seguro_salud_vida_sana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssvs.seguro_salud_vida_sana.models.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
