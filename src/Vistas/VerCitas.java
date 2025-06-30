package Vistas;

import Controlador.ControladorCita;
import Modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;

public class VerCitas extends JFrame {

    //Atributos
    private Usuario usuario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTableHeader headerTabla;
    private JScrollPane scroll;
    private JPanel panelIzquierdo;

    public VerCitas(Usuario u) {

        this.usuario = u;

        iniciarVentana();
        construirVentanaConTabla();
        setVisible(true);
    }


    private void iniciarVentana(){

        setTitle("Citas concertadas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }


    private void construirVentanaConTabla() {

        String[] columnas = { "Fecha", "Hora", "Tipo", "Subtipo" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            // Evitamos que el usuario edite directamente las celdas
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);

        Font fuenteEncabezado = new Font("SansSerif", Font.BOLD, 14);
        Font fuenteCeldas    = new Font("SansSerif", Font.PLAIN, 13);
        tabla.getTableHeader().setFont(fuenteEncabezado);
        tabla.setFont(fuenteCeldas);
        tabla.setRowHeight(25);

        //Mostramos cuadrícula horizontallíneas verticales
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setShowVerticalLines(true);
        tabla.setSelectionBackground(new Color(220, 230, 241));
        tabla.setSelectionForeground(Color.BLACK);

        //Encabezado con fondo oscuro y texto blanco en el centro de la celda
        headerTabla = tabla.getTableHeader();
        headerTabla.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                //Se llama al métod0 padre para renderizar y lo castea a JLabel para poder mostrar el contenido
                JLabel textoRenderizado = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                textoRenderizado.setBackground(new Color(60, 63, 65));
                textoRenderizado.setForeground(Color.WHITE);
                textoRenderizado.setHorizontalAlignment(SwingConstants.CENTER);
                return textoRenderizado;
            }
        });

        //Centramos el texto de todas las celdas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        //Filas alternas de color blanco (striped rows)
        //Color blanco para las pares y gris claro para las impares
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    //Si las filas son pares se asigna el blanco
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245));
                    }
                }
                return c;
            }
        });

        //Espacio entre celdas y margen de filas
        tabla.setIntercellSpacing(new Dimension(1, 1));
        tabla.setRowMargin(5);

        try {
            java.util.List<Object[]> lista = ControladorCita.obtenerCitasUsuario(usuario.getId_Usuario());
            if (lista.isEmpty()) {
                modeloTabla.addRow(new Object[]{ "No hay citas", "No hay citas", "No hay citas", "No hay citas" });
            } else {
                for (Object[] fila : lista) {
                    modeloTabla.addRow(new Object[]{
                            fila[0].toString(),
                            fila[1].toString(),
                            fila[2],
                            fila[3]
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar las citas:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        //Scroll que envuelve la tabla
        scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelIzquierdo.setBackground(new Color(0, 128, 128));

        BotonRedondeado btnVolver = new BotonRedondeado("← Volver");
        btnVolver.setBackground(Color.orange);
        btnVolver.setForeground(new Color(48,29,197));
        panelIzquierdo.add(btnVolver);
        btnVolver.addActionListener(e -> dispose());

        add(panelIzquierdo, BorderLayout.WEST);
    }
}
