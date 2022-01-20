package com.daniel.empresa.controller;


import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponse> credenciarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization) {
               return new ResponseEntity<>(empresaService.credenciar(empresaRequest,autorization), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmpresaResponse> alterarEmpresa(@RequestBody EmpresaRequest empresaRequest, @RequestHeader(value = "Authorization") String autorization){
        return new ResponseEntity<>(empresaService.alterar(empresaRequest,autorization), HttpStatus.OK);
    }




}
