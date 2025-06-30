package Vistas;

import Controlador.ControladorAdministrador;
import Controlador.ControladorCita;

import Controlador.ControladorContacto;
import Modelo.Cita;

import Modelo.Contacto;
import Modelo.Usuario;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VentanaUsuario extends JFrame {

    //Atributos
    private JPanel menuLateral;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    private JPanel panelUsuarios;
    private JTable tablaUsuarios;
    private JPanel footerUsuarios;
    private JScrollPane scroll;

    private JPanel panelCitas;
    private JTable tablaCitas;
    private DefaultTableModel modeloCitas;

    private JPanel panelMensajes;
    private JTable tablaMensajes;
    private DefaultTableModel modeloMensajes;

    public VentanaUsuario() throws SQLException {

        iniciarVentana();
        panelContenedor();
        setVisible(true);
    }


    private void iniciarVentana() {

        setTitle("Área de administrador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void panelContenedor() throws SQLException {

        //MENÚ lateral
        menuLateral = new JPanel();
        menuLateral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(0, 128, 128));
        menuLateral.setPreferredSize(new Dimension(200, 0));

        //Botón 1 "Ver usuarios"
        BotonRedondeado btnVerUsuarios = new BotonRedondeado("Ver usuarios");
        btnVerUsuarios.setMaximumSize(new Dimension(160, 35));
        btnVerUsuarios.setBackground(Color.orange);
        btnVerUsuarios.setForeground(new Color(48, 29, 197));
        btnVerUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Botón "Ver citas"
        BotonRedondeado btnVerDoctores = new BotonRedondeado("Ver citas");
        btnVerDoctores.setMaximumSize(new Dimension(160, 35));
        btnVerDoctores.setBackground(Color.orange);
        btnVerDoctores.setForeground(new Color(48, 29, 197));
        btnVerDoctores.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Botón 3 "Ver mensajes"
        BotonRedondeado btnVerMensajes = new BotonRedondeado("Ver mensajes");
        btnVerMensajes.setMaximumSize(new Dimension(160, 35));
        btnVerMensajes.setBackground(Color.orange);
        btnVerMensajes.setForeground(new Color(48, 29, 197));
        btnVerMensajes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón 4 "Volver atrás"
        BotonRedondeado btnVolver = new BotonRedondeado("Volver");
        btnVolver.setMaximumSize(new Dimension(100, 35));
        btnVolver.setBackground(new Color(216, 68, 68));
        btnVolver.setForeground(Color.white);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuLateral.add(Box.createRigidArea(new Dimension(0, 20)));
        menuLateral.add(btnVerUsuarios);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 20)));
        menuLateral.add(btnVerDoctores);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 20)));
        menuLateral.add(btnVerMensajes);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 570)));
        menuLateral.add(btnVolver);
        add(menuLateral, BorderLayout.WEST);

        //CARDLAYOUT principal
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.white);

        //PANEL USUARIOS
        panelUsuarios = new JPanel(new BorderLayout());
        panelUsuarios.setBackground(Color.white);

        List<Usuario> usuarios = ControladorAdministrador.obtenerUsuariosNormales();
        String[] columnas = {"Nombre", "Email", "Teléfono", "Dirección", "DNI"};
        DefaultTableModel modeloUsuarios = new DefaultTableModel(columnas, 0);
        for (Usuario usuario : usuarios) {
            modeloUsuarios.addRow(new Object[]{
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getDireccion(),
                    usuario.getDni()
            });
        }
        tablaUsuarios = new JTable(modeloUsuarios);

        {
            JTable tabla = tablaUsuarios;
            Font fuenteEncabezado = new Font("SansSerif", Font.BOLD, 14);
            Font fuenteCeldas = new Font("SansSerif", Font.PLAIN, 13);
            tabla.getTableHeader().setFont(fuenteEncabezado);
            tabla.setFont(fuenteCeldas);
            tabla.setRowHeight(25);
            // Mostrar cuadrícula horizontal y vertical
            tabla.setShowGrid(true);
            tabla.setShowVerticalLines(true);
            tabla.setGridColor(new Color(200, 200, 200));
            tabla.setSelectionBackground(new Color(220, 230, 241));
            tabla.setSelectionForeground(Color.BLACK);
            JTableHeader header = tabla.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    label.setBackground(new Color(60, 63, 65));
                    label.setForeground(Color.WHITE);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    return label;
                }
            });

            DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
            centrado.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
            }
            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            c.setBackground(Color.WHITE);
                        } else {
                            c.setBackground(new Color(245, 245, 245));
                        }
                    }
                    return c;
                }
            });
            tabla.setIntercellSpacing(new Dimension(1, 1));
            tabla.setRowMargin(5);
        }

        scroll = new JScrollPane(tablaUsuarios);
        panelUsuarios.add(scroll, BorderLayout.CENTER);

        //Footer usuarios con PDF
        footerUsuarios = new JPanel(new FlowLayout(FlowLayout.CENTER));
        BotonRedondeado btnExportarPDF = new BotonRedondeado("Exportar usuarios en PDF");
        btnExportarPDF.setPreferredSize(new Dimension(190, 30));
        btnExportarPDF.setBackground(new Color(216, 68, 68));
        btnExportarPDF.setForeground(Color.white);
        btnExportarPDF.addActionListener(e -> exportarUsuariosAPDF(tablaUsuarios));
        footerUsuarios.add(btnExportarPDF);
        panelUsuarios.add(footerUsuarios, BorderLayout.SOUTH);

        contentPanel.add(panelUsuarios, "usuarios");

        //PANEL CITAS
        panelCitas = new JPanel(new BorderLayout());
        panelCitas.setBackground(Color.white);

        //Modelo y tabla
        String[] colsCitas = {"ID Cita", "Fecha", "Hora", "ID Tratamiento", "ID Usuario"};
        modeloCitas = new DefaultTableModel(colsCitas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaCitas = new JTable(modeloCitas);
        tablaCitas.setFillsViewportHeight(true);

        {
            JTable tabla = tablaCitas;
            Font fuenteEncabezado = new Font("SansSerif", Font.BOLD, 14);
            Font fuenteCeldas = new Font("SansSerif", Font.PLAIN, 13);
            tabla.getTableHeader().setFont(fuenteEncabezado);
            tabla.setFont(fuenteCeldas);
            tabla.setRowHeight(25);
            // Mostrar cuadrícula horizontal y vertical
            tabla.setShowGrid(true);
            tabla.setShowVerticalLines(true);
            tabla.setGridColor(new Color(200, 200, 200));
            tabla.setSelectionBackground(new Color(220, 230, 241));
            tabla.setSelectionForeground(Color.BLACK);
            JTableHeader header = tabla.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    label.setBackground(new Color(60, 63, 65));
                    label.setForeground(Color.WHITE);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    return label;
                }
            });

            DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
            centrado.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
            }

            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            c.setBackground(Color.WHITE);
                        } else {
                            c.setBackground(new Color(245, 245, 245));
                        }
                    }
                    return c;
                }
            });
            tabla.setIntercellSpacing(new Dimension(1, 1));
            tabla.setRowMargin(5);
        }

        panelCitas.add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
        contentPanel.add(panelCitas, "citas");

        //PANEL MENSAJES
        panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBackground(Color.white);

        // Definición de columnas para mensajes
        String[] colsMensajes = {"ID", "Nombre", "Email", "Contenido", "Fecha"};
        modeloMensajes = new DefaultTableModel(colsMensajes, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaMensajes = new JTable(modeloMensajes);
        tablaMensajes.setFillsViewportHeight(true);

        {
            JTable tabla = tablaMensajes;
            Font fuenteEncabezado = new Font("SansSerif", Font.BOLD, 14);
            Font fuenteCeldas = new Font("SansSerif", Font.PLAIN, 13);
            tabla.getTableHeader().setFont(fuenteEncabezado);
            tabla.setFont(fuenteCeldas);
            tabla.setRowHeight(25);
            //Mostrar cuadrícula horizontal y vertical
            tabla.setShowGrid(true);
            tabla.setShowVerticalLines(true);
            tabla.setGridColor(new Color(200, 200, 200));
            tabla.setSelectionBackground(new Color(220, 230, 241));
            tabla.setSelectionForeground(Color.BLACK);
            JTableHeader header = tabla.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    label.setBackground(new Color(60, 63, 65));
                    label.setForeground(Color.WHITE);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    return label;
                }
            });

            DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
            centrado.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
            }

            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            c.setBackground(Color.WHITE);
                        } else {
                            c.setBackground(new Color(245, 245, 245));
                        }
                    }
                    return c;
                }
            });
            tabla.setIntercellSpacing(new Dimension(1, 1));
            tabla.setRowMargin(5);
        }

        tablaMensajes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablaMensajes.rowAtPoint(e.getPoint());
                int col = tablaMensajes.columnAtPoint(e.getPoint());
                // índice 3 corresponde a la columna "Contenido"
                if (col == 3 && row != -1) {
                    Object contenido = tablaMensajes.getValueAt(row, col);
                    if (contenido != null) {
                        JTextArea area = new JTextArea(contenido.toString());
                        area.setLineWrap(true);
                        area.setWrapStyleWord(true);
                        area.setEditable(false);
                        area.setFont(tablaMensajes.getFont());
                        JScrollPane scrollArea = new JScrollPane(area);
                        scrollArea.setPreferredSize(new Dimension(400, 300));
                        JOptionPane.showMessageDialog(
                                VentanaUsuario.this,
                                scrollArea,
                                "Contenido completo del mensaje",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });


        panelMensajes.add(new JScrollPane(tablaMensajes), BorderLayout.CENTER);

        JPanel pnlBotonesMensajes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelMensajes.add(pnlBotonesMensajes, BorderLayout.SOUTH);

        contentPanel.add(panelMensajes, "mensajes");

        add(contentPanel, BorderLayout.CENTER);

        // Listeners del menú
        btnVerUsuarios.addActionListener(e -> cardLayout.show(contentPanel, "usuarios"));
        btnVerDoctores.addActionListener(e -> {
            try {
                cargarDatosCitas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            cardLayout.show(contentPanel, "citas");
        });
        btnVerMensajes.addActionListener(e -> {
            try {
                cargarDatosMensajes();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            cardLayout.show(contentPanel, "mensajes");
        });
        btnVolver.addActionListener(e -> {
            new Vistas.VentanaAdmin().setVisible(true);
            this.dispose();
        });

        cardLayout.show(contentPanel, "usuarios");
    }

    private void cargarDatosCitas() throws SQLException {

        modeloCitas.setRowCount(0);
        List<Cita> lista = ControladorCita.obtenerCitas();
        for (Cita c : lista) {

            modeloCitas.addRow(new Object[]{
                    c.getIdCita(),
                    c.getFechaCita(),
                    c.getHoraCita(),
                    c.getId_tratamiento(),
                    c.getId_usuario()
            });
        }
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay citas registradas.",
                    "Sin datos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarDatosMensajes() throws SQLException {
        //Limpia la tabla
        modeloMensajes.setRowCount(0);

        // Llama al métod0 que ya no recibe parámetros
        List<Contacto> listaMensajes = ControladorContacto.obtenerMensajes();

        for (Contacto m : listaMensajes) {
            modeloMensajes.addRow(new Object[]{
                    m.getId_mensaje(),
                    m.getNombre(),
                    m.getEmail(),
                    m.getContenido(),
                    m.getFechaEnvio()
            });
        }

        if (listaMensajes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay mensajes nuevos.",
                    "Sin datos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exportarUsuariosAPDF(JTable tabla) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar PDF");
        chooser.setSelectedFile(new File("usuarios.pdf"));
        int sel = chooser.showSaveDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream(f)) {
                Document doc = new Document();
                PdfWriter.getInstance(doc, fos);
                doc.open();
                doc.add(new Paragraph("Listado Usuarios"));
                PdfPTable tablePdf = new PdfPTable(tabla.getColumnCount());
                for (int i = 0; i < tabla.getColumnCount(); i++)
                    tablePdf.addCell(new PdfPCell(new com.itextpdf.text.Phrase(tabla.getColumnName(i))));
                for (int r = 0; r < tabla.getRowCount(); r++) {
                    for (int c = 0; c < tabla.getColumnCount(); c++) {
                        Object v = tabla.getValueAt(r, c);
                        tablePdf.addCell(v != null ? v.toString() : "");
                    }
                }
                doc.add(tablePdf);
                doc.close();
                JOptionPane.showMessageDialog(this, "PDF guardado en: " + f.getAbsolutePath());
            } catch (DocumentException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
