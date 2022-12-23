package br.com.gusta.algalogapi.domain.service;

import br.com.gusta.algalogapi.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FinalizacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId) {
        var entrega = buscaEntregaService.buscar(entregaId);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }

}
