package Vista;
import javax.print.CancelablePrintJob;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;

public class VentanaInicio extends JFrame implements ActionListener, KeyListener {
    int cont=1;
    String[] columnNames = {"No. De Control", "Nombre", "Edad", "Apellido Paterno", "Apellido Materno", "Semestre", "Carrera"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    String datos[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    String [] items2 = {"Elige Carrera:", "ISC", "IM", "IIA", "LA", "LC"};
    JMenuBar menuBar;
    String [] colonias = {"Elige una Colonia:", "Niños Heroes", "Frac. Guadalupe", "Las Quintas"};
    String[]callesNiñosHeroes={"Juan Escutia","Juan de la Barrera","Francisco I Madero"};
    String[]callesQuintas={"Luis Moya","Suave Patria","Garcia Salinas"};
    String[]callesFracc={"Severo Cocio","Panfilo Natera","Enrique Estrada"};
    JRadioButton rb1,rb2,rb3,rb4,rb5;
    JMenu menuPaciente,menuMedicos,menuFarmacias,menuCompañiaF;
    JInternalFrame Altas_Pacientes, Bajas_Pacientes,Cambios_Pacientes, Consultas_Pacientes,Altas_Medicos, Bajas_Medicos,Cambios_Medicos, Consultas_Medicos;
    JMenuItem itemAltas, itemBajas, itemCambios, itemConsultas,itemAltas2, itemBajas2, itemCambios2, itemConsultas2,itemAltas3, itemBajas3, itemCambios3, itemConsultas3,
            itemAltas4, itemBajas4, itemCambios4, itemConsultas4;
    JTextField tf_SSN1,tf_Nombre1,tf_ap1,tf_am1,tf_Edad,tf_Col,tf_Call;
    JButton btnAgregarPaciente,btnCancelarPac,btnLimpiarPac,btnBajaPac, btn_Busc,btn_ActusliarPac,btn_Busc_Cambios,btn_Consulta;
    JScrollPane scrollPane, scrollPane2;
    JTable table2;
    JComboBox <String>comboEdad2,comboColonias1,comboCalles;
    JLabel lbl1, lbl_nomBtn,fondo;
    public VentanaInicio(){
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
                Altas_Pacientes.setVisible(true);
                btnAgregarPaciente.setVisible(true);
                btnAgregarPaciente.setEnabled(true);
                btnBajaPac.setEnabled(false);
                btnBajaPac.setVisible(false);
                lbl_nomBtn.setText("Agregar Paciente");
                lbl1.setText("Altas");
                fondo.setBackground(Color.green);
                lbl1.setBounds(290, 0, 685, 40);
                Altas_Pacientes.setBackground( new Color(131, 220, 196, 203));
                tf_Edad.setVisible(false);
                tf_Edad.setEnabled(false);
                tf_Call.setVisible(false);
                tf_Call.setEnabled(false);
                tf_Col.setVisible(false);
                tf_Col.setEnabled(false);
                tf_SSN1.setBounds(200,60,200,30);
                comboEdad2.setVisible(true);
                comboEdad2.setEnabled(true);
                Altas_Pacientes.setTitle("Altas Pacientes");
                comboColonias1.setVisible(true);
                comboColonias1.setEnabled(true);
                comboCalles.setVisible(true);
                comboCalles.setEnabled(true);
                btn_Busc.setEnabled(false);
                btn_Busc.setVisible(false);
                tf_Nombre1.setEnabled(true);
                tf_am1.setEnabled(true);
                tf_ap1.setEnabled(true);
                tf_SSN1.setEnabled(false);
                metodoRestablecer(tf_SSN1,tf_Call,tf_Col,tf_am1,tf_ap1,tf_Nombre1,tf_Edad);
                btn_Busc_Cambios.setEnabled(false);
                btn_Busc_Cambios.setVisible(false);
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
        tf_SSN1.setBounds(200,60,200,30);
        tf_SSN1.setBackground( new Color(198, 206, 201, 255));
        tf_SSN1.addKeyListener(this);
        Altas_Pacientes.add(tf_SSN1);


        JLabel lblNombre1=new JLabel("Nombre:");
        lblNombre1.setFont(new Font("Arial",Font.BOLD,20));
        lblNombre1.setBounds(20,110,100,30);
        Altas_Pacientes.add(lblNombre1);

         tf_Nombre1= new JTextField(10);
        tf_Nombre1.setBounds(200,110,200,30);
        tf_Nombre1.setBackground( new Color(198, 206, 201, 255));
        tf_Nombre1.addKeyListener(this);
        Altas_Pacientes.add(tf_Nombre1);


        JLabel lbl_Primer_Ap= new JLabel("Primer Apellido:");
        lbl_Primer_Ap.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Primer_Ap.setBounds(20,160,200,30);
        Altas_Pacientes.add(lbl_Primer_Ap);

        tf_ap1= new JTextField();
        tf_ap1.setBounds(200,160,200,30);
        tf_ap1.setBackground( new Color(198, 206, 201, 255));
        tf_ap1.addKeyListener(this);
        Altas_Pacientes.add(tf_ap1);

        JLabel lbl_Segundo_Ap= new JLabel("Segundo Apellido:");
        lbl_Segundo_Ap.setFont(new Font("Arial",Font.BOLD,20));
        lbl_Segundo_Ap.setBounds(20,210,200,30);
        Altas_Pacientes.add(lbl_Segundo_Ap);

        tf_am1= new JTextField();
        tf_am1.setBounds(200,210,200,30);
        tf_am1.setBackground( new Color(198, 206, 201, 255));
        tf_am1.addKeyListener(this);
        Altas_Pacientes.add(tf_am1);
        btn_Busc_Cambios= new JButton(new ImageIcon("./assets/lupa2.png"));
        btn_Busc_Cambios.addActionListener(this);
        btn_Busc_Cambios.setBounds(320,60,70,40);
        Altas_Pacientes.add(btn_Busc_Cambios);


        JLabel lblEdad = new JLabel("EDAD: ");
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
        comboCalles.setEnabled(false);

        Altas_Pacientes.add(comboCalles);



        comboColonias1 = new JComboBox<>(colonias);
        comboColonias1.setBounds(200, 370, 200, 30);
        comboColonias1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboColonias1.getSelectedIndex()==1){
                    comboCalles.removeAllItems();
                        for(int i=0;i<callesNiñosHeroes.length;i++){
                            comboCalles.addItem(callesNiñosHeroes[i]);
                        }
                    comboCalles.setEnabled(true);
                    }else if(comboColonias1.getSelectedIndex()==2){
                    comboCalles.removeAllItems();
                    for(int i=0;i<callesNiñosHeroes.length;i++){
                        comboCalles.addItem(callesFracc[i]);
                    }
                    comboCalles.setEnabled(true);
                }else if(comboColonias1.getSelectedIndex()==3){
                    comboCalles.removeAllItems();
                    for(int i=0;i<callesNiñosHeroes.length;i++){
                        comboCalles.addItem(callesQuintas[i]);
                    }
                    comboCalles.setEnabled(true);
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
                if(tf_SSN1.getText().equals("")||tf_Nombre1.getText().equals("")||tf_ap1.getText().equals("")||tf_am1.getText().equals("")||comboEdad2.getSelectedIndex()==0||comboColonias1.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(getContentPane(),"Error, falta algun dato, Verifique por favor...");
                }else if(tf_SSN1.getText().startsWith(" ")||tf_Nombre1.getText().startsWith(" ")||tf_ap1.getText().startsWith(" ")||tf_am1.getText().startsWith(" ")){
                    if(tf_SSN1.getText().startsWith(" ")){
                        JOptionPane.showMessageDialog(getContentPane(),"Los campos no deben comenzar con espacios");
                       metodoRestablecer(tf_SSN1);
                    }else if(tf_Nombre1.getText().startsWith(" ")){
                        JOptionPane.showMessageDialog(getContentPane(),"Los campos no deben comenzar con espacios");
                        metodoRestablecer(tf_Nombre1);
                    } else if (tf_ap1.getText().startsWith(" ")) {
                        JOptionPane.showMessageDialog(getContentPane(),"Los campos no deben comenzar con espacios");
                        metodoRestablecer(tf_ap1);
                    } else if (tf_am1.getText().startsWith(" ")) {
                        JOptionPane.showMessageDialog(getContentPane(),"Los campos no deben comenzar con espacios");
                        metodoRestablecer(tf_am1);
                    }else{

                    }

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
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboColonias1, comboCalles, comboEdad2);
                    comboCalles.setEnabled(false);
                    comboCalles.removeAllItems();
                } else if (tf_Edad.isVisible()) {
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, tf_Edad, tf_Col, tf_Call);
                } else{
                    metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
                }

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
                if(!(tf_SSN1.getText().equals(""))){
                    JOptionPane.showMessageDialog(getContentPane(),"No se pudo eliminar");
                }else {

                }
            }
        });
        Altas_Pacientes.add(btnBajaPac);

        tf_Edad= new JTextField();
        tf_Edad.setBounds(200,260,200,30);
        tf_Edad.setBackground( new Color(198, 206, 201, 255));
        Altas_Pacientes.add(tf_Edad);

        tf_Col= new JTextField();
        tf_Col.setBounds(200, 370, 200, 30);
        tf_Col.setBackground( new Color(198, 206, 201, 255));
       Altas_Pacientes.add(tf_Col);

       tf_Call= new JTextField();
       tf_Call.setBounds(200,410,200,30);
        tf_Call.setBackground( new Color(198, 206, 201, 255));
       Altas_Pacientes.add(tf_Call);

       btn_Busc= new JButton(new ImageIcon("./assets/lupa.png"));
       btn_Busc.setBounds(320,60,70,40);
       Altas_Pacientes.add(btn_Busc);



        desktopPane.add(Altas_Pacientes);
        add(desktopPane);

    }
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
        Component c=(Component) e.getSource();
        if(c==itemBajas){

            btn_Busc_Cambios.setEnabled(false);
            btn_Busc_Cambios.setVisible(false);
            Altas_Pacientes.setVisible(true);
            btnAgregarPaciente.setVisible(false);
            btnAgregarPaciente.setEnabled(false);
            btnBajaPac.setVisible(true);
            btnBajaPac.setEnabled(true);
            lbl_nomBtn.setText("Eliminar Paciente");
            lbl1.setText("Bajas");
            tf_SSN1.setBounds(75,60,200,30);
            fondo.setBackground(Color.red);
            Altas_Pacientes.setBackground( new Color(255, 175, 0, 255));
            tf_Edad.setVisible(true);
            tf_Col.setVisible(true);
            tf_Call.setVisible(true);
            btn_Busc.setVisible(true);
            btn_Busc.setEnabled(true);
            Altas_Pacientes.setTitle("Bajas Pacientes");

            comboEdad2.setVisible(false);
            comboEdad2.setEnabled(false);
            comboCalles.setVisible(false);
            comboCalles.setEnabled(false);
            comboColonias1.setEnabled(false);
            comboColonias1.setVisible(false);
            lbl1.setBounds(290, 0, 685, 40);
            tf_Nombre1.setEnabled(false);
            tf_am1.setEnabled(false);
            tf_ap1.setEnabled(false);
            tf_SSN1.setEnabled(true);
            tf_Edad.setEnabled(false);
            tf_Call.setEnabled(false);
            tf_Col.setEnabled(false);
            metodoDeshabilitar(btn_Busc);
            if(comboCalles.getItemCount()>0) {
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboColonias1, comboCalles, comboEdad2);
                comboCalles.setEnabled(false);
                comboCalles.removeAllItems();
            } else if (tf_Edad.isVisible()) {
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, tf_Edad, tf_Col, tf_Call);
            } else{
                metodoRestablecer(tf_SSN1, tf_am1, tf_Nombre1, tf_ap1, comboEdad2);
            }
        }// item bajas
        else if (c==itemCambios) {
            Altas_Pacientes.setTitle("Actualizar Paciente");
            btn_ActusliarPac= new JButton(new ImageIcon("./assets/modificar.png"));
            btn_ActusliarPac.addActionListener(this);
            btn_ActusliarPac.setBounds(495,320,60,50);

            Altas_Pacientes.add(btn_ActusliarPac);
            Altas_Pacientes.setVisible(true);
            btnAgregarPaciente.setVisible(false);
            btnAgregarPaciente.setEnabled(false);
            btnBajaPac.setEnabled(false);
            btnBajaPac.setVisible(false);
            lbl_nomBtn.setText("Actualizar");
            lbl1.setText("Cambios Paciente");
            tf_SSN1.setBounds(75,60,200,30);
            fondo.setBackground(new Color(120, 2, 141, 255));
            Altas_Pacientes.setBackground(new Color(228, 164, 241, 255));
            tf_Edad.setVisible(false);
            tf_Edad.setEnabled(false);
            tf_Call.setVisible(false);
            tf_Call.setEnabled(false);
            tf_Col.setVisible(false);
            tf_Col.setEnabled(false);
            comboEdad2.setVisible(true);
            metodoRestablecer(tf_SSN1);
            tf_SSN1.setEnabled(true);
            comboColonias1.setVisible(true);
            comboCalles.setVisible(true);
            metodoDeshabilitar(tf_am1,tf_Nombre1,comboEdad2,tf_ap1,comboColonias1,comboCalles);
            lbl1.setBounds(210, 0, 685, 40);
            btn_Busc.setEnabled(false);
            btn_Busc.setVisible(false);
            btn_Busc_Cambios.setVisible(true);
            btn_Busc_Cambios.setEnabled(true);
            metodoDeshabilitar(btn_ActusliarPac,btn_Busc_Cambios);
        }//item cambio
        else if (c==btn_Busc_Cambios) {
            comboEdad2.setEnabled(true);
            comboColonias1.setEnabled(true);
            tf_SSN1.setEnabled(false);
          //  comboCalles.setEnabled(true);
            tf_Nombre1.setEnabled(true);
            tf_ap1.setEnabled(true);
            tf_am1.setEnabled(true);
            btn_Busc.setEnabled(true);
            btn_ActusliarPac.setEnabled(true);
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
    public void metodoDeshabilitar(Component...componentes){
        for(Component x: componentes){
            if(x instanceof JTextField){
                ((JTextField)x).setEnabled(false);
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
                ((JTextField)x).setEnabled(true);
            }else if(x instanceof JComboBox<?>){
                ((JComboBox)x).setEnabled(true);
            } else if (x instanceof JButton) {
                ((JButton)x).setEnabled(false);
            }
        }//foreach

    }

    @Override
    public void keyTyped(KeyEvent e) {
        Component c=(Component) e.getSource();
        char caracter=e.getKeyChar();
      if(c==tf_SSN1){

          if(!(caracter>48&&caracter<58)) {
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
