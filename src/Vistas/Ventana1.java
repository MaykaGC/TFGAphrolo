package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ventana1 extends JFrame {

    //Atributos
    private JPanel headerVentana1;
    private JButton btnHeader;
    private JPanel panelHeaderDerecha;
    private JLabel tituloHeader;
    private ImageIcon iconoEscritorio;
    private Image imagenConEscala;

    private JPanel panelCentral;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;
    private ImageIcon[] imagenesCarrusel;
    private JLabel imagenesCarruselLabel;
    private JPanel panelDePosicionCentrada;
    private JPanel contenedorBoton;

    private JPanel footerPanel;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;
    private int indiceActual = 0;
    private Timer timer;
    private JPanel zonaDelMarquee;


    //CONSTRUCTOR
    public Ventana1() {

        iniciarVentana();
        iniciarHeader();
        iniciarPanelCentral();
        iniciarFooter();
        //Permite que t0do el contenido de la ventana sea visible
        setVisible(true);
    }

    //Mét0dos
    private void iniciarVentana() {

        //Configuración general de la pantalla
        setTitle("APHROLO (página de inicio)");
        setSize(600, 500);
        //EXIT_ON_CLOSE para cerrar la aplicación completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centra la pantalla al inicializarla
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //Esta característica me permite que la pantalla arranque maximizada
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarHeader() {

        /*  -----PRIMER PANEL---- HEADER-----*/
        headerVentana1 = new JPanel(new BorderLayout());
        headerVentana1.setBackground(new Color(0, 128, 128));
        headerVentana1.setPreferredSize(new Dimension(600, 100));

        //1º Elemento del HEADER  --> BOTÓN de administrador
        btnHeader = new JButton("ADMINISTRADOR");
        btnHeader.setBackground(new Color(102, 205, 170));
        btnHeader.setForeground(Color.WHITE);
        btnHeader.setFont(new Font("Georgia", Font.BOLD, 14));
        //Añadimos el evento para que nos dirija a la página de adminstrador
        btnHeader.addActionListener(e -> new Vistas.VentanaAdmin());

        //2º Elementos del HEADER --> TÍTULO de la página
        panelHeaderDerecha = new JPanel();
        panelHeaderDerecha.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelHeaderDerecha.setBackground(new Color(0, 128, 128));

        tituloHeader = new JLabel("APRHOLO, la app del bienestar", SwingConstants.CENTER);
        tituloHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        tituloHeader.setForeground(Color.WHITE);

        //Subelemento imagen
        iconoEscritorio = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\aplicacion-de-software.png");
        //Damos el tamaño deseado a la imagen
        imagenConEscala = iconoEscritorio.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenConEscala);
        JLabel lblIconoAppEscritorio = new JLabel(iconoRedimensionado);

        //Añadimos los dos elementos a su panel correspondiente para situarlos a la derecha en el header
        panelHeaderDerecha.add(tituloHeader);
        panelHeaderDerecha.add(lblIconoAppEscritorio);

        //Añadimos al contenedero principal los paneles contenidos dentro de el
        headerVentana1.add(panelHeaderDerecha, BorderLayout.EAST);
        headerVentana1.add(btnHeader, BorderLayout.WEST);
        //Añadimos el panel completo con los subpaneles en el panel padre
        add(headerVentana1, BorderLayout.NORTH);
    }

    public void iniciarPanelCentral() {

        panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);

        //Carrusel de imágenes
        imagenesCarrusel = new ImageIcon[]{
                new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\A-removebg-preview (4).png"),
                new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Vistas\\img_1.png"),
                new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Vistas\\img.png"),
                new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Vistas\\img_2.png")
        };

        imagenesCarruselLabel = new JLabel();
        imagenesCarruselLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenesCarruselLabel.setVerticalAlignment(SwingConstants.CENTER);
        actualizarImagen();

        //Panel que hace de "caja" para insertar el carrusel
        //Permite darle espacio por arriba y por abajo y se evita que el botón se mueva con el salto de imagen a imagen
        panelDePosicionCentrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelDePosicionCentrada.setBackground(Color.WHITE);
        panelDePosicionCentrada.add(imagenesCarruselLabel);

        //Timer que permite dar un tiempo entre imagen e imagen
        timer = new Timer(3000, (ActionEvent e) -> {
            indiceActual = (indiceActual + 1) % imagenesCarrusel.length;
            actualizarImagen();
        });
        timer.start();

        //Botón que da paso a la Ventana 2
        BotonRedondeado btnAperturaVentana2 = new BotonRedondeado("Categorías de tratamientos");
        btnAperturaVentana2.setBackground(Color.ORANGE);
        btnAperturaVentana2.setForeground(new Color(48, 29, 197));
        btnAperturaVentana2.setPreferredSize(new Dimension(200, 30));
        btnAperturaVentana2.setMaximumSize(new Dimension(200, 30));
        btnAperturaVentana2.setFont(new Font("Arial", Font.BOLD, 12));
        btnAperturaVentana2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAperturaVentana2.addActionListener(e -> {
            new Vistas.Ventana2();
            this.dispose();
        });

        //Footer que fija el botón en la parte inferior
        contenedorBoton = new JPanel();
        contenedorBoton.setBackground(Color.WHITE);
        contenedorBoton.setLayout(new BoxLayout(contenedorBoton, BoxLayout.Y_AXIS));
        //Espacio antes del botón
        contenedorBoton.add(Box.createVerticalStrut(20));
        contenedorBoton.add(btnAperturaVentana2);
        //Espacio después del botón
        contenedorBoton.add(Box.createVerticalStrut(20));

        //Añadir los elementos al panel central
        panelCentral.add(panelDePosicionCentrada, BorderLayout.CENTER);
        panelCentral.add(contenedorBoton, BorderLayout.SOUTH);

        //Añadir al contenedor principal
        add(panelCentral, BorderLayout.CENTER);

    }

    private void iniciarFooter() {

        /* -----TERCER PANEL----*/
        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //PANEL DE LA IZQUIERDA dentro del footerPanel
        panelIzquierdoFooter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        //Texto copyright
        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnLinkUno = new JButton("Política de privacidad");
        JButton btnLinkDos = new JButton("Contacto");
        //Con el array se recorren los botones y se les da el mismo diseño
        for (JButton link : new JButton[]{btnLinkUno, btnLinkDos}) {
            link.setBorderPainted(false);
            //Hacemos que se dibuje el contorno al pulsarlo
            link.setFocusPainted(true);
            //Eliminamos el fondo para darle esa apariencia de link
            link.setContentAreaFilled(false);
            link.setForeground(Color.BLACK);
            link.setFont(new Font("Arial", Font.BOLD, 14));
            //Cambia la forma de flecha a la de mano, similar a lo que hacen los hipervinculos
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        //Se añade la acción que abre la pantalla de Política de privacidad y la de contacto.
        btnLinkUno.addActionListener(e -> new PoliticaPrivacidad());
        btnLinkDos.addActionListener(e -> new Contacto());

        panelIzquierdoFooter.add(lblTextoAlLado);
        panelIzquierdoFooter.add(btnLinkUno);
        panelIzquierdoFooter.add(btnLinkDos);

        //PANEL DE LA DERECHA dentro del footer
        panelDerechoFooter = new JPanel();
        panelDerechoFooter.setLayout(new BoxLayout(panelDerechoFooter, BoxLayout.Y_AXIS));

        //Letras de síguenos
        JLabel lblSiguenos = new JLabel("SÍGUENOS EN");
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSiguenos.setFont(new Font("Roboto", Font.PLAIN, 12));

        //Iconos de redes sociales
        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        ImageIcon imgUno = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\logotipos.png");
        ImageIcon imgDos = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\instagram.png");
        ImageIcon imgTres = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\facebook.png");

        panelIconos.add(new JLabel(new ImageIcon(imgUno.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgDos.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgTres.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));

        panelDerechoFooter.add(lblSiguenos);
        panelDerechoFooter.add(panelIconos);

        footerPanel.add(panelIzquierdoFooter, BorderLayout.WEST);
        footerPanel.add(panelDerechoFooter, BorderLayout.EAST);

        /*---Marquee final---*/
        textoDesplazandose = new JPanel();
        textoDesplazandose.setLayout(new BoxLayout(textoDesplazandose, BoxLayout.Y_AXIS));
        textoDesplazandose.setFont(new Font("Arial", Font.PLAIN, 20));
        textoDesplazandose.add(footerPanel);
        textoDesplazandose.add(panelMarquee("Regístrate y obtén una cita. ¡Cambia tu vida con un click!", 200));
        add(textoDesplazandose, BorderLayout.SOUTH);
    }

    //Mét0do para cambiar imagen usando el array previamente declarado
    private void actualizarImagen() {
        imagenesCarruselLabel.setIcon(imagenesCarrusel[indiceActual]);
    }

    private JPanel panelMarquee(String texto, int espacioTiempo) {

        zonaDelMarquee = new JPanel(new BorderLayout());
        zonaDelMarquee.setPreferredSize(new Dimension(600, 30));

        //Cambio de color del fondo de Marquee
        zonaDelMarquee.setOpaque(true);
        zonaDelMarquee.setBackground(Color.WHITE);

        JLabel lblMarquee = new JLabel(texto);
        lblMarquee.setFont(new Font("Georgia", Font.BOLD, 12));
        lblMarquee.setHorizontalAlignment(SwingConstants.LEFT);
        zonaDelMarquee.add(lblMarquee, BorderLayout.CENTER);

        //Timer que permite desplazar el texto moviendo el primer caracter a la posición final
        new Timer(espacioTiempo, e -> {
            String t = lblMarquee.getText();
            lblMarquee.setText(t.substring(1) + t.charAt(0));
        }).start();

        return zonaDelMarquee;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ventana1::new);
    }
}
