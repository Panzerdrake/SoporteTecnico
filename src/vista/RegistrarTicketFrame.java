package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datos.TicketDAO;
import modelo.Producto;
import modelo.Usuario;

public class RegistrarTicketFrame extends JFrame {

    private Usuario usuario;
    private JTextField campoAsunto;
    private JTextArea campoDescripcion;
    private JComboBox<Producto> comboProducto;

    public RegistrarTicketFrame(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Registrar ticket");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        campoAsunto = new JTextField();
        campoDescripcion = new JTextArea(6, 20);
        campoDescripcion.setLineWrap(true);
        campoDescripcion.setWrapStyleWord(true);

        comboProducto = new JComboBox<>();

        JButton botonRegistrar = new JButton("Registrar ticket");
        JButton botonCancelar = new JButton("Cancelar");

        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 10));

        formulario.add(new JLabel("Asunto:"));
        formulario.add(campoAsunto);

        formulario.add(new JLabel("Producto:"));
        formulario.add(comboProducto);

        formulario.add(new JLabel("Descripción:"));
        formulario.add(new JScrollPane(campoDescripcion));

        JPanel botones = new JPanel();
        botones.add(botonRegistrar);
        botones.add(botonCancelar);

        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        cargarProductos();

        botonRegistrar.addActionListener(e -> registrarTicket());
        botonCancelar.addActionListener(e -> dispose());
    }

    private void cargarProductos() {

        TicketDAO ticketDAO = new TicketDAO();
        List<Producto> productos = ticketDAO.obtenerProductos();

        for (Producto producto : productos) {
            comboProducto.addItem(producto);
        }
    }

    private void registrarTicket() {

        String asunto = campoAsunto.getText().trim();
        String descripcion = campoDescripcion.getText().trim();
        Producto producto = (Producto) comboProducto.getSelectedItem();

        if (asunto.isEmpty() || descripcion.isEmpty() || producto == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        TicketDAO ticketDAO = new TicketDAO();

        boolean registrado = ticketDAO.registrarTicket(
                asunto,
                descripcion,
                usuario.getIdUsuario(),
                producto.getIdProducto()
        );

        if (registrado) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ticket fue registrado correctamente."
            );

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el ticket.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}