package br.edu.vicente.appgenesis.model;

import java.io.Serializable;

public class Padrao implements Serializable {
    private int id;
    private String tensao;
    private String corrente;
    private String distancia;
    private String potencia;
    private String disjuntor;
    private String queda;

    public Padrao(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getCorrente() {
        return corrente;
    }

    public void setCorrente(String corrente) {
        this.corrente = corrente;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getDisjuntor() {
        return disjuntor;
    }

    public void setDisjuntor(String disjuntor) {
        this.disjuntor = disjuntor;
    }

    public String getQueda() {
        return queda;
    }

    public void setQueda(String queda) {
        this.queda = queda;
    }

    @Override
    public String toString() {
        return "Padrao{" +
                "tensao='" + tensao + '\'' +
                '}';
    }
}
