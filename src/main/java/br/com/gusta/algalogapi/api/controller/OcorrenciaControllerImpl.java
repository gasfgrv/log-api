package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.assembler.OcorrenciaAssembler;
import br.com.gusta.algalogapi.api.model.OcorrenciaModel;
import br.com.gusta.algalogapi.api.model.input.OcorrenciaInput;
import br.com.gusta.algalogapi.domain.service.BuscaEntregaService;
import br.com.gusta.algalogapi.domain.service.RegistroOcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OcorrenciaControllerImpl implements OcorrenciaController {

    private final BuscaEntregaService buscaEntregaService;
    private final RegistroOcorrenciaService registroOcorrenciaService;
    private final OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict("ocorrencias")
    public OcorrenciaModel registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        var ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    @Cacheable("ocorrencias")
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        var entrega = buscaEntregaService.buscar(entregaId);
        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
