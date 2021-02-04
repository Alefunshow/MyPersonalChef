package com.alessiofurlan.mypersonalchef;

public class CardRicetta {
    private String immaginePiatto;
    private String nomePiatto;
    private String categoriaPiatto;

    public CardRicetta(String immaginePiatto,String nomePiatto,String categoriaPiatto){
        this.immaginePiatto = immaginePiatto;
        this.nomePiatto = nomePiatto;
        this.categoriaPiatto = categoriaPiatto;
    }

    public String getImmaginePiatto() {
        return immaginePiatto;
    }

    public String getNomePiatto() {
        return nomePiatto;
    }

    public String getCategoriaPiatto() {
        return categoriaPiatto;
    }
}
