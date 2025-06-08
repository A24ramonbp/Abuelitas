/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.profesional;

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
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.UsuarioCuidador;
import org.mindrot.jbcrypt.BCrypt;
import view.profesional.ProfessionalMenuFrame;
import view.profesional.UserManagerDialog;

/**
 *
 * @author rpbp
 */
public class UserManageController {

    private final UserManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public UserManageController(UserManagerDialog view, UserLogin userLogin) throws SQLException {
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

    //Método que indica la acción del botón filtrar.
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

    //Método que comprueba si hay relaciones con ese cuidador para poder borrarlo.
    public boolean puedeBorrarseCuidador(String idCuidador) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String sql = """
        SELECT COUNT(*) FROM Cliente WHERE cuidador = ?
        UNION
        SELECT COUNT(*) FROM Cliente WHERE fisioterapeuta = ?
        UNION
        SELECT COUNT(*) FROM Cliente WHERE neurologo = ?
        UNION
        SELECT COUNT(*) FROM TareasAsignadas WHERE medico_id = ?
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCuidador);
            stmt.setString(2, idCuidador);
            stmt.setString(3, idCuidador);
            stmt.setString(4, idCuidador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //Método que actualiza la información de la tabla según el filtro
    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();

        if (userLogin.getUsuarioProfesional() != null) {

            String sql = "SELECT * FROM UsuarioCuidador WHERE id Like ? OR name Like ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, view.getTextUsuarioTextField() + "%");
            stmt.setString(2, view.getTextUsuarioTextField() + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("dni"));
                row.add(result.getString("user"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }

    }

    //Método que elimina la selección de la tabla
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

     //Método que indica la acción del botón cancelar.
    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
                view.setNoteLabel("");
                view.clearForm();
            }
        };
        return al;
    }

    //Método que indica la acción al seleccionar una opción de la tabla
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
                        Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
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

    //Método que actualiza la información de la tabla.
    private void infoTable() throws SQLException {
        view.clearTable();
        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM UsuarioCuidador";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("dni"));
                row.add(result.getString("user"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }
    }

     //Método que indica la acción del botón volver.
    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();

            }
        };
        return al;
    }

     //Método que indica la acción del botón eliminar.
    private ActionListener getDeleteButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getUserTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione solo un registro.");
                } else {
                    view.setNoteLabel("");
                    String selected = view.getCellInfo();
                    int value = JOptionPane.showConfirmDialog(view, "Estas seguro que quieres eliminar el usuario?", " Confirm delete.", JOptionPane.YES_NO_OPTION);
                    if (value == 0) {
                        try {
                            UsuarioCuidador usuario = obtenerUsuario(selected);
                            if (puedeBorrarseCuidador(usuario.getId())) {
                                if (eliminarUsuario(usuario)) {
                                    view.setNoteLabel("Usuario eliminado.");
                                    view.clearForm();
                                    infoTable();
                                    view.setNoteColor(new Color(44, 99, 56));
                                    view.enabledDeleteButton(false);
                                    view.enabledEditButton(false);
                                }
                            } else {
                                JOptionPane.showMessageDialog(view, "No puede borrar a ese usuario mientras haya registros guardados.");
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        return al;
    }

     //Método que indica la acción del botón añadir.
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
                view.editableRolCombobox(true);
                view.editableTelephoneTextField(true);
                view.editableUserTextField(true);
                view.editableDniTextField(true);
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

     //Método que indica la acción del botón confirmar.
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
                        Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };
        return al;
    }

     //Método que indica la acción del botón editar.
    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getUserTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    view.setNoteLabel("");
                    edit = true;
                    view.editableAddressTextField(true);
                    view.editableCACombobox(true);
                    view.editableEmailTextField(true);
                    view.editableNameTextField(true);
                    view.editablePasswordTextField(false);
                    view.editableRolCombobox(true);
                    view.editableTelephoneTextField(true);
                    view.editableUserTextField(true);
                    view.editableDniTextField(false);
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
                        Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }
    
    //Método que obtiene el usuario por el id
    private UsuarioCuidador obtenerUsuario(String id) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        UsuarioCuidador user = new UsuarioCuidador();

        String sql = "SELECT * FROM UsuarioCuidador WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            user.setUsuario(result.getString("user"));
            user.setDni(result.getString("dni"));
            user.setAddress(result.getString("address"));
            user.setEmail(result.getString("email"));
            user.setCa(result.getString("ca"));
            user.setCenter(result.getString("code"));
            user.setRol(result.getString("rol"));
            user.setTelephone(result.getString("telefono"));
            user.setId(result.getString("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
        }

        return user;
    }

    //Método para encriptar la contraseña
    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    //Método para validar la contraseña según el formato establecido.
    public static boolean ValidadorContraseña(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,19}$";
        return password.matches(regex);
    }

    //Método para validar el id según el formato establecido.
    public static boolean validarId(String id) {
        return id != null && id.matches("\\d{1,6}");
    }

    //Método para validar el teléfono según el formato establecido.
    public boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    //Método para validar el dni según el formato establecido.
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

    //Método para validar el email según el formato establecido.
    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    //Método para añadir el usuario
    private boolean addUser() throws SQLException {
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
        UsuarioCuidador usuarioCuidador = new UsuarioCuidador(id, user, password, email, address, dni, name, telefono, rol, ca, userLogin.getUsuarioProfesional().getCenterCode());

        if (!validarEmail(email) || !validarTelefono(telefono) || !ValidadorDni(dni) || !ValidarCampos() || !validarId(id)) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del id son de 1 a 6 dígitos\n\t"
                    + "- El formato del dni es 12345678a\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;

        } else if (comprobarUsuarioAñadir(usuarioCuidador)) {
            JOptionPane.showMessageDialog(view, "El usuario o id ya existen.");
            return false;
        } else {
            String passwordEncriptada = encriptar(password);
            String sql = "Insert into UsuarioCuidador (id, user, password, email, address, dni, name, telefono, rol, ca, code) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, id);
            statament.setString(2, user);
            statament.setString(3, passwordEncriptada);
            statament.setString(4, email);
            statament.setString(5, address);
            statament.setString(6, dni);
            statament.setString(7, name);
            statament.setString(8, telefono);
            statament.setString(9, rol);
            statament.setString(10, ca);
            statament.setString(11, usuarioCuidador.getCenter());

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al crear el usuario.");
                return false;
            }

        }

    }

    //Método para comprobar el usuario a añadir no exista
    public static boolean comprobarUsuarioAñadir(UsuarioCuidador user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlAdmin = "SELECT * From UsuarioAdmin WHERE dni = ? OR user = ?";
        String sqlCuidadorUser = "SELECT 1 FROM UsuarioCuidador WHERE user = ?";
        PreparedStatement stmtUser = conn.prepareStatement(sqlCuidadorUser);
        stmtUser.setString(1, user.getUsuario());
        if (stmtUser.executeQuery().next()) {
            return true;
        }

      
        String sqlCuidadorDni = "SELECT 1 FROM UsuarioCuidador WHERE dni = ?";
        PreparedStatement stmtDni = conn.prepareStatement(sqlCuidadorDni);
        stmtDni.setString(1, user.getDni());
        if (stmtDni.executeQuery().next()) {
            return true;
        }

        String sqlCuidadorIdCentro = "SELECT * FROM UsuarioCuidador WHERE id = ? AND code = ?";
        PreparedStatement stmtIdCentro = conn.prepareStatement(sqlCuidadorIdCentro);
        stmtIdCentro.setString(1, user.getId());
        stmtIdCentro.setString(2, user.getCenter());
        ResultSet resultIdCentro = stmtIdCentro.executeQuery();

        if (resultIdCentro.next()) {
            return true;
        }

        PreparedStatement stmtProfesional = conn.prepareStatement(sqlProfesional);
        stmtProfesional.setString(1, user.getUsuario());
        ResultSet resultProfesional = stmtProfesional.executeQuery();

        if (resultProfesional.next()) {
            return true;
        }

        PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
        stmtAdmin.setString(1, user.getDni());
        stmtAdmin.setString(2, user.getUsuario());

        ResultSet resultAdmin = stmtAdmin.executeQuery();

        if (resultAdmin.next()) {
            return true;
        }

        PreparedStatement stmtPersonal = conn.prepareStatement(sqlPersonal);
        stmtPersonal.setString(1, user.getUsuario());
        ResultSet resultPersonal = stmtPersonal.executeQuery();

        return resultPersonal.next();

    }

    //Método para validar que todo esté rellenado
    public boolean ValidarCampos() {
        if (view.getTextIdTextField().isBlank() || view.getTextUserTextField().isBlank() || view.getTextPasswordPasswordField().isBlank() || view.getTextDniTextField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0 || view.getRolComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    //Método que comprueba si el usuario a editar existe.
    public static boolean comprobarUsuarioEditar(UsuarioCuidador user, String idActual) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlProfesional = "SELECT * FROM UsuarioProfesional WHERE user = ?";
        String sqlPersonal = "SELECT * FROM UsuarioPersonal WHERE user = ?";
        String sqlCuidador = "SELECT * FROM UsuarioCuidador WHERE user = ? AND id <> ?";
        String sqlAdmin = "SELECT * FROM UsuarioAdmin WHERE user = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, user.getUsuario());
        stmtCuidador.setString(2, idActual);
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

    //Método para eliminar un usuario
    public boolean eliminarUsuario(UsuarioCuidador user) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM UsuarioCuidador WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getId());
        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {

            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar el usuario.");
            return false;
        }

    }

    //Método para iniciar los componentes con el valor deseado
    public void initComponents() {

        edit = false;
        view.editableAddressTextField(false);
        view.editableCACombobox(false);
        view.editableEmailTextField(false);
        view.editableIdTextField(false);
        view.editableNameTextField(false);
        view.editablePasswordTextField(false);
        view.editableRolCombobox(false);
        view.editableTelephoneTextField(false);
        view.editableUserTextField(false);
        view.editableDniTextField(false);
        view.enabledEditButton(false);
        view.enabledDeleteButton(false);
        view.enabledAddButton(true);
        view.enabledBackButton(true);
        view.enabledConmfirmButton(false);
        view.enabledCancelButton(false);
        view.enabledFiltrarButton(true);
        view.enabledUsuarioTextField(true);
    }

    //Método para editar el usuario
    private boolean editUsuario() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String selected = view.getCellInfo();
        UsuarioCuidador original = obtenerUsuario(selected);

        String newUser = view.getTextUserTextField();

        String newDni = view.getTextDniTextField();

        if (!newUser.equals(original.getUsuario()) || !newDni.equals(original.getDni())) {
            UsuarioCuidador usuarioNuevo = new UsuarioCuidador();
            usuarioNuevo.setUsuario(newUser);
            usuarioNuevo.setDni(newDni);
            usuarioNuevo.setCenter(userLogin.getUsuarioProfesional().getCenterCode());

            if (comprobarUsuarioEditar(usuarioNuevo, original.getId())) {
                JOptionPane.showMessageDialog(view, "El usuario ya existe.");
                view.setNoteColor(Color.red);
                return false;
            }
        }

        if (!validarEmail(view.getTextEmailTextField()) || !validarTelefono(view.getTextTelephoneTextField()) || !ValidadorDni(view.getTextDniTextField()) || !ValidarCampos()) {

            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del dni es 12345678a\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        }
        String sql = "UPDATE UsuarioCuidador SET  user=?, email=?, address=?, dni=?, name=?, telefono=?, rol=?, ca=?, code=? WHERE id=? AND code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, newUser);
        stmt.setString(2, view.getTextEmailTextField());
        stmt.setString(3, view.getTextAddressTextField());
        stmt.setString(4, view.getTextDniTextField());
        stmt.setString(5, view.getTextNameTextField());
        stmt.setString(6, view.getTextTelephoneTextField());
        stmt.setString(7, view.getRolComboBox());
        stmt.setString(8, view.getCAComboBox());
        stmt.setString(9, userLogin.getUsuarioProfesional().getCenterCode());
        stmt.setString(10, original.getId());
        stmt.setString(11, userLogin.getUsuarioProfesional().getCenterCode());

        int affectedRows = stmt.executeUpdate();

        return affectedRows > 0;
    }

    //Método para obtener información de la selecciónd de la tabla.
    private void infoUser() throws SQLException {
        String selected = view.getCellInfo();
        UsuarioCuidador usuario = obtenerUsuario(selected);

        view.setTextIdTextField(usuario.getId());
        view.setTextUserTextField(usuario.getUsuario());
        view.setTextPasswordPasswordField(usuario.getPassword());
        view.setTextNameTextField(usuario.getName());
        view.setTextEmailTextField(usuario.getEmail());
        view.setTextAddressTextField(usuario.getAddress());
        view.setTextTelephoneTextField(usuario.getTelephone());
        view.setTextDniTextField(usuario.getDni());
        view.setCAComboBox(usuario.getCa());
        view.setRolComboBox(usuario.getRol());
    }

}
