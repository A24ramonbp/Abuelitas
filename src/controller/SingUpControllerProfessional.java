/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ConnectMdb;
import model.UserLogin;
import model.UsuarioProfesional;
import org.mindrot.jbcrypt.BCrypt;
import view.MainJFrame;
import view.RegistroDialogProfessional;

/**
 *
 * @author rpbp
 */
public class SingUpControllerProfessional {

    private final RegistroDialogProfessional view;
    private final UserLogin userLogin;

    public SingUpControllerProfessional(RegistroDialogProfessional view, UserLogin userLogin) {
        this.userLogin= userLogin;
        this.view = view;
        this.getQuitActionListener();
        this.view.addConfirmButtonActionListener(this.getConfirmButtonListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonListener());
    }

    private ActionListener getConfirmButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (CreateUser()) {
                        view.dispose();
                        MainJFrame md = new MainJFrame(view, true);
                        FrontController mc = new FrontController(md,userLogin);
                        md.setVisible(true);
                    }

                } catch (MalformedURLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SingUpControllerProfessional.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return al;
    }

    private ActionListener getCancelButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.dispose();
                    MainJFrame md = new MainJFrame(view, true);
                    FrontController mc = new FrontController(md,userLogin);
                    md.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean CreateUser() throws SQLException {
        String user = view.getTextUserTextField();
        String password = view.getTextPasswordPasswordField();
        String name = view.getTextNameTextField();
        String email = view.getTextEmailTextField();
        String code = view.getTextCodeTextField();
        String telephone = view.getTextTelephoneTextField();
        String address = view.getTextAddressTextField();
        String ca = view.getCAComboBox();

        if ((!ValidadorContraseña(password) || !ValidarCampos(user, password, name, email, code, telephone, address)) || !validarCodigoCentro(code) || !validarTelefono(telephone) || !validarEmail(email)) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t- Todos los campos rellenados.\n\t- La contraseña debe estar entre 8 y 20 caracteres y debe tener 1 letra y 1 numero.\n\t- El formato del codigo del centro es XX/123456\n\t- El formato del teléfono es 123456789\n\t- El formato del email es x@x.x");
            return false;
        } else {
            if (ValidadorContraseña(password) && ValidarCampos(user, password, name, email, code, telephone, address)) {
                password = encriptar(view.getTextPasswordPasswordField());
                UsuarioProfesional usuario = new UsuarioProfesional(code, user, password, name, email, address, telephone, ca);
                
                if (insertarUsuarioProfesional(usuario)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }

    }

    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean ValidadorContraseña(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,19}$";
        return password.matches(regex);
    }

    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    public boolean validarCodigoCentro(String codigo) {
        return codigo.matches("^[A-Z]{2}/\\d{6}$");
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean ValidarCampos(String user, String password, String name, String email, String code, String telephone, String address) {
        if (user.isBlank() || password.isBlank() || name.isBlank() || email.isBlank() || code.isBlank() || telephone.isBlank() || address.isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    public boolean insertarUsuarioProfesional(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        if (!comprobarUsuarioProfesional(user)) {
            if (!comprobarUsuario(user)) {
                insertarCentro(user);
                String sql = "Insert into UsuarioProfesional (code,user,password, name, email, address, telefono, ca) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statament = conn.prepareStatement(sql);

                statament.setString(1, user.getCenterCode());
                statament.setString(2, user.getUsuario());
                statament.setString(3, user.getPassword());
                statament.setString(4, user.getName());
                statament.setString(5, user.getEmail());
                statament.setString(6, user.getAddress());
                statament.setString(7, user.getTelephone());
                statament.setString(8, user.getCa());

                int filasAfectadas = statament.executeUpdate();

                if (filasAfectadas > 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(view, "Hubo un problema al crear el usuario.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(view, "El nombre de usuario ya existe en la base de datos.");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(view, "Ese codigo del centro o el usuario ya existe en la base de datos.");
            return false;
        }
    }

    public boolean insertarCentro(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        if (!comprobarUsuario(user)) {
            String sql = "Insert into Centro (code, name, email, address, telefono, ca) VALUES (?,?,?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, user.getCenterCode());
            statament.setString(2, user.getName());
            statament.setString(3, user.getEmail());
            statament.setString(4, user.getAddress());
            statament.setString(5, user.getTelephone());
            statament.setString(6, user.getCa());

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        }
            return false;
    }

    public static boolean comprobarUsuarioProfesional(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "SELECT * FROM UsuarioProfesional WHERE code = ?";
        PreparedStatement statament = conn.prepareStatement(sql);

        statament.setString(1, user.getCenterCode());

        ResultSet result = statament.executeQuery();

        boolean existe = result.next();

        return existe;

    }

    public static boolean comprobarUsuario(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";

        PreparedStatement stmtProfesional = conn.prepareStatement(sqlProfesional);
        stmtProfesional.setString(1, user.getUsuario());
        ResultSet resultProfesional = stmtProfesional.executeQuery();

        if (resultProfesional.next()) {
            return true;
        }

        PreparedStatement stmtPersonal = conn.prepareStatement(sqlPersonal);
        stmtPersonal.setString(1, user.getUsuario());
        ResultSet resultPersonal = stmtPersonal.executeQuery();

        return resultPersonal.next();

    }

}
