/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.main;

import controller.profesional.ProfessionalSingUpController;
import controller.personal.PersonalSingUpController;
import controller.SingInController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import model.UserLogin;
import view.SingInFrame;
import view.main.MainJFrame;
import view.personal.PersonalSingUpFrame;
import view.profesional.RegistroFrameProfessional;

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

    //Acción del botón SingIn para acceder a dicha ventana
    private ActionListener getSingInActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                SingInFrame isd = new SingInFrame(view, true);
                SingInController casc = new SingInController(isd, userLogin);
                isd.setVisible(true);

            }
        };
        return al;
    }

    //Acción del botón SingUp para acceder a dicha ventana
    private ActionListener getSingUpActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int selection = JOptionPane.showOptionDialog(view, "Que tipo de cuenta quiere crear?", "Tipo de cuenta",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Personal", "Profesinal", "Cancelar"}, "Personal");
                
                if (selection==0){
                    view.setVisible(false);
                    PersonalSingUpFrame rdper = new PersonalSingUpFrame(view, true);
                    PersonalSingUpController supc = new PersonalSingUpController(rdper,userLogin);
                    rdper.setVisible(true);
                }
                else if (selection == 1) {
                   view.setVisible(false);
                    RegistroFrameProfessional rd = new RegistroFrameProfessional(view, true);
                    ProfessionalSingUpController supc = new ProfessionalSingUpController(rd,userLogin);
                    rd.setVisible(true);
                }

            }
        };
        return al;
    }

    //Acción del menuItem salir y cerrar la app
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
