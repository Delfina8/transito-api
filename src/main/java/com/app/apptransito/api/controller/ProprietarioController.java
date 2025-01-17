package com.app.apptransito.api.controller;

import com.app.apptransito.domain.exception.NegocioException;
import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.repository.ProprietarioRepository;
import com.app.apptransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

//    @Autowired
//    private ProprietarioRepository proprietarioRepository;

    private final ProprietarioRepository proprietarioRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @GetMapping
    public List<Proprietario> buscarTodos() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{proprietarioId}")
//    public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long proprietarioId){
//        return proprietarioRepository.findById(proprietarioId)
//                .map(response -> ResponseEntity.ok(response))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario cadastrar(@Valid @RequestBody Proprietario proprietario) {
        return registroProprietarioService.salvar(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @Valid @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }


}