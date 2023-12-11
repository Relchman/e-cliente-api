package com.estudos.microservice.eclienteapi.api.controller;

import com.estudos.microservice.eclienteapi.domain.model.Cliente;
import com.estudos.microservice.eclienteapi.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "recursos relacionados ao cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    @ResponseBody
    @Operation(description = "Buscar clientes sem paginação", summary = "Buscar clientes sem paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))
            ),
    })
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }


    @PostMapping
    @Operation(description = "Criar cliente", summary = "Criar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request")
    })
    public ResponseEntity<Cliente> createAgrupamentoDeLocais(@RequestBody @Valid Cliente clienteRequest,
                                                                                      UriComponentsBuilder uriBuilder) {

        Cliente cliente = clienteService.create(clienteRequest);

        URI uri = uriBuilder.path("/cliente/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }





}

