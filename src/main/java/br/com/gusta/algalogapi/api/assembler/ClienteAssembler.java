package br.com.gusta.algalogapi.api.assembler;

import br.com.gusta.algalogapi.api.model.ClienteModel;
import br.com.gusta.algalogapi.api.model.ClienteResumoModel;
import br.com.gusta.algalogapi.api.model.input.ClienteInput;
import br.com.gusta.algalogapi.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteAssembler {

    private final ModelMapper mapper;

    public Cliente toEntity(ClienteInput clienteInput) {
        return mapper.map(clienteInput, Cliente.class);
    }

    public List<ClienteResumoModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> {
                    var model = new ClienteResumoModel();
                    model.setId(cliente.getId());
                    model.setNome(cliente.getNome());
                    return model;
                })
                .collect(Collectors.toList());
    }

    public ClienteModel toModel(Cliente cliente) {
        return mapper.map(cliente, ClienteModel.class);
    }

}
