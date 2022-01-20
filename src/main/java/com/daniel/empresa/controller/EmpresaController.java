package com.daniel.empresa.controller;


import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping()
    public ResponseEntity<List<EmpresaResponse>> consultarEmpresa(){
        return new ResponseEntity<>(empresaService.consultar(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpresaResponse> credenciarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization){
               return new ResponseEntity<>(empresaService.credenciar(empresaRequest,autorization), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmpresaResponse> alterarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization){
        return new ResponseEntity<>(empresaService.alterar(empresaRequest,autorization), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletar(@RequestParam long id,@RequestHeader(value = "Authorization") String autorization) {

        return new ResponseEntity<>(empresaService.deletar(id,autorization), HttpStatus.OK);
    }




}
