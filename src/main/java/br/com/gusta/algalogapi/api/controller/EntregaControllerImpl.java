package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.assembler.EntregaAssembler;
import br.com.gusta.algalogapi.api.model.EntregaModel;
import br.com.gusta.algalogapi.api.model.input.EntregaInput;
import br.com.gusta.algalogapi.domain.repository.EntregaRepository;
import br.com.gusta.algalogapi.domain.service.FinalizacaoEntregaService;
import br.com.gusta.algalogapi.domain.service.SolicitacaoEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntregaControllerImpl implements EntregaController {

    private final EntregaRepository entregaRepository;
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final FinalizacaoEntregaService finalizacaoEntregaService;
    private final EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Caching(evict = {
            @CacheEvict(value = "entregas", allEntries = true),
            @CacheEvict(value = "entrega", allEntries = true)
    })
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        var novaEntrega = entregaAssembler.toEntity(entregaInput);
        var entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Caching(evict = {
            @CacheEvict(value = "entregas", allEntries = true),
            @CacheEvict(value = "entrega", allEntries = true)
    })
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    @Cacheable("entregas")
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    @Cacheable("entrega")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

}
