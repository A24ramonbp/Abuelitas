/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.personal;

import controller.AssignTaskManagerController;
import controller.ClientManagerController;
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.TareasPersonales;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import view.personal.PersonalMenuFrame;
import view.personal.PersonalTaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class PersonalTaskManagerController {

    private final PersonalTaskManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public PersonalTaskManagerController(PersonalTaskManagerDialog view, UserLogin userLogin) throws SQLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addTareaTableMouseListener(this.getClientTableMouseListener());
        this.view.addTareaScrollPaneMouseListener(this.getClientScrollPaneMouseListener());
        this.view.addAddButtonActionListener(this.getAddButtonActionListener());
        this.view.addConfirmButtonActionListener(this.getConfirmButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addDeleteButtonActionListener(this.getDeleteButtonActionListener());
        this.view.addEditButtonActionListener(this.getEditButtonActionListener());
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        this.view.addImprimirButtonActionListener(this.getPrintButtonActionListener());
        initComponents();
        infoTable();
    }

    
    //Acción del botón para imprimir
    private ActionListener getPrintButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    print();
                } catch (IOException ex) {
                    Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return al;
    }

    //Método para realizar el reporte.
    public void print() throws IOException, SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String currentDirectory = System.getProperty("user.dir");
        ResultSet result = null;

        if (userLogin.getUsuarioPersonal() != null) {
            String sql = "SELECT * FROM TareasPersonal Where dni = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioPersonal().getDni());
            result = stmt.executeQuery();

        }

        JRResultSetDataSource jrDataSource = new JRResultSetDataSource(result);

        try {
            conn.setAutoCommit(false);
            Files.createDirectories(Paths.get(currentDirectory + "/Reports"));
            JasperReport report = JasperCompileManager.compileReport(ClientManagerController.class.getClassLoader().getResourceAsStream("reports/TareasPersonal.jrxml")
            );
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), jrDataSource);
            JasperExportManager.exportReportToPdfFile(print, currentDirectory + "/Reports/TareasPersonales.pdf");

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

    //Acción del botón filtro
    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextTareaTextField().isBlank()) {
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

   //Método que cambia la informaciónd e la tabla segun el filtro que lleve.
    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();

        if (userLogin.getUsuarioPersonal() != null) {

            String sql = "SELECT * FROM TareasPersonal Where dni = ? AND (id LIKE ? OR description LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioPersonal().getDni());
            stmt.setString(2, view.getTextTareaTextField() + "%");
            stmt.setString(3, view.getTextTareaTextField() + "%");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                view.addRowTable(row);
            }
        }

    }

    //Permite detectar cuando clicas fuera de la tabla y deseleccionar
    private MouseListener getClientScrollPaneMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    view.clearSelection(e);
                    view.enabledDeleteButton(false);
                    view.enabledEditButton(false);
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

    //Acción del botón cancelar
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

    //Método para cuando seleccionas una fila de la tabla.
    private MouseListener getClientTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.enabledDeleteButton(true);
                view.enabledEditButton(true);
                try {
                    infoUser();
                } catch (SQLException ex) {
                    Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
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

    //Actualiza la información de la tabla
    private void infoTable() throws SQLException {
        view.clearTable();
        if (userLogin.getUsuarioPersonal() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();
            String sql = "SELECT * FROM TareasPersonal Where dni = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioPersonal().getDni());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                view.addRowTable(row);
            }
        }
    }

    //Acción del botón volver
    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                view.dispose();

            }
        };
        return al;
    }

    //Acción del botón eliminar
    private ActionListener getDeleteButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTareasTable().getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione solo un registro.");
                } else {
                    view.setNoteLabel("");
                    String selected = view.getCellInfo();
                    int value = JOptionPane.showConfirmDialog(view, "Estas seguro que quieres eliminar la tarea?", " Confirm delete.", JOptionPane.YES_NO_OPTION);
                    if (value == 0) {
                        try {
                            TareasPersonales tarea = obtenerTarea(selected);

                            if (eliminarTareas(tarea)) {
                                view.setNoteLabel("Tarea Eliminado.");
                                view.clearForm();
                                infoTable();
                                view.setNoteColor(new Color(44, 99, 56));
                                view.enabledDeleteButton(false);
                                view.enabledEditButton(false);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        return al;
    }

    //Acción del botón añadir
    private ActionListener getAddButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit = false;
                view.editableIdTextField(true);
                view.editableDescripcionTextArea(true);
                view.enabledDeleteButton(false);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.enabledAddButton(false);
                view.enabledImprimirButton(false);
                view.enabledTareaTextField(false);
            }
        };
        return al;
    }

    //Acción del botón confirmar
    private ActionListener getConfirmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!edit) {
                    try {
                        if (addTarea()) {
                            view.setNoteLabel("Tarea añadida.");
                            view.setNoteColor(new Color(44, 99, 56));
                            view.clearForm();
                            initComponents();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        if (editTarea()) {

                            view.setNoteLabel("Tarea editada.");
                            view.setNoteColor(Color.blue);
                            view.clearForm();
                            initComponents();
                            infoTable();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };
        return al;
    }

    //Acción del botón editar
    private ActionListener getEditButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTareasTable().getSelectedRowCount() > 1) {
                    view.setNoteLabel("Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    view.setNoteLabel("");
                    edit = true;
                    view.enabledImprimirButton(false);
                    view.editableIdTextField(true);
                    view.editableDescripcionTextArea(true);
                    view.enabledEditButton(false);
                    view.enabledDeleteButton(false);
                    view.enabledConmfirmButton(true);
                    view.enabledCancelButton(true);
                    view.enabledBackButton(false);
                    view.enabledAddButton(false);
                    view.enabledTareaTextField(false);
                    view.setNoteLabel("");
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonalTaskManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    //Método para obtener una tarea de la base de datos
    private TareasPersonales obtenerTarea(String id) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        TareasPersonales tarea = new TareasPersonales();

        String sql = "SELECT * FROM TareasPersonal";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            tarea.setId(result.getString("id"));
            tarea.setDescription(result.getString("description"));

        } else {
            return null;
        }
        return tarea;
    }

    //Método para añadir tareas.
    private boolean addTarea() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String id = view.getTextIdTextField();
        String description = view.getDescriptionTextArea();
        TareasPersonales tarea = new TareasPersonales(id, description);

        if (comprobarTarea(tarea)) {
            JOptionPane.showMessageDialog(view, "Ese id ya existe en ese centro.");
            return false;
        } else if (!ValidarCampos() || !validarId(id)) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del id son de 1 a 6 dígitos");
            return false;
        } else {

            String sql = "Insert into TareasPersonal ( id, description, dni) VALUES (?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, id);
            statament.setString(2, description);
            statament.setString(3, userLogin.getUsuarioPersonal().getDni());

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al crear la tarea.");
                return false;
            }

        }

    }

    //Válida que todo este rellenado
    public boolean ValidarCampos() {
        if (view.getTextIdTextField().isBlank() || view.getDescriptionTextArea().isBlank()) {

            return false;
        }
        return true;
    }

    //Comprueba el formato del dni
    public static boolean validarId(String id) {
        return id != null && id.matches("\\d{1,6}");
    }

    //Elimina tareas de la base de datos
    public boolean eliminarTareas(TareasPersonales tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM TareasPersonal Where id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarea.getId());

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar la tarea.");
            return false;
        }

    }

    //Inicia los componentes.
    public void initComponents() {
        edit = false;

        view.enabledImprimirButton(true);
        view.editableIdTextField(false);
        view.editableDescripcionTextArea(false);
        view.enabledEditButton(false);
        view.enabledDeleteButton(false);
        view.enabledAddButton(true);
        view.enabledBackButton(true);
        view.enabledConmfirmButton(false);
        view.enabledCancelButton(false);
        view.enabledFiltrarButton(true);
        view.enabledTareaTextField(true);
    }

    //Comprueba que la Tarea este en la base de datos
    public static boolean comprobarTarea(TareasPersonales tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlCuidador = "SELECT * FROM TareasPersonal where id = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, tarea.getId());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        return resultCuidador.next();

    }

    //Edita tareas y las modifica en la base de datos 
    private boolean editTarea() throws SQLException {

        String oldId = view.getCellInfo();
        String newId = view.getTextIdTextField();
        String description = view.getDescriptionTextArea();

        if (!oldId.equals(newId)) {
            if (comprobarTarea(new TareasPersonales(newId, ""))) {
                view.setNoteLabel("El id ya existe.");
                view.setNoteColor(Color.red);
                return false;
            }
        }

        if (!ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo esté correcto:\n- Todos los campos deben estar rellenados.");
            return false;
        }

        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "UPDATE TareasPersonal SET id = ?, description = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, newId);
        stmt.setString(2, description);
        stmt.setString(3, oldId);

        int filas = stmt.executeUpdate();

        if (filas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo actualizar la tarea.");
            return false;
        }
    }

    //Rellena los campos con los datos de la seleccion de la tabla.
    private void infoUser() throws SQLException {
        String selected = view.getCellInfo();
        TareasPersonales tarea = obtenerTarea(selected);
        view.setDescriptionTextArea(tarea.getDescription());
        view.setIdTextField(tarea.getId());
    }

}
