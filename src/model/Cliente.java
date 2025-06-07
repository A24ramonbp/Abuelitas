/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class Cliente {
    private String dni;
    private String name;
    private String address;
    private String telefono;
    private String email;
    private String ca;
    private UsuarioCuidador cuidador;
    private UsuarioCuidador neurologo;
    private UsuarioCuidador fisioterapeuta;
    private String center;

    public Cliente(String dni, String name, String address, String email,String telefono, String ca, UsuarioCuidador cuidador, String center, UsuarioCuidador neurologo, UsuarioCuidador fisioterapeuta) {
        this.dni = dni;
        this.name = name;
        this.address = address;
        this.telefono = telefono;
        this.email = email;
        this.ca = ca;
        this.cuidador = cuidador;
        this.center =center;
        this.neurologo= neurologo;
        this.fisioterapeuta = fisioterapeuta;
    }
    
    public Cliente(String dni, String name, String address, String email,String telefono, String ca, UsuarioCuidador cuidador, String center) {
        this.dni = dni;
        this.name = name;
        this.address = address;
        this.telefono = telefono;
        this.email = email;
        this.ca = ca;
        this.cuidador = cuidador;
        this.center =center;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioCuidador getNeurologo() {
        return neurologo;
    }

    public void setNeurologo(UsuarioCuidador neurologo) {
        this.neurologo = neurologo;
    }

    public UsuarioCuidador getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(UsuarioCuidador fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente() {
    }
    
    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public UsuarioCuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(UsuarioCuidador cuidador) {
        this.cuidador = cuidador;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
    
    
    
    
    
}
