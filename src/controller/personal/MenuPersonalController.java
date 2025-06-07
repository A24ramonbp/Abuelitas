/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.personal;

import controller.personal.CenterManageController;
import controller.SingInController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserLogin;
import view.personal.CenterManagerDialog;
import view.personal.PersonalDataDialog;
import view.SingInFrame;
import view.personal.PersonalMenuFrame;
import view.personal.PersonalTaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class MenuPersonalController {

    private final PersonalMenuFrame view;
    private final UserLogin userLogin;

    public MenuPersonalController(PersonalMenuFrame view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addQuitMenuItem(this.getQuitMenuItemActionListener());
        this.view.addCerrarSesionMenuItem(this.getCerrarSesionMenuItemActionListener());
        this.view.addDatosButtonActionListener(this.getDatosCuentaButtonListener());
        this.view.addCentrosButtonActionListener(this.getCentrosButtonListener());
        this.view.addTareasButtonActionListener(this.getTareasButtonListener());
        this.view.setUserLabel("Bienvenido " + userLogin.getUsuarioPersonal().getUsuario());
    }

    //Añade acción al menuItem de cerrarSesión
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

    //Añade acción al menuItem de salir
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

    //Añade acción al botón de Datos de la cuenta
    private ActionListener getDatosCuentaButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PersonalDataDialog md = new PersonalDataDialog(view, true);
                PersonalDataController mc = new PersonalDataController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }
    
    //Añade acción al botón de tareas
    private ActionListener getTareasButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PersonalTaskManagerDialog md = new PersonalTaskManagerDialog(view, true);
                try {
                    PersonalTaskManagerController mc = new PersonalTaskManagerController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPersonalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

     //Añade acción al botón de centros
    private ActionListener getCentrosButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CenterManagerDialog md = new CenterManagerDialog(view, true);
                try {
                    CenterManageController mc = new CenterManageController(md, userLogin);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPersonalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.setVisible(true);
            }
        };
        return al;
    }

}
