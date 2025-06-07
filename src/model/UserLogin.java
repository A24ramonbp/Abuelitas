/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class UserLogin {
    private static UserLogin userLogin = null;
    private UsuarioPersonal usuarioPersonal;
    private UsuarioProfesional usuarioProfesional;
    private UsuarioCuidador usuarioCuidador;
    private UsuarioAdmin usuarioAdmin;

    private UserLogin() {
        this.usuarioPersonal = null;
        this.usuarioProfesional = null;
        this.usuarioCuidador = null;
        this.usuarioAdmin = null;
    }
    
    public static UserLogin getModel() {
        if (UserLogin.userLogin == null) {
            UserLogin.userLogin = new UserLogin();
        }
        return userLogin;
    }

    public UsuarioAdmin getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(UsuarioAdmin usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    
    public static UserLogin getUserLogin() {
        return userLogin;
    }

    public static void setUserLogin(UserLogin userLogin) {
        UserLogin.userLogin = userLogin;
    }

    public UsuarioCuidador getUsuarioCuidador() {
        return usuarioCuidador;
    }

    public void setUsuarioCuidador(UsuarioCuidador usuarioCuidador) {
        this.usuarioCuidador = usuarioCuidador;
    }

    
    public UsuarioPersonal getUsuarioPersonal() {
        return usuarioPersonal;
    }

    public void setUsuarioPersonal(UsuarioPersonal usuarioPersonal) {
        this.usuarioPersonal = usuarioPersonal;
    }

    public UsuarioProfesional getUsuarioProfesional() {
        return usuarioProfesional;
    }

    public void setUsuarioProfesional(UsuarioProfesional usuarioProfesional) {
        this.usuarioProfesional = usuarioProfesional;
    }
    
    
    
    
    
}
