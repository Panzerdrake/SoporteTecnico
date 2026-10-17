package vista;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datos.TicketDAO;
import modelo.Ticket;
import modelo.Usuario;

public class ConsultarTicketsFrame extends JFrame {

    private Usuario usuario;
    private DefaultTableModel modeloTabla;

    public ConsultarTicketsFrame(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Mis tickets");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(
                new Object[] {
                        "ID",
                        "Asunto",
                        "Producto",
                        "Estado",
                        "Fecha"
                },
                0
        );

        JTable tabla = new JTable(modeloTabla);
        JButton botonCerrar = new JButton("Cerrar");

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botonCerrar, BorderLayout.SOUTH);

        botonCerrar.addActionListener(e -> dispose());

        cargarTickets();
    }

    private void cargarTickets() {

        TicketDAO ticketDAO = new TicketDAO();

        List<Ticket> tickets =
                ticketDAO.obtenerTicketsCliente(usuario.getIdUsuario());

        for (Ticket ticket : tickets) {

            modeloTabla.addRow(new Object[] {
                    ticket.getIdTicket(),
                    ticket.getAsunto(),
                    ticket.getProducto(),
                    ticket.getEstado(),
                    ticket.getFechaCreacion()
            });
        }
    }
}