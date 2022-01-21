package com.daniel.empresa.dto;

import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

public class EmpresaResponse {

    @ApiModelProperty(value = "Codigo da empresa", example = "5")
    private Long id;

    @ApiModelProperty(value = "Nome fantasia", example = "Lachonete do tia")
    private String nomeFantasia;

    @ApiModelProperty(value = "Cnpj da empresa", example = "00.000.000/0000-01")
    private String cnpj;

    @ApiModelProperty(value = "Razao social", example = "Restaurante")
    private String razaoSocial;

    @ApiModelProperty(value = "Nome do proprietario(a) da empresa", example = "Cida")
    private String nomeProprietario;

    @ApiModelProperty(value = "Data de criação do registro")
    private Date dataCriacao;

    @ApiModelProperty(value = "Usuario que criou o registro", example = "Daniel")
    private String usuarioCriacao;

    @ApiModelProperty(value = "Data de alteração do registro")
    private Date dataAlteracao;

    @ApiModelProperty(value = "Usuario que alterou o registro", example = "Daniel")
    private String usuarioAlteracao;

    public EmpresaResponse() {
    }

    public EmpresaResponse(Long id, String nomeFantasia, String cnpj) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }
}
