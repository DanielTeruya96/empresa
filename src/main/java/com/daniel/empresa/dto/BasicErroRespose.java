package com.daniel.empresa.dto;

public class BasicErroRespose {

    String erro;

    public BasicErroRespose(String motivo) {
        this.erro = motivo;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
