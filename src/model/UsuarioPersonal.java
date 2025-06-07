/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class UsuarioPersonal extends Usuario{
    
    private String dni;

    public UsuarioPersonal(String dni, String usuario, String password, String name, String email, String address, String telephone, String ca) {
        super(usuario, password, name, email, address,telephone,ca);
        this.dni = dni;
    }

    public UsuarioPersonal() {
    }
    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    
    
}
