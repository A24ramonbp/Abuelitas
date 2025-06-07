/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import model.UserLogin;
import view.InicioSesionDialog;
import view.MainJFrame;
import view.Personal.RegistroDialogPersonal;
import view.RegistroDialogProfessional;

/**
 *
 * @author rpbp
 */
public class FrontController {

    private final MainJFrame view;
    private UserLogin userLogin;

    public FrontController(MainJFrame view, UserLogin userLogin) throws MalformedURLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addQuitMenuItem(this.getQuitActionListener());
        this.view.addSingInButtonActionListener(this.getSingInActionListener());
        this.view.addSingUpButtonActionListener(this.getSingUpActionListener());
    }

    private ActionListener getSingInActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                InicioSesionDialog isd = new InicioSesionDialog(view, true);
                SingInController casc = new SingInController(isd, userLogin);
                isd.setVisible(true);

            }
        };
        return al;
    }

    private ActionListener getSingUpActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int selection = JOptionPane.showOptionDialog(view, "Que tipo de cuenta quiere crear?", "Tipo de cuenta",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Personal", "Profesinal", "Cancelar"}, "Personal");
                
                if (selection==0){
                    view.dispose();
                    RegistroDialogPersonal rdper = new RegistroDialogPersonal(view, true);
                    SingUpControllerPersonal supc = new SingUpControllerPersonal(rdper,userLogin);
                    rdper.setVisible(true);
                }
                else if (selection == 1) {
                    view.dispose();
                    RegistroDialogProfessional rd = new RegistroDialogProfessional(view, true);
                    SingUpControllerProfessional supc = new SingUpControllerProfessional(rd,userLogin);
                    rd.setVisible(true);
                }

            }
        };
        return al;
    }

    private ActionListener getQuitActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }

}
