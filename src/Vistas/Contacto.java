package Vistas;

import Controlador.ControladorContacto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Contacto extends JFrame {

    //Atributos
    private JPanel headerVentana1;
    private JButton btnHeader;
    private JPanel panelDerechoCerrarPol;
    private JLabel tituloHeader;

    private JPanel panelCentral;
    private JPanel panelContenido;
    private JPanel panelIzquierdoCentral;
    private JPanel panelFormulario;
    private JScrollPane scrollMensaje;

    private JPanel footerPanel;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;

    public Contacto(){

        iniciarVentana();
        iniciarHeader();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }

    private void iniciarVentana(){

        setTitle("DATOS DE CONTACTO");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarHeader(){

        headerVentana1 = new JPanel(new BorderLayout());
        headerVentana1.setBackground(new Color(0, 128, 128));
        headerVentana1.setPreferredSize(new Dimension(600, 100));


        btnHeader = new JButton("ADMINISTRADOR");
        btnHeader.setBackground(new Color(102, 205, 170));
        btnHeader.setForeground(Color.WHITE);
        btnHeader.setFont(new Font("Georgia", Font.BOLD, 14));
        btnHeader.addActionListener(e -> new Vistas.VentanaAdmin());
        headerVentana1.add(btnHeader, BorderLayout.WEST);

        tituloHeader = new JLabel("APRHOLO, página de contacto", SwingConstants.CENTER);
        tituloHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        tituloHeader.setForeground(Color.WHITE);

        headerVentana1.add(tituloHeader, BorderLayout.CENTER);
        add(headerVentana1, BorderLayout.NORTH);


        panelDerechoCerrarPol= new JPanel(new FlowLayout(FlowLayout.CENTER,30,35));
        panelDerechoCerrarPol.setOpaque(false);

        BotonRedondeado btnCerrado = new BotonRedondeado("Cerrar");
        btnCerrado.setBackground(new Color(216,68,68));
        btnCerrado.setForeground(Color.white);
        btnCerrado.setPreferredSize(new Dimension(100, 35));
        panelDerechoCerrarPol.add(btnCerrado);
        headerVentana1.add(panelDerechoCerrarPol, BorderLayout.EAST);
        //Cerramos la ventana actual al hacer click en el botón
        btnCerrado.addActionListener(e -> {dispose();});
    }


    private void iniciarPanelCentral(){

        panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        add(panelCentral, BorderLayout.CENTER);

        panelContenido = new JPanel(new GridLayout(1, 2, 40, 0));
        panelContenido.setOpaque(false);
        panelContenido.setPreferredSize(new Dimension(800, 350));

        //Panel izquierdo
        panelIzquierdoCentral = new JPanel();
        panelIzquierdoCentral.setLayout(new BoxLayout(panelIzquierdoCentral, BoxLayout.Y_AXIS));
        panelIzquierdoCentral.setOpaque(false);

        JLabel lblTitulo = new JLabel("Contáctanos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));

        ImageIcon iconoTelefono = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\llamar.png");
        ImageIcon telfonoEscalado = new ImageIcon(iconoTelefono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JLabel lblTelefono = new JLabel("Tel: 678 123 521", telfonoEscalado, JLabel.LEFT);
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
        //Espacio entre icono y texto
        lblTelefono.setIconTextGap(10);
        //Se alinena los elementos horizontalmente dentro del boxlayout
        lblTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);


        ImageIcon iconoMail = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\email.png");
        ImageIcon emailEscalado = new ImageIcon(iconoMail.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JLabel lblEmail = new JLabel("Email: m&ccreator@gmail.com", emailEscalado, JLabel.LEFT);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        lblEmail.setIconTextGap(10);
        lblEmail.setAlignmentX(Component.LEFT_ALIGNMENT);


        ImageIcon iconoDireccion = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\ubicacion.png");
        ImageIcon direccionEscalado = new ImageIcon(iconoDireccion.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JLabel lblDireccion= new JLabel("Dirección: Calle la Bodega, Córdoba (14045)", direccionEscalado, JLabel.LEFT);
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 14));
        lblDireccion.setIconTextGap(10);
        lblDireccion.setAlignmentX(Component.LEFT_ALIGNMENT);


        panelIzquierdoCentral.add(lblTitulo);
        panelIzquierdoCentral.add(Box.createVerticalStrut(40));
        panelIzquierdoCentral.add(lblTelefono);
        panelIzquierdoCentral.add(Box.createVerticalStrut(20));
        panelIzquierdoCentral.add(lblEmail);
        panelIzquierdoCentral.add(Box.createVerticalStrut(20));
        panelIzquierdoCentral.add(lblDireccion);


        //Panel derecho
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setOpaque(false);

        Font lblFuente = new Font("Arial", Font.PLAIN, 13);
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(lblFuente);
        JTextField campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(800, 30));

        JLabel lblCorreoForm = new JLabel("Correo electrónico");
        lblCorreoForm.setFont(lblFuente);
        JTextField campoCorreo = new JTextField();
        campoCorreo.setPreferredSize(new Dimension(800, 30));

        JLabel lblMensaje = new JLabel("Mensaje*");
        lblMensaje.setFont(lblFuente);
        JTextArea campoMensaje = new JTextArea(6, 20);
        campoMensaje.setLineWrap(true);
        campoMensaje.setWrapStyleWord(true);
        scrollMensaje = new JScrollPane(campoMensaje);
        scrollMensaje.setPreferredSize(new Dimension(850, 160));
        scrollMensaje.setMaximumSize(new Dimension(850, 160));

        BotonRedondeado btnEnviar = new BotonRedondeado("Enviar");
        btnEnviar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEnviar.setBackground(Color.orange);
        btnEnviar.setForeground(new Color(48,29,147));
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre = campoNombre.getText().trim();
                String email = campoCorreo.getText().trim();
                String contenido = campoMensaje.getText().trim();

                if (nombre.isEmpty() || email.isEmpty() || contenido.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, completa todos los campos.",
                            "Campos incompletos",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int resultado;
                try {
                    resultado = ControladorContacto.insertarMensaje(nombre, email, contenido);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Mensaje enviado correctamente.\nGracias por contactarnos, " + nombre + ".",
                            "Mensaje Enviado",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    //Se limpian los campos
                    campoNombre.setText("");
                    campoCorreo.setText("");
                    campoMensaje.setText("");

                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al enviar el mensaje. Inténtalo de nuevo más tarde.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        panelFormulario.add(lblNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(Box.createVerticalStrut(15));
        panelFormulario.add(lblCorreoForm);
        panelFormulario.add(campoCorreo);
        panelFormulario.add(Box.createVerticalStrut(15));
        panelFormulario.add(lblMensaje);
        panelFormulario.add(scrollMensaje);
        panelFormulario.add(Box.createVerticalStrut(20));
        panelFormulario.add(btnEnviar);

        panelContenido.add(panelIzquierdoCentral);
        panelContenido.add(panelFormulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(panelContenido, gbc);
    }


    private void iniciarFooter(){

        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //PANEL IZQUIERDO
        panelIzquierdoFooter = new JPanel();
        panelIzquierdoFooter.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        panelIzquierdoFooter.setOpaque(false);

        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdoFooter.add(lblTextoAlLado);

        //PANEL DERECHO
        panelDerechoFooter = new JPanel();
        panelDerechoFooter.setLayout(new BoxLayout(panelDerechoFooter, BoxLayout.Y_AXIS));
        panelDerechoFooter.setPreferredSize(new Dimension(100, 0));
        panelDerechoFooter.setOpaque(false);
        panelDerechoFooter.setOpaque(false);

        JLabel lblSiguenos = new JLabel("SÍGUENOS EN");
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoFooter.add(lblSiguenos);

        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        ImageIcon imgUno = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\logotipos.png");
        ImageIcon imgDos = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\instagram.png");
        ImageIcon imgTres = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\facebook.png");
        panelIconos.add(new JLabel(new ImageIcon(imgUno.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgDos.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgTres.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelDerechoFooter.add(panelIconos);

        footerPanel.add(panelIzquierdoFooter, BorderLayout.WEST);
        footerPanel.add(panelDerechoFooter, BorderLayout.EAST);

        textoDesplazandose = new JPanel();
        textoDesplazandose.setLayout(new BoxLayout(textoDesplazandose, BoxLayout.Y_AXIS));
        textoDesplazandose.add(footerPanel);
        textoDesplazandose.add(panelMarquee(" Regístrate y obtén una cita. ¡Cambia tu vida con un click!", 200));
        add(textoDesplazandose, BorderLayout.SOUTH);
    }

    private JPanel panelMarquee(String texto, int espacioTiempo) {

        JPanel zonaDelMarquee = new JPanel(new BorderLayout());
        zonaDelMarquee.setPreferredSize(new Dimension(600, 30));

        zonaDelMarquee.setOpaque(true);
        zonaDelMarquee.setBackground(Color.WHITE);

        JLabel marqueeLabel = new JLabel(texto);
        marqueeLabel.setFont(new Font("Georgia", Font.BOLD, 12));
        marqueeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        zonaDelMarquee.add(marqueeLabel, BorderLayout.CENTER);

        new Timer(espacioTiempo, e -> {
            String t = marqueeLabel.getText();
            marqueeLabel.setText(t.substring(1) + t.charAt(0));
        }).start();

        return zonaDelMarquee;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Contacto::new);
    }

}




