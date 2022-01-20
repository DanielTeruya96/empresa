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
        return modelMapper.map(emp,EmpresaResponse.class);
    }
}
