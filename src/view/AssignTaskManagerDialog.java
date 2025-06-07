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
public class AssignTaskManagerDialog extends javax.swing.JDialog {

    /**
     * Creates new form clientManagerDialog
     */
    public AssignTaskManagerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("Asignar Tareas");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.addButton.setBackground(new Color(255, 255, 255));
        this.deleteButton.setBackground(new Color(255, 255, 255));
        this.editButton.setBackground(new Color(255, 255, 255));
        this.cancelButton.setBackground(new Color(255, 255, 255));
        this.confirmButton.setBackground(new Color(255, 255, 255));
        this.backButton.setBackground(new Color(255, 255, 255));
    }

    //Método para añadir acciones a los botones.
    public void addAddButtonActionListener(ActionListener al) {
        this.addButton.addActionListener(al);
    }

    public void addImprimirButtonActionListener(ActionListener al) {
        this.imprimirButton.addActionListener(al);
    }

    public void enabledImprimirButton(boolean state) {
        this.imprimirButton.setEnabled(state);
    }

    public void filtrarButtonActionListener(ActionListener al) {
        this.filtrarButton.addActionListener(al);
    }

    public void enabledFiltrarButton(boolean state) {
        this.filtrarButton.setEnabled(state);
    }

    public void enabledClienteTextField(boolean state) {
        this.clienteTextField.setEnabled(state);
    }

    public String getTextNameTextField() {
        return this.nameTextField.getText();
    }

    public void setTextNameTextField(String name) {
        this.nameTextField.setText(name);
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

    public void addBackButtonActionListener(ActionListener al) {
        this.backButton.addActionListener(al);
    }

    public void addCancelButtonActionListener(ActionListener al) {
        this.cancelButton.addActionListener(al);
    }

    public void dniSelection(ActionListener al) {
        dniComboBox.addActionListener(al);
    }

    public void medicoIdSelection(ActionListener al) {
        medicoIdComboBox.addActionListener(al);
    }

    public void tareaSelection(ActionListener al) {
        tareaComboBox.addActionListener(al);

    }

    //Métodos para gestionar la información de la tabla
    public JTable getClientTable() {
        return assignTaskTable;
    }

    public void addRowTable(Vector row) {
        DefaultTableModel model = (DefaultTableModel) this.assignTaskTable.getModel();
        model.addRow(row);
    }

    public String getCellInfo() {
        String info = "";
        int row = this.assignTaskTable.getSelectedRow();
        int col = 0;
        if (row >= 0 && col >= 0) {
            info = this.assignTaskTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public String getTareaCellInfo() {
        String info = "";
        int row = this.assignTaskTable.getSelectedRow();
        int col = 2;
        if (row >= 0 && col >= 0) {
            info = this.assignTaskTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public String getMedicoCellInfo() {
        String info = "";
        int row = this.assignTaskTable.getSelectedRow();
        int col = 4;
        if (row >= 0 && col >= 0) {
            info = this.assignTaskTable.getModel().getValueAt(row, col).toString();
        }
        return info;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) assignTaskTable.getModel();
        model.setRowCount(0);
        assignTaskTable.clearSelection();
        assignTaskTable.revalidate();
        assignTaskTable.repaint();
    }

    public void addClientTableMouseListener(MouseListener listener) {
        this.assignTaskTable.addMouseListener(listener);
    }

    public void addClientScrollPaneMouseListener(MouseListener listener) {
        this.assingTaskScrollPane.addMouseListener(listener);
    }

    public void clearSelection(MouseEvent e) {
        int selectedRow = assignTaskTable.rowAtPoint(e.getPoint());
        if (selectedRow == -1) {
            System.out.println("Selección eliminada (pulso fuera de la tabla)");
            assignTaskTable.clearSelection();
        } else {
            System.out.println("Estoy seleccionando la fila: " + selectedRow);
        }
    }

    //Métodos para obtener valores de los componentes, setearlos y bloquearlos.
    public int dniComboBoxCount() {
        return dniComboBox.getItemCount();
    }

    public int tareaComboBoxCount() {
        return tareaComboBox.getItemCount();
    }

    public int medicoComboBoxCount() {
        return medicoIdComboBox.getItemCount();
    }

    public void editableMedicoEspecialidadComboBox(boolean state) {
        this.especialidadComboBox.setEditable(state);
    }

    public String getTextMedicoEspecialidadComboBox() {
        return String.valueOf(this.especialidadComboBox.getSelectedItem());
    }

    public void setTextMedicoEspecialidadComboBox(String email) {
        this.especialidadComboBox.setSelectedItem(email);
    }

    public void editableNameTextField(boolean state) {
        this.nameTextField.setEditable(state);
    }

    public String getTextClienteTextField() {
        return this.clienteTextField.getText();
    }

    public void setTextClienteTextField(String name) {
        this.clienteTextField.setText(name);
    }

    public void editableMedicoIdComboBox(boolean state) {
        this.medicoIdComboBox.setEditable(state);
    }

    public String getTextMedicoIdComboBox() {
        return String.valueOf(this.medicoIdComboBox.getSelectedItem());
    }

    public void setTextMedicoIdComboBox(String id) {
        this.medicoIdComboBox.setSelectedItem(id);
    }

    public void editableDescripcionTextArea(boolean state) {
        this.descripcionTextArea.setEditable(state);
    }

    public String getTextDescripcionTextArea() {
        return this.descripcionTextArea.getText();
    }

    public void setTextDescripcionTextArea(String descripcion) {
        this.descripcionTextArea.setText(descripcion);
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

    public void editableDniCombobox(boolean state) {
        this.dniComboBox.setEnabled(state);
    }

    public void editableEspecialidadCombobox(boolean state) {
        this.especialidadComboBox.setEnabled(state);
    }

    public void editableMedicoIDCombobox(boolean state) {
        this.medicoIdComboBox.setEnabled(state);
    }

    public void setDniComboBox(String ca) {
        this.dniComboBox.setSelectedItem(ca);
    }

    public void editableTareaCombobox(boolean state) {
        this.tareaComboBox.setEnabled(state);
    }

    public void setTareaComboBox(String ca) {
        this.tareaComboBox.setSelectedItem(ca);
    }

    public void setNoteLabel(String note) {
        this.noteLabel.setText(note);
    }

    public String getTextNoteLabel() {
        return this.noteLabel.getText();
    }

    public void setDniComboBoxIndex(int ca) {
        this.dniComboBox.setSelectedIndex(ca);
    }

    public String getDniComboBox() {
        return String.valueOf(this.dniComboBox.getSelectedItem());
    }

    public int getDniComboBoxIndex() {
        return this.dniComboBox.getSelectedIndex();
    }

    public void addItemCuidadorComboBox(String item) {
        this.dniComboBox.addItem(item);
    }

    public void setNeurologoComboBoxIndex(int ca) {
        this.tareaComboBox.setSelectedIndex(ca);
    }

    public void setMedicoIdComboBoxIndex(int ca) {
        this.medicoIdComboBox.setSelectedIndex(ca);
    }

    public void setEspecialidadComboBoxIndex(int ca) {
        this.especialidadComboBox.setSelectedIndex(ca);
    }

    public String getTareaComboBox() {
        return String.valueOf(this.tareaComboBox.getSelectedItem());
    }

    public int getTareaComboBoxIndex() {
        return this.tareaComboBox.getSelectedIndex();
    }

    public int getMedicoIdComboBoxIndex() {
        return this.medicoIdComboBox.getSelectedIndex();
    }

    public int getEspecialidadComboBoxIndex() {
        return this.especialidadComboBox.getSelectedIndex();
    }

    public void clearComboBox() {
        this.tareaComboBox.removeAllItems();
        this.dniComboBox.removeAllItems();
    }

    public void clearMedicoComboBox() {
        this.medicoIdComboBox.removeAllItems();
    }

    public void addItemTareaComboBox(String item) {
        this.tareaComboBox.addItem(item);
    }

    public void addItemMedicoComboBox(String item) {
        this.medicoIdComboBox.addItem(item);
    }

    public void setNoteColor(Color color) {
        this.noteLabel.setForeground(color);
    }

    //Métodos para limpiar el formulario.
    public void clearForm() {
        this.setTextDescripcionTextArea("");
        this.clienteTextField.setText("");
        this.dniComboBox.setSelectedIndex(0);
        this.tareaComboBox.setSelectedIndex(0);
        this.medicoIdComboBox.setSelectedIndex(0);
        this.especialidadComboBox.setSelectedIndex(0);
        this.setTextClienteTextField("");
    }

    public void clearForm2() {
        this.setTextDescripcionTextArea("");
        this.clienteTextField.setText("");
        this.dniComboBox.setSelectedIndex(0);
        this.tareaComboBox.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        medicoIdLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        clienteTextField = new javax.swing.JTextField();
        dniLabel = new javax.swing.JLabel();
        medicoEspecialidadLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        descripcionLabel = new javax.swing.JLabel();
        assingTaskScrollPane = new javax.swing.JScrollPane();
        assignTaskTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        dniComboBox = new javax.swing.JComboBox<>();
        noteLabel = new javax.swing.JLabel();
        tareaLabel = new javax.swing.JLabel();
        tareaComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionTextArea = new javax.swing.JTextArea();
        medicoIdComboBox = new javax.swing.JComboBox<>();
        especialidadComboBox = new javax.swing.JComboBox<>();
        nameTextField = new javax.swing.JTextField();
        nameLabel1 = new javax.swing.JLabel();
        filtrarButton = new javax.swing.JButton();
        imprimirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        medicoIdLabel.setText("Medico Id:");

        confirmButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        confirmButton.setText("Confirm");

        cancelButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        cancelButton.setText("Cancel");

        clienteTextField.setText("");

        dniLabel.setText("Dni:");

        medicoEspecialidadLabel.setText("Especialidad:");

        nameLabel.setText("Cliente:");

        descripcionLabel.setText("Descripcion:");

        assignTaskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Nombre", "Id_Tarea", "Descripcion", "Medico","Especialidad"
            }
        ));
        assingTaskScrollPane.setViewportView(assignTaskTable);

        deleteButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        deleteButton.setText("Delete");

        backButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        backButton.setText("Back");

        editButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        editButton.setText("Edit");

        addButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        addButton.setText("Add");

        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText(" ");

        tareaLabel.setText("Tarea:");

        descripcionTextArea.setColumns(20);
        descripcionTextArea.setRows(5);
        jScrollPane1.setViewportView(descripcionTextArea);

        especialidadComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unsigned -", "Fisioterapeuta", "Neurologo" }));

        nameTextField.setText("");

        nameLabel1.setText("Nombre:");

        filtrarButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        filtrarButton.setText("Filtrar");

        imprimirButton.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        imprimirButton.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtrarButton)
                                .addGap(124, 124, 124))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(dniLabel)
                                                    .addComponent(tareaLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(tareaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(medicoIdLabel))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(52, 52, 52)
                                                        .addComponent(nameLabel1))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(descripcionLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(medicoEspecialidadLabel)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(medicoIdComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(especialidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nameTextField))))
                                .addGap(47, 47, 47)))))
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(assingTaskScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(filtrarButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(backButton)
                                .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(editButton)
                                .addComponent(addButton)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dniLabel)
                            .addComponent(dniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel1))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tareaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tareaLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(medicoIdLabel)
                                .addComponent(medicoIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(especialidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medicoEspecialidadLabel))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(imprimirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(noteLabel)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(descripcionLabel)
                        .addGap(155, 155, 155))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(assingTaskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(361, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable assignTaskTable;
    private javax.swing.JScrollPane assingTaskScrollPane;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField clienteTextField;
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel descripcionLabel;
    private javax.swing.JTextArea descripcionTextArea;
    private javax.swing.JComboBox<String> dniComboBox;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> especialidadComboBox;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JButton imprimirButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel medicoEspecialidadLabel;
    private javax.swing.JComboBox<String> medicoIdComboBox;
    private javax.swing.JLabel medicoIdLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JComboBox<String> tareaComboBox;
    private javax.swing.JLabel tareaLabel;
    // End of variables declaration//GEN-END:variables
}
