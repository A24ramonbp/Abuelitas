/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import model.ConnectMdb;
import model.UserLogin;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.Cliente;
import model.UsuarioCuidador;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import view.MenuDialogProfesional;
import view.ClientManagerDialog;

/**
 *
 * @author rpbp
 */
public class ClientManageController {

    private final ClientManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public ClientManageController(ClientManagerDialog view, UserLogin userLogin) throws SQLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addClientTableMouseListener(this.getClientTableMouseListener());
        this.view.addClientScrollPaneMouseListener(this.getClientScrollPaneMouseListener());
        this.view.addAddButtonActionListener(this.getAddButtonActionListener());
        this.view.addConfirmButtonActionListener(this.getConfirmButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addDeleteButtonActionListener(this.getDeleteButtonActionListener());
        this.view.addEditButtonActionListener(this.getEditButtonActionListener());
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        this.view.addImprimirButtonActionListener(this.getPrintButtonActionListener());
        initComponents();
        dataCuidadorComboBox();
        infoTable();
    }

    private MouseListener getClientScrollPaneMouseListener() {
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

    private ActionListener getPrintButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    print();
                } catch (IOException ex) {
                    Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    public void print() throws IOException, SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String currentDirectory = System.getProperty("user.dir");

        try {
            conn.setAutoCommit(false);
            Files.createDirectories(Paths.get(currentDirectory + "/Reports"));
            JasperReport report = JasperCompileManager.compileReport(
                    ClientManageController.class.getClassLoader().getResourceAsStream("reports/Cliente.jrxml")
            );
            JasperPrint print = JasperFillManager.fillReport(report, null, conn);
            JasperExportManager.exportReportToPdfFile(print, currentDirectory + "/Reports/Clientes.pdf");

            JRViewer viewer = new JRViewer(print);
            JDialog dialog = new JDialog((Frame) null, "Vista previa del reporte", true);
            dialog.getContentPane().add(viewer);
            dialog.setSize(800, 600);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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

    private MouseListener getClientTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    view.enabledDeleteButton(true);
                    view.enabledEditButton(true);
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

    private void infoTable() throws SQLException {
        view.clearTable();
        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM Cliente";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Vector row = new Vector();
                row.add(result.getString("dni"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                row.add(result.getString("cuidador"));
                row.add(result.getString("neurologo"));
                row.add(result.getString("fisioterapeuta"));
                view.addRowTable(row);
            }
        }
    }

    private void dataCuidadorComboBox() throws SQLException {

        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM UsuarioCuidador WHERE rol = 'Cuidador'";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                view.addItemCuidadorComboBox(result.getString("id"));
            }

            sql = "SELECT * FROM UsuarioCuidador WHERE rol = 'Neurologo'";

            stmt = conn.prepareStatement(sql);

            result = stmt.executeQuery();

            while (result.next()) {

                view.addItemNeurologoComboBox(result.getString("id"));
            }

            sql = "SELECT * FROM UsuarioCuidador WHERE rol = 'Fisioterapeuta'";

            stmt = conn.prepareStatement(sql);

            result = stmt.executeQuery();

            while (result.next()) {

                view.addItemFisioterapeutaComboBox(result.getString("id"));
            }
        }
    }

    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                MenuDialogProfesional md = new MenuDialogProfesional(view, true);
                MenuProfesionalController mc = new MenuProfesionalController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener getDeleteButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getClientTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione solo un registro.");
                } else {
                    String selected = view.getCellInfo();
                    int value = JOptionPane.showConfirmDialog(view, "Estas seguro que quieres eliminar el cliente?", " Confirm delete.", JOptionPane.YES_NO_OPTION);
                    if (value == 0) {
                        try {
                            Cliente client = obtenerCliente(selected);

                            if (eliminarCliente(client)) {
                                view.setNoteLabel("Cliente Eliminado.");
                                view.clearForm();
                                infoTable();
                                view.setNoteColor(new Color(44, 99, 56));
                                view.enabledDeleteButton(false);
                                view.enabledEditButton(false);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        return al;
    }

    private ActionListener getAddButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit = false;
                view.setNoteLabel("");
                view.clearForm();
                view.editableAddressTextField(true);
                view.editableCACombobox(true);
                view.editableEmailTextField(true);
                view.editableNameTextField(true);
                view.editableCuidadorCombobox(true);
                view.editableTelephoneTextField(true);
                view.editableDniTextField(true);
                view.enabledEditButton(false);
                view.enabledImprimirButton(true);
                view.enabledDeleteButton(false);
                view.enabledImprimirButton(false);
                view.enableClienteTextField(false);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.enabledAddButton(false);
                view.editableFisioterapuetaCombobox(true);
                view.editableNeurologoCombobox(true);
            }
        };
        return al;
    }

    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextClienteTextField().isBlank()) {

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        infoTableFiltro();
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();

        if (userLogin.getUsuarioProfesional() != null) {

            String sql = "SELECT * FROM Cliente WHERE dni Like ? OR name Like ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, view.getTextClienteTextField() + "%");
            stmt.setString(2, view.getTextClienteTextField() + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Vector row = new Vector();
                row.add(result.getString("dni"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                row.add(result.getString("cuidador"));
                row.add(result.getString("neurologo"));
                row.add(result.getString("fisioterapeuta"));
                view.addRowTable(row);
            }
        }

    }

    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!edit) {

                    try {
                        if (addClient()) {
                            view.setNoteLabel("Cliente añadido.");
                            view.setNoteColor(new Color(44, 99, 56));
                            view.clearForm();
                            initComponents();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        if (editCliente()) {
                            view.setNoteLabel("Cliente editado.");
                            view.setNoteColor(Color.blue);
                            view.clearForm();
                            initComponents();
                            infoTable();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };
        return al;
    }

    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getClientTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    edit = true;
                    view.setNoteLabel("");
                    view.editableAddressTextField(true);
                    view.editableCACombobox(true);
                    view.editableEmailTextField(true);
                    view.editableNameTextField(true);
                    view.editableTelephoneTextField(true);
                    view.editableDniTextField(false);
                    view.editableCuidadorCombobox(true);
                    view.enabledEditButton(false);
                    view.enabledEditButton(false);
                    view.enabledDeleteButton(false);
                    view.enabledConmfirmButton(true);
                    view.enabledCancelButton(true);
                    view.enabledBackButton(false);
                    view.enabledAddButton(false);
                    view.editableFisioterapuetaCombobox(true);
                    view.editableNeurologoCombobox(true);
                    view.setNoteLabel("");
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    private Cliente obtenerCliente(String dni) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        Cliente client = null;

        String sql = "SELECT * FROM Cliente WHERE dni = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dni);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            client = new Cliente();
            client.setDni(result.getString("dni"));
            client.setAddress(result.getString("address"));
            client.setEmail(result.getString("email"));
            client.setCa(result.getString("ca"));
            client.setCenter(result.getString("code"));
            client.setTelefono(result.getString("telefono"));
            client.setName(result.getString("name"));

            // Obtener y setear cuidador
            String cuidadorId = result.getString("cuidador");
            if (cuidadorId != null) {
                client.setCuidador(obtenerUsuario(cuidadorId));
            }

            // Obtener y setear neurólogo
            String neurologoId = result.getString("neurologo");
            if (neurologoId != null) {
                client.setNeurologo(obtenerUsuario(neurologoId));
            }

            // Obtener y setear fisioterapeuta
            String fisioId = result.getString("fisioterapeuta");
            if (fisioId != null) {
                client.setFisioterapeuta(obtenerUsuario(fisioId));
            }
        }

        return client;
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
        Pattern pattern = Pattern.compile(
                "^[\\p{L}0-9._%+-]+@[\\p{L}0-9.-]+\\.[a-zA-Z]{2,}$",
                Pattern.UNICODE_CHARACTER_CLASS
        );
        return pattern.matcher(email).matches();
    }

    private boolean addClient() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String dni = view.getTextDniTextField();
        String email = view.getTextEmailTextField();
        String address = view.getTextAddressTextField();
        String name = view.getTextNameTextField();
        String telefono = view.getTextTelephoneTextField();
        String cuidador = view.getCuidadorComboBox();
        String ca = view.getCAComboBox();
        String neurologo = view.getNeurologoComboBox();
        String fisioterapeuta = view.getFisioterapeutaComboBox();

        UsuarioCuidador usuarioC = obtenerUsuario(cuidador);
        UsuarioCuidador usuarioN = obtenerUsuario(cuidador);
        UsuarioCuidador usuarioF = obtenerUsuario(cuidador);

        Cliente cliente = new Cliente(dni, name, address, email, telefono, ca, usuarioC, userLogin.getUsuarioProfesional().getCenterCode(), usuarioN, usuarioF);

        if (comprobarClient(cliente)) {
            JOptionPane.showMessageDialog(view, "Ese dni ya existe.");
            return false;
        } else if (!validarEmail(email) || !validarTelefono(telefono) || !ValidadorDni(dni) || !ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del dni es 12345678a\n\t"
                    + "- El formato del teléfono es 123456789\n\t"
                    + "- El formato del email es x@x.x");
            return false;
        } else {

            String sql = "Insert into Cliente (dni, name, address, email, telefono, ca, cuidador, code, neurologo, fisioterapeuta) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, dni);
            statament.setString(2, name);
            statament.setString(3, address);
            statament.setString(4, email);
            statament.setString(5, telefono);
            statament.setString(6, ca);
            statament.setString(7, cuidador);
            statament.setString(8, userLogin.getUsuarioProfesional().getCenterCode());
            if (view.getNeurologoComboBoxIndex() != 0) {
                statament.setString(9, neurologo);
            } else {
                statament.setString(9, null);
            }
            if (view.getFisioterapeutaComboBoxIndex() != 0) {
                statament.setString(10, fisioterapeuta);
            } else {
                statament.setString(10, null);
            }

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al crear el cliente.");
                return false;
            }

        }

    }

    public boolean ValidarCampos() {
        if (view.getTextDniTextField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0 || view.getCuidadorComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    public static boolean comprobarClient(Cliente client) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlCuidador = "SELECT * FROM Cliente WHERE dni = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, client.getDni());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        return resultCuidador.next();

    }

    public boolean eliminarCliente(Cliente client) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM Cliente WHERE dni = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, client.getDni());
        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar el cliente.");
            return false;
        }

    }

    public void initComponents() {
        edit = false;
        view.editableAddressTextField(false);
        view.editableCACombobox(false);
        view.editableEmailTextField(false);
        view.editableNameTextField(false);
        view.editableCuidadorCombobox(false);
        view.editableTelephoneTextField(false);
        view.editableDniTextField(false);
        view.enabledEditButton(false);
        view.enabledImprimirButton(true);
        view.enabledDeleteButton(false);
        view.enabledAddButton(true);
        view.enabledBackButton(true);
        view.enabledConmfirmButton(false);
        view.enabledCancelButton(false);
        view.editableFisioterapuetaCombobox(false);
        view.editableNeurologoCombobox(false);
    }

    private boolean editCliente() throws SQLException {
        String selected = view.getCellInfo();
        Cliente antiguo = obtenerCliente(selected);

        String nuevoDni = view.getTextDniTextField();
        Cliente duplicado = obtenerCliente(nuevoDni);

        if (!nuevoDni.equals(antiguo.getDni()) && duplicado != null) {
            view.setNoteLabel("El dni ya existe.");
            view.setNoteColor(Color.red);
            return false;
        }

        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "UPDATE Cliente SET dni = ?, name = ?, telefono = ?, email = ?, address = ?, ca = ?, cuidador = ?, neurologo = ?, fisioterapeuta = ? WHERE dni = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, nuevoDni);
        stmt.setString(2, view.getTextNameTextField());
        stmt.setString(3, view.getTextTelephoneTextField());
        stmt.setString(4, view.getTextEmailTextField());
        stmt.setString(5, view.getTextAddressTextField());
        stmt.setString(6, view.getCAComboBox());
        stmt.setString(7, (String) view.getCuidadorComboBox());

        if (view.getNeurologoComboBoxIndex() != 0) {
            stmt.setString(8, (String) view.getNeurologoComboBox());
        } else {
            stmt.setString(8, null);
        }

        if (view.getFisioterapeutaComboBoxIndex() != 0) {
            stmt.setString(9, (String) view.getFisioterapeutaComboBox());
        } else {
            stmt.setString(9, null);
        }

        stmt.setString(10, antiguo.getDni());

        int rows = stmt.executeUpdate();
        return rows > 0;
    }

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

    private void infoUser() throws SQLException {
        String cliente = view.getCellInfo();
        Cliente client = obtenerCliente(cliente);
        view.setTextNameTextField(client.getName());
        view.setTextEmailTextField(client.getEmail());
        view.setTextAddressTextField(client.getAddress());
        view.setTextTelephoneTextField(client.getTelefono());
        view.setTextDniTextField(client.getDni());
        view.setCAComboBox(client.getCa());
        if (client.getCuidador() != null) {
            view.setCuidadorComboBox(client.getCuidador().getId());
        } else {
            view.setCuidadorComboBox("");
        }

        if (client.getNeurologo() != null) {
            view.setNeurologoComboBox(client.getNeurologo().getId());
        } else {

            view.setNeurologoComboBoxIndex(0);
        }

        if (client.getFisioterapeuta() != null) {
            view.setFisioterapuetaComboBox(client.getFisioterapeuta().getId());
        } else {
            view.setFisioterapeutaComboBoxIndex(0);
        }
    }

}
