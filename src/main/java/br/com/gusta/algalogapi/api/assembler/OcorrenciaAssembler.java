package br.com.gusta.algalogapi.api.assembler;

import br.com.gusta.algalogapi.api.model.OcorrenciaModel;
import br.com.gusta.algalogapi.domain.model.Ocorrencia;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OcorrenciaAssembler {

    private final ModelMapper mapper;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return mapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
