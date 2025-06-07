/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.profesional;

import view.profesional.ProfessionalDataDialog;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import model.UsuarioProfesional;
import view.AssignTaskManagerDialog;
import view.ClientManagerDialog;
import view.SingInFrame;
import view.TaskManagerDialog;

/**
 *
 * @author rpbp
 */
public class ProfessionalMenuFrame extends javax.swing.JFrame{

    /**
     * Creates new form MenuJDialog
     */
    public ProfessionalMenuFrame(SingInFrame parent, boolean modal) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
    }

    public ProfessionalMenuFrame(UserManagerDialog view, boolean b) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
    }

    public ProfessionalMenuFrame(ClientManagerDialog view, boolean b) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
    }

    public ProfessionalMenuFrame(TaskManagerDialog view, boolean b) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
    }

    public ProfessionalMenuFrame(ProfessionalDataDialog view, boolean b) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
    }

    public ProfessionalMenuFrame(AssignTaskManagerDialog view, boolean b) {
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
        this.clientesButton.setBackground(new Color(255, 255, 255));
        this.datosCuentaButton.setBackground(new Color(255, 255, 255));
        this.listaTareasButton.setBackground(new Color(255, 255, 255));
        this.tareasAsignadasButton.setBackground(new Color(255, 255, 255));
        this.userButton.setBackground(new Color(255, 255, 255));
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
        userButton = new javax.swing.JButton();
        datosCuentaButton = new javax.swing.JButton();
        clientesButton = new javax.swing.JButton();
        listaTareasButton = new javax.swing.JButton();
        tareasAsignadasButton = new javax.swing.JButton();
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

        userButton.setText("Usuarios");

        datosCuentaButton.setText("Datos de la cuenta");

        clientesButton.setText("Clientes");

        listaTareasButton.setText("Lista de tareas");

        tareasAsignadasButton.setText("Tareas Asignadas");

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
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(userButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(clientesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(datosCuentaButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(listaTareasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(119, 119, 119)
                                    .addComponent(registerLabel1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tareasAsignadasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userLabel)
                        .addGap(18, 18, 18)
                        .addComponent(registerLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datosCuentaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaTareasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(tareasAsignadasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addCerrarSesionMenuItem(ActionListener al) {
        this.cerrarSesionMenuItem.addActionListener(al);
    }

    public void addQuitMenuItem(ActionListener al) {
        this.quitMenuItem.addActionListener(al);
    }

    public void addUsuariosButtonActionListener(ActionListener al) {
        this.userButton.addActionListener(al);
    }

    public void addClientesButtonActionListener(ActionListener al) {
        this.clientesButton.addActionListener(al);
    }
    
    public void addDatosCuentaButtonActionListener(ActionListener al) {
        this.datosCuentaButton.addActionListener(al);
    }
    
    public void addListaTareasButtonActionListener(ActionListener al) {
        this.listaTareasButton.addActionListener(al);
    }
    
    public void addTareasAsignadasButtonActionListener(ActionListener al) {
        this.tareasAsignadasButton.addActionListener(al);
    }

    public void setUserLabel(String user) {
        this.userLabel.setText(user);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cerrarSesionMenuItem;
    private javax.swing.JButton clientesButton;
    private javax.swing.JButton datosCuentaButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton listaTareasButton;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JLabel registerLabel1;
    private javax.swing.JButton tareasAsignadasButton;
    private javax.swing.JButton userButton;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
