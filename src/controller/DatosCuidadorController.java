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
import model.UsuarioCuidador;
import view.DatosCuidadorDialog;
import view.MenuDialogCuidador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rpbp
 */
public class DatosCuidadorController {

    private final DatosCuidadorDialog view;
    private UserLogin userLogin;

    public DatosCuidadorController(DatosCuidadorDialog view, UserLogin userLogin) {
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
                MenuDialogCuidador md = new MenuDialogCuidador(view, true);
                MenuCuidadorController mc = new MenuCuidadorController(md, userLogin);
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
                        userLogin.getUsuarioCuidador().setAddress(view.getTextAddressTextField());
                        userLogin.getUsuarioCuidador().setName(view.getTextNameTextField());
                        userLogin.getUsuarioCuidador().setCa(view.getCAComboBox());
                        userLogin.getUsuarioCuidador().setEmail(view.getTextEmailTextField());
                        userLogin.getUsuarioCuidador().setTelephone(view.getTextTelephoneTextField());
                        initComponents();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DatosCuidadorController.class.getName()).log(Level.SEVERE, null, ex);
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
        if (view.getTextIdTextField().isBlank() || view.getTextUserTextField().isBlank() || view.getTextPasswordPasswordField().isBlank() || view.getTextDniTextField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0 || view.getRolComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    public void initComponents() {
        view.setTextAddressTextField(userLogin.getUsuarioCuidador().getAddress());
        this.view.editableAddressTextField(false);
        view.setCAComboBox(userLogin.getUsuarioCuidador().getCa());
        this.view.editableCACombobox(false);
        view.setTextDniTextField(userLogin.getUsuarioCuidador().getDni());
        this.view.editableDniTextField(false);
        view.setTextEmailTextField(userLogin.getUsuarioCuidador().getEmail());
        this.view.editableEmailTextField(false);
        view.setTextIdTextField(userLogin.getUsuarioCuidador().getId());
        this.view.editableIdTextField(false);
        view.setTextNameTextField(userLogin.getUsuarioCuidador().getName());
        this.view.editableNameTextField(false);
        view.setTextPasswordPasswordField(userLogin.getUsuarioCuidador().getPassword());
        this.view.editablePasswordTextField(false);
        view.setRolComboBox(userLogin.getUsuarioCuidador().getRol());
        this.view.editableRolCombobox(false);
        view.setTextTelephoneTextField(userLogin.getUsuarioCuidador().getTelephone());
        this.view.editableTelephoneTextField(false);
        view.setTextUserTextField(userLogin.getUsuarioCuidador().getUsuario());
        this.view.editableUserTextField(false);
        this.view.enabledConmfirmButton(false);
        this.view.enabledCancelButton(false);
        this.view.enabledEditButton(true);
        this.view.enabledBackButton(true);
    }

    public boolean editarDatos() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String id = view.getTextIdTextField();
        String user = view.getTextUserTextField();
        String password = view.getTextPasswordPasswordField();
        String email = view.getTextEmailTextField();
        String address = view.getTextAddressTextField();
        String dni = view.getTextDniTextField();
        String name = view.getTextNameTextField();
        String telefono = view.getTextTelephoneTextField();
        String rol = view.getRolComboBox();
        String ca = view.getCAComboBox();
        UsuarioCuidador usuarioCuidador = new UsuarioCuidador(id, user, password, email, address, dni, name, telefono, rol, ca, userLogin.getUsuarioCuidador().getCenter());

        if (!validarEmail(email) || !validarTelefono(telefono) || !ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        } else {
            String sql = "UPDATE UsuarioCuidador SET id=?, user=?, password=?, email=?, address=?, dni=?, name=?, telefono=?, rol=?, ca=?, code=? WHERE id=? AND code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            stmt.setString(2, user);
            stmt.setString(3, encriptar(password));
            stmt.setString(4, email);
            stmt.setString(5, address);
            stmt.setString(6, dni);
            stmt.setString(7, name);
            stmt.setString(8, telefono);
            stmt.setString(9, rol);
            stmt.setString(10, ca);
            stmt.setString(11, userLogin.getUsuarioCuidador().getCenter());
            stmt.setString(12, id);
            stmt.setString(13, userLogin.getUsuarioCuidador().getCenter());

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        }

    }

    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean comprobarUsuario(UsuarioCuidador user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE user = ? OR id = ? AND code = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, user.getUsuario());
        stmtCuidador.setString(2, user.getId());
        stmtCuidador.setString(3, user.getCenter());
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
