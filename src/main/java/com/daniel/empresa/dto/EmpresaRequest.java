package com.daniel.empresa.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmpresaRequest {

    @ApiModelProperty(value = "Nome fantasia", example = "Lachonete da tia")
    private String nomeFantasia;

    @ApiModelProperty(value = "Cnpj da empresa", example = "00.000.000/0000-01", required = true)
    private String cnpj;

    @ApiModelProperty(value = "Razao social", example = "Restaurante")
    private String razaoSocial;

    @ApiModelProperty(value = "Nome do proprietario(a) da empresa", example = "Cida")
    private String nomeProprietario;

    public EmpresaRequest(String nomeFantasia, String cnpj) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }
}
