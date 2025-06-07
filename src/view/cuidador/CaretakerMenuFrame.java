/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.cuidador;

import view.cuidador.CaretakerDataDialog;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import view.AssignTaskManagerDialog;
import view.SingInFrame;
import view.PatientManagerDialog;
import view.profesional.RegistroFrameProfessional;
import view.TaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class CaretakerMenuFrame extends javax.swing.JFrame {

    /**
     * Creates new form MenuJDialog
     */
    public CaretakerMenuFrame(RegistroFrameProfessional parent, boolean modal) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setTitle("Menú");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.datosButton.setBackground(new Color(255, 255, 255));
        this.assignTaskButton.setBackground(new Color(255, 255, 255));
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.tareasButton.setBackground(new Color(255, 255, 255));
    }

    public CaretakerMenuFrame(SingInFrame parent, boolean modal) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setTitle("Menú");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.datosButton.setBackground(new Color(255, 255, 255));
        this.assignTaskButton.setBackground(new Color(255, 255, 255));
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.tareasButton.setBackground(new Color(255, 255, 255));
    }

    public CaretakerMenuFrame(PatientManagerDialog view, boolean b) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setTitle("Menú");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.datosButton.setBackground(new Color(255, 255, 255));
        this.assignTaskButton.setBackground(new Color(255, 255, 255));
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.tareasButton.setBackground(new Color(255, 255, 255));
    }

    public CaretakerMenuFrame(TaskManagerDialog view, boolean b) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setTitle("Menú");
        getContentPane().setBackground(new Color(255, 228, 235));
        this.datosButton.setBackground(new Color(255, 255, 255));
        this.assignTaskButton.setBackground(new Color(255, 255, 255));
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.tareasButton.setBackground(new Color(255, 255, 255));

    }

    public CaretakerMenuFrame(AssignTaskManagerDialog view, boolean b) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
    }

    public CaretakerMenuFrame(CaretakerDataDialog view, boolean b) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        Image icono = new ImageIcon(getClass().getResource("/assets/icon.png")).getImage();
        setIconImage(icono);
        setResizable(false);
    }
    
    //Añadir acciones a los botones y al menuItem
    public void addCerrarSesionMenuItem(ActionListener al) {
        this.cerrarSesionMenuItem.addActionListener(al);
    }

    public void addQuitMenuItem(ActionListener al) {
        this.quitMenuItem.addActionListener(al);
    }

    public void addTareasButtonActionListener(ActionListener al) {
        this.tareasButton.addActionListener(al);
    }

    public void addDatosButtonActionListener(ActionListener al) {
        this.datosButton.addActionListener(al);
    }

    public void addClientesButtonActionListener(ActionListener al) {
        this.clientesButton.addActionListener(al);
    }

    public void addAssignTaskButtonActionListener(ActionListener al) {
        this.assignTaskButton.addActionListener(al);
    }

    
    //Métodos para  cambiar el texto del label y desabilitar el boton de tareas
    public void enabledTareasButton(boolean state) {
        this.tareasButton.setEnabled(state);
    }

    public void setUserLabel(String user) {
        this.userLabel.setText(user);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        registerLabel1 = new javax.swing.JLabel();
        clientesButton = new javax.swing.JButton();
        assignTaskButton = new javax.swing.JButton();
        tareasButton = new javax.swing.JButton();
        datosButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        cerrarSesionMenuItem = new javax.swing.JMenuItem();
        quitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 51));
        jSeparator1.setForeground(new java.awt.Color(51, 0, 51));

        registerLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        registerLabel1.setText("Menú");

        clientesButton.setText("Clientes");

        assignTaskButton.setText("Asignar/Comprobar Tareas");
        assignTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignTaskButtonActionPerformed(evt);
            }
        });

        tareasButton.setText("Lista de Tareas");

        datosButton.setText("Datos Personales");

        userLabel.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText(" ");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Escalada.png"))); // NOI18N

        fileMenu.setText("File");

        cerrarSesionMenuItem.setText("Cerrar Sesión");
        fileMenu.add(cerrarSesionMenuItem);

        quitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        quitMenuItem.setText("Quit");
        fileMenu.add(quitMenuItem);

        mainMenuBar.add(fileMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clientesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(assignTaskButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tareasButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(registerLabel1)))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tareasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void assignTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignTaskButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assignTaskButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignTaskButton;
    private javax.swing.JMenuItem cerrarSesionMenuItem;
    private javax.swing.JButton clientesButton;
    private javax.swing.JButton datosButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JLabel registerLabel1;
    private javax.swing.JButton tareasButton;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
