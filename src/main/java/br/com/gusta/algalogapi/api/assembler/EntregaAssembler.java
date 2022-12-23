package br.com.gusta.algalogapi.api.assembler;

import br.com.gusta.algalogapi.api.model.EntregaModel;
import br.com.gusta.algalogapi.api.model.input.EntregaInput;
import br.com.gusta.algalogapi.domain.model.Entrega;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntregaAssembler {

    private final ModelMapper mapper;

    public EntregaModel toModel(Entrega entrega) {
        return mapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return mapper.map(entregaInput, Entrega.class);
    }

}
