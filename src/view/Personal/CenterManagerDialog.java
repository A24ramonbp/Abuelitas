/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.personal;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rpbp
 */
public class CenterManagerDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public CenterManagerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("Centros");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.filtrarButton.setBackground(new Color(255, 255, 255));
        this.backButton.setBackground(new Color(255, 255, 255));

    }

    //Añadir acciones a los botones
    public void filtrarButtonActionListener(ActionListener al) {
        this.filtrarButton.addActionListener(al);
    }

    public void addBackButtonActionListener(ActionListener al) {
        this.backButton.addActionListener(al);
    }

    //Metodos para obtener, modificar y bloquear controles.
    public void enabledFiltrarButton(boolean state) {
        this.filtrarButton.setEnabled(state);
    }

    public String getTextCentroTextField() {
        return this.centroTextField.getText();
    }

    public void setTextCentroTextField(String name) {
        this.centroTextField.setText(name);
    }

    public void enableCentroTextField(boolean state) {
        this.centroTextField.setEnabled(state);
    }

    public void editableCodeTextField(boolean state) {
        this.codeTextField.setEditable(state);
    }

    public void editableEmailTextField(boolean state) {
        this.emailTextField.setEditable(state);
    }

    public String getTextEmailTextField() {
        return this.emailTextField.getText();
    }

    public void setTextEmailTextField(String email) {
        this.emailTextField.setText(email);
    }

    public void editableNameTextField(boolean state) {
        this.nameTextField.setEditable(state);
    }

    public String getTextNameTextField() {
        return this.nameTextField.getText();
    }

    public void setTextNameTextField(String name) {
        this.nameTextField.setText(name);
    }

    public void editableTelephoneTextField(boolean state) {
        this.telefonoTextField.setEditable(state);
    }

    public String getTextTelephoneTextField() {
        return this.telefonoTextField.getText();
    }

    public void setTextTelephoneTextField(String telefono) {
        this.telefonoTextField.setText(telefono);
    }

    public void setTextCodeTextField(String dni) {
        this.codeTextField.setText(dni);
    }

    public String getTextCodeTextField() {
        return this.codeTextField.getText();
    }

    public void editableAddressTextField(boolean state) {
        this.addressTextField.setEditable(state);
    }

    public String getTextAddressTextField() {
        return this.addressTextField.getText();
    }

    public void setTextAddressTextField(String address) {
        this.addressTextField.setText(address);
    }

    public void editableCACombobox(boolean state) {
        this.caComboBox.setEnabled(state);
    }

    public void enabledBackButton(boolean state) {
        this.backButton.setEnabled(state);
    }

    public void setCAComboBox(String ca) {
        this.caComboBox.setSelectedItem(ca);
    }

    public void setCAComboBoxIndex(int ca) {
        this.caComboBox.setSelectedIndex(ca);
    }

    public String getCAComboBox() {
        return String.valueOf(this.caComboBox.getSelectedItem());
    }

    public int getCAComboBoxIndex() {
        return this.caComboBox.getSelectedIndex();
    }

    public void setNoteLabel(String note) {
        this.noteLabel.setText(note);
    }

    public String getTextNoteLabel() {
        return this.noteLabel.getText();
    }

    public void setNoteColor(Color color) {
        this.noteLabel.setForeground(color);
    }

    //Métodos para modificar la tabla.
    public JTable getCentrosTable() {
        return centrosTable;
    }

    public void addRowTable(Vector row) {
        DefaultTableModel model = (DefaultTableModel) this.centrosTable.getModel();
        model.addRow(row);
    }

    public String getCellInfo() {
        String info = "";
        int row = this.centrosTable.getSelectedRow();
        int col = 0;
        if (row >= 0 && col >= 0) {
            info = this.centrosTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) centrosTable.getModel();
        model.setRowCount(0);
        centrosTable.clearSelection();
        centrosTable.revalidate();
        centrosTable.repaint();
    }

    public void addCentroTableMouseListener(MouseListener listener) {
        this.centrosTable.addMouseListener(listener);
    }

    public void addCentroScrollPaneMouseListener(MouseListener listener) {
        this.centerScrollPane.addMouseListener(listener);
    }

    public void clearSelection(MouseEvent e) {
        int selectedRow = centrosTable.rowAtPoint(e.getPoint());
        if (selectedRow == -1) {
            System.out.println("Selección eliminada (pulso fuera de la tabla)");
            centrosTable.clearSelection();
        } else {
            System.out.println("Estoy seleccionando la fila: " + selectedRow);
        }
    }

    //Limpiar el formulario
    public void clearForm() {
        this.codeTextField.setText("");
        this.nameTextField.setText("");
        this.caComboBox.setSelectedIndex(0);
        this.addressTextField.setText("");
        this.telefonoTextField.setText("");
        this.emailTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        telefonoLabel = new javax.swing.JLabel();
        caLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        caComboBox = new javax.swing.JComboBox<>();
        dniLabel = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        centerScrollPane = new javax.swing.JScrollPane();
        centrosTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        codeTextField = new javax.swing.JTextField();
        noteLabel = new javax.swing.JLabel();
        centroLabel = new javax.swing.JLabel();
        centroTextField = new javax.swing.JTextField();
        filtrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        telefonoLabel.setText("Telefono:");

        caLabel.setText("C.A:");

        nameTextField.setText("");

        addressTextField.setText("");

        caComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -", "Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y León", "Cataluña", "Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunitat Valenciana", "Extremadura", "Galicia", "Illes Balears", "La Rioja", "País Vasco o Euskadi", "Principado de Asturias", "Región de Murcia" }));

        dniLabel.setText("Código:");

        telefonoTextField.setText("");

        emailTextField.setText("");

        emailLabel.setText("Email:");

        nameLabel.setText("Nombre:");

        emailLabel1.setText("Dirección:");

        centrosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Name", "Email", "CA","Direccion", "Telefono"
            }
        ));
        centerScrollPane.setViewportView(centrosTable);

        backButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        backButton.setText("Back");

        codeTextField.setText("");

        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText(" ");

        centroLabel.setText("Centro:");

        centroTextField.setText("");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(noteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(centerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(centroLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtrarButton)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailLabel)
                    .addComponent(nameLabel)
                    .addComponent(dniLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(caLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(telefonoLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(caComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(centerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(centroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centroLabel)
                    .addComponent(filtrarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniLabel)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caLabel)
                    .addComponent(caComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(noteLabel)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> caComboBox;
    private javax.swing.JLabel caLabel;
    private javax.swing.JScrollPane centerScrollPane;
    private javax.swing.JLabel centroLabel;
    private javax.swing.JTextField centroTextField;
    private javax.swing.JTable centrosTable;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
