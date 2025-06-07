/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.profesional;

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
import model.UsuarioProfesional;
import org.mindrot.jbcrypt.BCrypt;
import view.profesional.ProfessionalDataDialog;
import view.profesional.ProfessionalMenuFrame;

/**
 *
 * @author rpbp
 */
public class ProfessionalDataController {

    private final ProfessionalDataDialog view;
    private UserLogin userLogin;

    public ProfessionalDataController(ProfessionalDataDialog view, UserLogin userLogin) {
        this.view = view;
        this.userLogin = userLogin;
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addEditButtonActionListener(this.getEditButtonActionListener());
        this.view.addConfirmButtonActionListener(this.getConfirmButtonActionListener());
        initComponents();
    }

    //Método que indica la acción del botón editar.
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

    //Método que indica la acción del botón cancelar.
    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
            }
        };
        return al;
    }

    //Método que indica la acción del botón vovler.
    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();

            }
        };
        return al;
    }

    //Método que indica la acción del botón confirmar.
    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (editarDatos()) {
                        userLogin.getUsuarioProfesional().setAddress(view.getTextAddressTextField());
                        userLogin.getUsuarioProfesional().setName(view.getTextNameTextField());
                        userLogin.getUsuarioProfesional().setCa(view.getCAComboBox());
                        userLogin.getUsuarioProfesional().setEmail(view.getTextEmailTextField());
                        userLogin.getUsuarioProfesional().setTelephone(view.getTextTelephoneTextField());
                        initComponents();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessionalDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    //Método para validar el teléfono según el formato establecido.
    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    //Método para validar el código según el formato establecido.
    public boolean validarCodigoCentro(String codigo) {
        return codigo.matches("^[A-Z]{2}/\\d{6}$");
    }

    //Método para validar el email según el formato establecido.
    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    //Validar que los campos estén cubiertos.
    public boolean ValidarCampos() {
        if (view.getTextIdTextField().isBlank() || view.getTextUserTextField().isBlank() || view.getTextPasswordPasswordField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    //Método para iniciar los componentes de la forma deseada.
    public void initComponents() {
        view.setTextAddressTextField(userLogin.getUsuarioProfesional().getAddress());
        this.view.editableAddressTextField(false);
        view.setCAComboBox(userLogin.getUsuarioProfesional().getCa());
        this.view.editableCACombobox(false);
        view.setTextEmailTextField(userLogin.getUsuarioProfesional().getEmail());
        this.view.editableEmailTextField(false);
        view.setTextCenterCodeTextField(userLogin.getUsuarioProfesional().getCenterCode());
        this.view.editableIdTextField(false);
        view.setTextNameTextField(userLogin.getUsuarioProfesional().getName());
        this.view.editableNameTextField(false);
        view.setTextPasswordPasswordField(userLogin.getUsuarioProfesional().getPassword());
        this.view.editablePasswordTextField(false);
        view.setTextTelephoneTextField(userLogin.getUsuarioProfesional().getTelephone());
        this.view.editableTelephoneTextField(false);
        view.setTextUserTextField(userLogin.getUsuarioProfesional().getUsuario());
        this.view.editableUserTextField(false);
        this.view.enabledConmfirmButton(false);
        this.view.enabledCancelButton(false);
        this.view.enabledEditButton(true);
        this.view.enabledBackButton(true);
    }

    //Método que habilita los campos a editar
    public boolean editarDatos() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String id = view.getTextIdTextField();
        String user = view.getTextUserTextField();
        String password = view.getTextPasswordPasswordField();
        String email = view.getTextEmailTextField();
        String address = view.getTextAddressTextField();
        String name = view.getTextNameTextField();
        String telefono = view.getTextTelephoneTextField();
        String ca = view.getCAComboBox();
        UsuarioProfesional UsuarioProfesional = new UsuarioProfesional(id, user, password, email, address, name, telefono, ca);

        if (!validarEmail(email) || !validarTelefono(telefono) || !ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        } else {
            String sql = "UPDATE UsuarioProfesional SET code=?, user=?, password=?, email=?, address=?, name=?, telefono=?, ca=? WHERE code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            stmt.setString(2, user);
            stmt.setString(3, encriptar(password));
            stmt.setString(4, email);
            stmt.setString(5, address);
            stmt.setString(6, name);
            stmt.setString(7, telefono);
            stmt.setString(8, ca);
            stmt.setString(9, userLogin.getUsuarioProfesional().getCenterCode());

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        }

    }

    //Método que encripta la contraseña
    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    //Método que comprueba que el usuario existe.
    public static boolean comprobarUsuario(UsuarioProfesional user) throws SQLException {
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
