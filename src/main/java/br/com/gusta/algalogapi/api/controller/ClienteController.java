package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.model.ClienteModel;
import br.com.gusta.algalogapi.api.model.ClienteResumoModel;
import br.com.gusta.algalogapi.api.model.input.ClienteInput;
import br.com.gusta.algalogapi.domain.model.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Clientes", description = "Endpoint para manipulação dos dados de um cliente")
public interface ClienteController {

        @Operation(summary = "Listar clientes", description = "Listar todos os clientes salvos")
        @ApiResponse(responseCode = "200", description = "Todos os clientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResumoModel.class)))
        List<ClienteResumoModel> listar();

        @Operation(summary = "Buscar cliente", description = "Buscar determinado cliente a partir do id")
        @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(mediaType = "application/json"))
        ResponseEntity<Cliente> buscar(
                        @Parameter(name = "clienteId", in = ParameterIn.PATH, description = "Id do cliente", required = true) Long clienteId);

        @Operation(summary = "Salvar cliente", description = "Salvar cliente na base")
        @ApiResponse(responseCode = "201", description = "Cliente Salvo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
        Cliente adicionar(
                        @RequestBody(description = "Formulário de cadastro", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteInput.class))) ClienteInput cliente);

        @Operation(summary = "Atualizar cliente", description = "Atualizar dados do cliente")
        @ApiResponse(responseCode = "200", description = "Cliente atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteModel.class)))
        ResponseEntity<ClienteModel> atualizar(
                        @Parameter(name = "clienteId", in = ParameterIn.PATH, description = "Id do cliente", required = true) Long clienteId,
                        @Valid @RequestBody(description = "Formulário de atualização", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteInput.class))) ClienteInput cliente);

        @Operation(summary = "Deletar cliente", description = "Deletar dados do cliente")
        @ApiResponse(responseCode = "204", description = "Cliente apagado", content = @Content(mediaType = "application/json"))
        ResponseEntity<Void> remover(
                        @Parameter(name = "clienteId", in = ParameterIn.PATH, description = "Id do cliente", required = true) Long clienteId);
}
