package br.com.gusta.algalogapi.domain.service;

import br.com.gusta.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.gusta.algalogapi.domain.model.Entrega;
import br.com.gusta.algalogapi.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BuscaEntregaService {

    private final EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
