package com.daniel.empresa.service;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.model.Empresa;
import com.daniel.empresa.model.SituacaoEnum;
import com.daniel.empresa.repository.EmpresaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmpresaServiceTest {

    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    private String basicAuth = "Basic RGFuaWVsOkxlaXRl";

    @Test
    public void credenciarEmpresa() {
        Empresa empresaMock = criarEmpresaMock("00000000000004");
        String cnpj = "00.000.000/0000-04";
        EmpresaRequest request = new EmpresaRequest("BRQ", cnpj);


        when(empresaRepository.findByCnpjAndSituacao(empresaMock.getCnpj(), SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(null);

        when(empresaRepository.save(Mockito.any(Empresa.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        EmpresaResponse response = empresaService.credenciar(request, basicAuth);

        Assertions.assertEquals(response.getCnpj(), cnpj);
        Assertions.assertNotNull(response.getUsuarioCriacao());
        Assertions.assertNotNull(response.getDataCriacao());
    }

    @Test
    public void alterarEmpresa(){
        String cnpj = "00.000.000/0000-04";
        String cnpjSemFormatacao = "00000000000004";
        Empresa empresaMock = criarEmpresaMock(cnpjSemFormatacao);


        EmpresaRequest request = new EmpresaRequest("BRQ", cnpj);

        when(empresaRepository.findByCnpjAndSituacao(cnpjSemFormatacao,SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(empresaMock);

        when(empresaRepository.save(Mockito.any(Empresa.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        EmpresaResponse response = empresaService.alterar(request, basicAuth);

        Assertions.assertNotNull(response.getDataAlteracao());
        Assertions.assertNotNull(response.getUsuarioAlteracao());
    }


    @Test
    public void deletarEmpresa(){

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(new Empresa()));

        String a = empresaService.deletar(1L,basicAuth);

        Assertions.assertEquals(a,"Removido com sucesso");
    }

    @Test
    public void removerEmpresa(){

        Empresa empresa = criarEmpresaMock("00000000000004");

        when(empresaRepository.save(Mockito.any(Empresa.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        Empresa removida = empresaService.remover(basicAuth,empresa);
        Assertions.assertNotNull(removida.getDataAlteracao());
        Assertions.assertNotNull(removida.getUsuarioAlteracao());
        Assertions.assertEquals(removida.getSituacao(),SituacaoEnum.APAGADO.getIndex());
    }





    private Empresa criarEmpresaMock(String cnpj) {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj(cnpj);
        return empresa;
    }


}