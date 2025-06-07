/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.MenuDialogProfesional;
import view.UserManageJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.AssignTaskManagerDialog;
import view.ClientManagerDialog;
import view.DatosProfesionalDialog;
import view.InicioSesionDialog;
import view.TareasManagerDialog;

/**
 *
 * @author rpbp
 */
public class MenuProfesionalController {
    
    private final MenuDialogProfesional view;
    private UserLogin userLogin;
    
    public MenuProfesionalController(MenuDialogProfesional view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addUsuariosButtonActionListener(this.getUserManageButtonListener());
        this.view.addClientesButtonActionListener(this.getClienteManageButtonListener());
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addDatosCuentaButtonActionListener(this.getDatosCuentaButtonListener());
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addListaTareasButtonActionListener(this.getListaTareasButtonListener());
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioProfesional().getUsuario());
        this.view.addTareasAsignadasButtonActionListener(this.getAdministrarTareasMenuItemActionListener());
    }
    
    private ActionListener getUserManageButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                UserManageJDialog md = new UserManageJDialog(view, true);
                try {
                    view.dispose();
                    UserManageController mc = new UserManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfesionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
                
            }
        };
        return al;
    }
    
    private ActionListener getCerrarSesionMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setUsuarioProfesional(null);
                view.dispose();
                InicioSesionDialog md = new InicioSesionDialog(view, true);
                SingInController mc = new SingInController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getAdministrarTareasMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                AssignTaskManagerDialog md = new AssignTaskManagerDialog(view, true);
                try {
                    AssignTaskManageController mc = new AssignTaskManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfesionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    
    private ActionListener getClienteManageButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                ClientManagerDialog md = new ClientManagerDialog(view, true);
                try {
                    ClientManageController mc = new ClientManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfesionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
                
            }
        };
        return al;
    }
    
    private ActionListener getDatosCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                DatosProfesionalDialog md = new DatosProfesionalDialog(view, true);
                DatosProfesionalController mc = new DatosProfesionalController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getListaTareasButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                TareasManagerDialog md = new TareasManagerDialog(view, true);
                try {
                    TareaManageController mc = new TareaManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfesionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }
}
