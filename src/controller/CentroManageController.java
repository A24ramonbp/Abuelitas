/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
import model.Centro;
import view.CentroManagerDialog;
import view.Personal.MenuDialogPersonal;

/**
 *
 * @author rpbp
 */
public class CentroManageController {

    private final CentroManagerDialog view;
    private UserLogin userLogin;
    private boolean edit;

    public CentroManageController(CentroManagerDialog view, UserLogin userLogin) throws SQLException {
        this.userLogin = userLogin;
        this.view = view;
        this.view.addCentroTableMouseListener(this.getCentroTableMouseListener());
        this.view.addCentroScrollPaneMouseListener(this.getCentroScrollPaneMouseListener());
        this.view.addBackButtonActionListener(this.getBackButtonActionListener());
        this.view.filtrarButtonActionListener(this.getFiltrarButtonActionListener());
        initComponents();
        infoTable();
    }

    private ActionListener getFiltrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextCentroTextField().isBlank()) {

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

    private MouseListener getCentroScrollPaneMouseListener() {
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

    private MouseListener getCentroTableMouseListener() {
        MouseListener al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.getCellInfo().isEmpty() == false) {
                    try {
                        infoUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(CentroManageController.class.getName()).log(Level.SEVERE, null, ex);
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
        if (userLogin.getUsuarioPersonal() != null) {
            ConnectMdb connMdb = new ConnectMdb();
            Connection conn = connMdb.getConnection();

            String sql = "SELECT * FROM Centro";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Vector row = new Vector();
                row.add(result.getString("code"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }
    }

    private void infoTableFiltro() throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();
        view.clearTable();

        if (userLogin.getUsuarioPersonal() != null) {

            String sql = "SELECT * FROM Centro WHERE code Like ? OR name Like ? OR ca Like ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, view.getTextCentroTextField() + "%");
            stmt.setString(2, view.getTextCentroTextField() + "%");
            stmt.setString(3, view.getTextCentroTextField() + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Vector row = new Vector();
                row.add(result.getString("code"));
                row.add(result.getString("name"));
                row.add(result.getString("email"));
                row.add(result.getString("ca"));
                row.add(result.getString("address"));
                row.add(result.getString("telefono"));
                view.addRowTable(row);
            }
        }

    }

    private ActionListener getBackButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                MenuDialogPersonal md = new MenuDialogPersonal(view, true);
                MenuPersonalController mc = new MenuPersonalController(md, userLogin);
                md.setVisible(true);
            }
        };
        return al;
    }

    private Centro obtenerCentro(String selected) throws SQLException {
        ConnectMdb connMdb = new ConnectMdb();
        Connection conn = connMdb.getConnection();

        Centro centro = new Centro();

        String sql = "SELECT * FROM Centro WHERE code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, selected);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            centro.setCode(result.getString("code"));
            centro.setAddress(result.getString("address"));
            centro.setEmail(result.getString("email"));
            centro.setCa(result.getString("ca"));
            centro.setTelefono(result.getString("telefono"));
            centro.setName(result.getString("name"));
        }

        return centro;
    }

    public void initComponents() {
        edit = false;
        view.editableAddressTextField(false);
        view.editableCACombobox(false);
        view.editableEmailTextField(false);
        view.editableNameTextField(false);
        view.editableTelephoneTextField(false);
        view.editableCodeTextField(false);
        view.enabledBackButton(true);
        view.enableCentroTextField(true);
        view.enabledFiltrarButton(true);
    }

    private void infoUser() throws SQLException {
        String selected = view.getCellInfo();
        Centro centro = obtenerCentro(selected);
        view.setTextNameTextField(centro.getName());
        view.setTextEmailTextField(centro.getEmail());
        view.setTextAddressTextField(centro.getAddress());
        view.setTextTelephoneTextField(centro.getTelefono());
        view.setTextCodeTextField(centro.getCode());
        view.setCAComboBox(centro.getCa());

    }

}
