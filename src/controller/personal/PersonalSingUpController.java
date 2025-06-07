/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.personal;

import controller.main.FrontController;
import controller.SingInController;
import controller.profesional.ProfessionalSingUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ConnectMdb;
import model.UsuarioPersonal;
import view.main.MainJFrame;
import view.personal.PersonalSingUpFrame;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import model.UserLogin;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rpbp
 */
public class PersonalSingUpController {

    private final PersonalSingUpFrame view;
    private UserLogin userLogin;

    public PersonalSingUpController(PersonalSingUpFrame view, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.view = view;
        this.getQuitActionListener();
        this.view.addConfirmButtonActionListener(this.getConfirmButtonListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonListener());
    }

    //Añdir acción del botón confirmar.
    private ActionListener getConfirmButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (CreateUser()) {
                        view.dispose();
                        MainJFrame md = new MainJFrame(view, true);
                        FrontController mc = new FrontController(md, userLogin);
                        md.setVisible(true);
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessionalSingUpController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return al;
    }

    //Añdir acción del menuItem salir.
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

    //Añdir acción del botón cancelar.
    private ActionListener getCancelButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.dispose();
                    MainJFrame md = new MainJFrame(view, true);
                    FrontController mc = new FrontController(md, userLogin);
                    md.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SingInController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        return al;
    }

    //Método para crear un usuario
    public boolean CreateUser() throws SQLException {
        String user = view.getTextUserTextField();
        String password = view.getTextPasswordPasswordField();
        String name = view.getTextNameTextField();
        String email = view.getTextEmailTextField();
        String dni = view.getTextDniTextField();
        String telephone = view.getTextTelephoneTextField();
        String address = view.getTextAddressTextField();
        String ca = view.getCAComboBox();

        if ((!ValidadorContraseña(password) || !ValidarCampos(user, password, name, email, dni, telephone, address)) || !ValidadorDni(dni) || !validarTelefono(telephone) || !validarEmail(email)) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t- Todos los campos rellenados.\n\t- La contraseña debe estar entre 8 y 20 caracteres y debe tener 1 letra y 1 numero.\n\t- El formato del dni es 12345678a\n\t- El formato del teléfono es 123456789\n\t- El formato del email es x@x.x");
            return false;
        } else {
            if (ValidadorContraseña(password) && ValidarCampos(user, password, name, email, dni, telephone, address)) {
                password = encriptar(view.getTextPasswordPasswordField());
                UsuarioPersonal usuario = new UsuarioPersonal(dni, user, password, name, email, address, telephone, ca);
                if (insertarUsuarioPersonal(usuario)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }

    }

    //Encripta la contraseña
    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    //Válida que la contraseña siga el formato establecido
    public static boolean ValidadorContraseña(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,19}$";
        return password.matches(regex);
    }

    //Válida que el teléfono siga el formato establecido
    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    //Válida que el dni siga el formato establecido
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

    //Válida que el email siga el formato establecido
    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    //Válida que los campos este rellandos
    public boolean ValidarCampos(String user, String password, String name, String email, String dni, String telephone, String address) {
        if (user.isBlank() || password.isBlank() || name.isBlank() || email.isBlank() || dni.isBlank() || telephone.isBlank() || address.isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    //Añade al usuario a la base de datos
    public boolean insertarUsuarioPersonal(UsuarioPersonal user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        if (!comprobarUsuarioPersonal(user)) {
            if (!comprobarUsuario(user)) {
                String sql = "Insert into UsuarioPersonal (dni,user,password, name, email, address, telefono, ca) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statament = conn.prepareStatement(sql);

                statament.setString(1, user.getDni());
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
            JOptionPane.showMessageDialog(view, "Ese dni ya existe en la base de datos.");
            return false;
        }
    }

    //Comprueba que el usuario no exista
    public static boolean comprobarUsuarioPersonal(UsuarioPersonal user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "SELECT * FROM UsuarioPersonal WHERE dni = ?";
        PreparedStatement statament = conn.prepareStatement(sql);

        statament.setString(1, user.getDni());

        ResultSet result = statament.executeQuery();

        boolean existe = result.next();

        return existe;

    }

    //Comprueba que el usuario no exista
    public static boolean comprobarUsuario(UsuarioPersonal user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlAdmin = "SELECT * FROM UsuarioAdmin WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuiador WHERE user = ?";

        PreparedStatement stmtProfesional = conn.prepareStatement(sqlProfesional);
        stmtProfesional.setString(1, user.getUsuario());
        ResultSet resultProfesional = stmtProfesional.executeQuery();

        if (resultProfesional.next()) {
            return true;
        }
        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, user.getUsuario());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        if (resultCuidador.next()) {
            return true;
        }
        PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
        stmtAdmin.setString(1, user.getUsuario());
        ResultSet resultAdmin = stmtAdmin.executeQuery();

        if (resultAdmin.next()) {
            return true;
        }

        PreparedStatement stmtPersonal = conn.prepareStatement(sqlPersonal);
        stmtPersonal.setString(1, user.getUsuario());
        ResultSet resultPersonal = stmtPersonal.executeQuery();

        return resultPersonal.next();

    }

}
