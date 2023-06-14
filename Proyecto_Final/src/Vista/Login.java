package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import controlador.UsuarioDAO;
import modelo.Usuario;
import java.sql.Connection;
import ConexionBD.ConexionBD;
public class Login extends JFrame  {

    JLabel lblUsuario, lblContraseña;
    JTextField jtfUsuario;
    JPasswordField jpfContraseña;
    JButton btnIngresar;
    BufferedImage imagen;
    JLabel imagen1;
    JComboBox <String> cmbTipo;
    Color colorBoton = new Color(5, 27, 248);
    Color grisClaro = new Color(212,212,212);
    UsuarioDAO uDAO = new UsuarioDAO();
    boolean mostrarUsuarios = false;
    public static boolean bandera;

    public Login() throws IOException {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,470);
        getContentPane().setBackground(new Color(115,115,115));
        setLocationRelativeTo(null);
        setTitle("Ingresar");
        setVisible(true);

        imagen = ImageIO.read(new File("./assets/login.png"));
        imagen1 = new JLabel(new ImageIcon(imagen));
        imagen1.setBounds(70, 35, 150, 150);
        add(imagen1);

        lblUsuario = new JLabel("Nombre");
        lblUsuario.setBounds(50, 180, 100, 25);
        lblUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        add(lblUsuario);

        jtfUsuario = new JTextField();
        jtfUsuario.setBounds(50, 210, 185, 25);
        add(jtfUsuario);

        lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(50, 250, 100, 25);
        lblContraseña.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        add(lblContraseña);

        jpfContraseña = new JPasswordField();
        jpfContraseña.setBounds(50, 280, 185, 25);
        add(jpfContraseña);



        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(95, 340, 100, 35);
        btnIngresar.setBackground(colorBoton);
        btnIngresar.setForeground(grisClaro);
        btnIngresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        btnIngresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Connection c = ConexionBD.getConexion();
                if (verificar()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if(mostrarUsuarios) {
                                new VentanaInicio();
                            }else {
                                new VentanaInicio();
                            }
                        }
                    });
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario o la contraseña son incorrectos");
                }
            }
        });
        add(btnIngresar);
    }

    public boolean verificar() {
        try {
            ArrayList<Usuario> listaUsuarios = uDAO.buscarUsuario("SELECT * FROM usuarios WHERE nombre = '"+jtfUsuario.getText()+"'");
            uDAO.setFiltro("SELECT * FROM usuarios WHERE nombre = '"+jtfUsuario.getText()+"'");

            Thread hilo = new Thread(uDAO);
            hilo.start();
             if (listaUsuarios.size()!=0 && bandera) {
                     Usuario usuario = listaUsuarios.get(0);
                     return usuario.getContraseña().equals(jpfContraseña.getText());


             }
             else {
                 return false;
             }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return true;
    }
}
