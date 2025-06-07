/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.main.FrontController;
import controller.cuidador.CaretakerMenuController;
import controller.profesional.MenuProfessionalController;
import controller.personal.MenuPersonalController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ConnectMdb;
import model.UserLogin;
import model.UsuarioAdmin;
import model.UsuarioCuidador;
import model.UsuarioPersonal;
import model.UsuarioProfesional;
import org.mindrot.jbcrypt.BCrypt;
import view.SingInFrame;
import view.main.MainJFrame;
import view.admin.MenuFrame;
import view.cuidador.CaretakerMenuFrame;
import view.personal.PersonalMenuFrame;
import view.profesional.ProfessionalMenuFrame;

/**
 *
 * @author rpbp
 */
public class SingInController {

    private final SingInFrame view;
    private UserLogin userLogin;

    public SingInController(SingInFrame view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addConfirmButtonActionListener(this.getConfirmButtonListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonListener());
    }

    //Método que define la acción del botón confirmar
    private ActionListener getConfirmButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = view.getTextUserTextField();
                String password = view.getTextPasswordTextField();

                try {
                    if (iniciarSesion(user, password) == null) {
                        JOptionPane.showMessageDialog(view, "El usuario o la contraseña no son correctos.");
                    } else {
                        if (iniciarSesion(user, password) instanceof UsuarioPersonal) {
                            userLogin.setUsuarioPersonal((UsuarioPersonal) iniciarSesion(user, password));
                            view.dispose();
                            PersonalMenuFrame md = new PersonalMenuFrame(view, true);
                            MenuPersonalController mc = new MenuPersonalController(md, userLogin);
                            md.setVisible(true);
                        }
                        if (iniciarSesion(user, password) instanceof UsuarioProfesional) {
                            userLogin.setUsuarioProfesional((UsuarioProfesional) iniciarSesion(user, password));
                            view.dispose();
                            ProfessionalMenuFrame md = new ProfessionalMenuFrame(view, true);
                            MenuProfessionalController mc = new MenuProfessionalController(md, userLogin);
                            md.setVisible(true);
                        }
                        if (iniciarSesion(user, password) instanceof UsuarioAdmin) {
                            userLogin.setUsuarioAdmin((UsuarioAdmin) iniciarSesion(user, password));
                            view.dispose();
                            MenuFrame md = new MenuFrame(view, true);
                            MenuController mc = new MenuController(md, userLogin);
                            md.setVisible(true);
                        }
                        if (iniciarSesion(user, password) instanceof UsuarioCuidador) {
                            userLogin.setUsuarioCuidador((UsuarioCuidador) iniciarSesion(user, password));
                            view.dispose();
                            CaretakerMenuFrame md = new CaretakerMenuFrame(view, true);
                            CaretakerMenuController mc = new CaretakerMenuController(md, userLogin);
                            md.setVisible(true);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return al;
    }

    //Método que define la acción del botón cancelar
    private ActionListener getCancelButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.dispose();
                    MainJFrame md = new MainJFrame(view, true);
                    FrontController mc = new FrontController(md, userLogin);
                    md.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    //Método para iniciar sesión.
    public static Object iniciarSesion(String usuario, String contraseña) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE user = ?";
        String sqlAdmin = "SELECT * FROM UsuarioAdmin WHERE user = ?";

        PreparedStatement stmtPersonal = conn.prepareStatement(sqlPersonal);
        stmtPersonal.setString(1, usuario);
        ResultSet rsPersonal = stmtPersonal.executeQuery();

        if (rsPersonal.next()) {
            String hash = rsPersonal.getString("password");
            if (BCrypt.checkpw(contraseña, hash)) {

                UsuarioPersonal user = new UsuarioPersonal();
                user.setUsuario(rsPersonal.getString("user"));
                user.setDni(rsPersonal.getString("dni"));
                user.setAddress(rsPersonal.getString("address"));
                user.setEmail(rsPersonal.getString("email"));
                user.setCa(rsPersonal.getString("ca"));
                user.setName(rsPersonal.getString("name"));
                user.setTelephone(rsPersonal.getString("telefono"));
                user.setPassword(contraseña);
                return user;
            }
        }
        
        PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
        stmtAdmin.setString(1, usuario);
        ResultSet rsAdmin = stmtAdmin.executeQuery();

        if (rsAdmin.next()) {
            String hash = rsAdmin.getString("password");
            if (BCrypt.checkpw(contraseña, hash)) {

                UsuarioAdmin user = new UsuarioAdmin();
                user.setUsuario(rsAdmin.getString("user"));
                user.setDni(rsAdmin.getString("dni"));
                user.setAddress(rsAdmin.getString("address"));
                user.setEmail(rsAdmin.getString("email"));
                user.setCa(rsAdmin.getString("ca"));
                user.setName(rsAdmin.getString("name"));
                user.setTelephone(rsAdmin.getString("telefono"));
                user.setPassword(contraseña);
                return user;
            }
        }
        
        

        PreparedStatement stmtProfesional = conn.prepareStatement(sqlProfesional);
        stmtProfesional.setString(1, usuario);
        ResultSet rsProfesional = stmtProfesional.executeQuery();

        if (rsProfesional.next()) {
            String hash = rsProfesional.getString("password");

            if (BCrypt.checkpw(contraseña, hash)) {
                UsuarioProfesional user = new UsuarioProfesional();
                user.setUsuario(rsProfesional.getString("user"));
                user.setCenterCode(rsProfesional.getString("code"));
                user.setAddress(rsProfesional.getString("address"));
                user.setEmail(rsProfesional.getString("email"));
                user.setCa(rsProfesional.getString("ca"));
                user.setName(rsProfesional.getString("name"));
                user.setTelephone(rsProfesional.getString("telefono"));
                user.setPassword(contraseña);
                return user;
            }
        }

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, usuario);
        ResultSet rsCuidador = stmtCuidador.executeQuery();

        if (rsCuidador.next()) {
            String hash = rsCuidador.getString("password");
            if (BCrypt.checkpw(contraseña, hash)) {
                UsuarioCuidador user = new UsuarioCuidador();
                user.setId(rsCuidador.getString("id"));
                user.setUsuario(rsCuidador.getString("user"));
                user.setDni(rsCuidador.getString("dni"));
                user.setAddress(rsCuidador.getString("address"));
                user.setEmail(rsCuidador.getString("email"));
                user.setCa(rsCuidador.getString("ca"));
                user.setName(rsCuidador.getString("name"));
                user.setRol(rsCuidador.getString("rol"));
                user.setCenter(rsCuidador.getString("code"));
                user.setTelephone(rsCuidador.getString("telefono"));
                user.setPassword(contraseña);

                return user;
            }
        }

        return null;
    }
}
