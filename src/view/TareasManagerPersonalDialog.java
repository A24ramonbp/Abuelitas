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
public class TareasManagerPersonalDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public TareasManagerPersonalDialog(java.awt.Dialog parent, boolean modal) {
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

    public void addAddButtonActionListener(ActionListener al) {
        this.addButton.addActionListener(al);
    }

    public void addEditButtonActionListener(ActionListener al) {
        this.editButton.addActionListener(al);
    }
    
    

    public void addDeleteButtonActionListener(ActionListener al) {
        this.deleteButton.addActionListener(al);
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

    public void addConfirmButtonActionListener(ActionListener al) {
        this.confirmButton.addActionListener(al);
    }

    public void addUpdateButtonActionListener(ActionListener al) {
        this.editButton.addActionListener(al);
    }

    public void addCancelButtonActionListener(ActionListener al) {
        this.cancelButton.addActionListener(al);
    }
    
    public void addImprimirButtonActionListener(ActionListener al) {
        this.imprimirButton.addActionListener(al);
    }

    public void enabledImprimirButton(boolean state) {
        this.imprimirButton.setEnabled(state);
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

    public String getDescriptionTextArea() {
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

    public void setNoteLabel(String note) {
        this.noteLabel.setText(note);
    }

    public String getTextNoteLabel() {
        return this.noteLabel.getText();
    }

    public void setNoteColor(Color color) {
        this.noteLabel.setForeground(color);
    }

    public void clearForm() {
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
        noteLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        tareaLabel = new javax.swing.JLabel();
        tareaTextField = new javax.swing.JTextField();
        filtrarButton = new javax.swing.JButton();
        imprimirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                "Id", "Description"
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

        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText(" ");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        tareaLabel.setText("Tarea:");

        tareaTextField.setText("");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        imprimirButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        imprimirButton.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tareaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tareaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtrarButton)
                .addGap(167, 167, 167))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(idLabel)
                        .addGap(18, 18, 18)
                        .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(computerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tareaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tareaLabel)
                    .addComponent(filtrarButton))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(editButton)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noteLabel))
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(computerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(368, Short.MAX_VALUE)))
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
    private javax.swing.JButton filtrarButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JButton imprimirButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel tareaLabel;
    private javax.swing.JTable tareaTable;
    private javax.swing.JTextField tareaTextField;
    // End of variables declaration//GEN-END:variables
}
