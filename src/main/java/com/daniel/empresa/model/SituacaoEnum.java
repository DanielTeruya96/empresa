package com.daniel.empresa.model;

public enum SituacaoEnum {
    RASCUNHO(0,"Rascunho"),CRIADO(1,"Criado"),APAGADO(2,"Apagado");

    int index;
    String label;

    SituacaoEnum(int index, String label) {
        this.index = index;
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
