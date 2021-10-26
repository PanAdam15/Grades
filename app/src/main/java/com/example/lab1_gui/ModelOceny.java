package com.example.lab1_gui;

public class ModelOceny {
    private String nazwa;
    private int ocena=2;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
    }
    public int getOcena() {
        return ocena;
    }
    public String getNazwa() {
        return nazwa;
    }
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
