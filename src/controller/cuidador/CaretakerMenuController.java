/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.cuidador;

import controller.AssignTaskManagerController;
import controller.PatientManagerController;
import controller.SingInController;
import controller.TaskManagerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.AssignTaskManagerDialog;
import view.cuidador.CaretakerDataDialog;
import view.SingInFrame;
import view.cuidador.CaretakerMenuFrame;
import view.PatientManagerDialog;
import view.TaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class CaretakerMenuController {

    private final CaretakerMenuFrame view;
    private UserLogin userLogin;

    public CaretakerMenuController(CaretakerMenuFrame view, UserLogin userLogin) {
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

    //Método que define la acción del botón paciente, ir a dicha ventana.
    private ActionListener getPacientesButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PatientManagerDialog md = new PatientManagerDialog(view, true);
                try {
                    PatientManagerController mc = new PatientManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(CaretakerMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

    //Método que define la acción del menuItem cerrar sesión.
    private ActionListener getCerrarSesionMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setUsuarioCuidador(null);
                view.dispose();
                SingInFrame md = new SingInFrame(view, true);
                SingInController mc = new SingInController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    //Método que define la acción del menuItem salir.
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
    
    //Método que define la acción del botón AsignarTareas, ir a dicha ventana.
    private ActionListener getAssingTaskButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AssignTaskManagerDialog md = new AssignTaskManagerDialog(view, true);
                try {
                    AssignTaskManagerController mc = new AssignTaskManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

    //Método que define la acción del botón Lista de tareas, ir a dicha ventana.
    private ActionListener getTareasButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TaskManagerDialog md = new TaskManagerDialog(view, true);
                try {
                    TaskManagerController mc = new TaskManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(CaretakerMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

    //Método que define la acción del botón datos de la cuenta, ir a dicha ventana.
    private ActionListener getDatosCuentaButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CaretakerDataDialog md = new CaretakerDataDialog(view, true);
                CaretakerDataController mc = new CaretakerDataController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    //Inicia los componentes de la forma deseada.
    public void initComponents() {
        if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
            view.enabledTareasButton(false);
        }
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioCuidador().getUsuario());
    }
}
