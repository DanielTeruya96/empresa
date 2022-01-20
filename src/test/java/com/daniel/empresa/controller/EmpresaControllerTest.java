package com.daniel.empresa.controller;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.service.EmpresaService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
public class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController empresaController;

    @Mock
    private EmpresaService empresaService;

    private String basicAuth = "Basic RGFuaWVsOkxlaXRl";



    @Test
    public void consultaEmpresa() {
        List<EmpresaResponse> listEmpresa = mockListaEmpresa();
        when(this.empresaService.consultar()).thenReturn(listEmpresa);

        ResponseEntity<List<EmpresaResponse>> response = empresaController.consultarEmpresa();

        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void inserirEmpresa(){
        EmpresaRequest empresaRequest = new EmpresaRequest("BRQ","00.000.000/0000-00");
        EmpresaResponse empresaResponse = new EmpresaResponse(1L, "BRQ", "00.000.000/0000-00");
        when(this.empresaService.credenciar(empresaRequest,basicAuth)).thenReturn(empresaResponse);

        ResponseEntity<EmpresaResponse> response = empresaController.credenciarEmpresa(empresaRequest,basicAuth);

        Assertions.assertEquals(response.getStatusCode(),HttpStatus.CREATED);
    }

    @Test
    public void alterarEmpresa(){
        EmpresaRequest empresaRequest = new EmpresaRequest("BRQ","00.000.000/0000-00");
        EmpresaResponse empresaResponse = new EmpresaResponse(1L, "BRQ", "00.000.000/0000-00");

        when(this.empresaService.alterar(empresaRequest,basicAuth)).thenReturn(empresaResponse);

        ResponseEntity<EmpresaResponse> response = empresaController.alterarEmpresa(empresaRequest,basicAuth);
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void deletarEmpresa(){

        when(this.empresaService.deletar(1L,basicAuth)).thenReturn(basicAuth);

        ResponseEntity<String> response = empresaController.deletar(1L,basicAuth);

        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);


    }









    private List<EmpresaResponse> mockListaEmpresa() {
        List<EmpresaResponse> empresaResponseList = new ArrayList<>();
        empresaResponseList.add(new EmpresaResponse(1L, "BRQ", "00.000.000/0000-00"));
        empresaResponseList.add(new EmpresaResponse(2L, "BRQ1", "00.000.000/0000-01"));
        empresaResponseList.add(new EmpresaResponse(3L, "BRQ3", "00.000.000/0000-03"));

        return empresaResponseList;

    }


}
