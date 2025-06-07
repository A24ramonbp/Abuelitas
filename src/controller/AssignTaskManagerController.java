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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.Cliente;
import model.TareaAsignada;
import model.Tareas;
import model.UsuarioCuidador;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import view.AssignTaskManagerDialog;


/**
 *
 * @author rpbp
 */
public class AssignTaskManagerController {

    private final AssignTaskManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public AssignTaskManagerController(AssignTaskManagerDialog view, UserLogin userLogin) throws SQLException {
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
        if (userLogin.getUsuarioCuidador() != null) {
            this.view.dniSelection(this.getDniComboBoxActionListener());
            this.view.tareaSelection(this.getTareaComboBoxActionListener());
            dataMedicosComboBox();
        } else if (userLogin.getUsuarioProfesional() != null) {
            this.view.dniSelection(this.getDniComboBoxActionListener());
            this.view.tareaSelection(this.getTarea2ComboBoxActionListener());
            this.view.medicoIdSelection(this.getMedicoComboBoxActionListener());
        }
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        this.view.addImprimirButtonActionListener(this.getPrintButtonActionListener());
        dataClienteComboBox();
        dataTareasComboBox();
        initComponents();
        infoTable();
    }

   //Método para que indica lo que hacer al deseleccionar una fila de la tabla
    private MouseListener getClientScrollPaneMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    view.clearSelection(e);
                    view.enabledDeleteButton(false);
                    view.enabledEditButton(false);
                    if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().equals("Cuidador")) {
                        view.clearForm();
                    } else {
                        view.clearForm2();
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

    //Método para indicar la acción del botón cancelar
    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initComponents();
                if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().equals("Cuidador")) {
                    view.clearForm();
                } else {
                    view.clearForm2();
                }
            }
        };
        return al;
    }

    //Método para indicar la acción del botón Imprimir
    private ActionListener getPrintButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (userLogin.getUsuarioProfesional() != null) {
                        printProfesional();
                    } else {
                        printCuidador();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    //Método para imprimir el informe siendo un usarioProfesional
    public void printProfesional() throws IOException, SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String code = userLogin.getUsuarioProfesional().getCenterCode();
        String currentDirectory = System.getProperty("user.dir");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("code", code);

        try {
            conn.setAutoCommit(false);
            Files.createDirectories(Paths.get(currentDirectory + "/Reports"));
            JasperReport report = JasperCompileManager.compileReport(
                    ClientManagerController.class.getClassLoader().getResourceAsStream("reports/TareasAsignadas.jrxml")
            );
            JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
            JasperExportManager.exportReportToPdfFile(print, currentDirectory + "/Reports/TareasAsignadas.pdf");

            JRViewer viewer = new JRViewer(print);
            JDialog dialog = new JDialog((Frame) null, "Vista previa del reporte", true);
            dialog.getContentPane().add(viewer);
            dialog.setSize(800, 600);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Método para imprimir el informe siendo un usarioCuidador
    public void printCuidador() throws IOException, SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String code = userLogin.getUsuarioCuidador().getCenter();
        String idUsuario = userLogin.getUsuarioCuidador().getId();
        String currentDirectory = System.getProperty("user.dir");
        String rol = userLogin.getUsuarioCuidador().getRol();
        ResultSet result= null;

        String columnaRol;
        switch (rol.toLowerCase()) {
            case "cuidador":
                columnaRol = "cuidador";
                break;
            case "fisioterapeuta":
                columnaRol = "fisioterapeuta";
                break;
            case "neurologo":
                columnaRol = "neurologo";
                break;
            default:
                throw new IllegalArgumentException("Rol desconocido: " + rol);
        }

         if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                String sql = "SELECT T.* FROM TareasAsignadas T "
                        + "JOIN Cliente C ON T.dni = C.dni "
                        + "WHERE T.code = ? AND C." + columnaRol + " = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, code);
                stmt.setString(2, idUsuario);

                result = stmt.executeQuery();
               
            } else {
                String sql = "SELECT T.* FROM TareasAsignadas T "
                        + "JOIN Cliente C ON T.dni = C.dni "
                        + "WHERE T.code = ? AND C." + columnaRol + " = ? AND T.especialidad = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, code);
                stmt.setString(2, idUsuario);
                stmt.setString(3, userLogin.getUsuarioCuidador().getRol());

                result = stmt.executeQuery();
                
            }

        JRResultSetDataSource jrDataSource = new JRResultSetDataSource(result);

        try {
            conn.setAutoCommit(false);
            Files.createDirectories(Paths.get(currentDirectory + "/Reports"));
            JasperReport report = JasperCompileManager.compileReport(
                    ClientManagerController.class.getClassLoader().getResourceAsStream("reports/TareasAsignadas2.jrxml")
            );
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), jrDataSource);
            JasperExportManager.exportReportToPdfFile(print, currentDirectory + "/Reports/TareasAsignadas Cuidador.pdf");

            JRViewer viewer = new JRViewer(print);
            JDialog dialog = new JDialog((Frame) null, "Vista previa del reporte", true);
            dialog.getContentPane().add(viewer);
            dialog.setSize(800, 600);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Método que indica la acción al seleccionar una fila de la tabla.
    private MouseListener getClientTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    try {
                        if (userLogin.getUsuarioProfesional() != null) {
                            infoUser();
                            view.enabledDeleteButton(true);
                            view.enabledEditButton(true);
                        } else if (!userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                            infoUser();
                            view.enabledDeleteButton(true);
                            view.enabledEditButton(true);
                        } else {
                            infoUserCuidador();
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
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

    //Método para actualzar la información de la tabla
    private void infoTable() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();
        if (userLogin.getUsuarioCuidador() != null) {

            String idUsuario = userLogin.getUsuarioCuidador().getId();
            String codeCentro = userLogin.getUsuarioCuidador().getCenter();
            String rol = userLogin.getUsuarioCuidador().getRol();

            String columnaRol;
            switch (rol.toLowerCase()) {
                case "cuidador":
                    view.clearComboBox();
                    view.addItemTareaComboBox("-Unasigned-");
                    view.addItemCuidadorComboBox("-Unasigned-");
                    columnaRol = "cuidador";
                    break;
                case "fisioterapeuta":
                    columnaRol = "fisioterapeuta";
                    break;
                case "neurologo":
                    columnaRol = "neurologo";
                    break;
                default:
                    throw new IllegalArgumentException("Rol desconocido: " + rol);
            }

            if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                String sql = "SELECT T.* FROM TareasAsignadas T "
                        + "JOIN Cliente C ON T.dni = C.dni "
                        + "WHERE T.code = ? AND C." + columnaRol + " = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, codeCentro);
                stmt.setString(2, idUsuario);

                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                        view.addItemTareaComboBox(result.getString("tarea_id"));
                        view.addItemCuidadorComboBox(result.getString("dni"));
                    }
                    Vector row = new Vector();
                    row.add(result.getString("dni"));
                    row.add(result.getString("name"));
                    row.add(result.getString("tarea_id"));
                    row.add(result.getString("descripcion"));
                    row.add(result.getString("medico_id"));
                    row.add(result.getString("especialidad"));
                    view.addRowTable(row);
                }
            } else {
                String sql = "SELECT T.* FROM TareasAsignadas T "
                        + "JOIN Cliente C ON T.dni = C.dni "
                        + "WHERE T.code = ? AND C." + columnaRol + " = ? AND T.especialidad = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, codeCentro);
                stmt.setString(2, idUsuario);
                stmt.setString(3, userLogin.getUsuarioCuidador().getRol());

                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                        view.addItemTareaComboBox(result.getString("tarea_id"));
                        view.addItemCuidadorComboBox(result.getString("dni"));
                    }
                    Vector row = new Vector();
                    row.add(result.getString("dni"));
                    row.add(result.getString("name"));
                    row.add(result.getString("tarea_id"));
                    row.add(result.getString("descripcion"));
                    row.add(result.getString("medico_id"));
                    row.add(result.getString("especialidad"));
                    view.addRowTable(row);
                }
            }

        }

        if (userLogin.getUsuarioProfesional() != null) {
            String sql = "SELECT * FROM TareasAsignadas where code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("dni"));
                row.add(result.getString("name"));
                row.add(result.getString("tarea_id"));
                row.add(result.getString("descripcion"));
                row.add(result.getString("medico_id"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

    }
    
    //Método para actualzar la información de la tabla según el filtro
    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();
        if (userLogin.getUsuarioCuidador() != null) {

            String rol = userLogin.getUsuarioCuidador().getRol();
            String idUsuario = userLogin.getUsuarioCuidador().getId();
            String codeCentro = userLogin.getUsuarioCuidador().getCenter();

            String columnaRol;
            switch (rol.toLowerCase()) {
                case "cuidador":
                    view.clearComboBox();
                    view.addItemTareaComboBox("-Unasigned-");
                    view.addItemCuidadorComboBox("-Unasigned-");
                    columnaRol = "cuidador";
                    break;
                case "fisioterapeuta":
                    columnaRol = "fisioterapeuta";
                    break;
                case "neurologo":
                    columnaRol = "neurologo";
                    break;
                default:
                    throw new IllegalArgumentException("Rol desconocido: " + rol);
            }

            String sql = "SELECT T.* FROM TareasAsignadas T "
                    + "JOIN Cliente C ON T.dni = C.dni "
                    + "WHERE T.code = ? AND C." + columnaRol + " = ? AND (C.dni LIKE ? OR C.name LIKE ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codeCentro);
            stmt.setString(2, idUsuario);
            stmt.setString(3, view.getTextClienteTextField().trim() + "%");
            stmt.setString(4, view.getTextClienteTextField().trim() + "%");

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                    view.addItemTareaComboBox(result.getString("tarea_id"));
                    view.addItemCuidadorComboBox(result.getString("dni"));
                }
                Vector row = new Vector();
                row.add(result.getString("dni"));
                row.add(result.getString("name"));
                row.add(result.getString("tarea_id"));
                row.add(result.getString("descripcion"));
                row.add(result.getString("medico_id"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

        if (userLogin.getUsuarioProfesional() != null) {

            String sql = "SELECT * FROM TareasAsignadas where code = ? AND (dni LIKE ? OR name Like ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            stmt.setString(2, view.getTextClienteTextField() + "%");
            stmt.setString(3, view.getTextClienteTextField() + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("dni"));
                row.add(result.getString("name"));
                row.add(result.getString("tarea_id"));
                row.add(result.getString("descripcion"));
                row.add(result.getString("medico_id"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

    }

    //Método para rellenar el comboBox del cliente
    private void dataClienteComboBox() throws SQLException {

        if (userLogin.getUsuarioCuidador() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            if (userLogin.getUsuarioCuidador().getRol().equals("Neurologo")) {
                String sql = "SELECT * FROM Cliente WHERE  neurologo = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getId());
                ResultSet result = stmt.executeQuery();
                view.addItemCuidadorComboBox("-Unasigned-");
                while (result.next()) {
                    view.addItemCuidadorComboBox(result.getString("dni"));
                }

            } else if (userLogin.getUsuarioCuidador().getRol().equals("Fisioterapeuta")) {
                String sql = "SELECT * FROM Cliente WHERE  fisioterapeuta = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getId());
                ResultSet result = stmt.executeQuery();
                view.addItemCuidadorComboBox("-Unasigned-");
                while (result.next()) {
                    view.addItemCuidadorComboBox(result.getString("dni"));
                }

            } else if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                String sql = "SELECT * FROM Cliente WHERE  cuidador = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getId());
                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    view.addItemCuidadorComboBox(result.getString("dni"));
                }
            }

        } else if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();
            view.addItemCuidadorComboBox("-Unasigned-");
            String sql = "SELECT * FROM Cliente where code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                view.addItemCuidadorComboBox(result.getString("dni"));
            }

        }

    }
    
    //Método para rellenar el comboBox de las tareas
    private void dataTareasComboBox() throws SQLException {

        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM Tareas where code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            ResultSet result = stmt.executeQuery();
            view.addItemTareaComboBox("-Unasigned-");
            while (result.next()) {
                view.addItemTareaComboBox(result.getString("id"));
            }

        } else if (userLogin.getUsuarioCuidador() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            if (userLogin.getUsuarioCuidador().getRol().equals("Neurologo")) {
                String sql = "SELECT * FROM Tareas WHERE  especialidad = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getRol());
                ResultSet result = stmt.executeQuery();
                view.addItemTareaComboBox("-Unasigned-");
                while (result.next()) {
                    view.addItemTareaComboBox(result.getString("id"));
                }
            } else if (userLogin.getUsuarioCuidador().getRol().equals("Fisioterapeuta")) {
                String sql = "SELECT * FROM Tareas WHERE  especialidad = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getRol());
                ResultSet result = stmt.executeQuery();
                view.addItemTareaComboBox("-Unasigned-");
                while (result.next()) {
                    view.addItemTareaComboBox(result.getString("id"));
                }
            }
        }

    }
    
    //Método para rellenar el comboBox del medico
    private void dataMedicosComboBox() throws SQLException {
        view.addItemMedicoComboBox("-Unasigned-");
        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM UsuarioCuidador WHERE rol <> 'Cuidador' and code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                view.addItemMedicoComboBox(result.getString("id"));
            }
        } else if (userLogin.getUsuarioCuidador() != null) {
            if (userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                ConnectMdb connMdb = new ConnectMdb();
                Connection conn = connMdb.getConnection();

                String sql = "SELECT * FROM UsuarioCuidador WHERE rol <> 'Cuidador' and code = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userLogin.getUsuarioCuidador().getCenter());
                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    view.addItemMedicoComboBox(result.getString("id"));
                }
            } else {
                view.addItemMedicoComboBox(userLogin.getUsuarioCuidador().getId());
            }

        }
    }

    //Método para indicar la acción del botón volver
    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userLogin.getUsuarioProfesional() != null) {
                    view.dispose();
                    
                } else {
                    view.dispose();
                   
                }
            }
        };
        return al;
    }

    //Método para indicar la acción del botón eliminar
    private ActionListener getDeleteButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getClientTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione solo un registro.");
                } else {
                    String dniSelected = view.getCellInfo();
                    String tareaSelected = view.getTareaCellInfo();
                    String medicoSelected = view.getMedicoCellInfo();
                    int value = JOptionPane.showConfirmDialog(view, "Estas seguro que quieres eliminar la tarea asignada?", " Confirm delete.", JOptionPane.YES_NO_OPTION);
                    if (value == 0) {
                        try {
                            TareaAsignada tarea = obtenerTareaAsignada(dniSelected, tareaSelected, medicoSelected);

                            if (eliminarTarea(tarea)) {
                                view.setNoteLabel("Tarea Eliminada.");
                                if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().equals("Cuidador")) {
                                    view.clearForm();
                                } else {
                                    view.clearForm2();
                                }
                                infoTable();
                                view.setNoteColor(new Color(44, 99, 56));
                                view.enabledDeleteButton(false);
                                view.enabledEditButton(false);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        return al;
    }

    //Método para indicar la acción del botón añadir
    private ActionListener getAddButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit = false;
                if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                    view.clearForm();
                } else {
                    view.clearForm2();
                }
                if (userLogin.getUsuarioProfesional() != null) {
                    view.editableMedicoIDCombobox(true);
                    view.editableEspecialidadCombobox(false);
                }

                view.setNoteLabel("");
                view.enabledImprimirButton(false);
                view.editableDniCombobox(true);
                view.editableTareaCombobox(true);
                view.enabledEditButton(false);
                view.enabledDeleteButton(false);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.enabledAddButton(false);
                view.enabledClienteTextField(false);
                view.enabledFiltrarButton(false);

            }
        };
        return al;
    }

    //Método para indicar la acción del botón filtrar
    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextClienteTextField().isBlank()) {

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
    
    //Método para indicar la acción del comboBox dni
    private ActionListener getDniComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < view.dniComboBoxCount(); i++) {
                    if (i == 0) {
                        view.setTextNameTextField("");
                    } else if (view.getDniComboBoxIndex() == i) {
                        try {
                            view.setTextNameTextField(obtenerCliente(view.getDniComboBox()).getName());
                        } catch (SQLException ex) {
                            Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        };
        return al;
    }

    //Método para indicar la acción del comboBox medico
    private ActionListener getMedicoComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < view.medicoComboBoxCount(); i++) {
                    if (view.getMedicoIdComboBoxIndex() == i) {
                        try {
                            view.setTextMedicoEspecialidadComboBox(obtenerUsuario(view.getTextMedicoIdComboBox()).getRol());
                        } catch (SQLException ex) {
                            Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        };
        return al;
    }

    //Método para indicar la acción del comboBox Tarea
    private ActionListener getTareaComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < view.tareaComboBoxCount(); i++) {
                    if (i == 0) {
                        view.setTextDescripcionTextArea("");
                    } else if (view.getTareaComboBoxIndex() == i) {
                        try {
                            view.setTextDescripcionTextArea(obtenerTarea(view.getTareaComboBox()).getDescription());
                        } catch (SQLException ex) {
                            Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        };
        return al;
    }

    //Método para indicar la acción del comboBox Tarea2
    private ActionListener getTarea2ComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearMedicoComboBox();
                view.addItemMedicoComboBox("-Unasigned-");
                for (int i = 0; i < view.tareaComboBoxCount(); i++) {
                    if (i == 0) {
                        view.setTextDescripcionTextArea("");
                    } else if (view.getTareaComboBoxIndex() == i) {
                        try {
                            if (userLogin.getUsuarioProfesional() != null) {
                                ConnectMdb connMdb = new ConnectMdb();
                                Connection conn = connMdb.getConnection();

                                String sql = "SELECT * FROM UsuarioCuidador WHERE rol <> 'Cuidador' and code = ? AND rol = ?";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
                                stmt.setString(2, obtenerTarea(view.getTareaComboBox()).getEspecialidad());
                                ResultSet result = stmt.executeQuery();

                                while (result.next()) {
                                    view.addItemMedicoComboBox(result.getString("id"));
                                }
                            }
                            view.setTextDescripcionTextArea(obtenerTarea(view.getTareaComboBox()).getDescription());
                        } catch (SQLException ex) {
                            Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        };
        return al;
    }

    //Método para indicar la acción del botón confirmar
    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!edit) {

                    try {
                        if (addTareaAsignada()) {
                            view.setNoteLabel("Tarea añadida.");
                            view.setNoteColor(new Color(44, 99, 56));
                            if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                                view.clearForm();
                            } else {
                                view.clearForm2();
                            }
                            initComponents();
                            infoTable();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        if (editTareaAsignada()) {
                            view.setNoteLabel("Tarea editada.");
                            view.setNoteColor(Color.blue);
                            if (userLogin.getUsuarioProfesional() != null || userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                                view.clearForm();
                            } else {
                                view.clearForm2();
                            };
                            initComponents();
                            infoTable();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };
        return al;
    }

    //Método para indicar la acción del botón editar
    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getClientTable().getSelectedRowCount() > 1) {
                    view.setNoteLabel("Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    edit = true;
                    if (userLogin.getUsuarioProfesional() != null) {
                        view.editableMedicoIDCombobox(true);
                        view.editableEspecialidadCombobox(false);
                    }

                    view.editableTareaCombobox(true);
                    view.editableDniCombobox(true);
                    view.setNoteLabel("");
                    view.enabledImprimirButton(false);
                    view.enabledEditButton(false);
                    view.enabledDeleteButton(false);
                    view.enabledConmfirmButton(true);
                    view.enabledCancelButton(true);
                    view.enabledBackButton(false);
                    view.enabledAddButton(false);
                    view.enabledClienteTextField(false);
                    view.enabledFiltrarButton(false);
                    view.setNoteLabel("");

                    try {
                        if (userLogin.getUsuarioProfesional() != null) {
                            infoUser();
                        } else if (!userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
                            infoUser();
                        } else {
                            infoUserCuidador();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AssignTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    //Método para obtener la tarea asiganda por dni, tarea y el id del medico
    private TareaAsignada obtenerTareaAsignada(String dni, String tarea, String medico) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        TareaAsignada tareaAsignada = null;

        String sql = "SELECT * FROM TareasAsignadas WHERE dni = ? AND tarea_id = ? AND medico_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dni);
        stmt.setString(2, tarea);
        stmt.setString(3, medico);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            tareaAsignada = new TareaAsignada();
            tareaAsignada.setDni(result.getString("dni"));
            tareaAsignada.setName(result.getString("name"));
            tareaAsignada.setTarea_id(result.getString("tarea_id"));
            tareaAsignada.setDescripcion(result.getString("descripcion"));
            tareaAsignada.setMedico_id(result.getString("medico_id"));
            tareaAsignada.setEspecialidad(result.getString("especialidad"));
        }

        return tareaAsignada;
    }

    //Método para añadir la tarea
    private boolean addTareaAsignada() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String dni = view.getDniComboBox();
        String name = view.getTextNameTextField();
        String tarea_id = view.getTareaComboBox();
        String descripcion = view.getTextDescripcionTextArea();
        String medico_id = view.getTextMedicoIdComboBox();
        String especialidad = view.getTextMedicoEspecialidadComboBox();
        Tareas tarea = obtenerTarea(tarea_id);
        Cliente cliente = obtenerCliente(dni);
        String code = "";

        if (userLogin.getUsuarioProfesional() != null) {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        } else {
            code = userLogin.getUsuarioCuidador().getCenter();
        }

        TareaAsignada tareaAsignada = new TareaAsignada(dni, cliente.getName(), tarea_id, tarea.getDescription(), medico_id, especialidad, code);

        if (comprobarTarea(tareaAsignada)) {
            JOptionPane.showMessageDialog(view, "Esa tarea ya fue asignada.");
            return false;
        } else if (!ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t");
            return false;
        } else {

            String sql = "Insert into TareasAsignadas (dni, name, tarea_id, descripcion, medico_id, especialidad,code) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, dni);
            statament.setString(2, cliente.getName());
            statament.setString(3, tarea_id);
            statament.setString(4, tarea.getDescription());
            statament.setString(5, medico_id);
            statament.setString(6, especialidad);
            statament.setString(7, code);

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al asignar la tarea.");
                return false;
            }

        }

    }

    //Método para obtener el cliente por el dni
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

            String cuidadorId = result.getString("cuidador");
            if (cuidadorId != null) {
                client.setCuidador(obtenerUsuario(cuidadorId));
            }

            String neurologoId = result.getString("neurologo");
            if (neurologoId != null) {
                client.setNeurologo(obtenerUsuario(neurologoId));
            }

            String fisioId = result.getString("fisioterapeuta");
            if (fisioId != null) {
                client.setFisioterapeuta(obtenerUsuario(fisioId));
            }
        }

        return client;
    }

    //Método para la tarea por el id
    private Tareas obtenerTarea(String id) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        Tareas tarea = new Tareas();
        String code = "";
        if (userLogin.getUsuarioProfesional() != null) {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        } else {
            code = userLogin.getUsuarioCuidador().getCenter();
        }

        String sql = "SELECT * FROM Tareas WHERE id = ? AND code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, code);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            tarea.setId(result.getString("id"));
            tarea.setDescription(result.getString("description"));
            tarea.setEspecialidad(result.getString("especialidad"));
            tarea.setCode(result.getString("code"));
        } else {
            return null;
        }
        return tarea;
    }

    //Método para validar que los campos estén cubiertos
    public boolean ValidarCampos() {
        if (view.getDniComboBoxIndex() == 0 || view.getTareaComboBoxIndex() == 0 || view.getMedicoIdComboBoxIndex() == 0 || view.getEspecialidadComboBoxIndex() == 0) {

            return false;
        }
        return true;
    }

    //Método para comprobar la tarea exista.
    public static boolean comprobarTarea(TareaAsignada tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "SELECT * FROM TareasAsignadas WHERE dni = ? AND tarea_id = ? AND medico_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarea.getDni());
        stmt.setString(2, tarea.getTarea_id());
        stmt.setString(3, tarea.getMedico_id());

        ResultSet result = stmt.executeQuery();

        return result.next();

    }

    //Método para eliminar tarea
    public boolean eliminarTarea(TareaAsignada tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM TareasAsignadas WHERE dni = ? AND tarea_id = ? AND medico_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarea.getDni());
        stmt.setString(2, tarea.getTarea_id());
        stmt.setString(3, tarea.getMedico_id());

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar la tarea.");
            return false;
        }

    }

    //Método para iniciar los componentes de forma deseada
    public void initComponents() {
        edit = false;

        if (userLogin.getUsuarioProfesional() != null) {
            view.enabledAddButton(true);
            view.setEspecialidadComboBoxIndex(0);
            view.setMedicoIdComboBoxIndex(0);
        } else if (!userLogin.getUsuarioCuidador().getRol().equals("Cuidador")) {
            view.enabledAddButton(true);
            view.setTextMedicoIdComboBox(userLogin.getUsuarioCuidador().getId());
            view.setTextMedicoEspecialidadComboBox(userLogin.getUsuarioCuidador().getRol());
        } else {
            view.enabledAddButton(false);
            view.setTextMedicoIdComboBox("");
            view.setTextMedicoEspecialidadComboBox("");
        }
        view.editableDescripcionTextArea(false);
        view.enabledImprimirButton(true);
        view.editableNameTextField(false);
        view.editableDniCombobox(false);
        view.editableEspecialidadCombobox(false);
        view.editableMedicoIDCombobox(false);
        view.editableTareaCombobox(false);
        view.enabledClienteTextField(true);
        view.editableNameTextField(false);
        view.enabledEditButton(false);
        view.enabledDeleteButton(false);
        view.enabledBackButton(true);
        view.enabledConmfirmButton(false);
        view.enabledCancelButton(false);
        view.enabledFiltrarButton(true);
        view.enabledClienteTextField(true);

    }

    //Método para editar la tarea asignada
    private boolean editTareaAsignada() throws SQLException {
        String selectedDni = view.getCellInfo();
        String selectedTareaId = view.getTareaCellInfo();
        String selectedMedicoId = view.getMedicoCellInfo();

        TareaAsignada antigua = obtenerTareaAsignada(selectedDni, selectedTareaId, selectedMedicoId);

        String nuevoDni = view.getDniComboBox();
        String nuevaTareaId = view.getTareaComboBox();

        Cliente cliente = obtenerCliente(nuevoDni);
        Tareas tarea = obtenerTarea(nuevaTareaId);

        String code = "";

        if (userLogin.getUsuarioProfesional() != null) {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        } else {
            code = userLogin.getUsuarioCuidador().getCenter();
        }

        boolean claveCambiada = !nuevoDni.equals(antigua.getDni()) || !nuevaTareaId.equals(antigua.getTarea_id());

        if (claveCambiada) {
            TareaAsignada duplicada = new TareaAsignada(nuevoDni, cliente.getName(), nuevaTareaId, tarea.getDescription(), view.getTextMedicoIdComboBox(), view.getTextMedicoEspecialidadComboBox(), code);
            if (comprobarTarea(duplicada)) {
                JOptionPane.showMessageDialog(view, "Esa tarea ya fue asignada.");
                return false;
            }
        }

        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "UPDATE TareasAsignadas SET dni = ?, name = ?, tarea_id = ?, descripcion = ?, medico_id = ?, especialidad = ? "
                + "WHERE dni = ? AND tarea_id = ? AND medico_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, nuevoDni);
        stmt.setString(2, cliente.getName());
        stmt.setString(3, nuevaTareaId);
        stmt.setString(4, tarea.getDescription());
        stmt.setString(5, view.getTextMedicoIdComboBox());
        stmt.setString(6, view.getTextMedicoEspecialidadComboBox());

        stmt.setString(7, antigua.getDni());
        stmt.setString(8, antigua.getTarea_id());
        stmt.setString(9, antigua.getMedico_id());

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo actualizar la tarea asignada.");
            return false;
        }
    }

    //Método para obtener usuario.
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

    //Método para obtener información del usuario seleccionado entrando como centro
    private void infoUser() throws SQLException {
        String dni = view.getCellInfo();
        String tarea = view.getTareaCellInfo();
        String medico = view.getMedicoCellInfo();
        TareaAsignada tareaAsignada = obtenerTareaAsignada(dni, tarea, medico);
        view.setTextNameTextField(tareaAsignada.getName());
        view.setTextDescripcionTextArea(tareaAsignada.getDescripcion());
        view.setTareaComboBox(tareaAsignada.getTarea_id());
        view.setDniComboBox(tareaAsignada.getDni());
        view.setTextMedicoIdComboBox(tareaAsignada.getMedico_id());
        view.setTextMedicoEspecialidadComboBox(tareaAsignada.getEspecialidad());
    }

    //Método para obtener información del usuario seleccionado entrando como cuidador
    private void infoUserCuidador() throws SQLException {
        String dni = view.getCellInfo();
        String tarea = view.getTareaCellInfo();
        String medico = view.getMedicoCellInfo();
        TareaAsignada tareaAsignada = obtenerTareaAsignada(dni, tarea, medico);
        view.setTextNameTextField(tareaAsignada.getName());
        view.setTextDescripcionTextArea(tareaAsignada.getDescripcion());
        view.addItemTareaComboBox(tarea);
        view.setTareaComboBox(tarea);
        view.addItemCuidadorComboBox(dni);
        view.setDniComboBox(dni);
        view.setTextMedicoIdComboBox(tareaAsignada.getMedico_id());
        view.setTextMedicoEspecialidadComboBox(tareaAsignada.getEspecialidad());
    }

}
