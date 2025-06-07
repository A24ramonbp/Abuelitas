/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.admin.UserProfesionalManagerController;
import controller.admin.UserPersonalManagerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.SingInFrame;
import view.admin.MenuFrame;
import view.admin.UserPersonalManagerDialog;
import view.admin.UserProfessionalManagerDialog;

/**
 *
 * @author rpbp
 */
public class MenuController {

    private final MenuFrame view;
    private UserLogin userLogin;

    MenuController(MenuFrame view, UserLogin userLogin) {
        this.view = view;
        this.userLogin = userLogin;
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addUsuarioProfesionalButtonActionListener(this.getUserProfesionalCuentaButtonListener());
        this.view.addUsuarioPersonalButtonActionListener(this.getUserPersonalCuentaButtonListener());
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioAdmin().getUsuario());
    }

    //Método que indica la acción del menuItem cerrar sesión
    private ActionListener getCerrarSesionMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setUsuarioPersonal(null);
                view.dispose();
                SingInFrame md = new SingInFrame(view, true);
                SingInController mc = new SingInController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    //Método que indica la acción del menuItem salir
    private ActionListener getQuitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }
    
    //Método que indica la acción del botón usuarios profesionales
    private ActionListener getUserProfesionalCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserProfessionalManagerDialog md = new UserProfessionalManagerDialog(view, true);
                try {
                    UserProfesionalManagerController mc = new UserProfesionalManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);

            }
        };
        return al;
    }

    //Método que indica la acción del botón usuarios personales
    private ActionListener getUserPersonalCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserPersonalManagerDialog md = new UserPersonalManagerDialog(view, true);
                try {
                    UserPersonalManagerController mc = new UserPersonalManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);

            }
        };
        return al;
    }

}
