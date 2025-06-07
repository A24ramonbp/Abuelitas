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
public class TareasManagerDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public TareasManagerDialog(java.awt.Dialog parent, boolean modal) {
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
    
     public void filtrarButtonActionListener(ActionListener al) {
        this.filtrarButton.addActionListener(al);
    }

    public void enabledFiltrarButton(boolean state) {
        this.filtrarButton.setEnabled(state);
    }

    public void enabledTareaTextField(boolean state) {
        this.tareaTextField.setEnabled(state);
    }

    public String getTextTareaTextField() {
        return this.tareaTextField.getText();
    }

    public void setTextTareaTextField(String name) {
        this.tareaTextField.setText(name);
    }

    public void addAddButtonActionListener(ActionListener al) {
        this.addButton.addActionListener(al);
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

    public JTable getTareasTable() {
        return tareaTable;
    }

    public void addRowTable(Vector row) {
        DefaultTableModel model = (DefaultTableModel) this.tareaTable.getModel();
        model.addRow(row);
    }

    public String getCellInfo() {
        String info = "";
        int row = this.tareaTable.getSelectedRow();
        int col = 0;
        if (row >= 0 && col >= 0) {
            info = this.tareaTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }
    public String getCellInfo2() {
        String info = "";
        int row = this.tareaTable.getSelectedRow();
        int col = 1;
        if (row >= 0 && col >= 0) {
            info = this.tareaTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }
    public String getCellInfo3() {
        String info = "";
        int row = this.tareaTable.getSelectedRow();
        int col = 2;
        if (row >= 0 && col >= 0) {
            info = this.tareaTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public void addBackButtonActionListener(ActionListener al) {
        this.backButton.addActionListener(al);
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tareaTable.getModel();
        model.setRowCount(0);
        tareaTable.clearSelection();
        tareaTable.revalidate();
        tareaTable.repaint();
    }

    public void addTareaTableMouseListener(MouseListener listener) {
        this.tareaTable.addMouseListener(listener);
    }

    public void addTareaScrollPaneMouseListener(MouseListener listener) {
        this.computerScrollPane.addMouseListener(listener);
    }

    public void clearSelection(MouseEvent e) {
        int selectedRow = tareaTable.rowAtPoint(e.getPoint());
        if (selectedRow == -1) {
            System.out.println("Selección eliminada (pulso fuera de la tabla)");
            tareaTable.clearSelection();
        } else {
            System.out.println("Estoy seleccionando la fila: " + selectedRow);
        }
    }

    public void editableIdTextField(boolean state) {
        this.idTextField.setEditable(state);
    }
    
    public void editableDescripcionTextArea(boolean state) {
        this.descriptionTextArea.setEditable(state);
    }

    public void setIdTextField(String dni) {
        this.idTextField.setText(dni);
    }

    public String getTextIdTextField() {
        return this.idTextField.getText();
    }
    
     public void setDescriptionTextArea(String descripcion) {
        this.descriptionTextArea.setText(descripcion);
    }

    public String getDescriptionTextArea(){
        return this.descriptionTextArea.getText();
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
    
    public void editableEspecialidadCombobox(boolean state) {
        this.especialidadComboBox.setEnabled(state);
    }

    public void setEspecialidadComboBox(String ca) {
        this.especialidadComboBox.setSelectedItem(ca);
    }

    public void setNoteLabel(String note) {
        this.noteLabel.setText(note);
    }

    public String getTextNoteLabel() {
        return this.noteLabel.getText();
    }

    public void setEspecialidadComboBoxIndex(int ca) {
        this.especialidadComboBox.setSelectedIndex(ca);
    }

    public String getEspecialidadComboBox() {
        return String.valueOf(this.especialidadComboBox.getSelectedItem());
    }

    public int getEspecialidadComboBoxIndex() {
        return this.especialidadComboBox.getSelectedIndex();
    }

    public void setNoteColor(Color color) {
        this.noteLabel.setForeground(color);
    }

    public void clearForm() {
        this.idTextField.setText("");
        this.descriptionTextArea.setText(""); 
        this.especialidadComboBox.setSelectedIndex(0);
    }
    
     public void clearForm2() {
        this.idTextField.setText("");
        this.descriptionTextArea.setText(""); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        especialidadLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        computerScrollPane = new javax.swing.JScrollPane();
        tareaTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        idTextField = new javax.swing.JTextField();
        especialidadComboBox = new javax.swing.JComboBox<>();
        noteLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        tareaTextField = new javax.swing.JTextField();
        filtrarButton = new javax.swing.JButton();
        tareaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        especialidadLabel.setText("Especialidad:");

        confirmButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        confirmButton.setText("Confirm");

        cancelButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        cancelButton.setText("Cancel");

        idLabel.setText("Id:");

        descriptionLabel.setText("Description:");

        tareaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description", "Especialidad"
            }
        ));
        computerScrollPane.setViewportView(tareaTable);

        deleteButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        deleteButton.setText("Delete");

        backButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        backButton.setText("Back");

        editButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        editButton.setText("Edit");

        addButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        addButton.setText("Add");

        idTextField.setText("");

        especialidadComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -", "Fisioterapeuta", "Neurologo" }));

        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText(" ");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        tareaTextField.setText("");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        tareaLabel.setText("Tarea:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filtrarButton)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(especialidadLabel)
                        .addGap(18, 18, 18)
                        .addComponent(especialidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(tareaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tareaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(computerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tareaLabel)
                    .addComponent(tareaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrarButton))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(editButton)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especialidadLabel)
                    .addComponent(especialidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(computerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(351, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane computerScrollPane;
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> especialidadComboBox;
    private javax.swing.JLabel especialidadLabel;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel tareaLabel;
    private javax.swing.JTable tareaTable;
    private javax.swing.JTextField tareaTextField;
    // End of variables declaration//GEN-END:variables
}
