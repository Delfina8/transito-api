package com.app.apptransito.api.controller;

import com.app.apptransito.domain.exception.NegocioException;
import com.app.apptransito.domain.model.Veiculo;
import com.app.apptransito.domain.repository.VeiculoRepository;
import com.app.apptransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;

    public VeiculoController (VeiculoRepository veiculoRepository, RegistroVeiculoService registroVeiculoService) {
        this.veiculoRepository = veiculoRepository;
        this.registroVeiculoService = registroVeiculoService;
    }

    @GetMapping
    public List<Veiculo> buscarTodos(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return registroVeiculoService.cadastrar(veiculo);
    }


}
