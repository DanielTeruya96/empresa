package com.daniel.empresa.service;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.exception.BasicException;
import com.daniel.empresa.mapper.EmpresaMapper;
import com.daniel.empresa.model.Empresa;
import com.daniel.empresa.repository.EmpresaRepository;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Base64;
import java.util.Date;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;




    public EmpresaResponse credenciar(EmpresaRequest empresaRequest, String autorization) {
        validarCampsObrigatorio(empresaRequest);
        empresaRequest.setCnpj(empresaRequest.getCnpj().replace(".","").replace("/","").replace("-","") );
        Empresa emp = new EmpresaMapper().toEmpresa(empresaRequest);
        validar(emp);
        emp.setUsuarioCriacao(getUsuario(autorization));
        emp.setDataCriacao(new Date());
        emp = empresaRepository.save(emp);
        return  new EmpresaMapper().toResponse(emp);


    }

    private void validarCampsObrigatorio(EmpresaRequest empresaRequest) {
        if(empresaRequest.getCnpj()==null || empresaRequest.getCnpj().isEmpty()){
            throw new BasicException("cnpj é obrigatorio!");
        }

        String cnpj = empresaRequest.getCnpj();
        if(!cnpj.matches("^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$")){
            throw new BasicException("cnpj informar cnpj com o formato XX.XXX.XXX/XXXX-XX");
        }
    }

    private void validar(Empresa emp) {
        Empresa empresa = empresaRepository.findByCnpj(emp.getCnpj());
        if(empresa != null){
            throw new BasicException("CNPJ ja cadastrado");
        }

    }

    private String getUsuario(String autorization) {
//        TODO implementar a decodificação
//        byte[] i =  Base64.getDecoder().decode(autorization);
//        String decodificado = new String(i);
//        return decodificado.split(":")[0];
        return autorization;


    }
}
