/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.CentroManagerDialog;
import view.DatosPersonalDialog;
import view.InicioSesionDialog;
import view.Personal.MenuDialogPersonal;
import view.TareasManagerPersonalDialog;

/**
 *
 * @author rpbp
 */
public class MenuPersonalController {

    private final MenuDialogPersonal view;
    private final UserLogin userLogin;

    public MenuPersonalController(MenuDialogPersonal view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addDatosButtonActionListener(this.getDatosCuentaButtonListener());
        this.view.addCentrosButtonActionListener(this.getCentrosButtonListener());
        this.view.addTareasButtonActionListener(this.getTareasButtonListener());
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioPersonal().getUsuario());
   }

    private ActionListener getCerrarSesionMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setUsuarioPersonal(null);
                view.dispose();
                InicioSesionDialog md = new InicioSesionDialog(view, true);
                SingInController mc = new SingInController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

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

    private ActionListener getDatosCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                DatosPersonalDialog md = new DatosPersonalDialog(view, true);
                DatosPersonalController mc = new DatosPersonalController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
      private ActionListener getTareasButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                TareasManagerPersonalDialog md = new TareasManagerPersonalDialog(view, true);
                try {
                    TareaManagePersonalController mc = new TareaManagePersonalController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPersonalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener getCentrosButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                CentroManagerDialog md = new CentroManagerDialog(view, true);
                try {
                    CentroManageController mc = new CentroManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPersonalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

}
