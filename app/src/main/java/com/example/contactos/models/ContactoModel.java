package com.example.contactos.models;

import java.io.Serializable;

public class ContactoModel implements Serializable {
    private String nombre;
    private String ciclo;
    private int telefono;

    public ContactoModel(String nombre, String ciclo, int telefono) {
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.telefono = telefono;
    }

    public ContactoModel() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ContactoModel{" +
                "nombre='" + nombre + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
