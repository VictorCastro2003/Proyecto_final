package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaInicio extends JFrame implements ActionListener {

    JMenu menuPantallas;
    JMenuBar menuBar;
    JInternalFrame IF_Altas, IF_Bajas, IF_Cambios, IF_Consultas;
    JMenuItem itemAltas, itemBajas, itemCambios, itemConsultas;
    public VentanaInicio(){
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sistema Farmacia");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        //Componentes Graficos
        menuBar =new  JMenuBar();
        menuPantallas = new JMenu();
        menuPantallas.setIcon(new ImageIcon("./assets/opciones.png"));

        itemAltas = new JMenuItem("Agregar");
        itemAltas.setIcon(new ImageIcon("./assets/UserAdd.png"));
        itemAltas.addActionListener(this);
        menuPantallas.add(itemAltas);

        itemBajas = new JMenuItem("Bajas");
        itemBajas.setIcon(new ImageIcon("./assets/UserRemove.png"));
        itemBajas.addActionListener(this);
        menuPantallas.add(itemBajas);

        itemCambios = new JMenuItem("Cambios");
        itemCambios.setIcon(new ImageIcon("./assets/modificar.png"));
        itemCambios.addActionListener(this);
        menuPantallas.add(itemCambios);

        itemConsultas  = new JMenuItem("Consultas");
        itemConsultas.setIcon(new ImageIcon("./assets/consulta.png"));
        itemConsultas.addActionListener(this);

        menuPantallas.add(itemConsultas);
        menuBar.add(menuPantallas);
        setJMenuBar(menuBar);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaInicio();
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
