/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
import model.Tareas;
import view.MenuDialogCuidador;
import view.MenuDialogProfesional;
import view.TareasManagerDialog;

/**
 *
 * @author rpbp
 */
public class TareaManageController {

    private final TareasManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public TareaManageController(TareasManagerDialog view, UserLogin userLogin) throws SQLException {
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
        initComponents();
        infoTable();
    }

    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextTareaTextField().isBlank()) {
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

            String sql = "SELECT * FROM Tareas Where code = ? AND especialidad = ? AND (id LIKE ? OR description LIKE ? OR especialidad LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioCuidador().getCenter());
            stmt.setString(2, userLogin.getUsuarioCuidador().getRol());
            stmt.setString(3, view.getTextTareaTextField() + "%");
            stmt.setString(4, view.getTextTareaTextField() + "%");
            stmt.setString(5, view.getTextTareaTextField() + "%");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

        if (userLogin.getUsuarioProfesional() != null) {

            String sql = "SELECT * FROM Tareas where code = ? AND (id LIKE ? OR description LIKE ? OR especialidad LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            stmt.setString(2, view.getTextTareaTextField() + "%");
            stmt.setString(3, view.getTextTareaTextField() + "%");
            stmt.setString(4, view.getTextTareaTextField() + "%");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

    }

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

    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearForm();
                initComponents();

            }
        };
        return al;
    }

    private MouseListener getClientTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.enabledDeleteButton(true);
                view.enabledEditButton(true);
                try {
                    infoUser();
                } catch (SQLException ex) {
                    Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "SELECT * FROM Tareas where especialidad = ? And code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioCuidador().getRol());
            stmt.setString(2, userLogin.getUsuarioCuidador().getCenter());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }

        if (userLogin.getUsuarioProfesional() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM Tareas where code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userLogin.getUsuarioProfesional().getCenterCode());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("id"));
                row.add(result.getString("description"));
                row.add(result.getString("especialidad"));
                view.addRowTable(row);
            }
        }
    }

    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userLogin.getUsuarioProfesional() != null) {
                    view.dispose();
                    MenuDialogProfesional md = new MenuDialogProfesional(view, true);
                    MenuProfesionalController mc = new MenuProfesionalController(md, userLogin);
                    md.setVisible(true);
                } else {
                    view.dispose();
                    MenuDialogCuidador md = new MenuDialogCuidador(view, true);
                    MenuCuidadorController mc = new MenuCuidadorController(md, userLogin);
                    md.setVisible(true);
                }

            }
        };
        return al;
    }

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
                            Tareas tarea = obtenerTarea(selected);

                            if (eliminarTareas(tarea)) {
                                view.setNoteLabel("Tarea Eliminada.");
                                view.clearForm();
                                initComponents();
                                infoTable();
                                view.setNoteColor(new Color(44, 99, 56));
                                view.enabledDeleteButton(false);
                                view.enabledEditButton(false);

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
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
                view.clearForm();
                view.setNoteLabel("");
                if (userLogin.getUsuarioProfesional() != null) {
                    view.editableEspecialidadCombobox(true);
                } else {
                    view.editableEspecialidadCombobox(false);
                    view.setEspecialidadComboBox("Fisioterapeuta");
                }
                view.editableIdTextField(true);
                view.editableDescripcionTextArea(true);
                view.enabledDeleteButton(false);
                view.enabledConmfirmButton(true);
                view.enabledCancelButton(true);
                view.enabledBackButton(false);
                view.enabledAddButton(false);
                view.enabledFiltrarButton(false);
                view.enabledTareaTextField(false);
            }
        };
        return al;
    }

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
                        Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        infoTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
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
                if (view.getTareasTable().getSelectedRowCount() > 1) {
                    view.setNoteLabel("Por favor, selecione solo un registro.");
                    view.setNoteColor(Color.red);

                } else {
                    view.setNoteLabel("");
                    edit = true;
                    if (userLogin.getUsuarioProfesional() != null) {
                        view.editableEspecialidadCombobox(true);
                    } else {
                        view.editableEspecialidadCombobox(false);
                    }
                    view.editableIdTextField(false);
                    view.editableDescripcionTextArea(true);
                    view.enabledEditButton(false);
                    view.enabledDeleteButton(false);
                    view.enabledConmfirmButton(true);
                    view.enabledCancelButton(true);
                    view.enabledBackButton(false);
                    view.enabledAddButton(false);
                    view.enabledFiltrarButton(false);
                    view.enabledTareaTextField(false);
                    view.setNoteLabel("");
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(TareaManageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return al;
    }

    private Tareas obtenerTarea(String id) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        String code = "";
        if (userLogin.getUsuarioCuidador() != null) {
            code = userLogin.getUsuarioCuidador().getCenter();
        } else {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        }
        Tareas tarea = new Tareas();

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

    private boolean addTarea() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String id = view.getTextIdTextField();
        String description = view.getDescriptionTextArea();
        String especialidad = view.getEspecialidadComboBox();
        String code = "";

        if (userLogin.getUsuarioCuidador() != null) {
            code = userLogin.getUsuarioCuidador().getCenter();
        } else {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        }

        Tareas tarea = new Tareas(id, description, especialidad, code);

        if (comprobarTarea(tarea)) {
            JOptionPane.showMessageDialog(view, "Ese id ya existe en ese centro.");
            return false;
        } else if (!ValidarCampos() || !validarId(id)) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo este correcto: \n\t"
                    + "- Todos los campos rellenados.\n\t"
                    + "- El formato del id son de 1 a 6 dígitos");
            return false;
        } else {

            String sql = "Insert into Tareas (id, description, especialidad, code) VALUES (?,?,?,?)";
            PreparedStatement statament = conn.prepareStatement(sql);

            statament.setString(1, id);
            statament.setString(2, description);
            statament.setString(3, especialidad);
            statament.setString(4, code);

            int filasAfectadas = statament.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Hubo un problema al crear la tarea.");
                return false;
            }

        }

    }

    public boolean ValidarCampos() {
        if (view.getTextIdTextField().isBlank() || view.getDescriptionTextArea().isBlank() || view.getEspecialidadComboBoxIndex() == 0) {

            return false;
        }
        return true;
    }

    public static boolean comprobarTarea(Tareas tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sqlCuidador = "SELECT * FROM Tareas WHERE id = ? And code = ?";

        PreparedStatement stmtCuidador = conn.prepareStatement(sqlCuidador);
        stmtCuidador.setString(1, tarea.getId());
        stmtCuidador.setString(2, tarea.getCode());
        ResultSet resultCuidador = stmtCuidador.executeQuery();

        return resultCuidador.next();

    }

    public boolean eliminarTareas(Tareas tarea) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "DELETE FROM Tareas WHERE id = ? AND code = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarea.getId());
        stmt.setString(2, tarea.getCode());

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Hubo un problema al eliminar la tarea.");
            return false;
        }

    }

    public void initComponents() {
        edit = false;
        view.editableEspecialidadCombobox(false);
        if (userLogin.getUsuarioCuidador() != null) {
            view.setEspecialidadComboBox(userLogin.getUsuarioCuidador().getRol());
        }
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

    private boolean editTarea() throws SQLException {
        String oldId = view.getCellInfo();
        String newId = view.getTextIdTextField();
        String description = view.getDescriptionTextArea();
        String especialidad = view.getEspecialidadComboBox();
        String code = "";
        if (userLogin.getUsuarioCuidador() != null) {
            code = userLogin.getUsuarioCuidador().getCenter();
        } else {
            code = userLogin.getUsuarioProfesional().getCenterCode();
        }

        if (!oldId.equals(newId) && comprobarTarea(new Tareas(newId, "", "", code))) {
            JOptionPane.showMessageDialog(view, "El id ya existe en este centro.");
            view.setNoteColor(Color.red);
            return false;
        }

        if (!ValidarCampos()) {
            JOptionPane.showMessageDialog(view, "Comprueba que todo esté correcto:\n- Todos los campos deben estar rellenados.");
            return false;
        }

        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        String sql = "UPDATE Tareas SET id = ?, description = ?, especialidad = ? WHERE id = ? AND code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, newId);
        stmt.setString(2, description);
        stmt.setString(3, especialidad);
        stmt.setString(4, oldId);
        stmt.setString(5, code);

        int filas = stmt.executeUpdate();

        if (filas > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo actualizar la tarea.");
            return false;
        }
    }

    private void infoUser() throws SQLException {
        String selected = view.getCellInfo();
        Tareas tarea = obtenerTarea(selected);
        view.setDescriptionTextArea(tarea.getDescription());
        view.setEspecialidadComboBox(tarea.getEspecialidad());
        view.setIdTextField(tarea.getId());
    }

    public static boolean validarId(String id) {
        return id != null && id.matches("\\d{1,6}");
    }
}
