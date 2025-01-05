package com.app.apptransito.api.controller;

import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

//    @Autowired
//    private ProprietarioRepository proprietarioRepository;

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioController(ProprietarioRepository proprietarioRepository){
        this.proprietarioRepository = proprietarioRepository;
    }

    @GetMapping
    public List<Proprietario> listar(){
        return proprietarioRepository.findAll();
    }


}
