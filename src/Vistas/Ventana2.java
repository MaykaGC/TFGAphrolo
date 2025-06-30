package Vistas;

import javax.swing.*;
import java.awt.*;

public class Ventana2 extends JFrame {

    //Atributos
    private JPanel headerVentana2;
    private JButton btnHeaderVentana2;
    private JPanel volver;
    private JLabel tituloHeader;
    private JPanel rellenoEste;
    private JPanel panelContenidoCentral;
    private JPanel wrapperCentral;

    private JPanel footerPanel2;
    private JPanel panelIzquierdo;
    private JButton btnLinkUno;
    private JButton btnLinkDos;
    private JPanel panelDerecho;
    private JPanel panelIconos2;
    private JPanel textoDesplazandose2;
    private JPanel zonaDelMarquee;

    public Ventana2() {

        iniciarVentana();
        iniciarHeader();
        iniciarPanelVolver();
        panelRellenoEast();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }

    public void iniciarVentana(){

        setTitle("Categorías de tratamientos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void iniciarHeader(){

        headerVentana2 = new JPanel(new BorderLayout());
        headerVentana2.setBackground(new Color(0, 128, 128));
        headerVentana2.setPreferredSize(new Dimension(600, 100));

        btnHeaderVentana2 = new JButton("ADMINISTRADOR");
        btnHeaderVentana2.setBackground(new Color(102, 205, 170));
        btnHeaderVentana2.setForeground(Color.WHITE);
        btnHeaderVentana2.setFont(new Font("Georgia", Font.BOLD, 14));
        btnHeaderVentana2.addActionListener(e -> new Vistas.VentanaAdmin());
        headerVentana2.add(btnHeaderVentana2, BorderLayout.WEST);

        tituloHeader = new JLabel("APRHOLO, categorías de tratamientos disponibles", SwingConstants.CENTER);
        tituloHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        tituloHeader.setForeground(Color.WHITE);
        headerVentana2.add(tituloHeader, BorderLayout.CENTER);
        add(headerVentana2, BorderLayout.NORTH);
    }

    public void iniciarPanelVolver(){

        volver = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 35));
        volver.setBackground(Color.white);
        BotonRedondeado btnVolver = new BotonRedondeado("← Volver");
        btnVolver.setBackground(Color.orange);
        btnVolver.setForeground(new Color(48, 29, 197));
        volver.add(btnVolver);
        add(volver, BorderLayout.WEST);
        btnVolver.addActionListener(e -> {
            new Vistas.Ventana1();
            this.dispose();
        });
    }

    private void panelRellenoEast(){

        rellenoEste = new JPanel();
        rellenoEste.setPreferredSize(new Dimension(volver.getPreferredSize().width, 100));
        rellenoEste.setOpaque(true);
        rellenoEste.setBackground(Color.white);
        add(rellenoEste, BorderLayout.EAST);
    }

    public void iniciarPanelCentral(){

        /*---PANEL CENTRAL CON BOTONES---*/
        panelContenidoCentral = new JPanel(new GridBagLayout());
        panelContenidoCentral.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(40, 90, 40, 90);
        gbc.fill = GridBagConstraints.NONE;

        String[] nombres = {"FACIAL", "CORPORAL", "CAPILAR", "DEPILACIÓN"};
        String[] rutasImagenes = {
                "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\botox (1).png",
                "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\contorno-corporal.png",
                "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\perdida-de-cabello.png",
                "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\depilacion.png"
        };

        for (int i = 0; i < nombres.length; i++) {
            JPanel panelBotonConImagen = new JPanel();
            panelBotonConImagen.setLayout(new BoxLayout(panelBotonConImagen, BoxLayout.Y_AXIS));
            panelBotonConImagen.setBackground(Color.WHITE);

            ImageIcon imagenOriginal = new ImageIcon(rutasImagenes[i]);
            Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            ImageIcon icono = new ImageIcon(imagenEscalada);

            JLabel lblImagen = new JLabel(icono);
            lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

            BotonRedondeado btnCategorias = new BotonRedondeado(nombres[i]);
            btnCategorias.setFont(new Font("Georgia", Font.PLAIN, 16));
            btnCategorias.setBackground(new Color(0, 128, 128));
            btnCategorias.setForeground(Color.white);
            btnCategorias.setFocusPainted(false);
            btnCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCategorias.setPreferredSize(new Dimension(150, 40));
            btnCategorias.setMaximumSize(new Dimension(150, 40));
            btnCategorias.setMinimumSize(new Dimension(150, 40));

            if (i == 0) {
                btnCategorias.addActionListener(e -> {
                    new Vistas.Ventana3();
                    this.dispose();
                });
            }

            panelBotonConImagen.add(lblImagen);
            panelBotonConImagen.add(Box.createVerticalStrut(10));
            panelBotonConImagen.add(btnCategorias);

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;

            panelContenidoCentral.add(panelBotonConImagen, gbc);
        }

        wrapperCentral = new JPanel(new GridBagLayout());
        wrapperCentral.setBackground(Color.WHITE);
        wrapperCentral.add(panelContenidoCentral);
        add(wrapperCentral, BorderLayout.CENTER);
    }


    private void iniciarFooter(){

        footerPanel2 = new JPanel(new BorderLayout());
        footerPanel2.setPreferredSize(new Dimension(600, 120));
        footerPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdo.setOpaque(false);

        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblTextoAlLado);

        btnLinkUno = new JButton("Política de privacidad ");
        btnLinkDos = new JButton("Contacto");
        for (JButton link : new JButton[]{btnLinkUno, btnLinkDos}) {
            link.setBorderPainted(false);
            link.setFocusPainted(false);
            link.setContentAreaFilled(false);
            link.setForeground(Color.BLACK);
            link.setFont(new Font("Arial", Font.BOLD, 14));
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        btnLinkUno.addActionListener(e -> new PoliticaPrivacidad());
        btnLinkDos.addActionListener(e -> new Contacto());

        panelIzquierdo.add(btnLinkUno);
        panelIzquierdo.add(btnLinkDos);

        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setOpaque(false);

        JLabel lblSiguenos = new JLabel("SÍGUENOS EN");
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblSiguenos);

        panelIconos2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        ImageIcon imgUno = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\logotipos.png");
        ImageIcon imgDos = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\instagram.png");
        ImageIcon imgTres = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\facebook.png");

        panelIconos2.add(new JLabel(new ImageIcon(imgUno.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos2.add(new JLabel(new ImageIcon(imgDos.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos2.add(new JLabel(new ImageIcon(imgTres.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));

        panelDerecho.add(panelIconos2);

        footerPanel2.add(panelIzquierdo, BorderLayout.WEST);
        footerPanel2.add(panelDerecho, BorderLayout.EAST);

        textoDesplazandose2 = new JPanel();
        textoDesplazandose2.setLayout(new BoxLayout(textoDesplazandose2, BoxLayout.Y_AXIS));
        textoDesplazandose2.add(footerPanel2);
        textoDesplazandose2.add(panelMarquee2("Regístrate y obtén una cita. ¡Cambia tu vida con un click!", 200));

        add(textoDesplazandose2, BorderLayout.SOUTH);
    }

    private JPanel panelMarquee2(String texto, int espacioTiempo) {

        zonaDelMarquee = new JPanel(new BorderLayout());
        zonaDelMarquee.setPreferredSize(new Dimension(600, 30));
        zonaDelMarquee.setOpaque(true);
        zonaDelMarquee.setBackground(Color.WHITE);

        JLabel lblMarquee = new JLabel(texto);
        lblMarquee.setFont(new Font("Georgia", Font.BOLD, 12));
        lblMarquee.setHorizontalAlignment(SwingConstants.LEFT);
        zonaDelMarquee.add(lblMarquee, BorderLayout.CENTER);

        new Timer(espacioTiempo, e -> {
            String t = lblMarquee.getText();
            lblMarquee.setText(t.substring(1) + t.charAt(0));
        }).start();

        return zonaDelMarquee;
    }

    public static void main(String[] args) {

    }
}
