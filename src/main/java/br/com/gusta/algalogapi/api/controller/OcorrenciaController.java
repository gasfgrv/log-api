package br.com.gusta.algalogapi.api.controller;

import br.com.gusta.algalogapi.api.model.OcorrenciaModel;
import br.com.gusta.algalogapi.api.model.input.OcorrenciaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Ocorrências", description = "Endpoint para tratamento de Ocorrências relacionadas a uma entrega")
public interface OcorrenciaController {
        @Operation(summary = "Registrar ocorrencias", description = "Vincula uma ocorrência a uma entrega")
        @ApiResponse(responseCode = "201", description = "Ocorrência registrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaModel.class)))
        OcorrenciaModel registrar(
                        @Parameter(name = "entregaId", in = ParameterIn.PATH, description = "Id da entrega", required = true) Long entregaId,
                        @RequestBody(description = "dados da ocorrência", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaInput.class))) OcorrenciaInput ocorrenciaInput);

        @Operation(summary = "Listar ocorrências", description = "Listar todas as ocorrências relacionadas a uma entrega")
        @ApiResponse(responseCode = "200", description = "Todos as ocorrências", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaModel.class)))
        List<OcorrenciaModel> listar(
                        @Parameter(name = "entregaId", in = ParameterIn.PATH, description = "Id da entrega", required = true) Long entregaId);

}
