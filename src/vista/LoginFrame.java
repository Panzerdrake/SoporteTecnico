package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import datos.UsuarioDAO;
import modelo.Usuario;

public class LoginFrame extends JFrame {

    private JTextField campoEmail;
    private JPasswordField campoContrasena;
    private JButton botonIngresar;

    public LoginFrame() {

        setTitle("Córdoba Microelectrónica - Soporte Técnico");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel(
                "Sistema de Soporte Técnico",
                JLabel.CENTER
        );

        campoEmail = new JTextField();
        campoContrasena = new JPasswordField();
        botonIngresar = new JButton("Iniciar sesión");

        JPanel formulario = new JPanel(new GridLayout(2, 2, 10, 10));

        formulario.add(new JLabel("Email:"));
        formulario.add(campoEmail);

        formulario.add(new JLabel("Contraseña:"));
        formulario.add(campoContrasena);

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonIngresar);

        add(titulo, BorderLayout.NORTH);
        add(formulario, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        botonIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String email = campoEmail.getText();
        String contrasena = new String(campoContrasena.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.iniciarSesion(email, contrasena);

        if (usuario != null) {

            MenuClienteFrame menu = new MenuClienteFrame(usuario);
            menu.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Email o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}