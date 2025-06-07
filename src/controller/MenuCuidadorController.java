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
import view.AssignTaskManagerDialog;
import view.DatosCuidadorDialog;
import view.InicioSesionDialog;
import view.MenuDialogCuidador;
import view.PacienteManagerDialog;
import view.TareasManagerDialog;

/**
 *
 * @author rpbp
 */
public class MenuCuidadorController {

    private final MenuDialogCuidador view;
    private UserLogin userLogin;

    public MenuCuidadorController(MenuDialogCuidador view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addClientesButtonActionListener(this.getPacientesButtonActionListener());
        this.view.addTareasButtonActionListener(this.getTareasButtonActionListener());
        this.view.addAssignTaskButtonActionListener(this.getAssingTaskButtonActionListener());
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addDatosButtonActionListener(this.getDatosCuentaButtonActionListener());
        initComponents();
    }

    private ActionListener getPacientesButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                PacienteManagerDialog md = new PacienteManagerDialog(view, true);
                try {
                    PacienteManageController mc = new PacienteManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuCuidadorController.class.getName()).log(Level.SEVERE, null, ex);
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
                userLogin.setUsuarioCuidador(null);
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
    
       private ActionListener getAssingTaskButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                AssignTaskManagerDialog md = new AssignTaskManagerDialog(view, true);
                try {
                    AssignTaskManageController mc = new AssignTaskManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(AssignTaskManageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }


    private ActionListener getTareasButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                TareasManagerDialog md = new TareasManagerDialog(view, true);
                try {
                    TareaManageController mc = new TareaManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuCuidadorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }
    
     private ActionListener getDatosCuentaButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                DatosCuidadorDialog md = new DatosCuidadorDialog(view, true);
                DatosCuidadorController mc = new DatosCuidadorController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    public void initComponents() {
        if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
            view.enabledTareasButton(false);
        }
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioCuidador().getUsuario());
    }
}
