/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class UsuarioCuidador extends Usuario {

    private String id;

    private String dni;

    private String rol;

    private String center;

    public UsuarioCuidador(String id, String user, String password, String dni, String name, String email, String address, String telefono, String rol, String ca, String center) {
        super(user, password, name, email, address, telefono, ca);
        this.id = id;
        this.dni = dni;
        this.rol = rol;
        this.center = center;

    }

    public UsuarioCuidador() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

}
