/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;


import controller.AssignTaskManagerController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import model.ConnectMdb;
import model.UserLogin;
import model.UsuarioProfesional;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import view.admin.UserProfessionalManagerDialog;

/**
 *
 * @author rpbp
 */
public class UserProfesionalManagerController {

    private final UserProfessionalManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public UserProfesionalManagerController(UserProfessionalManagerDialog view, UserLogin userLogin) throws SQLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addUserTableMouseListener(this.getUserTableMouseListener());
        this.view.addUserScrollPaneMouseListener(this.getUserScrollPaneMouseListener());
        this.view.addAddButtonActionListener(this.getAddButtonActionListener());
        this.view.addConfirmButtonActionListener(this.getConfirmButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addDeleteButtonActionListener(this.getDeleteButtonActionListener());
        this.view.addEditButtonActionListener(this.getEditButtonActionListener());
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        initComponents();
        infoTable();
    }

    //Método que define la acción del botón filtrar.
    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextUsuarioTextField().isBlank()) {

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        infoTableFiltro();
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    //Método que actualiza los datos de la tabla con el valor del filtro
    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();

        if (userLogin.getUsuarioAdmin() != null) {

            String sql = "SELECT * FROM UsuarioProfesional WHERE code Like ? OR name Like ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, view.getTextUsuarioTextField() + "%");
            stmt.setString(2, view.getTextUsuarioTextField() + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Vector row = new Vector();
                row.add(result.getString("code"));
                row.add(result.getString("name"));
                row.add(result.getString("user"));
                row.add(result.getString("email"));
                row.add(result.getString("address"));
                row.add(result.getString("ca"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }

    }

    //Método que actualiza los datos de la tabla
    private void infoTable() throws SQLException {
        view.clearTable();
        if (userLogin.getUsuarioAdmin() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM UsuarioProfesional";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
    
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("code"));
                row.add(result.getString("name"));
                row.add(result.getString("user"));
                row.add(result.getString("email"));
                row.add(result.getString("address"));
                row.add(result.getString("ca"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }
    }

    //Método para eliminar selección
    private MouseListener getUserScrollPaneMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    view.clearSelection(e);
                    view.enabledDeleteButton(false);
                    view.enabledEditButton(false);
                    view.clearForm();

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return al;

    }

    //Método que define la acción del botón cancelar.
    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
                view.clearForm();
            }
        };
        return al;
    }

    //Método para seleccionar un fila de la tabla
    private MouseListener getUserTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    view.enabledDeleteButton(true);
                    view.enabledEditButton(true);
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return al;
    }

    //Método que define la acción del botón volver.
    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
               
            }
        };
        return al;
    }

    //Método que define la acción del botón eliminar.
    private ActionListener getDeleteButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getUserTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione solo un registro.");
                } else {
                    view.setNoteLabel("");
                    String selected = view.getCellInfo();
                    int value = JOptionPane.showConfirmDialog(view, "Seguro que quieres eliminar la cuenta", " Confirm delete.", JOptionPane.YES_NO_OPTION);
                    if (value == 0) {
                        try {
                            UsuarioProfesional usuario = obtenerUsuario(selected);

                            if (eliminarUsuario(usuario)) {
                                view.setNoteLabel("Cuenta eliminada.");
                                view.clearForm();
                                infoTable();
                                view.setNoteColor(new Color(44, 99, 56));
                                view.enabledDeleteButton(false);
                                view.enabledEditButton(false);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        return al;
    }

    //Método que define la acción del botón añadir.
    private ActionListener getAddButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setNoteLabel("");
                view.clearForm();
                edit = false;
                view.editableAddressTextField(true);
                view.editableCACombobox(true);
                view.editableEmailTextField(true);
                view.editableNameTextField(true);
                view.editablePasswordTextField(true);
                view.editableTelephoneTextField(true);
                view.editableUserTextField(true);
                view.enabledEditButton(false);
                view.enabledDeleteButton(false);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.editableIdTextField(true);
                view.enabledAddButton(false);
                view.enabledFiltrarButton(false);
                view.enabledUsuarioTextField(false);
            }
        };
        return al;
    }

    //Método que define la acción del botón confirmar.
    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!edit) {

                    try {
                        if (addUser()) {
                            view.setNoteLabel("Usuario añadido.");
                            view.setNoteColor(new Color(44, 99, 56));
                            view.clearForm();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        if (editUsuario()) {
                            view.setNoteLabel("Usuario editado.");
                            view.setNoteColor(Color.blue);
                            view.clearForm();
                            initComponents();
                            infoTable();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };
        return al;
    }

    //Método que define la acción del botón editar.
    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getUserTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view,"Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    view.setNoteLabel("");
                    edit = true;
                    view.editableIdTextField(false);
                    view.editableAddressTextField(true);
                    view.editableCACombobox(true);
                    view.editableEmailTextField(true);
                    view.editableNameTextField(true);
                    view.editablePasswordTextField(false);
                    view.editableTelephoneTextField(true);
                    view.editableUserTextField(true);
                    view.enabledEditButton(false);
                    view.enabledDeleteButton(false);
                    view.enabledConmfirmButton(true);
                    view.enabledCancelButton(true);
                    view.enabledBackButton(false);
                    view.enabledAddButton(false);
                    view.enabledFiltrarButton(false);
                    view.enabledUsuarioTextField(false);
                    view.setNoteLabel("");
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserProfesionalManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }
    
    //Método para obtener el usuario según el código
    private UsuarioProfesional obtenerUsuario(String code) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        UsuarioProfesional user = new UsuarioProfesional();

        String sql = "SELECT * FROM UsuarioProfesional WHERE code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, code);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            user.setUsuario(result.getString("user"));
            user.setAddress(result.getString("address"));
            user.setEmail(result.getString("email"));
            user.setCa(result.getString("ca"));
            user.setCenterCode(result.getString("code"));
            user.setTelephone(result.getString("telefono"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
        }

        return user;
    }

    //Método para validar el teléfono según el formato establecido.
    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    //Método para validar el email según el formato establecido.
    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    //Método para añadir usuario.
    private boolean addUser() throws SQLException {
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

        UsuarioProfesional usuario = new UsuarioProfesional(id, user, password, name, email, address, telefono, ca);

        if (!validarEmail(email) || !validarTelefono(telefono) || !ValidarCampos() || !validarCodigoCentro(view.getTextIdTextField())) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del codigo del centro es XX/123456\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
            
        } else if (comprobarUsuarioAñadir(usuario)) {
            JOptionPane.showMessageDialog(view, "El usuario o el codigo ya existe.");
            return false;
        } else {
            String passwordEncriptada = encriptar(password);
            String sql = "Insert into UsuarioProfesional (code, user, password, email, address, name, telefono, ca) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, id);
            statament.setString(2, user);
            statament.setString(3, passwordEncriptada);
            statament.setString(4, email);
            statament.setString(5, address);
            statament.setString(6, name);
            statament.setString(7, telefono);
            statament.setString(8, ca);

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al crear el usuario.");
                return false;
            }

        }

    }

    //Método para encriptar.
    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    //Método para validar que los campos estén cubiertos.
    public boolean ValidarCampos() {
        if (view.getTextIdTextField().isBlank() || view.getTextUserTextField().isBlank() || view.getTextPasswordPasswordField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    //Método que comprueba que el usuario a añadir no exista
    public static boolean comprobarUsuarioAñadir(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ? OR code = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlAdmin = "SELECT * FROM UsuarioAdmin WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE  user = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, user.getUsuario());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        if (resultCuidador.next()) {
            return true;
        }

        PreparedStatement stmtProfesional = conn.prepareStatement(sqlProfesional);
        stmtProfesional.setString(1, user.getUsuario());
        stmtProfesional.setString(2, user.getCenterCode());
        ResultSet resultProfesional = stmtProfesional.executeQuery();

        if (resultProfesional.next()) {
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

    //Método para eliminar usuarios.
    public boolean eliminarUsuario(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM UsuarioProfesional WHERE code = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getCenterCode());
        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {

            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar el usuario.");
            return false;
        }

    }

    //Método para iniciar componentes de forma deseada
    public void initComponents() {

        edit = false;
        view.editableAddressTextField(false);
        view.editableCACombobox(false);
        view.editableEmailTextField(false);
        view.editableIdTextField(false);
        view.editableNameTextField(false);
        view.editablePasswordTextField(false);
        view.editableTelephoneTextField(false);
        view.editableUserTextField(false);
        view.enabledEditButton(false);
        view.enabledDeleteButton(false);
        view.enabledAddButton(true);
        view.enabledBackButton(true);
        view.enabledConmfirmButton(false);
        view.enabledCancelButton(false);
        view.enabledFiltrarButton(true);
        view.enabledUsuarioTextField(true);
    }

    //Método que comprueba el usuario a editar.
    public static boolean comprobarUsuarioEditar(UsuarioProfesional user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlAdmin = "SELECT * FROM UsuarioAdmin WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE user = ?";

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

    //Método para editar usuarios.
    private boolean editUsuario() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String selected = view.getCellInfo();
        UsuarioProfesional original = obtenerUsuario(selected);

        String newUser = view.getTextUserTextField();

        if (!newUser.equals(original.getUsuario())) {
            UsuarioProfesional usuarioNuevo = new UsuarioProfesional();
            usuarioNuevo.setUsuario(newUser);

            if (comprobarUsuarioEditar(usuarioNuevo)) {
                view.setNoteLabel("El nombre de usuario ya está en uso.");
                view.setNoteColor(Color.red);
                return false;
            }
        }

        if (!validarEmail(view.getTextEmailTextField())
                || !validarTelefono(view.getTextTelephoneTextField())
                || !ValidarCampos() || !validarCodigoCentro(view.getTextIdTextField())) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo esté correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        }

        String sql = "UPDATE UsuarioProfesional SET code=?, user=?, email=?, address=?, name=?, telefono=?, ca=? WHERE code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, view.getTextIdTextField());
        stmt.setString(2, newUser);
        stmt.setString(3, view.getTextEmailTextField());
        stmt.setString(4, view.getTextAddressTextField());
        stmt.setString(5, view.getTextNameTextField());
        stmt.setString(6, view.getTextTelephoneTextField());
        stmt.setString(7, view.getCAComboBox());
        stmt.setString(8, view.getTextIdTextField());

        int affectedRows = stmt.executeUpdate();

        return affectedRows > 0;
    }

    //Método que devuelve información del usuario selccionado.
    private void infoUser() throws SQLException {
        String selected = view.getCellInfo();
        UsuarioProfesional usuario = obtenerUsuario(selected);

        view.setTextIdTextField(usuario.getCenterCode());
        view.setTextUserTextField(usuario.getUsuario());
        view.setTextPasswordPasswordField(usuario.getPassword());
        view.setTextNameTextField(usuario.getName());
        view.setTextEmailTextField(usuario.getEmail());
        view.setTextAddressTextField(usuario.getAddress());
        view.setTextTelephoneTextField(usuario.getTelephone());
        view.setCAComboBox(usuario.getCa());
    }
    
    //Método para validar el código según el formato establecido.
    public boolean validarCodigoCentro(String codigo) {
        return codigo.matches("^[A-Z]{2}/\\d{6}$");
    }

}
