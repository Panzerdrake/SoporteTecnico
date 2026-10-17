package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Usuario;

public class MenuClienteFrame extends JFrame {

    private Usuario usuario;

    public MenuClienteFrame(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Córdoba Microelectrónica - Soporte Técnico");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel bienvenida = new JLabel(
                "Bienvenido, " + usuario.getNombre(),
                JLabel.CENTER
        );

        JButton botonRegistrar = new JButton("Registrar ticket");
        JButton botonConsultar = new JButton("Consultar mis tickets");
        JButton botonSalir = new JButton("Salir");

        JPanel opciones = new JPanel(new GridLayout(3, 1, 10, 10));

        opciones.add(botonRegistrar);
        opciones.add(botonConsultar);
        opciones.add(botonSalir);

        add(bienvenida, BorderLayout.NORTH);
        add(opciones, BorderLayout.CENTER);

        botonSalir.addActionListener(e -> System.exit(0));
        
        botonRegistrar.addActionListener(e -> {
            RegistrarTicketFrame ventana = new RegistrarTicketFrame(usuario);
            ventana.setVisible(true);
        });
        
        botonConsultar.addActionListener(e -> {
            ConsultarTicketsFrame ventana =
                    new ConsultarTicketsFrame(usuario);

            ventana.setVisible(true);
        });
        
    }
}