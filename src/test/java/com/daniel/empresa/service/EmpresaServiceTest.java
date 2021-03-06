package com.daniel.empresa.service;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.exception.BasicException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmpresaServiceTest {

    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    private final String basicAuth = "Basic RGFuaWVsOkxlaXRl";

    @Test
    public void validarSucesso(){
        String cnpjSemFormatacao = "00000000000004";

        naoEncontrarEmpresa(cnpjSemFormatacao);
        empresaService.validar(criarEmpresaMock(cnpjSemFormatacao));
        //nao pode ocorrer o Basic exception aqui
    }


    @Test
    public void validarErro(){
        String cnpjSemFormatacao = "00000000000004";

        when(empresaRepository.findByCnpjAndSituacao(cnpjSemFormatacao,SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(new Empresa());

        BasicException exception = assertThrows(BasicException.class, () -> empresaService.validar(criarEmpresaMock(cnpjSemFormatacao)));
        assertEquals("CNPJ ja cadastrado", exception.getMotivo());
    }

    @Test
    public void getUsuario(){
        String usuario = empresaService.getUsuario(basicAuth);
        Assertions.assertEquals("Daniel",usuario);
    }



    @Test
    public void credenciarEmpresa() {
        Empresa empresaMock = criarEmpresaMock("00000000000004");
        String cnpj = "00.000.000/0000-04";
        EmpresaRequest request = new EmpresaRequest("BRQ", cnpj);


        naoEncontrarEmpresa(empresaMock.getCnpj());

        mockSaveEmpresa();


        EmpresaResponse response = empresaService.credenciar(request, basicAuth);

        assertEquals(response.getCnpj(), cnpj);
        Assertions.assertNotNull(response.getUsuarioCriacao());
        Assertions.assertNotNull(response.getDataCriacao());
    }

    private void mockSaveEmpresa() {
        when(empresaRepository.save(Mockito.any(Empresa.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void alterarEmpresa(){
        String cnpj = "00.000.000/0000-04";
        String cnpjSemFormatacao = "00000000000004";
        Empresa empresaMock = criarEmpresaMock(cnpjSemFormatacao);


        EmpresaRequest request = new EmpresaRequest("BRQ", cnpj);

        when(empresaRepository.findByCnpjAndSituacao(cnpjSemFormatacao,SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(empresaMock);

        mockSaveEmpresa();

        EmpresaResponse response = empresaService.alterar(request, basicAuth);

        Assertions.assertNotNull(response.getDataAlteracao());
        Assertions.assertNotNull(response.getUsuarioAlteracao());
    }

    @Test
    public void alterarEmpresaQuandoNaoExisteCnpj(){
        String cnpj = "00.000.000/0000-04";
        String cnpjSemFormatacao = "00000000000004";
        EmpresaRequest request = new EmpresaRequest("BRQ", cnpj);

        naoEncontrarEmpresa(cnpjSemFormatacao);

        BasicException exception = Assertions.assertThrows(BasicException.class,
                () -> empresaService.alterar(request,basicAuth));

        assertEquals(exception.getMotivo(),"n??o foi encontrado uma empresa com esse cnpj!");
    }

    private void naoEncontrarEmpresa(String cnpjSemFormatacao) {
        when(empresaRepository.findByCnpjAndSituacao(cnpjSemFormatacao,SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(null);
    }


    @Test
    public void deletarEmpresa(){

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(new Empresa()));

        String a = empresaService.deletar(1L,basicAuth);

        assertEquals(a,"Removido com sucesso");
    }

    @Test
    public void removerEmpresa(){

        Empresa empresa = criarEmpresaMock("00000000000004");

        mockSaveEmpresa();


        Empresa removida = empresaService.remover(basicAuth,empresa);
        Assertions.assertNotNull(removida.getDataAlteracao());
        Assertions.assertNotNull(removida.getUsuarioAlteracao());
        assertEquals(removida.getSituacao(),SituacaoEnum.APAGADO.getIndex());
    }

    @Test
    public void consultarEmpresa(){
        List<Empresa> empresas = mockListaEmpresa();

        when(empresaRepository.findBySituacao(SituacaoEnum.CRIADO.getIndex()))
                .thenReturn(empresas);

        List<EmpresaResponse> empresaResponses = empresaService.consultar();
        assertEquals(empresaResponses.size(),empresas.size());
        empresaResponses.forEach(emp -> Assertions.assertTrue(emp.getCnpj().matches("^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$")));


    }

    @Test
    void camposInvalido() {
        EmpresaRequest empresaRequest = new EmpresaRequest("aa","00");
        BasicException exception = assertThrows(BasicException.class, () -> empresaService.validarCampsObrigatorio(empresaRequest));
        assertEquals("cnpj informar cnpj com o formato XX.XXX.XXX/XXXX-XX", exception.getMotivo());
    }



    private List<Empresa> mockListaEmpresa() {

        List<Empresa> empresas = new ArrayList<>();
        empresas.add(criarEmpresaMock("00000000000001"));
        empresas.add(criarEmpresaMock("00000000000002"));
        empresas.add(criarEmpresaMock("00000000000003"));
        empresas.add(criarEmpresaMock("00000000000004"));
        return empresas;

    }

    private Empresa criarEmpresaMock(String cnpj) {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj(cnpj);
        return empresa;
    }


}
