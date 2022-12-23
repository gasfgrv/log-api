package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.assembler.ClienteAssembler;
import br.com.gusta.algalogapi.api.model.ClienteModel;
import br.com.gusta.algalogapi.api.model.ClienteResumoModel;
import br.com.gusta.algalogapi.api.model.input.ClienteInput;
import br.com.gusta.algalogapi.domain.model.Cliente;
import br.com.gusta.algalogapi.domain.repository.ClienteRepository;
import br.com.gusta.algalogapi.domain.service.CatalogoClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/clientes")

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteControllerImpl implements ClienteController {

    private final ClienteRepository clienteRepository;
    private final CatalogoClienteService catalogoClienteService;
    private final ClienteAssembler clienteAssembler;

    @GetMapping
    @Cacheable("clientes")
    public List<ClienteResumoModel> listar() {
        var clientes = clienteRepository.findAll();
        return clienteAssembler.toCollectionModel(clientes);
    }

    @GetMapping("/{clienteId}")
    @Cacheable("cliente")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Caching(evict = {
            @CacheEvict("clientes"),
            @CacheEvict("cliente")
    })
    public Cliente adicionar(@Valid @RequestBody ClienteInput cliente) {
        var clienteEntity = clienteAssembler.toEntity(cliente);
        return catalogoClienteService.salvar(clienteEntity);
    }

    @PutMapping("/{clienteId}")
    @Caching(evict = {
            @CacheEvict("clientes"),
            @CacheEvict("cliente")
    })
    public ResponseEntity<ClienteModel> atualizar(@PathVariable Long clienteId, @Valid @RequestBody ClienteInput cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);

        var clienteEntity = clienteAssembler.toEntity(cliente);
        var clienteSalvo = catalogoClienteService.salvar(clienteEntity);

        return ResponseEntity.ok(clienteAssembler.toModel(clienteSalvo));
    }

    @DeleteMapping("/{clienteId}")
    @Caching(evict = {
            @CacheEvict("clientes"),
            @CacheEvict("cliente")
    })
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }

}
