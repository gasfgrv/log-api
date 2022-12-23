package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.model.EntregaModel;
import br.com.gusta.algalogapi.api.model.input.EntregaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Entregas", description = "Endpoint para tratamento das entregas")
public interface EntregaController {

    @Operation(
            summary = "Solicitar entrega",
            description = "Vincula um clienate à entrega e cria a mesma"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrega solicitada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EntregaModel.class))
    )
    EntregaModel solicitar(@RequestBody(description = "Formulário de cadastro", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntregaInput.class))) EntregaInput entregaInput);

    @Operation(
            summary = "Finalizar entrega",
            description = "Finalizar uma determinada entregas a partir do id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrega finalizada"
    )
    void finalizar(@Parameter(name = "entregaId", in = ParameterIn.PATH, description = "Id da entrega", required = true) Long entregaId);

    @Operation(
            summary = "Listar entregas",
            description = "Listar todas as entregas salvas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Todas as entregas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = EntregaModel.class
                    )
            )
    )
    List<EntregaModel> listar();

    @Operation(
            summary = "Buscar entrega",
            description = "Buscar determinada entrega a partir do id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrega encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = EntregaModel.class
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Entrega não encontrada",
            content = @Content(
                    mediaType = "application/json")
    )
    ResponseEntity<EntregaModel> buscar(@Parameter(name = "entregaId", in = ParameterIn.PATH, description = "Id da entrega", required = true) Long entregaId);

}
