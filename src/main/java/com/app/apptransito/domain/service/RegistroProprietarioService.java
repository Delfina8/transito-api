package com.app.apptransito.domain.service;

import com.app.apptransito.domain.exception.NegocioException;
import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.repository.ProprietarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public RegistroProprietarioService(ProprietarioRepository proprietarioRepository){
        this.proprietarioRepository = proprietarioRepository;
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario){
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if(emailEmUso) {
            throw new NegocioException("Já existe um proprietário cadastrado com este e-mail");
        }
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId){
        proprietarioRepository.deleteById(proprietarioId);
    }

}