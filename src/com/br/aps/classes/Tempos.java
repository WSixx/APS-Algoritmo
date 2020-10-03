package com.br.aps.classes;

import java.sql.Date;

public class Tempos {
    private int id;
    private String algoritmo;
    private double tempo;
    private Date dataNow;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public Date getDataNow() {
        return dataNow;
    }

    public void setDataNow(Date dataNow) {
        this.dataNow = dataNow;
    }
}
