package com.caesperdidos.app;
import java.io.Serializable;
public class Cachorro implements Serializable {

    public Cachorro() {

    }
    private String id;
    private String nome;
    private String raca;
    private String cor;
    private String descricao;
    private String telefone;
    private String tutor;
    private String localizacao;
    private double latitude;
    private double longitude;


    //getters
    public String getId() {return id;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}
    public String getLocalizacao() {return localizacao;}
    public String getNome() {return nome;}
    public String getRaca() {return raca;}
    public String getCor() {return cor;}
    public String getDescricao() {return descricao;}
    public String getTelefone() { return  telefone; }
    public String getTutor() { return  tutor; }

    //setters 
    public void setNome(String nome) {this.nome = nome;}
    public void setRaca(String raca) {this.raca = raca;}
    public void setCor(String cor) {this.cor = cor;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setTutor(String tutor) {this.tutor = tutor;}
    public void setLocalizacao(String localizacao) {this.localizacao = localizacao;}
    public void setId(String id) {this.id = id;}
    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}









}
