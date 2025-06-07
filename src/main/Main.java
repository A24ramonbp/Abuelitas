/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.main.FrontController;
import java.net.MalformedURLException;
import model.UserLogin;
import view.main.MainJFrame;

/**
 *
 * @author rpbp
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException{
        MainJFrame view= new MainJFrame();
        UserLogin userLogin = UserLogin.getModel();
        FrontController fc= new FrontController(view,userLogin);
        view.setVisible(true);
    }
    
}
