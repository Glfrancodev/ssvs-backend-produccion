package com.ssvs.seguro_salud_vida_sana.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssvs.seguro_salud_vida_sana.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
  Optional<Medico> findByItem(String item);
}
