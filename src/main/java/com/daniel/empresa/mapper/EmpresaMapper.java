package com.daniel.empresa.mapper;

import com.daniel.empresa.dto.EmpresaRequest;
import com.daniel.empresa.dto.EmpresaResponse;
import com.daniel.empresa.model.Empresa;

import org.modelmapper.ModelMapper;

public class EmpresaMapper {



    public Empresa toEmpresa(EmpresaRequest empresaRequest){
        ModelMapper modelMapper = new ModelMapper();
         return modelMapper.map(empresaRequest,Empresa.class);
    }

    public EmpresaResponse toResponse(Empresa emp){
        ModelMapper modelMapper = new ModelMapper();
        EmpresaResponse response = modelMapper.map(emp,EmpresaResponse.class);
        response.setCnpj(response.getCnpj().replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})","$1.$2.$3/$4-$5"));


        return response;
    }
}
