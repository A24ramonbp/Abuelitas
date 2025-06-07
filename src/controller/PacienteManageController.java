/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.SingUpControllerProfessional.comprobarUsuario;
import static controller.SingUpControllerProfessional.comprobarUsuarioProfesional;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import model.Cliente;
import model.UsuarioCuidador;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import view.MenuDialogCuidador;
import view.PacienteManagerDialog;

/**
 *
 * @author rpbp
 */
public class PacienteManageController {

    private final PacienteManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public PacienteManageController(PacienteManagerDialog view, UserLogin userLogin) throws SQLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addPacienteTableMouseListener(this.getClientTableMouseListener());
        this.view.addPacienteScrollPaneMouseListener(this.getPacienteScrollPaneMouseListener());
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        this.view.addImprimirButtonActionListener(this.getPrintButtonActionListener());
        initComponents();
        infoTable();
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
        ResultSet result = null;

        if (userLogin.getUsuarioCuidador() != null) {

            String sql = "SELECT * FROM Cliente where cuidador = ? or neurologo = ? or fisioterapeuta = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioCuidador().getId());
            stmt.setString(2, userLogin.getUsuarioCuidador().getId());
            stmt.setString(3, userLogin.getUsuarioCuidador().getId());
            result = stmt.executeQuery();

        }

        JRResultSetDataSource jrDataSource = new JRResultSetDataSource(result);

        try {
            conn.setAutoCommit(false);
            Files.createDirectories(Paths.get(currentDirectory + "/Reports"));
            JasperReport report = JasperCompileManager.compileReport(
                    ClientManageController.class.getClassLoader().getResourceAsStream("reports/Paciente.jrxml")
            );
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), jrDataSource);
            JasperExportManager.exportReportToPdfFile(print, currentDirectory + "/Reports/Pacientes.pdf");

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

    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextPacienteTextField().isBlank()) {

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

        if (userLogin.getUsuarioCuidador() != null) {

            String sql = "SELECT * FROM Cliente where (cuidador = ? or neurologo = ? or fisioterapeuta = ?) AND (dni LIKE ? OR name Like ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioCuidador().getId());
            stmt.setString(2, userLogin.getUsuarioCuidador().getId());
            stmt.setString(3, userLogin.getUsuarioCuidador().getId());
            stmt.setString(4, view.getTextPacienteTextField() + "%");
            stmt.setString(5, view.getTextPacienteTextField() + "%");

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
                view.addRowTable(row);
            }
        }

    }

    private MouseListener getPacienteScrollPaneMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    view.clearSelection(e);
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

    private MouseListener getClientTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(PacienteManageController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void infoTable() throws SQLException {
        view.clearTable();
        if (userLogin.getUsuarioCuidador() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM Cliente where cuidador = ? or neurologo = ? or fisioterapeuta = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioCuidador().getId());
            stmt.setString(2, userLogin.getUsuarioCuidador().getId());
            stmt.setString(3, userLogin.getUsuarioCuidador().getId());
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
                view.addRowTable(row);
            }
        }
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

    private Cliente obtenerCliente(String dni) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        Cliente client = new Cliente();

        String sql = "SELECT * FROM Cliente WHERE dni = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dni);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            client.setDni(result.getString("dni"));
            client.setAddress(result.getString("address"));
            client.setEmail(result.getString("email"));
            client.setCa(result.getString("ca"));
            client.setCenter(result.getString("code"));
            client.setCuidador(obtenerUsuario(result.getString("cuidador")));
            client.setTelefono(result.getString("telefono"));
            client.setName(result.getString("name"));
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

    public boolean ValidarCampos() {
        if (view.getTextDniTextField().isBlank() || view.getTextTelephoneTextField().isBlank() || view.getTextAddressTextField().isBlank() || view.getTextEmailTextField().isBlank() || view.getTextNameTextField().isBlank() || view.getCAComboBoxIndex() == 0) {
            return false;
        }
        return true;
    }

    public void initComponents() {
        edit = false;
        view.editableAddressTextField(false);
        view.editableCACombobox(false);
        view.editableEmailTextField(false);
        view.editableNameTextField(false);
        view.editableTelephoneTextField(false);
        view.editableDniTextField(false);
        view.enabledBackButton(true);
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

    }

}
