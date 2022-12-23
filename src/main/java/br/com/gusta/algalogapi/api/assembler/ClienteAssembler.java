package br.com.gusta.algalogapi.api.assembler;

import br.com.gusta.algalogapi.api.model.ClienteModel;
import br.com.gusta.algalogapi.api.model.input.ClienteInput;
import br.com.gusta.algalogapi.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteAssembler {

    private final ModelMapper mapper;

    public Cliente toEntity(ClienteInput clienteInput) {
        return mapper.map(clienteInput, Cliente.class);
    }

    public ClienteModel toModel(Cliente cliente) {
        return mapper.map(cliente, ClienteModel.class);
    }

}
