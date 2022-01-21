package com.daniel.empresa.controller;


import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@Api(tags = "Empresa", value = "Empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    @ApiOperation(value = "Consulta todas empresas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de clientes"),
            @ApiResponse(code = 500, message = "Erro não mapeado")


    })
    public ResponseEntity<List<EmpresaResponse>> consultarEmpresa(){
        return new ResponseEntity<>(empresaService.consultar(),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Credenciar uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna  Empresa cadastrada"),
            @ApiResponse(code = 500, message = "Erro não mapeado")


    })
    public ResponseEntity<EmpresaResponse> credenciarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization){
               return new ResponseEntity<>(empresaService.credenciar(empresaRequest,autorization), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Altera uma empresa que ja existe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna  Empresa alterada"),
            @ApiResponse(code = 415, message = "Retorna o motivo do erro"),
            @ApiResponse(code = 500, message = "Erro não mapeado")


    })
    public ResponseEntity<EmpresaResponse> alterarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization){
        return new ResponseEntity<>(empresaService.alterar(empresaRequest,autorization), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "Deleta uma empresa que ja existe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna  uma mensagem de sucesso"),
            @ApiResponse(code = 415, message = "Retorna o motivo do erro"),
            @ApiResponse(code = 500, message = "Erro não mapeado")


    })
    public ResponseEntity<String> deletar(@RequestParam long id,@RequestHeader(value = "Authorization") String autorization) {

        return new ResponseEntity<>(empresaService.deletar(id,autorization), HttpStatus.OK);
    }




}
