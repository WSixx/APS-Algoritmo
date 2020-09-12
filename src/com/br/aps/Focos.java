package com.br.aps;

import java.time.LocalDateTime;

public class Focos {
    private int index;
    private LocalDateTime data;
    private String satelite;
    private String pais;
    private String estado;
    private String municipio;
    private String bioma;
    private Double lat;
    private Double lon;
    private String regiao;

    public Focos(int index, LocalDateTime data, String satelite, String pais, String estado, String municipio, String bioma, Double lat, Double lon, String regiao) {
        super();
        this.index = index;
        this.data = data;
        this.satelite = satelite;
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.bioma = bioma;
        this.lat = lat;
        this.lon = lon;
        this.regiao = regiao;
    }

    public Focos() { }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getSatelite() {
        return satelite;
    }

    public void setSatelite(String satelite) {
        this.satelite = satelite;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBioma() {
        return bioma;
    }

    public void setBioma(String bioma) {
        this.bioma = bioma;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}
