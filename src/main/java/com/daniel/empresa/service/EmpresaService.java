package com.daniel.empresa.service;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.exception.BasicException;
import com.daniel.empresa.mapper.EmpresaMapper;
import com.daniel.empresa.model.Empresa;
import com.daniel.empresa.model.SituacaoEnum;
import com.daniel.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;





    public EmpresaResponse credenciar(EmpresaRequest empresaRequest, String autorization) {
        validarCampsObrigatorio(empresaRequest);
        empresaRequest.setCnpj(empresaRequest.getCnpj().replaceAll("[./-]", "") );
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
        Empresa empresa = empresaRepository.findByCnpjAndSituacao(emp.getCnpj(),SituacaoEnum.CRIADO.getIndex());
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

    public EmpresaResponse alterar(EmpresaRequest empresaRequest, String autorization) {
        validarCampsObrigatorio(empresaRequest);
        empresaRequest.setCnpj(empresaRequest.getCnpj().replaceAll("[./-]", "") );
        Empresa empresaAntiga = empresaRepository.findByCnpjAndSituacao(empresaRequest.getCnpj(), SituacaoEnum.CRIADO.getIndex());
        if(empresaAntiga == null){
            throw new BasicException("não foi encontrado uma empresa com esse cnpj!");
        }else{
            Empresa empresaNova = new EmpresaMapper().toEmpresa(empresaRequest);
            empresaNova.setId(empresaAntiga.getId());
            empresaNova.setUsuarioCriacao(empresaAntiga.getUsuarioCriacao());
            empresaNova.setDataCriacao(empresaAntiga.getDataCriacao());
            empresaNova.setDataAlteracao(new Date());
            empresaNova.setUsuarioAlteracao(getUsuario(autorization));
            empresaNova.setSituacao(empresaAntiga.getSituacao());
            empresaRepository.save(empresaNova);
            return new EmpresaMapper().toResponse(empresaNova);
        }

    }

    public String deletar(Long id, String autorization) {
        Optional<Empresa> emp = empresaRepository.findById(id);
        if(emp.isPresent()){
            Empresa empresa = emp.get();
            empresa.setSituacao(2);
            empresa.setUsuarioAlteracao(getUsuario(autorization));
            empresa.setDataAlteracao(new Date());
            empresaRepository.save(empresa);
            return "Removido com sucesso";
        }else{
            throw new BasicException("Empresa nao encontrada");
        }
    }

    public List<EmpresaResponse> consultar() {
        return empresaRepository.findBySituacao(SituacaoEnum.CRIADO.getIndex()).stream().map(emp -> new EmpresaMapper().toResponse(emp)).collect(Collectors.toList());

    }
}
