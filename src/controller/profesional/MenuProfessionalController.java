/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.profesional;

import controller.AssignTaskManagerController;
import controller.ClientManagerController;
import controller.SingInController;
import controller.TaskManagerController;
import view.profesional.ProfessionalMenuFrame;
import view.profesional.UserManagerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.AssignTaskManagerDialog;
import view.ClientManagerDialog;
import view.profesional.ProfessionalDataDialog;
import view.SingInFrame;
import view.TaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class MenuProfessionalController {
    
    private final ProfessionalMenuFrame view;
    private UserLogin userLogin;
    
    public MenuProfessionalController(ProfessionalMenuFrame view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addUsuariosButtonActionListener(this.getUserManageButtonListener());
        this.view.addClientesButtonActionListener(this.getClienteManageButtonListener());
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addDatosCuentaButtonActionListener(this.getDatosCuentaButtonListener());
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addListaTareasButtonActionListener(this.getListaTareasButtonListener());
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioProfesional().getUsuario());
        this.view.addTareasAsignadasButtonActionListener(this.getAdministrarTareasButtonctionListener());
    }
    
    //Método que define la acción del botón Usuarios
    private ActionListener getUserManageButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                UserManagerDialog md = new UserManagerDialog(view, true);
                try {
                   
                    UserManageController mc = new UserManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
                
            }
        };
        return al;
    }
    
    //Método que define la acción del menu item cerrar sesión
    private ActionListener getCerrarSesionMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setUsuarioProfesional(null);
                view.dispose();
                SingInFrame md = new SingInFrame(view, true);
                SingInController mc = new SingInController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
    //Método que define la acción del botón Asignar Tareas 
    private ActionListener getAdministrarTareasButtonctionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AssignTaskManagerDialog md = new AssignTaskManagerDialog(view, true);
                try {
                    AssignTaskManagerController mc = new AssignTaskManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }
    
    //Método que define la acción del menu item salir
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
    
    //Método que define la acción del botón Cliente
    private ActionListener getClienteManageButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                ClientManagerDialog md = new ClientManagerDialog(view, true);
                try {
                    ClientManagerController mc = new ClientManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
                
            }
        };
        return al;
    }
    
    //Método que define la acción del botón Datos de la cuenta
    private ActionListener getDatosCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProfessionalDataDialog md = new ProfessionalDataDialog(view, true);
                ProfessionalDataController mc = new ProfessionalDataController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
    //Método que define la acción del botón Tareas
    private ActionListener getListaTareasButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                TaskManagerDialog md = new TaskManagerDialog(view, true);
                try {
                    TaskManagerController mc = new TaskManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }
}
