package com.example.simuladorso.domain;

public class Proceso {
    private  String nombre;
    private  int tamanio;
    private  int estado;
    private  int direIni;
    private  int pid;
    private  int prioridad;
    private  int cp;
    private  int aciertos;
    private  int fallos;
    private  float tasaFallos;
    

    public Proceso(String nombre,int estado,int direIni ,int tamanio,int pid) {
        this.nombre = nombre;
        this.direIni = direIni;
        this.tamanio = tamanio;
        this.pid = pid;
        this.estado = estado;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getTamanio() {
        return tamanio;
    }


    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }


    public int getEstado() {
        return estado;
    }


    public void setEstado(int estado) {
        this.estado = estado;
    }


    public int getDireIni() {
        return direIni;
    }


    public void setDireIni(int direIni) {
        this.direIni = direIni;
    }


    public int getPid() {
        return pid;
    }


    public void setPid(int pid) {
        this.pid = pid;
    }


    public int getPrioridad() {
        return prioridad;
    }


    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }


    public int getCp() {
        return cp;
    }


    public void setCp(int cp) {
        this.cp = cp;
    }


    public int getAciertos() {
        return aciertos;
    }


    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }


    public int getFallos() {
        return fallos;
    }


    public void setFallos(int fallos) {
        this.fallos = fallos;
    }


    public float getTasaFallos() {
        return tasaFallos;
    }


    public void setTasaFallos(float tasaFallos) {
        this.tasaFallos = tasaFallos;
    }


    

    
    

}
