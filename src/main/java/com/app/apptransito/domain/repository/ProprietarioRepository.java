package com.app.apptransito.domain.repository;

import com.app.apptransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    List<Proprietario> findByNome(String nome);

    List<Proprietario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
