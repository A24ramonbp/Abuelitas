/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

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
public class PacienteManagerDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public PacienteManagerDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
    }

  
    public JTable getPacienteTable() {
        return pacienteTable;
    }
    
    public void filtrarButtonActionListener(ActionListener al) {
        this.filtrarButton.addActionListener(al);
    }

    public void enabledFiltrarButton(boolean state) {
        this.filtrarButton.setEnabled(state);
    }

    public String getTextPacienteTextField() {
        return this.pacienteTextField.getText();
    }

    public void enablePacienteTextField(boolean state) {
        this.pacienteTextField.setEnabled(state);
    }

    public void setTextPacienteTextField(String name) {
        this.pacienteTextField.setText(name);
    }

    public void addRowTable(Vector row) {
        DefaultTableModel model = (DefaultTableModel) this.pacienteTable.getModel();
        model.addRow(row);
    }

    public String getCellInfo() {
        String info = "";
        int row = this.pacienteTable.getSelectedRow();
        int col = 0;
        if (row >= 0 && col >= 0) {
            info = this.pacienteTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public void addBackButtonActionListener(ActionListener al) {
        this.backButton.addActionListener(al);
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) pacienteTable.getModel();
        model.setRowCount(0);
        pacienteTable.clearSelection();
        pacienteTable.revalidate();
        pacienteTable.repaint();
    }

    public void addPacienteTableMouseListener(MouseListener listener) {
        this.pacienteTable.addMouseListener(listener);
    }

    public void addPacienteScrollPaneMouseListener(MouseListener listener) {
        this.computerScrollPane.addMouseListener(listener);
    }

    public void clearSelection(MouseEvent e) {
        int selectedRow = pacienteTable.rowAtPoint(e.getPoint());
        if (selectedRow == -1) {
            System.out.println("Selección eliminada (pulso fuera de la tabla)");
            pacienteTable.clearSelection();
        } else {
            System.out.println("Estoy seleccionando la fila: " + selectedRow);
        }
    }

    public void editableDniTextField(boolean state) {
        this.dniTextField.setEditable(state);
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

    public void addImprimirButtonActionListener(ActionListener al) {
        this.imprimirButton.addActionListener(al);
    }

    public void enabledImprimirButton(boolean state) {
        this.imprimirButton.setEnabled(state);
    }

    public void clearForm() {
        this.dniTextField.setText("");
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
        computerScrollPane = new javax.swing.JScrollPane();
        pacienteTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        dniTextField = new javax.swing.JTextField();
        pacienteLabel = new javax.swing.JLabel();
        pacienteTextField = new javax.swing.JTextField();
        filtrarButton = new javax.swing.JButton();
        imprimirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        telefonoLabel.setText("Telefono:");

        caLabel.setText("C.A:");

        nameTextField.setText("");

        addressTextField.setText("");

        caComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -", "Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y León", "Cataluña", "Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunitat Valenciana", "Extremadura", "Galicia", "Illes Balears", "La Rioja", "País Vasco o Euskadi", "Principado de Asturias", "Región de Murcia" }));

        dniLabel.setText("Dni:");

        telefonoTextField.setText("");

        emailTextField.setText("");

        emailLabel.setText("Email:");

        nameLabel.setText("Name:");

        emailLabel1.setText("Address:");

        pacienteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Name", "Email", "CA","Direccion", "Telefono", "Cuidador"
            }
        ));
        computerScrollPane.setViewportView(pacienteTable);

        backButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        backButton.setText("Back");

        dniTextField.setText("");

        pacienteLabel.setText("Paciente:");

        pacienteTextField.setText("");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        imprimirButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        imprimirButton.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
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
                                .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(computerScrollPane)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pacienteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pacienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtrarButton)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(computerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pacienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pacienteLabel)
                    .addComponent(filtrarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(imprimirButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> caComboBox;
    private javax.swing.JLabel caLabel;
    private javax.swing.JScrollPane computerScrollPane;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JButton imprimirButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel pacienteLabel;
    private javax.swing.JTable pacienteTable;
    private javax.swing.JTextField pacienteTextField;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
