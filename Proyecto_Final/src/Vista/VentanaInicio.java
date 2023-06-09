package Vista;
import controlador.*;
import modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class VentanaInicio extends JFrame implements ActionListener, KeyListener {
    PacienteDAO p1= new PacienteDAO();
    int cont=3;
    int ContD;
    int posVar=0;
    int cont2 =0;
    String[] columnNames = {"SSN", "Nombre", "Apellido Paterno", "Apellido Materno", "Edad","Calle"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    String datos[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    JMenuBar menuBar;


    int ssn =p1.buscarPacientes("").size();

    String [] colonias ,calles;

    JMenu
            menuPaciente,menuMedicos,menuFarmacias,menuCompañiaF;
    JInternalFrame Altas_Pacientes;
    //, Bajas_Pacientes,Cambios_Pacientes, Consultas_Pacientes,Altas_Medicos, Bajas_Medicos,Cambios_Medicos, Consultas_Medicos
    JMenuItem itemAltas, itemBajas, itemCambios, itemConsultas,itemAltas2, itemBajas2, itemCambios2, itemConsultas2,itemAltas3, itemBajas3, itemCambios3, itemConsultas3,
            itemAltas4, itemBajas4, itemCambios4, itemConsultas4;
    JTextField tf_SSN1,tf_Nombre1,tf_ap1,tf_am1,tf_Edad,tf_Col,tf_Call,tfCons;
    JButton btnAgregarPaciente,btnCancelarPac,btnLimpiarPac,btnBajaPac, btn_Busc,btn_ActusliarPac,btn_Busc_Cambios,btn_Consulta,btnPrim,btnUlt,btnAntes,btnDespues;
    JScrollPane scrollPane, scrollPane2;
    JTable table2;
    JComboBox <String>comboEdad2,comboColonias1,comboCalles;
    JLabel lbl1, lbl_nomBtn,fondo;
    Paciente pm1;
    int mayor=0,menor=0;
    public VentanaInicio(){
        llenarComboColonia();
        cont2=p1.buscarPacientes("").size();
        for(int i=0;i<p1.buscarPacientes("").size();i++){

        }
        ssn++;


        System.out.println(ssn);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sistema Farmacia");
        setSize(700, 780);
        setLocationRelativeTo(null);

        setVisible(true);
        setResizable(false);

        //Componentes Graficos
        menuBar =new  JMenuBar();
        menuBar.setBackground( new Color(109, 209, 222));
        menuPaciente = new JMenu("Paciente");

        itemAltas = new JMenuItem("Agregar");
        itemAltas.setIcon(new ImageIcon("./assets/UserAdd.png"));
        itemAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0;i<p1.buscarPacientes("").size();i++){
                    if(mayor>(p1.buscarPacientes("").get(i).getSSN())){

                    }else {
                        mayor=p1.buscarPacientes("").get(i).getSSN();
                    }
                }

                actualizarTablas(table2);
                Altas_Pacientes.setVisible(true);
                btnAgregarPaciente.setVisible(true);
                btnBajaPac.setVisible(false);
                lbl_nomBtn.setText("Agregar Paciente");
                lbl1.setText("Altas");
                fondo.setBackground(Color.green);
                lbl1.setBounds(290, 0, 685, 40);
                Altas_Pacientes.setBackground( new Color(131, 220, 196, 203));
                tf_Edad.setVisible(false);
                tf_Call.setVisible(false);
                tf_Col.setVisible(false);

                tf_SSN1.setBounds(200,60,200,30);
                comboEdad2.setVisible(true);
                comboEdad2.setSelectedIndex(0);

                Altas_Pacientes.setTitle("Altas Pacientes");
                comboColonias1.setVisible(true);
                comboCalles.setVisible(true);
                btn_Busc.setVisible(false);
                tf_SSN1.setEnabled(false);
                btnPrim.setVisible(false);
                btnDespues.setVisible(false);
                btnAntes.setVisible(false);
                btnUlt.setVisible(false);
                tfCons.setVisible(false);
                btn_Consulta.setVisible(false);
                metodoDeshabilitar(tf_Col,tf_Call,tf_Edad,btnBajaPac,btn_Busc_Cambios,btn_Busc,btn_Consulta,btnPrim,btnAntes,btnDespues,btnUlt,tfCons);
                metodoHabilitar(tf_am1,tf_ap1,comboCalles,comboColonias1,comboEdad2,  btnAgregarPaciente,tf_Nombre1,btnAgregarPaciente);
                metodoRestablecer(tf_SSN1,tf_Call,tf_Col,tf_am1,tf_ap1,tf_Nombre1,tf_Edad);

                btn_Busc_Cambios.setVisible(false);
                tf_SSN1.setEnabled(true);


                tf_SSN1.setText(String.valueOf(mayor+1));
                tf_SSN1.setForeground( new Color(0, 76, 217));
                tf_SSN1.setFont(new Font("Arial",Font.BOLD,20));
                tf_SSN1.setEditable(false);


            }

        });
        menuPaciente.add(itemAltas);

        itemBajas = new JMenuItem("Bajas");
        itemBajas.setIcon(new ImageIcon("./assets/UserRemove.png"));
        itemBajas.addActionListener(this);
        menuPaciente.add(itemBajas);

        itemCambios = new JMenuItem("Cambios");
        itemCambios.setIcon(new ImageIcon("./assets/modificar.png"));
        itemCambios.addActionListener(this);
        menuPaciente.add(itemCambios);

        itemConsultas  = new JMenuItem("Consultas");
        itemConsultas.setIcon(new ImageIcon("./assets/consulta.png"));
        itemConsultas.addActionListener(this);

        menuPaciente.add(itemConsultas);
        menuBar.add(menuPaciente);

        menuMedicos=new JMenu("Medicos");
        itemAltas2 = new JMenuItem("Agregar");
        itemAltas2.setIcon(new ImageIcon("./assets/UserAdd.png"));
        itemAltas2.addActionListener(this);
        menuMedicos.add(itemAltas2);

        itemBajas2 = new JMenuItem("Bajas");
        itemBajas2.setIcon(new ImageIcon("./assets/UserRemove.png"));
        itemBajas2.addActionListener(this);
        menuMedicos.add(itemBajas2);

        itemCambios2 = new JMenuItem("Cambios");
        itemCambios2.setIcon(new ImageIcon("./assets/modificar.png"));
        itemCambios2.addActionListener(this);
        menuMedicos.add(itemCambios2);

        itemConsultas2 = new JMenuItem("Consultas");
        itemConsultas2.setIcon(new ImageIcon("./assets/consulta.png"));
        itemConsultas2.addActionListener(this);
        menuMedicos.add(itemConsultas2);
        menuBar.add(menuMedicos);

        menuFarmacias = new JMenu("Farmacias");
        itemAltas3 = new JMenuItem("Agregar");
        itemAltas3.setIcon(new ImageIcon("./assets/agregarEdif.png"));
        itemAltas3.addActionListener(this);
        menuFarmacias.add(itemAltas3);

        itemBajas3 = new JMenuItem("Bajas");
        itemBajas3.setIcon(new ImageIcon("./assets/eliminar2.png"));
        itemBajas3.addActionListener(this);
        menuFarmacias.add(itemBajas3);

        itemCambios3 = new JMenuItem("Cambios");
        itemCambios3.setIcon(new ImageIcon("./assets/modificar.png"));
        itemCambios3.addActionListener(this);
        menuFarmacias.add(itemCambios3);

        itemConsultas3 = new JMenuItem("Consultas");
        itemConsultas3.setIcon(new ImageIcon("./assets/consulta.png"));
        itemConsultas3.addActionListener(this);
        menuFarmacias.add(itemConsultas3);
        menuBar.add(menuFarmacias);

        menuCompañiaF=new JMenu("Compañias Farmaceuticas");
        itemAltas4 = new JMenuItem("Agregar");
        itemAltas4.setIcon(new ImageIcon("./assets/agregarEdif.png"));
        itemAltas4.addActionListener(this);
        menuCompañiaF.add(itemAltas4);

        itemBajas4 = new JMenuItem("Bajas");
        itemBajas4.setIcon(new ImageIcon("./assets/eliminar2.png"));
        itemBajas4.addActionListener(this);
        menuCompañiaF.add(itemBajas4);

        itemCambios4 = new JMenuItem("Cambios");
        itemCambios4.setIcon(new ImageIcon("./assets/modificar.png"));
        itemCambios4.addActionListener(this);
        menuCompañiaF.add(itemCambios4);

        itemConsultas4 = new JMenuItem("Consultas");
        itemConsultas4.setIcon(new ImageIcon("./assets/consulta.png"));
        itemConsultas4.addActionListener(this);
        menuCompañiaF.add(itemConsultas4);
        menuBar.add(menuCompañiaF);

        setJMenuBar(menuBar);
//DesktoPane
        JDesktopPane desktopPane = new JDesktopPane();
        Altas_Pacientes = new JInternalFrame();
        Altas_Pacientes.getContentPane().setLayout(null);
        Altas_Pacientes.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Altas_Pacientes.setTitle("ALTAS DE PACIENTES");
        Altas_Pacientes.setSize(685, 719);
        Altas_Pacientes.setResizable(false);
        Altas_Pacientes.setBackground( new Color(131, 220, 196, 203));

        lbl1  = new JLabel("ALTAS");
        lbl1.setFont(new Font("Arial", Font.BOLD, 30));
        lbl1.setForeground(Color.white);
        lbl1.setBounds(290, 0, 685, 40);
        Altas_Pacientes.add(lbl1);
        fondo = new JLabel();
        fondo.setBounds(0, 0, 684, 40);
        fondo.setBackground(Color.green);
        fondo.setOpaque(true);
        Altas_Pacientes.add(fondo);

        JLabel imagen = new JLabel(new ImageIcon("./assets/enfermo.png"));
        imagen.setBounds(420,50,240,240);
        Altas_Pacientes.add(imagen);

        JLabel lbl_SSN=new JLabel("SSN:");
        lbl_SSN.setFont(new Font("Arial",Font.BOLD,20));
        lbl_SSN.setBounds(20,60,80,30);

        Altas_Pacientes.add(lbl_SSN);

        tf_SSN1= new JTextField(10);
        tf_SSN1.setText(String.valueOf(cont));
        tf_SSN1.setBounds(200,60,200,30);
        tf_SSN1.setBackground(  new Color(255, 255, 255, 255));
        tf_SSN1.addKeyListener(this);
        Altas_Pacientes.add(tf_SSN1);

        JLabel lblNombre1=new JLabel("Nombre:");
        lblNombre1.setFont(new Font("Arial",Font.BOLD,20));
        lblNombre1.setBounds(20,110,100,30);
        Altas_Pacientes.add(lblNombre1);

        tf_Nombre1= new JTextField(10);
        tf_Nombre1.setBounds(200,110,200,30);
        tf_Nombre1.setBackground(  new Color(255, 255, 255, 255));
        tf_Nombre1.addKeyListener(this);
        Altas_Pacientes.add(tf_Nombre1);


        JLabel lbl_Primer_Ap= new JLabel("Primer Apellido:");
        lbl_Primer_Ap.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Primer_Ap.setBounds(20,160,200,30);
        Altas_Pacientes.add(lbl_Primer_Ap);

        tf_ap1= new JTextField();
        tf_ap1.setBounds(200,160,200,30);
        tf_ap1.setBackground(   new Color(255, 255, 255, 255));
        tf_ap1.addKeyListener(this);
        Altas_Pacientes.add(tf_ap1);

        JLabel lbl_Segundo_Ap= new JLabel("Segundo Apellido:");
        lbl_Segundo_Ap.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Segundo_Ap.setBounds(20,210,200,30);
        Altas_Pacientes.add(lbl_Segundo_Ap);

        tf_am1= new JTextField();
        tf_am1.setBounds(200,210,200,30);
        tf_am1.setBackground(   new Color(255, 255, 255, 255));
        tf_am1.addKeyListener(this);
        Altas_Pacientes.add(tf_am1);
        btn_Busc_Cambios= new JButton(new ImageIcon("./assets/lupa2.png"));
        btn_Busc_Cambios.addActionListener(this);
        btn_Busc_Cambios.setBounds(320,60,70,40);
        Altas_Pacientes.add(btn_Busc_Cambios);


        JLabel lblEdad = new JLabel("Edad: ");
        lblEdad.setFont(new Font("Arial",Font.BOLD,20));
        lblEdad.setBounds(25,260,150,30);
        Altas_Pacientes.add(lblEdad);

        comboEdad2=new JComboBox<String>();
        comboEdad2.addItem("Selecciona Edad..");
        for (int i=1;i<=120;i++) {
            String n=String.valueOf(i);
            comboEdad2.addItem(n);
        }
        comboEdad2.setBounds(200,260,200,30);
        Altas_Pacientes.add(comboEdad2);

        JLabel lbl_Direccion= new JLabel("Dirección ");
        lbl_Direccion.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Direccion.setBounds(20,330,100,30);
        Altas_Pacientes.add(lbl_Direccion);

        JLabel lbl_Colonia= new JLabel("Colonia: ");
        lbl_Colonia.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Colonia.setBounds(20,370,100,30);
        Altas_Pacientes.add(lbl_Colonia);

        comboCalles= new JComboBox<>();
        comboCalles.setBounds(200,410,200,30);
        comboCalles.addActionListener(this);
        comboCalles.setEnabled(false);

        Altas_Pacientes.add(comboCalles);

        comboColonias1 = new JComboBox<>(colonias);
        comboColonias1.setBounds(200, 370, 200, 30);
        comboColonias1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c=(Component) e.getSource();
                if (comboColonias1.getSelectedIndex()>=0 && c==comboColonias1) {
                    int pos=comboColonias1.getSelectedIndex();
                    comboCalles.removeAllItems();
                    LlenarCB_Calles(pos);
                }
            }

        });
        Altas_Pacientes.add(comboColonias1);

        JLabel lbl_Calles= new JLabel("Calles: ");
        lbl_Calles.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Calles.setBounds(20,410,100,30);
        Altas_Pacientes.add(lbl_Calles);

        lbl_nomBtn= new JLabel("Añadir Paciente");
        lbl_nomBtn.setBounds(480,300,100,20);
        Altas_Pacientes.add(lbl_nomBtn);
        btnAgregarPaciente= new JButton(new ImageIcon("./assets/añadirPaciente.png"));

        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(tf_Nombre1.getText().equals("")||tf_ap1.getText().equals("")||tf_am1.getText().equals("")||comboEdad2.getSelectedIndex()==0||comboColonias1.getSelectedIndex()==0||comboCalles.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(getContentPane(),"Error, no se puede ingresar un paciente(Faltan datos o datos incorrectos)");
                    for(int i=0;i<p1.buscarPacientes("").size();i++){
                        if(mayor>(p1.buscarPacientes("").get(i).getSSN())){

                        }else {
                            mayor=p1.buscarPacientes("").get(i).getSSN();
                        }
                    }
                    tf_SSN1.setText(String.valueOf(mayor+1));
                }else{
                    for (int i = 0; i < p1.buscarCalle("").size(); i++) {
                        if (comboCalles.getSelectedItem().equals(p1.buscarCalle("").get(i).getNombre_Calle())) {
                            posVar = p1.buscarCalle("").get(i).getID_Calle();
                            break;
                        }
                    }
                    for(int i=0;i<p1.buscarPacientes("").size();i++){
                        if(mayor>(p1.buscarPacientes("").get(i).getSSN())){

                        }else {
                            mayor=p1.buscarPacientes("").get(i).getSSN();
                        }
                    }
                    tf_SSN1.setText(String.valueOf(ssn));
                    pm1= new Paciente(mayor+1,tf_Nombre1.getText(),tf_ap1.getText(),tf_am1.getText(), (byte) Integer.parseInt(comboEdad2.getItemAt(comboEdad2.getSelectedIndex())),posVar);
                    System.out.println(p1.agregarPaciente(pm1));
                    JOptionPane.showMessageDialog(getContentPane(),"Se agrego correctamente");
                    tf_SSN1.setText(String.valueOf(mayor+1));
                    cont=cont+1;
                    actualizarTablas(table2);
                    ssn++;
                    tf_SSN1.setText(String.valueOf(ssn));
                    metodoRestablecer(tf_am1,tf_ap1,tf_Nombre1,comboColonias1,comboEdad2,comboCalles);
                }
            }
        });

        btnAgregarPaciente.setBounds(495,320,60,50);
        Altas_Pacientes.add(btnAgregarPaciente);

        JLabel lbl_btnVaciar= new JLabel("Limpiar");
        lbl_btnVaciar.setBounds(445,375,100,20);
        Altas_Pacientes.add(lbl_btnVaciar);
        btnLimpiarPac= new JButton(new ImageIcon("./assets/basura.png"));
        btnLimpiarPac.setBounds(445,395,60,50);
        btnLimpiarPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboCalles.getItemCount()>0) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboColonias1);
                    comboCalles.setEnabled(false);
                    comboCalles.removeAllItems();
                    tf_SSN1.setEditable(true);
                } else if (btn_ActusliarPac.isEnabled()||btn_Busc.isEnabled()) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, tf_Edad, tf_Col, tf_Call,comboEdad2);
                    tf_SSN1.setEditable(true);
                    tf_SSN1.setEnabled(true);
                } else if (btn_Consulta.isEnabled()) {
                    tf_SSN1.setEnabled(false);
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2,tf_Edad,tf_Col,tf_Call);
                    tf_SSN1.setEditable(false);
                } else if (!btn_ActusliarPac.isEnabled()) {
                    metodoDeshabilitar(comboColonias1,comboCalles,comboEdad2);

                } else if (btn_Consulta.isEnabled()) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
                    tf_SSN1.setEnabled(false);
                } else if (btnAgregarPaciente.isEnabled()) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
                    comboColonias1.setEnabled(true);
                    comboEdad2.setEnabled(true);
                } else{
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
                }
                metodoDeshabilitar(btn_ActusliarPac,btn_Busc,btn_Busc_Cambios);
                metodoDeshabilitar(comboColonias1,comboCalles,comboEdad2);
                metodoRestablecer(tf_SSN1);
                comboEdad2.setEnabled(true);
                comboColonias1.setEnabled(true);


            }
        });
        Altas_Pacientes.add(btnLimpiarPac);

        JLabel lbl_btnCancelar= new JLabel("Cancelar");
        lbl_btnCancelar.setBounds(550,375,100,20);
        Altas_Pacientes.add(lbl_btnCancelar);
        btnCancelarPac= new JButton(new ImageIcon("./assets/cancelar.png"));
        btnCancelarPac.setBounds(550,395,60,50);
        btnCancelarPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboCalles.getItemCount()>0) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboColonias1, comboCalles, comboEdad2);
                    comboCalles.setEnabled(false);
                    comboCalles.removeAllItems();
                    Altas_Pacientes.setVisible(false);


                } else if (tf_Edad.isVisible()) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, tf_Col,tf_Call,tf_Edad);
                    Altas_Pacientes.setVisible(false);
                } else{
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1,  comboEdad2);
                    Altas_Pacientes.setVisible(false);
                }

            }
        });
        Altas_Pacientes.add(btnCancelarPac);

        table2 = new JTable(model);
        scrollPane2=  new JScrollPane(table2);
        scrollPane2.setBounds(50,480,570,150);
        Altas_Pacientes.add(scrollPane2);

        btnBajaPac= new JButton(new ImageIcon("./assets/eliminarPac.png"));
        btnBajaPac.setBounds(495,320,60,50);
        btnBajaPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(p1.buscarPacientes("").size()>1){
                    for(int i=0;i<p1.buscarPacientes("").size();i++){
                        if(Integer.parseInt(tf_SSN1.getText())==p1.buscarPacientes("").get(i).getSSN()){
                            System.out.println(p1.eliminarPaciente(tf_SSN1.getText()));
                            tf_SSN1.setText("");
                            metodoRestablecer(tf_SSN1,tf_Edad,tf_Nombre1,tf_ap1,tf_Col,tf_Call,tf_am1);
                            btnBajaPac.setEnabled(false);
                            actualizarTablas(table2);

                            JOptionPane.showMessageDialog(getContentPane(),"Se elimino correctamente");
                            btnBajaPac.setEnabled(false);
                            tf_SSN1.setText("");
                            break;
                        }
                    }



                } else if(tf_SSN1.getText().equals("")){
                    JOptionPane.showMessageDialog(getContentPane(),"No se pudo eliminar, SNN incorrecto");
                    tf_SSN1.setText("");
                    btnBajaPac.setEnabled(false);
                }else  if(p1.buscarPacientes("").size()<1){
                    JOptionPane.showMessageDialog(getContentPane(),"No se pudo eliminar");
                    btnBajaPac.setEnabled(false);
                }
            }
        });
        Altas_Pacientes.add(btnBajaPac);

        tf_Edad= new JTextField();
        tf_Edad.setBounds(200,260,200,30);
        tf_Edad.setBackground(   new Color(255, 255, 255, 255));
        Altas_Pacientes.add(tf_Edad);

        tf_Col= new JTextField();
        tf_Col.setBounds(200, 370, 200, 30);
        tf_Col.setBackground(   new Color(255, 255, 255, 255));
        Altas_Pacientes.add(tf_Col);

        tf_Call= new JTextField();
        tf_Call.setBounds(200,410,200,30);
        tf_Call.setBackground(  new Color(255, 255, 255, 255));
        Altas_Pacientes.add(tf_Call);

        btn_Busc= new JButton(new ImageIcon("./assets/lupa.png"));
        btn_Busc.setBounds(320,60,70,40);
        btn_Busc.addActionListener(this);
        Altas_Pacientes.add(btn_Busc);

        btn_ActusliarPac= new JButton(new ImageIcon("./assets/modificar.png"));
        btn_ActusliarPac.addActionListener(this);
        btn_ActusliarPac.setBounds(495,320,60,50);


        btn_Consulta= new JButton(new ImageIcon("./assets/lupa2.png"));
        btn_Consulta.setBounds(495,320,60,50);
        Altas_Pacientes.add(btn_Consulta);
        btn_Consulta.addActionListener(this);

        btnPrim= new JButton("<<");
        btnPrim.setBounds(180,640,50,20);
        btnPrim.addActionListener(this);
        Altas_Pacientes.add(btnPrim);

        btnAntes= new JButton("<");
        btnAntes.setBounds(250,640,50,20);
        btnAntes.addActionListener(this);
        Altas_Pacientes.add(btnAntes);

        tfCons= new JTextField();
        tfCons.setBounds(310,640,40,20);
        tfCons.setText("0");
        Altas_Pacientes.add(tfCons);

        btnDespues= new JButton(">");
        btnDespues.setBounds(360,640,50,20);
        btnDespues.addActionListener(this);
        Altas_Pacientes.add(btnDespues);

        btnUlt= new JButton(">>");
        btnUlt.setBounds(420,640,50,20);
        btnUlt.addActionListener(this);
        Altas_Pacientes.add(btnUlt);


        desktopPane.add(Altas_Pacientes);
        add(desktopPane);

    }//class
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }//main
    @Override
    public void actionPerformed(ActionEvent e) {
        Component c = (Component) e.getSource();
        if (c == itemBajas) {
            tf_SSN1.setForeground(new Color(0, 76, 217));
            tf_SSN1.setFont(new Font("Arial", Font.BOLD, 20));
            actualizarTablas(table2);
            tf_SSN1.setEditable(true);
            btn_Busc_Cambios.setVisible(false);
            Altas_Pacientes.setVisible(true);
            btnAgregarPaciente.setVisible(false);
            btnBajaPac.setVisible(true);

            lbl_nomBtn.setText("Eliminar Paciente");
            lbl1.setText("Bajas");
            tf_SSN1.setBounds(75, 60, 200, 30);
            fondo.setBackground(Color.red);
            Altas_Pacientes.setBackground(new Color(255, 175, 0, 255));
            Altas_Pacientes.setTitle("Bajas Pacientes");

            btn_ActusliarPac.setVisible(false);
            tf_Edad.setVisible(true);
            tf_Col.setVisible(true);
            tf_Call.setVisible(true);
            btn_Busc.setVisible(true);
            btn_Consulta.setVisible(false);
            comboEdad2.setVisible(false);
            comboCalles.setVisible(false);
            comboColonias1.setVisible(false);
            btnPrim.setVisible(false);
            btnDespues.setVisible(false);
            btnAntes.setVisible(false);
            btnUlt.setVisible(false);
            tfCons.setVisible(false);

            lbl1.setBounds(290, 0, 685, 40);


            metodoHabilitar(tf_SSN1, btnBajaPac);
            metodoDeshabilitar(tf_Nombre1, tf_am1, tf_ap1, tf_Edad, tf_Call, tf_Col, btnPrim, btnUlt, btnAntes, btnDespues, tfCons, comboColonias1, comboCalles, comboEdad2,
                    btn_Consulta, btn_Busc, btnBajaPac, btn_ActusliarPac, btn_Busc_Cambios, btnAgregarPaciente);

            btnPrim.setVisible(false);
            btnDespues.setVisible(false);
            btnAntes.setVisible(false);
            btnUlt.setVisible(false);
            tfCons.setVisible(false);
            metodoDeshabilitar(btn_Busc);
            if (comboCalles.getItemCount() > 0) {
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboColonias1, comboCalles, comboEdad2);
                comboCalles.setEnabled(false);
                comboCalles.removeAllItems();
            } else if (tf_Edad.isVisible()) {
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, tf_Edad, tf_Col, tf_Call);
            } else {
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
            }
        }// item bajas
        else if (c == itemCambios) {
            actualizarTablas(table2);
            Altas_Pacientes.setTitle("Actualizar Paciente");
            metodoHabilitar(btn_ActusliarPac);
            btn_ActusliarPac.setVisible(true);
            Altas_Pacientes.add(btn_ActusliarPac);
            Altas_Pacientes.setVisible(true);
            btnAgregarPaciente.setVisible(false);
            btnBajaPac.setVisible(false);
            lbl_nomBtn.setText("Actualizar");
            lbl1.setText("Cambios Paciente");
            tf_SSN1.setBounds(75, 60, 200, 30);
            fondo.setBackground(new Color(120, 2, 141, 255));
            Altas_Pacientes.setBackground(new Color(228, 164, 241, 255));

            metodoDeshabilitar(tf_Edad, tf_Call, tf_Col, btn_Busc);
            tf_Edad.setVisible(false);
            tf_Call.setVisible(false);
            tf_Col.setVisible(false);
            comboEdad2.setVisible(true);
            comboColonias1.setVisible(true);
            comboCalles.setVisible(true);
            btn_Consulta.setVisible(false);
            btnPrim.setVisible(false);
            btnDespues.setVisible(false);
            btnAntes.setVisible(false);
            btnUlt.setVisible(false);
            tfCons.setVisible(false);

            lbl1.setBounds(210, 0, 685, 40);
            btn_Busc.setVisible(false);
            btn_Busc_Cambios.setVisible(true);
            metodoRestablecer(tf_SSN1);
            metodoHabilitar(tf_SSN1, btn_Busc_Cambios);
            metodoDeshabilitar(btn_ActusliarPac, btn_Busc_Cambios, btnAgregarPaciente, btnBajaPac, tf_am1, tf_Nombre1, comboEdad2, tf_ap1, comboColonias1, comboCalles, btn_Consulta, btnAntes, btnUlt, btnPrim, btnDespues, tfCons);

        }//item cambio
        else if (c == btn_Busc_Cambios) {
            comboEdad2.setEnabled(true);
            comboColonias1.setEnabled(true);
            tf_SSN1.setEditable(false);
            //  comboCalles.setEnabled(true);
            tf_Nombre1.setEnabled(true);
            tf_ap1.setEnabled(true);
            tf_am1.setEnabled(true);
            btn_Busc.setEnabled(true);
            btn_ActusliarPac.setEnabled(true);
            btnBajaPac.setEnabled(true);

            if (!(tf_SSN1.getText().equals(""))) {

                int posT = Integer.parseInt(tf_SSN1.getText());
                int contBajas = 0;
                for (int i = 0; i < p1.buscarPacientes("").size(); i++) {
                    if (posT == p1.buscarPacientes("").get(i).getSSN() && tf_SSN1.getText() != "") {
                        tf_Nombre1.setText(p1.buscarPacientes("").get(i).getNombre());
                        tf_ap1.setText(p1.buscarPacientes("").get(i).getPrimerAp());
                        tf_am1.setText(p1.buscarPacientes("").get(i).getSegundoAp());
                        comboEdad2.setSelectedIndex(Integer.parseInt(String.valueOf(p1.buscarPacientes("").get(i).getEdad())));
                        int contCol = 0;
                        for (int j = 0; j < p1.buscarCalle("").size(); j++) {
                            if (p1.buscarPacientes("").get(i).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                                contCol = p1.buscarCalle("").get(j).getID_Colonia();
                                break;
                            }
                        }
                        String nomColonia = "";
                        for (int k = 0; k < p1.buscarColonia("").size(); k++) {
                            if (contCol == p1.buscarColonia("").get(k).getID_Colonia()) {
                                nomColonia = p1.buscarColonia("").get(k).getNombreColonia();
                                llenarComboColonia();
                                comboColonias1.setSelectedIndex(contCol);
                                break;
                            }
                        }
                        for (int j = 0; j < p1.buscarCalle("").size(); j++) {
                            if (p1.buscarCalle("").get(j).getID_Calle() == p1.buscarPacientes("").get(i).getCalle()) {
                                comboCalles.removeAllItems();
                                LlenarCB_Calles(contCol);
                                comboCalles.setSelectedIndex(contCol);
                            }
                        }
                        tf_SSN1.setText(String.valueOf(p1.buscarPacientes("").get(i).getSSN()));
                    } else {
                        contBajas++;
                    }
                    tf_Nombre1.setEditable(true);
                    tf_am1.setEditable(true);
                    tf_ap1.setEditable(true);

                }
                if (contBajas == p1.buscarPacientes("").size()) {

                    JOptionPane.showMessageDialog(getContentPane(), "Ese registro no se encuentra disponible o no existe!!!");
                    btnBajaPac.setEnabled(false);
                    tf_SSN1.setText("");
                    tf_SSN1.setEditable(true);


                }
                contBajas = 0;

            } else {
                JOptionPane.showMessageDialog(getContentPane(), "Error en busqueda, ingrese un ssn para poder buscarlo");
                tf_SSN1.setEditable(true);
            }

        } else if (c == btn_Busc) {
            metodoHabilitar(btnBajaPac);
            posVar = Integer.parseInt(tf_SSN1.getText());
            for (int i = 0; i < p1.buscarPacientes("").size(); i++) {
                if (mayor > (p1.buscarPacientes("").get(i).getSSN())) {

                } else {
                    mayor = p1.buscarPacientes("").get(i).getSSN();
                }
            }
            for (int i = 0; i < p1.buscarPacientes("").size(); i++) {
                if (menor < (p1.buscarPacientes("").get(i).getSSN())) {

                } else {
                    menor = p1.buscarPacientes("").get(i).getSSN();
                }
            }
            if (Integer.parseInt(tf_SSN1.getText()) <= mayor && Integer.parseInt(tf_SSN1.getText()) >=menor){
                for (int i = 0; i < p1.buscarPacientes("").size(); i++) {
                    if (Integer.parseInt(tf_SSN1.getText()) == p1.buscarPacientes("").get(i).getSSN() && tf_SSN1.getText() != ("")) {
                        tf_Nombre1.setText(p1.buscarPacientes("").get(i).getNombre());
                        tf_ap1.setText(p1.buscarPacientes("").get(i).getPrimerAp());
                        tf_am1.setText(p1.buscarPacientes("").get(i).getSegundoAp());
                        tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(i).getEdad()));

                        for (int j = 0; j < p1.buscarCalle("").size(); j++) {
                            if (p1.buscarPacientes("").get(i).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                                tf_Call.setText(p1.buscarCalle("").get(j).getNombre_Calle());

                                for (int k = 0; k < p1.buscarColonia("").size(); k++) {
                                    if (p1.buscarCalle("").get(j).getID_Colonia() == p1.buscarColonia("").get(k).getID_Colonia()) {
                                        tf_Col.setText(p1.buscarColonia("").get(k).getNombreColonia());
                                    }
                                }
                            }

                        }
                        break;
                    } else  {
                        JOptionPane.showMessageDialog(getContentPane(), "Ese registro no se encuentra disponible o no existe!!!");
                        btnBajaPac.setEnabled(false);
                        tf_SSN1.setText("");
                        break;
                    }
                }}else  {
                JOptionPane.showMessageDialog(getContentPane(), "Ese registro no se encuentra disponible o no existe!!!");
                tf_SSN1.setText("");
        }

        }//BTN BUSCAR
        else if (c == itemConsultas) {
            actualizarTablas(table2);
            btn_ActusliarPac.setEnabled(false);
            btn_Consulta.setVisible(true);
            btn_Consulta.setEnabled(true);
            Altas_Pacientes.setTitle("Consultas Pacientes");
            lbl_nomBtn.setText("Buscar Pacientes");
            lbl1.setText("Consultas");
            fondo.setBackground(new Color(131, 220, 196, 203));
            lbl1.setBounds(260, 0, 685, 40);
            Altas_Pacientes.setBackground(new Color(161, 156, 156, 250));
            tf_SSN1.setBounds(200, 60, 200, 30);
            tf_SSN1.setEnabled(false);
            Altas_Pacientes.setVisible(true);
            tf_Edad.setVisible(true);
            tf_Call.setVisible(true);
            tf_Col.setVisible(true);
            btnAgregarPaciente.setVisible(false);
            btnBajaPac.setVisible(false);
            comboEdad2.setVisible(false);
            comboColonias1.setVisible(false);
            comboCalles.setVisible(false);
            btn_Busc.setVisible(false);
            btnPrim.setVisible(false);
            btnDespues.setVisible(false);
            btnAntes.setVisible(false);
            btnUlt.setVisible(false);
            tfCons.setVisible(false);
            btn_ActusliarPac.setVisible(false);

            btnDespues.setVisible(true);
            btnUlt.setVisible(true);
            btnAntes.setVisible(true);
            btnPrim.setVisible(true);
            tfCons.setVisible(true);

            metodoHabilitar(btnDespues, btnPrim, btnUlt, btnAntes);
            metodoDeshabilitar(tf_SSN1, tf_Nombre1, tf_ap1, tf_am1, tf_Col, tf_Call, tf_Edad, btnAntes, btnPrim, btnUlt, btnDespues, tfCons);
            metodoRestablecer(tf_SSN1, tf_Call, tf_Col, tf_am1, tf_ap1, tf_Nombre1, tf_Edad);
            btnDespues.setEnabled(true);
            btnUlt.setEnabled(true);
            btnAntes.setEnabled(true);
            btnPrim.setEnabled(true);
            tf_SSN1.setEnabled(true);
            tf_SSN1.setEditable(false);
            btn_Busc_Cambios.setVisible(false);


        }  else if (btn_Consulta == c) {
            tf_SSN1.setText(String.valueOf(Integer.parseInt(String.valueOf(p1.buscarPacientes("").get(0).getSSN()))));
            tf_Nombre1.setText(p1.buscarPacientes("").get(0).getNombre());
            tf_ap1.setText(p1.buscarPacientes("").get(0).getPrimerAp());
            tf_am1.setText(p1.buscarPacientes("").get(0).getSegundoAp());
            tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(0).getEdad()));
                //=============

            int varCol=0;
            for(int i=0;i<p1.buscarPacientes("").size();i++){
                for (int j=0;j<p1.buscarCalle("").size();j++) {
                    if (p1.buscarPacientes("").get(0).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                        varCol=p1.buscarCalle("").get(j).getID_Colonia();
                        tf_Call.setText( p1.buscarCalle("").get(j).getNombre_Calle());
                    }
                }
            }
            for (int j=0;j<p1.buscarColonia("").size();j++){
                if(varCol==p1.buscarColonia("").get(j).getID_Colonia()){
                    tf_Col.setText(p1.buscarColonia("").get(j).getNombreColonia());
                }
            }


            //=============
        tfCons.setText("1");
        actualizarTablas(table2);
     } else if (c==btnPrim) {

            tf_SSN1.setText(String.valueOf(p1.buscarPacientes("").get(0).getSSN()));
            tf_Nombre1.setText(p1.buscarPacientes("").get(0).getNombre());
            tf_ap1.setText( p1.buscarPacientes("").get(0).getPrimerAp());
            tf_am1.setText( p1.buscarPacientes("").get(0).getSegundoAp());
            tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(0).getEdad()));
       //====================
int varCol=0;
         for(int i=0;i<p1.buscarPacientes("").size();i++){
                for (int j=0;j<p1.buscarCalle("").size();j++) {
                    if (p1.buscarPacientes("").get(0).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                        varCol=p1.buscarCalle("").get(j).getID_Colonia();
                        tf_Call.setText( p1.buscarCalle("").get(j).getNombre_Calle());
                    }
                }
         }
             for (int j=0;j<p1.buscarColonia("").size();j++){
                 if(varCol==p1.buscarColonia("").get(j).getID_Colonia()){
                     tf_Col.setText(p1.buscarColonia("").get(j).getNombreColonia());
                 }
             }
            //==============
            tfCons.setText("1");
        }else if(c==btnDespues){
            if(Integer.parseInt(tfCons.getText())>=p1.buscarPacientes("").size()){

            }else {
                tf_SSN1.setText(String.valueOf(p1.buscarPacientes("").get((Integer.parseInt((tfCons.getText())))).getSSN()));
                tf_Nombre1.setText( p1.buscarPacientes("").get(((Integer.parseInt(tfCons.getText())))).getNombre());
                tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(Integer.parseInt(tfCons.getText())).getEdad()));
                tf_ap1.setText( p1.buscarPacientes("").get((Integer.parseInt(tfCons.getText()))).getPrimerAp());
                tf_am1.setText( p1.buscarPacientes("").get((Integer.parseInt(tfCons.getText()))).getSegundoAp());
                //    tfSemestre4.setText(String.valueOf(p1.buscarPacientes("").get((Integer.parseInt(tfCons.getText()))).getSemestre()));
                tfCons.setText(String.valueOf((Integer.parseInt(tfCons.getText()))+1));
                int varCol=0;
                for(int i=0;i<p1.buscarPacientes("").size();i++){
                    for (int j=0;j<p1.buscarCalle("").size();j++) {
                        if (p1.buscarPacientes("").get(Integer.parseInt(tfCons.getText())-1).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                            tf_Call.setText( p1.buscarCalle("").get(j).getNombre_Calle());
                            varCol=p1.buscarCalle("").get(j).getID_Colonia();
                        }
                    }
                }
                for (int j=0;j<p1.buscarColonia("").size();j++){
                    if(varCol==p1.buscarColonia("").get(j).getID_Colonia()){
                        tf_Col.setText(p1.buscarColonia("").get(j).getNombreColonia());
                    }
                }

            }
        } else if (c==btnAntes) {
            if(Integer.parseInt(tfCons.getText())<=1){
            }else {
                tfCons.setText(String.valueOf(Integer.parseInt(tfCons.getText())-1));
                //tfCons.setText(String.valueOf(Integer.parseInt(String.valueOf(Integer.parseInt(tfCons.getText())-1))));
                tf_SSN1.setText(String.valueOf(p1.buscarPacientes("").get(((Integer.parseInt((tfCons.getText()))))-1).getSSN()));
                tf_Nombre1.setText( p1.buscarPacientes("").get(((Integer.parseInt(tfCons.getText()))-1)).getNombre());
                tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(Integer.parseInt(tfCons.getText())-1).getEdad()));
                tf_ap1.setText( p1.buscarPacientes("").get((Integer.parseInt(tfCons.getText())-1)).getPrimerAp());
                tf_am1.setText( p1.buscarPacientes("").get((Integer.parseInt(tfCons.getText())-1)).getSegundoAp());
                int varCol=0;
                for(int i=0;i<p1.buscarPacientes("").size();i++){
                    for (int j=0;j<p1.buscarCalle("").size();j++) {
                        if (p1.buscarPacientes("").get(Integer.parseInt(tfCons.getText())-1).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                            tf_Call.setText( p1.buscarCalle("").get(j).getNombre_Calle());
                            varCol=p1.buscarCalle("").get(j).getID_Colonia();
                        }
                    }
                }
                for (int j=0;j<p1.buscarColonia("").size();j++){
                    if(varCol==p1.buscarColonia("").get(j).getID_Colonia()){
                        tf_Col.setText(p1.buscarColonia("").get(j).getNombreColonia());
                    }
                }

            }


        } else if (c==btnUlt) {
            tf_SSN1.setText(String.valueOf(p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getSSN()));
            tf_Nombre1.setText(p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getNombre());
            tf_ap1.setText( p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getPrimerAp());
            tf_am1.setText( p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getSegundoAp());
            tf_Edad.setText(String.valueOf(p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getEdad()));
            //====================
            int varCol=0;
            for(int i=0;i<p1.buscarPacientes("").size();i++){
                for (int j=0;j<p1.buscarCalle("").size();j++) {
                    if (p1.buscarPacientes("").get(p1.buscarPacientes("").size()-1).getCalle() == p1.buscarCalle("").get(j).getID_Calle()) {
                        varCol=p1.buscarCalle("").get(j).getID_Colonia();
                        tf_Call.setText( p1.buscarCalle("").get(j).getNombre_Calle());
                    }
                }
            }
            for (int j=0;j<p1.buscarColonia("").size();j++){
                if(varCol==p1.buscarColonia("").get(j).getID_Colonia()){
                    tf_Col.setText(p1.buscarColonia("").get(j).getNombreColonia());
                }
            }

            //==============
            tfCons.setText(String.valueOf(p1.buscarPacientes("").size()));
        } else if (c==btn_ActusliarPac) {

            for (int i = 0; i < p1.buscarCalle("").size(); i++) {
                if (comboCalles.getSelectedItem().equals(p1.buscarCalle("").get(i).getNombre_Calle())) {
                    posVar = p1.buscarCalle("").get(i).getID_Calle();
                    break;
                }
            }

            if(tf_Nombre1.getText().equals("")||tf_ap1.getText().equals("")||tf_am1.getText().equals("")||comboEdad2.getSelectedIndex()==0||comboColonias1.getSelectedIndex()==0||comboCalles.getSelectedIndex()==0){

                JOptionPane.showMessageDialog(getContentPane(),"Faltan datos");

        }else{
                pm1 = new Paciente(Integer.parseInt(tf_SSN1.getText()), tf_Nombre1.getText(), tf_ap1.getText(), tf_am1.getText(), (byte) Integer.parseInt(comboEdad2.getItemAt(comboEdad2.getSelectedIndex())), posVar);
                if (p1.actualizarAlumno(pm1)) {
                    // JOptionPane.showMessageDialog(getContentPane(),p1.buscarCalle("").get(posVar).getNombre_Calle());
                    JOptionPane.showMessageDialog(getContentPane(), "Actualizado con exito");
                    actualizarTablas(table2);
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "No se actualizo");
                }


            }
        }

    }
    public void metodoRestablecer(Component...componentes){
        for(Component x: componentes){
            if(x instanceof JTextField){
                ((JTextField)x).setText("");
            }else if(x instanceof JComboBox<?>){
                ((JComboBox)x).setSelectedIndex(0);
            } else if (x instanceof  JSpinner) {
                ((JSpinner)x).setValue(String.valueOf(1));
            }
        }//foreach

    }
    public void llenarComboColonia(){
        int size=p1.buscarColonia("").size();
        colonias= new String[size+1];
        colonias[0]="Elige una Colonia:";
        for(int i=1;i<size+1;i++){
            colonias[i]=p1.buscarColonia("").get(i-1).getNombreColonia();
        }


    }
    public void LlenarCB_Calles(int pos) {
        comboCalles.setEnabled(true);
        comboCalles.addItem("Selecciona tu calle");
        if (pos == 0) {

        } else {
            int tamaño1 = p1.buscarCalle("").size();
            for (int i = 0; i < tamaño1; i++) {
                if (pos == p1.buscarCalle("").get(i).getID_Colonia()) {
                    comboCalles.addItem(p1.buscarCalle("").get(i).getNombre_Calle());
                }

            }//for

        }
    }

    public void metodoDeshabilitar(Component...componentes){
        for(Component x: componentes){
            if(x instanceof JTextField){
                ((JTextField)x).setEditable(false);
            }else if(x instanceof JComboBox<?>){
                ((JComboBox)x).setEnabled(false);
            } else if (x instanceof JButton) {
                ((JButton)x).setEnabled(false);
            }
        }//foreach

    }
    public void metodoHabilitar(Component...componentes){
        for(Component x: componentes){
            if(x instanceof JTextField){
                ((JTextField)x).setEditable(true);
            }else if(x instanceof JComboBox<?>){
                ((JComboBox)x).setEnabled(true);
            } else if (x instanceof JButton) {
                ((JButton)x).setEnabled(true);
            }
        }//foreach

    }
    public void actualizarTablas(JTable tabla){
        String controlador="com.mysql.cj.jdbc.Driver";
        String URL= "jdbc:mysql://localhost:3306/poyecto_farmacias";
        String consulta="SELECT * FROM pacientes";
        ResultSetTableModel modeloTabla=null;
        try {
            modeloTabla = new ResultSetTableModel(controlador,URL,consulta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tabla.setModel(modeloTabla);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        Component c=(Component) e.getSource();
        char caracter=e.getKeyChar();
        if(c==tf_SSN1){

            if(!(caracter>47&&caracter<58)) {
                e.consume();
            }else{
                btn_Busc.setEnabled(true);
                btn_Busc_Cambios.setEnabled(true);

            }

        } else if (c==tf_Nombre1||c==tf_ap1||c==tf_am1) {

            if(!(caracter>64&&caracter<91||caracter>96&&caracter<123||caracter==32)) {
                e.consume();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
