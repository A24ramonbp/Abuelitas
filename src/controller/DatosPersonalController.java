/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ConnectMdb;
import model.UserLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsuarioPersonal;
import model.UsuarioProfesional;
import org.mindrot.jbcrypt.BCrypt;
import view.DatosPersonalDialog;
import view.DatosProfesionalDialog;
import view.MenuDialogProfesional;
import view.Personal.MenuDialogPersonal;

/**
 *
 * @author rpbp
 */
public class DatosPersonalController {

    private final DatosPersonalDialog view;
    private UserLogin userLogin;

    public DatosPersonalController(DatosPersonalDialog view, UserLogin userLogin) {
        this.view = view;
        this.userLogin = userLogin;
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addEditButtonActionListener(this.getEditButtonActionListener());
        this.view.addConfirmButtonActionListener(this.getConfirmButtonActionListener());
        initComponents();
    }

    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.editableAddressTextField(true);
                view.editableCACombobox(true);
                view.editableEmailTextField(true);
                view.editableNameTextField(true);
                view.editableTelephoneTextField(true);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.enabledEditButton(false);
            }
        };
        return al;
    }

    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
            }
        };
        return al;
    }

    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                MenuDialogPersonal md = new MenuDialogPersonal(view, true);
                MenuPersonalController mc = new MenuPersonalController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (editarDatos()) {
                        userLogin.getUsuarioPersonal().setAddress(view.getTextAddressTextField());
                        userLogin.getUsuarioPersonal().setName(view.getTextNameTextField());
                        userLogin.getUsuarioPersonal().setCa(view.getCAComboBox());
                        userLogin.getUsuarioPersonal().setEmail(view.getTextEmailTextField());
                        userLogin.getUsuarioPersonal().setTelephone(view.getTextTelephoneTextField());
                        initComponents();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DatosPersonalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    public static boolean ValidadorDni(String dni) {
        if (!dni.matches("\\d{8}[a-zA-Z]")) {
            return false;
        }

        /*String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numeros = dni.substring(0, 8);
        char letraIngresada = Character.toUpperCase(dni.charAt(8));
        int numero = Integer.parseInt(numeros);
        char letraCorrecta = letras.charAt(numero % 23);

        return letraIngresada == letraCorrecta;*/
        return true;
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean ValidarCampos() {
        if (view.getTextDniTextField().isBlank() || view.getTextUserTextField().isBlank() || view.getTextPasswordPasswordField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    public void initComponents() {
        view.setTextAddressTextField(userLogin.getUsuarioPersonal().getAddress());
        this.view.editableAddressTextField(false);
        view.setCAComboBox(userLogin.getUsuarioPersonal().getCa());
        this.view.editableCACombobox(false);
        view.setTextEmailTextField(userLogin.getUsuarioPersonal().getEmail());
        this.view.editableEmailTextField(false);
        view.setTextDniTextField(userLogin.getUsuarioPersonal().getDni());
        this.view.editableDniTextField(false);
        view.setTextNameTextField(userLogin.getUsuarioPersonal().getName());
        this.view.editableNameTextField(false);
        view.setTextPasswordPasswordField(userLogin.getUsuarioPersonal().getPassword());
        this.view.editablePasswordTextField(false);
        view.setTextTelephoneTextField(userLogin.getUsuarioPersonal().getTelephone());
        this.view.editableTelephoneTextField(false);
        view.setTextUserTextField(userLogin.getUsuarioPersonal().getUsuario());
        this.view.editableUserTextField(false);
        this.view.enabledConmfirmButton(false);
        this.view.enabledCancelButton(false);
        this.view.enabledEditButton(true);
        this.view.enabledBackButton(true);
    }

    public boolean editarDatos() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String dni = view.getTextDniTextField();
        String user = view.getTextUserTextField();
        String password = view.getTextPasswordPasswordField();
        String email = view.getTextEmailTextField();
        String address = view.getTextAddressTextField();
        String name = view.getTextNameTextField();
        String telefono = view.getTextTelephoneTextField();
        String ca = view.getCAComboBox();
        UsuarioPersonal usuarioPersonal = new UsuarioPersonal(dni, user, password, email, address, name, telefono, ca);

        if (!validarEmail(email) || !validarTelefono(telefono) || !ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        } else {
            String sql = "UPDATE UsuarioPersonal SET user=?, password=?, email=?, address=?, name=?, telefono=?, ca=? WHERE dni = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user);
            stmt.setString(2, encriptar(password));
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, name);
            stmt.setString(6, telefono);
            stmt.setString(7, ca);
            stmt.setString(8, dni);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        }

    }

    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean comprobarUsuario(UsuarioPersonal user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE user = ? ";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, user.getUsuario());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        if (resultCuidador.next()) {
            return true;
        }

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
