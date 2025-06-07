/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class UsuarioProfesional extends Usuario{
    private String centerCode;

    public UsuarioProfesional(String centerCode, String usuario, String password, String name, String email, String address,String telephone,String ca) {
        super(usuario, password, name, email, address,telephone,ca);
        this.centerCode = centerCode;
    }

    public UsuarioProfesional() {
    }

    
    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }
    
    
}
