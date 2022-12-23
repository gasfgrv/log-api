package br.com.gusta.algalogapi.domain.service;

import br.com.gusta.algalogapi.domain.model.Entrega;
import br.com.gusta.algalogapi.domain.model.StatusEntrega;
import br.com.gusta.algalogapi.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SolicitacaoEntregaService {

    private final CatalogoClienteService catalogoClienteService;
    private final EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        var cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

}
