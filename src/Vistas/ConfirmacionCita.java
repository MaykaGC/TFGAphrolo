package Vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import Controlador.ControladorCita;
import Modelo.Tratamiento;
import Modelo.Usuario;
import org.jdatepicker.impl.*;

public class ConfirmacionCita extends JFrame {

    //Atributos
    private Usuario usuario;
    private JPanel header;
    private JPanel headerIzquierdo;
    private JPanel volver;

    private JPanel panelPrincipal;
    private JSplitPane splitCentral;
    private JPanel acciones;
    private JPanel panelIzquierdoLibre;
    private JPanel panelDerechoControles;
    private ArrayList<Tratamiento> tratamientosTodos;
    private Tratamiento tratamientoElegido;
    private JScrollPane scrollHoras;
    private JPanel panelIconosCita;

    private JPanel footerPanelCita;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;
    private JPanel panelSur;

    public ConfirmacionCita(Usuario u) throws SQLException {

        this.usuario = u;
        tratamientosTodos = (ArrayList<Tratamiento>) ControladorCita.obtenerTratamientos();

        iniciarVentana();
        iniciarHeader();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }


    private void iniciarVentana() {

        setTitle("Área personal");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarHeader() {

        header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 128, 128));
        header.setPreferredSize(new Dimension(700, 80));

        //Panel contener izquierod para título y logo
        headerIzquierdo = new JPanel();
        headerIzquierdo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        headerIzquierdo.setBackground(new Color(0, 128, 128));

        JLabel lblTitulo = new JLabel("Área personal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        ImageIcon icono = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\usuario.png");
        Image iconoEscalado = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icono = new ImageIcon(iconoEscalado);
        JLabel lblIconoPerfil = new JLabel(icono);

        headerIzquierdo.add(lblTitulo);
        headerIzquierdo.add(lblIconoPerfil);

        header.add(headerIzquierdo, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        //PANEL "Volver"
        volver = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 35));
        volver.setBackground(Color.WHITE);

        BotonRedondeado btnVolver = new BotonRedondeado("← Volver");
        btnVolver.setBackground(Color.ORANGE);
        btnVolver.setForeground(new Color(48, 29, 197));

        volver.add(btnVolver);
        add(volver, BorderLayout.WEST);
        btnVolver.addActionListener(e -> {
            new RegristroInicioSesionUsuario();
            this.dispose();
        });
    }


    private void iniciarPanelCentral() {

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        /*---Panel izquierdo con GridBagLayout---*/
        panelIzquierdoLibre = new JPanel(new GridBagLayout());
        panelIzquierdoLibre.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);


        String textoHtml =
                "<html><div style='text-align:center; font-size:10px;'>" +
                        "<h1 style='font-family:Georgia, serif; color:#008080; margin:0;'>" +
                        "Hola, <span style='color:red;'>" + usuario.getNombre() + "</span>" +
                        "</h1><br>" +
                        "Selecciona tu tratamiento y agenda tu cita.<br><br>" +
                        "</div></html>";

        //SwingConstant permite alinear horizontalmente el texto
        JLabel lblIzq = new JLabel(textoHtml, SwingConstants.CENTER);
        gbc.gridy = 0;
        panelIzquierdoLibre.add(lblIzq, gbc);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\agenda.png");
        Image scaledImg = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(scaledImg));
        gbc.gridy = 1;
        panelIzquierdoLibre.add(lblImagen, gbc);

        gbc.gridy = 2;
        acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        acciones.setBackground(panelIzquierdoLibre.getBackground());

        //Botón "Ver citas"
        BotonRedondeado btnMisCitas = new BotonRedondeado("Mis citas");
        btnMisCitas.setPreferredSize(new Dimension(110, 30));
        btnMisCitas.setFont(new Font("Arial", Font.PLAIN, 13));
        btnMisCitas.setBackground(new Color(0, 128, 128));
        btnMisCitas.setForeground(Color.WHITE);
        btnMisCitas.setFocusPainted(false);
        acciones.add(btnMisCitas);
        btnMisCitas.addActionListener(e -> new Vistas.VerCitas(usuario));


        //Botón "Cerrar Sesión"
        BotonRedondeado btnCerrarSesion = new BotonRedondeado("Cerrar sesión");
        btnCerrarSesion.setPreferredSize(new Dimension(110, 30));
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 13));
        btnCerrarSesion.setBackground(new Color(216, 68, 68));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        acciones.add(btnCerrarSesion);
        btnCerrarSesion.addActionListener(e -> {
            new RegristroInicioSesionUsuario();
            this.dispose();
        });

        panelIzquierdoLibre.add(acciones, gbc);

        /*---PANEL DERECHO---*/
        panelDerechoControles = new JPanel();
        panelDerechoControles.setBackground(Color.WHITE);
        panelDerechoControles.setBorder(new EmptyBorder(20, 40, 20, 40));
        panelDerechoControles.setLayout(new BoxLayout(panelDerechoControles, BoxLayout.Y_AXIS));

        panelDerechoControles.add(Box.createVerticalGlue());
        JLabel lblTrat = new JLabel("Selecciona tu tratamiento");
        lblTrat.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(lblTrat);
        panelDerechoControles.add(Box.createVerticalStrut(10));

        //Se recorre la lista tratamientosTodos y se añaden cada uno de sus elementos al combo.
        JComboBox<Tratamiento> comboTratamientos = new JComboBox<>();
        for (Tratamiento t : tratamientosTodos) {
            comboTratamientos.addItem(t);
        }

        comboTratamientos.setMaximumSize(new Dimension(250, 30));
        comboTratamientos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(comboTratamientos);
        panelDerechoControles.add(Box.createVerticalStrut(30));

        JLabel lblFecha = new JLabel("Selecciona fecha");
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(lblFecha);
        panelDerechoControles.add(Box.createVerticalStrut(10));
        //Utilmodel almacena la fecha seleccionada
        UtilDateModel modelo = new UtilDateModel();
        //Se les pasa unas características específicas para cambiar los texto a español
        Properties propiedades = new Properties();
        propiedades.put("text.today", "Hoy");
        propiedades.put("text.month", "Mes");
        propiedades.put("text.year", "Año");
        //Se dibuja el panel con las características proporcionadas
        JDatePanelImpl datePanel = new JDatePanelImpl(modelo, propiedades);
        //Este contiene el calendario creado y un formateador que convierte la fecha en texto (y al contrario)
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setMaximumSize(new Dimension(250, 30));
        datePicker.getJFormattedTextField().setBackground(Color.WHITE);
        datePicker.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(datePicker);
        panelDerechoControles.add(Box.createVerticalStrut(30));

        JLabel lblHora = new JLabel("Selecciona hora");
        lblHora.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(lblHora);
        panelDerechoControles.add(Box.createVerticalStrut(10));

        //Se crea una lista añadiendo las horas establecidas
        DefaultListModel<String> horasCita = new DefaultListModel<>();
        for (String h : new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "15:00", "16:00", "17:00"}) {
            horasCita.addElement(h);
        }
        JList<String> listaHoras = new JList<>(horasCita);
        //Se establecen cinco filas y 120 píxeles
        listaHoras.setVisibleRowCount(5);
        listaHoras.setFixedCellWidth(120);
        listaHoras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Envolvemos la lista en un scroll para que me deje visualizar todas las que están disponibles
        scrollHoras = new JScrollPane(listaHoras);
        scrollHoras.setMaximumSize(new Dimension(140, 120));
        scrollHoras.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoControles.add(scrollHoras);
        panelDerechoControles.add(Box.createVerticalStrut(30));

        BotonRedondeado btnConfirmacion = new BotonRedondeado("Confirmar cita");
        btnConfirmacion.setBackground(Color.orange);
        btnConfirmacion.setForeground(new Color(48, 29, 197));
        btnConfirmacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.tratamientoElegido = (Tratamiento) comboTratamientos.getSelectedItem();
        System.out.println("el id elegido es: " + this.tratamientoElegido.getIdTratamiento());
        btnConfirmacion.addActionListener(e -> {

            //Recoje el tratamiento seleccionado del desplegable
            Tratamiento tratamientoSeleccionado = (Tratamiento) comboTratamientos.getSelectedItem();
            java.util.Date date = (java.util.Date) datePicker.getModel().getValue();
            String horaStr = listaHoras.getSelectedValue();

            if (tratamientoSeleccionado == null || date == null || horaStr == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona tratamiento, fecha y hora.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idTratamiento = tratamientoSeleccionado.getIdTratamiento();
            //Adaptamos la hora a la zona horaria y extrae año, mes y día
            LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //Convertimos la fecha en un objeto
            LocalTime hora = LocalTime.parse(horaStr);

            //Se valida el usuario
            if (usuario == null || usuario.getId_Usuario() == 0) {
                JOptionPane.showMessageDialog(this,
                        "No se ha identificado correctamente el usuario. Intente iniciar sesión de nuevo.",
                        "Error de sesión", JOptionPane.ERROR_MESSAGE);
                //System.err para distinguir mensajes de error
                System.err.println("ERROR: Usuario nulo o sin ID válido.");
                return;
            }

            //Confirmación mostrando un cuando de texto de opción sí/no
            int res = JOptionPane.showConfirmDialog(this,
                    "Confirmar cita para '" + tratamientoSeleccionado + "' el " + fecha + " a las " + hora + "?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            //Si la respuesta es no se sale
            if (res != JOptionPane.YES_OPTION)
                return;

            try {
                System.out.println("ID Usuario usado: " + usuario.getId_Usuario());
                int filas = new ControladorCita().confirmarCita(hora, fecha, idTratamiento, usuario.getId_Usuario());

                if (filas == 1) {
                    JOptionPane.showMessageDialog(this,
                            "Cita confirmada correctamente.",
                            "¡Hecho!", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo guardar la cita.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error al guardar la cita: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelDerechoControles.add(btnConfirmacion);
        panelDerechoControles.add(Box.createVerticalGlue());

        //Añadir SplitPane con ambos paneles
        splitCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                panelIzquierdoLibre, panelDerechoControles);
        splitCentral.setResizeWeight(0.5);
        splitCentral.setContinuousLayout(true);
        splitCentral.setDividerSize(4);
        splitCentral.setBorder(null);
        panelPrincipal.add(splitCentral, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }


    private void iniciarFooter() {

        footerPanelCita = new JPanel(new BorderLayout());
        footerPanelCita.setPreferredSize(new Dimension(600, 120));
        footerPanelCita.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdoFooter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdoFooter.add(new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados"));

        JButton linkUno = new JButton("Política de privacidad");
        JButton linkDos = new JButton("Contacto");
        for (JButton link : new JButton[]{linkUno, linkDos}) {
            link.setBorderPainted(false);
            link.setFocusPainted(false);
            link.setContentAreaFilled(false);
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panelIzquierdoFooter.add(link);
        }
        linkUno.addActionListener(e -> new PoliticaPrivacidad());
        linkDos.addActionListener(e -> new Contacto());
        footerPanelCita.add(panelIzquierdoFooter, BorderLayout.WEST);

        panelDerechoFooter = new JPanel();
        panelDerechoFooter.setLayout(new BoxLayout(panelDerechoFooter, BoxLayout.Y_AXIS));

        JLabel lblSiguenos = new JLabel("SÍGUENOS EN");
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSiguenos.setFont(new Font("Roboto", Font.PLAIN, 12));


        panelIconosCita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelIconosCita.setOpaque(false);
        for (String ico : new String[]{"logotipos.png", "instagram.png", "facebook.png"}) {
            ImageIcon img = new ImageIcon("src/Imagenes/" + ico);
            panelIconosCita.add(new JLabel(
                    new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        }
        panelDerechoFooter.add(lblSiguenos);
        panelDerechoFooter.add(panelIconosCita);
        footerPanelCita.add(panelDerechoFooter, BorderLayout.EAST);

        panelSur = new JPanel(new BorderLayout());
        panelSur.setBackground(Color.WHITE);
        panelSur.add(footerPanelCita, BorderLayout.NORTH);
        add(panelSur, BorderLayout.SOUTH);

    }

    class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        @Override
        public Object stringToValue(String texto) throws ParseException {
            return java.sql.Date.valueOf(texto);
        }

        //Se llama para mostrar la fecha en el campo de texto
        //Nos permite dar consistencia e integración a la hora de escribir la fecha
        //Puente entre la fecha vista por el usuario y el objeto interno
        @Override
        public String valueToString(Object valor) {
            if (valor != null) {
                Calendar cal = (Calendar) valor;
                return String.format("%04d-%02d-%02d",
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
            }
            return "";
        }
    }

}

