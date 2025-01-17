package com.app.apptransito.domain.service;

import com.app.apptransito.domain.exception.NegocioException;
import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.model.StatusVeiculo;
import com.app.apptransito.domain.model.Veiculo;
import com.app.apptransito.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public RegistroVeiculoService (VeiculoRepository veiculoRepository, RegistroProprietarioService registroProprietarioService) {
        this.veiculoRepository = veiculoRepository;
        this.registroProprietarioService = registroProprietarioService;
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
        if(veiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um ID");
        }
/*        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca())
                        .filter(v -> !v.equals(veiculo))
                        .isPresent();
 */
        Veiculo veiculoEncontrado = veiculoRepository.findByPlaca(veiculo.getPlaca()).orElse(null);
        boolean placaEmUso = veiculoEncontrado != null && !veiculoEncontrado.equals(veiculo);

        if(placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(veiculo);
    }


}
