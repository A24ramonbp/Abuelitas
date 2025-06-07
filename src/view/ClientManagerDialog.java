/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rpbp
 */
public class ClientManagerDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public ClientManagerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("Clientes");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.addButton.setBackground(new Color(255, 255, 255));
        this.deleteButton.setBackground(new Color(255, 255, 255));
        this.editButton.setBackground(new Color(255, 255, 255));
        this.cancelButton.setBackground(new Color(255, 255, 255));
        this.confirmButton.setBackground(new Color(255, 255, 255));
        this.backButton.setBackground(new Color(255, 255, 255));

    }

    //Métodos para añadir acciones a los botones.
    public void addAddButtonActionListener(ActionListener al) {
        this.addButton.addActionListener(al);
    }

    public void addImprimirButtonActionListener(ActionListener al) {
        this.imprimirButton.addActionListener(al);
    }

    public void filtrarButtonActionListener(ActionListener al) {
        this.filtrarButton.addActionListener(al);
    }

    public void enabledImprimirButton(boolean state) {
        this.imprimirButton.setEnabled(state);
    }

    public void addEditButtonActionListener(ActionListener al) {
        this.editButton.addActionListener(al);
    }

    public void addDeleteButtonActionListener(ActionListener al) {
        this.deleteButton.addActionListener(al);
    }

    public void addConfirmButtonActionListener(ActionListener al) {
        this.confirmButton.addActionListener(al);
    }

    public void addUpdateButtonActionListener(ActionListener al) {
        this.editButton.addActionListener(al);
    }

    public void addCancelButtonActionListener(ActionListener al) {
        this.cancelButton.addActionListener(al);
    }

    public void addBackButtonActionListener(ActionListener al) {
        this.backButton.addActionListener(al);
    }

    //Métodos para gestionar la información de la tabla.
    public JTable getClientTable() {
        return clientTable;
    }

    public void addRowTable(Vector row) {
        DefaultTableModel model = (DefaultTableModel) this.clientTable.getModel();
        model.addRow(row);
    }

    public String getCellInfo() {
        String info = "";
        int row = this.clientTable.getSelectedRow();
        int col = 0;
        if (row >= 0 && col >= 0) {
            info = this.clientTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
        model.setRowCount(0);
        clientTable.clearSelection();
        clientTable.revalidate();
        clientTable.repaint();
    }

    public void addClientTableMouseListener(MouseListener listener) {
        this.clientTable.addMouseListener(listener);
    }

    public void addClientScrollPaneMouseListener(MouseListener listener) {
        this.clientScrollPane.addMouseListener(listener);
    }

    public void clearSelection(MouseEvent e) {
        int selectedRow = clientTable.rowAtPoint(e.getPoint());
        if (selectedRow == -1) {
            System.out.println("Selección eliminada (pulso fuera de la tabla)");
            clientTable.clearSelection();
        } else {
            System.out.println("Estoy seleccionando la fila: " + selectedRow);
        }
    }

    //Metodos para obtener valores , setearlos y bloquear componentes.
    public void editableDniTextField(boolean state) {
        this.dniTextField.setEditable(state);
    }

    public void editableEmailTextField(boolean state) {
        this.emailTextField.setEditable(state);
    }

    public void enabledFiltrarButton(boolean state) {
        this.filtrarButton.setEnabled(state);
    }

    public String getTextClienteTextField() {
        return this.clienteTextField.getText();
    }

    public void enableClienteTextField(boolean state) {
        this.clienteTextField.setEnabled(state);
    }

    public void setTextClienteTextField(String name) {
        this.clienteTextField.setText(name);
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

    public void setTextDniTextField(String dni) {
        this.dniTextField.setText(dni);
    }

    public String getTextDniTextField() {
        return this.dniTextField.getText();
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

    public void enabledAddButton(boolean state) {
        this.addButton.setEnabled(state);
    }

    public void enabledDeleteButton(boolean state) {
        this.deleteButton.setEnabled(state);
    }

    public void enabledEditButton(boolean state) {
        this.editButton.setEnabled(state);
    }

    public void enabledConmfirmButton(boolean state) {
        this.confirmButton.setEnabled(state);
    }

    public void enabledCancelButton(boolean state) {
        this.cancelButton.setEnabled(state);
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

    public void editableCuidadorCombobox(boolean state) {
        this.cuidadorComboBox.setEnabled(state);
    }

    public void setCuidadorComboBox(String ca) {
        this.cuidadorComboBox.setSelectedItem(ca);
    }

    public void editableNeurologoCombobox(boolean state) {
        this.neurologoComboBox.setEnabled(state);
    }

    public void setNeurologoComboBox(String ca) {
        this.neurologoComboBox.setSelectedItem(ca);
    }

    public void editableFisioterapuetaCombobox(boolean state) {
        this.fisioterapeutaComboBox.setEnabled(state);
    }

    public void setFisioterapuetaComboBox(String ca) {
        this.fisioterapeutaComboBox.setSelectedItem(ca);
    }

    public void setNoteLabel(String note) {
        this.noteLabel.setText(note);
    }

    public String getTextNoteLabel() {
        return this.noteLabel.getText();
    }

    public void setCuidadorComboBoxIndex(int ca) {
        this.cuidadorComboBox.setSelectedIndex(ca);
    }

    public String getCuidadorComboBox() {
        return String.valueOf(this.cuidadorComboBox.getSelectedItem());
    }

    public int getCuidadorComboBoxIndex() {
        return this.cuidadorComboBox.getSelectedIndex();
    }

    public void addItemCuidadorComboBox(String item) {
        this.cuidadorComboBox.addItem(item);
    }

    public void setNeurologoComboBoxIndex(int ca) {
        this.neurologoComboBox.setSelectedIndex(ca);
    }

    public String getNeurologoComboBox() {
        return String.valueOf(this.neurologoComboBox.getSelectedItem());
    }

    public int getNeurologoComboBoxIndex() {
        return this.neurologoComboBox.getSelectedIndex();
    }

    public void addItemNeurologoComboBox(String item) {
        this.neurologoComboBox.addItem(item);
    }

    public void setNoteColor(Color color) {
        this.noteLabel.setForeground(color);
    }

    public void setFisioterapeutaComboBoxIndex(int ca) {
        this.fisioterapeutaComboBox.setSelectedIndex(ca);
    }

    public String getFisioterapeutaComboBox() {
        return String.valueOf(this.fisioterapeutaComboBox.getSelectedItem());
    }

    public int getFisioterapeutaComboBoxIndex() {
        return this.fisioterapeutaComboBox.getSelectedIndex();
    }

    public void addItemFisioterapeutaComboBox(String item) {
        this.fisioterapeutaComboBox.addItem(item);
    }

    //Método para limpiar el formulario
    public void clearForm() {
        this.dniTextField.setText("");
        this.nameTextField.setText("");
        this.cuidadorComboBox.setSelectedIndex(0);
        this.caComboBox.setSelectedIndex(0);
        this.neurologoComboBox.setSelectedIndex(0);
        this.fisioterapeutaComboBox.setSelectedIndex(0);
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
        rolLabel = new javax.swing.JLabel();
        caLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        caComboBox = new javax.swing.JComboBox<>();
        dniLabel = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        clientScrollPane = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        dniTextField = new javax.swing.JTextField();
        cuidadorComboBox = new javax.swing.JComboBox<>();
        noteLabel = new javax.swing.JLabel();
        rolLabel1 = new javax.swing.JLabel();
        neurologoComboBox = new javax.swing.JComboBox<>();
        fisioterapuetaLabel = new javax.swing.JLabel();
        fisioterapeutaComboBox = new javax.swing.JComboBox<>();
        nameLabel1 = new javax.swing.JLabel();
        clienteTextField = new javax.swing.JTextField();
        filtrarButton = new javax.swing.JButton();
        imprimirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        telefonoLabel.setText("Telefono:");

        rolLabel.setText("Cuidador:");

        caLabel.setText("C.A:");

        confirmButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        confirmButton.setText("Confirm");

        cancelButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        cancelButton.setText("Cancel");

        nameTextField.setText("");

        addressTextField.setText("");

        caComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -", "Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y León", "Cataluña", "Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunitat Valenciana", "Extremadura", "Galicia", "Illes Balears", "La Rioja", "País Vasco o Euskadi", "Principado de Asturias", "Región de Murcia" }));

        dniLabel.setText("Dni:");

        telefonoTextField.setText("");

        emailTextField.setText("");

        emailLabel.setText("Email:");

        nameLabel.setText("Nombre:");

        emailLabel1.setText("Dirección:");

        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Name", "Email", "CA","Direccion", "Telefono", "Cuidador","Neurologo","Fisioterapeuta"
            }
        ));
        clientScrollPane.setViewportView(clientTable);

        deleteButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        deleteButton.setText("Delete");

        backButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        backButton.setText("Back");

        editButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        editButton.setText("Edit");

        addButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        addButton.setText("Add");

        dniTextField.setText("");

        cuidadorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -" }));

        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText(" ");

        rolLabel1.setText("Neurologo:");

        neurologoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -" }));

        fisioterapuetaLabel.setText("Fisioterapeuta:");

        fisioterapeutaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -" }));

        nameLabel1.setText("Cliente:");

        clienteTextField.setText("");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        imprimirButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        imprimirButton.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(noteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(43, 43, 43)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emailLabel)
                                    .addComponent(nameLabel)
                                    .addComponent(dniLabel)
                                    .addComponent(emailLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(caLabel))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(telefonoLabel))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rolLabel))
                                            .addComponent(rolLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cuidadorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(caComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(neurologoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fisioterapuetaLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fisioterapeutaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(nameLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtrarButton)))
                        .addGap(77, 77, 77)))
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(clientScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel1)
                    .addComponent(filtrarButton))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(editButton)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniLabel)
                    .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cuidadorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rolLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel1)
                    .addComponent(neurologoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rolLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fisioterapeutaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fisioterapuetaLabel))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noteLabel)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(clientScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(355, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> caComboBox;
    private javax.swing.JLabel caLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane clientScrollPane;
    private javax.swing.JTable clientTable;
    private javax.swing.JTextField clienteTextField;
    private javax.swing.JButton confirmButton;
    private javax.swing.JComboBox<String> cuidadorComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniTextField;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JComboBox<String> fisioterapeutaComboBox;
    private javax.swing.JLabel fisioterapuetaLabel;
    private javax.swing.JButton imprimirButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox<String> neurologoComboBox;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel rolLabel;
    private javax.swing.JLabel rolLabel1;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
