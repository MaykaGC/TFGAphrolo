package Vistas;

import javax.swing.*;
import java.awt.*;


public class Ventana3 extends JFrame {

    //Atributos
    private JPanel headerVentana3;
    private JButton btnHeaderVentana3;
    private JLabel tituloHeader3;
    private JPanel panelDerechoGuia;
    private BotonRedondeado btnGuia;

    private JPanel contenedorConMargenes;
    private JPanel panelCentralCatalogo;
    private BotonRedondeado btnVolver;
    private JPanel volver;
    private JScrollPane panelConScroll;
    private JPanel panelIzquierda;
    private JPanel panelCentro;
    private JPanel filaEstrellas;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;

    private JPanel footerPanel;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;
    private JPanel panelRegistroDoctor;


    public Ventana3() {

        iniciarVentana();
        iniciarHeader();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }

    private void iniciarVentana() {

        setTitle("Tratamientos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarHeader() {

        headerVentana3 = new JPanel(new BorderLayout());
        headerVentana3.setBackground(new Color(0, 128, 128));
        headerVentana3.setPreferredSize(new Dimension(600, 100));

        //1º Elemento del HEADER --> Botón administrador
        btnHeaderVentana3 = new JButton("ADMINISTRADOR");
        btnHeaderVentana3.setBackground(new Color(102, 205, 170));
        btnHeaderVentana3.setForeground(Color.WHITE);
        btnHeaderVentana3.setFont(new Font("Georgia", Font.BOLD, 14));
        btnHeaderVentana3.addActionListener(e -> new Vistas.VentanaAdmin());
        //Añadimos el primer elemento al panel y lo situamos en el lugar deseado
        headerVentana3.add(btnHeaderVentana3, BorderLayout.WEST);

        //2º Elemento del HEADER --> TÍTULO de la página
        tituloHeader3 = new JLabel("APRHOLO, tratamientos faciales", SwingConstants.CENTER);
        tituloHeader3.setFont(new Font("Georgia", Font.BOLD, 24));
        tituloHeader3.setForeground(Color.WHITE);
        headerVentana3.add(tituloHeader3, BorderLayout.CENTER);
        add(headerVentana3, BorderLayout.NORTH);

        //3º Elemento del HEADER --> Bóton de acceso a guía
        panelDerechoGuia = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 35));
        panelDerechoGuia.setOpaque(false);

        btnGuia = new BotonRedondeado("Guía de tratamientos");
        btnGuia.setBackground(Color.red);
        btnGuia.setForeground(Color.white);
        btnGuia.setPreferredSize(new Dimension(160, 35));
        panelDerechoGuia.add(btnGuia);
        headerVentana3.add(panelDerechoGuia, BorderLayout.EAST);

        btnGuia.addActionListener(e -> new Vistas.GuiaTratamientos());
    }

    private void iniciarPanelCentral() {

        //Panel con margenes laterales
        contenedorConMargenes = new JPanel(new BorderLayout());
        contenedorConMargenes.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        contenedorConMargenes.setBackground(Color.white);

        //Panel central
        panelCentralCatalogo = new JPanel();
        panelCentralCatalogo.setLayout(new BoxLayout(panelCentralCatalogo, BoxLayout.Y_AXIS));
        panelCentralCatalogo.setBackground(Color.WHITE);

        //Botón para volver hacía atras
        volver = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 35));
        volver.setBackground(Color.white);
        btnVolver = new BotonRedondeado("← Volver");
        btnVolver.setBackground(Color.orange);
        btnVolver.setForeground(new Color(48, 29, 197));
        volver.add(btnVolver);
        panelCentralCatalogo.add(volver);
        btnVolver.addActionListener(e -> {
            new Vistas.Ventana2();
            this.dispose();
        });

        //Array que contiene los diferentes elementos de las diferentes filas del segundo panel
        String[][] grupoDoctores = {
                {"Doctora Mónica Ramírez Lujan- Clínica Love", "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\mujermedico.png",
                        "<html><br>La Dra. Mónica Ramírez es una reconocida cirujana estética con más de 15 años de experiencia, especializada en procedimientos de remodelación facial y corporal.<br> Graduada " +
                                "con honores de la Facultad de Medicina de la Universidad Complutense de Madrid, completó su formación con un máster en Cirugía Plástica y Estética <br> en la Universidad de " +
                                "Barcelona y estancias internacionales en centros de referencia en París y Nueva York.<br>" +
                                "<br><strong>Tratamientos: Bótox, rellenos con ácido hialurónico, láser CO2, Láser Er:YAG y plasma rico en plaquetas.</strong> "},
                {"Doctor David Jurado Martínez- Clínica Guzmán", "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\doctorHombre(1).png",
                        "<html><br>El Dr. David Jurado es un prestigioso médico especialista en Medicina Estética con más de 10 años de trayectoria, licenciado en Medicina y Cirugía por la Universidad<br> de Valencia " +
                                "y con un Máster en Medicina Estética y Antienvejecimiento por la Universidad Autónoma de Madrid. Su enfoque combina procedimientos mínimamente invasivos <br> con tecnologías" +
                                "de última generación: desde tratamientos con toxina botulínica y rellenos dérmicos de ácido hialurónico hasta técnicas avanzadas de rejuvenecimiento láser.<br>" +
                                "<br> <strong>Tratamientos: Bótox, rellenos con ácido hialurónica, Láser Q-Switched para eliminación de tatuajes e IPL.</strong> </html>"},
                {"Doctora MariSol López Mena- Clínica LovingArms", "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\mujermedico.png",
                        "<html><br>La Dra. MariSol López es una especialista en Medicina Estética y Dermatología con más de 12 años de experiencia, graduada en la Universidad de Santiago de Compostela y <br>con formación " +
                                "complementaria en cirugía dermatológica y tratamientos regenerativos en el King’s College London.Su práctica se centra en la revitalización facial y corporal a " +
                                "<br>través de técnicas avanzadas.<br>" +
                                "<br><strong>Tratamientos: peeling médico, remodelación con microcánulas, terapias con láser fraccionado y plasma rico en plaquetas.</strong></html>"},
                {"Doctor José Alberto de la Torre Torres- Clínica Magic ", "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\doctorHombre(1).png",
                        "<html><br>El Dr. José Alberto de la Torre es un experto en Medicina Estética Avanzada con más de 8 años de dedicación al embellecimiento y rejuvenecimiento no quirúrgico. " +
                                "<br>Licenciado en Medicina y Cirugía por la Universidad de Sevilla, completó su formación con un posgrado en Técnicas Injectables y Láser Médico en la Universidad " +
                                "<br>de Milán. Su práctica clínica abarca desde la corrección de arrugas dinámicas y estáticas con toxina botulínica y ácido hialurónico, hasta protocolos de bioestimulación " +
                                "<br>con radiofrecuencia y ultrasonidos focalizados de alta intensidad "}
        };

        //Añadir ítems al panel con datos personalizados
        for (String[] doctor : grupoDoctores) {
            String nombre = doctor[0];
            String rutaImagen = doctor[1];
            String descripcion = doctor[2];
            panelCentralCatalogo.add(crearRegristroCatalogo(nombre, rutaImagen, descripcion));
            //Espacio vertical
            panelCentralCatalogo.add(Box.createVerticalStrut(30));
        }

        //Scroll vertical
        panelConScroll = new JScrollPane(panelCentralCatalogo);
        panelConScroll.setBorder(null);
        panelConScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelConScroll.getVerticalScrollBar().setUnitIncrement(16);

        contenedorConMargenes.add(panelConScroll, BorderLayout.CENTER);
        add(contenedorConMargenes);
    }

    //Mét0do para crear un panel de un ítem del catálogo con imagen, texto y descripción personalizados
    private JPanel crearRegristroCatalogo(String nombre, String rutaIcono, String descripcion) {

        panelRegistroDoctor = new JPanel(new BorderLayout(20, 0));
        panelRegistroDoctor.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelRegistroDoctor.setBackground(Color.WHITE);

        //Panel izquierdo con imagen y botón
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setBackground(Color.WHITE);
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        Image img = imagenEscalada(rutaIcono, 120, 120);
        JLabel lblImagen = new JLabel(new ImageIcon(img));
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        BotonRedondeado botonCita = new BotonRedondeado("Concertar cita");
        botonCita.setBackground(new Color(0, 128, 128));
        botonCita.setForeground(Color.white);
        botonCita.addActionListener(e -> {
            new Vistas.RegristroInicioSesionUsuario();
            this.dispose();
        });
        botonCita.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelIzquierda.add(lblImagen);
        panelIzquierda.add(Box.createVerticalStrut(8));
        panelIzquierda.add(botonCita);

        //Texto descriptivo en la parte central
        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(Color.WHITE);
        String tentoHtml = String.format("<html><b>%s</b><br>%s</html>", nombre, descripcion);
        JLabel lblTexto = new JLabel(tentoHtml);
        lblTexto.setFont(new Font("Arial", Font.PLAIN, 14));

        panelCentro.add(lblTexto, BorderLayout.CENTER);

        //Derecha: estrellas de valoración y botón "Comentar" debajo
        JPanel panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
        panelDerecha.setBackground(Color.WHITE);

        //Fila de estrellas inactivas en gris
        filaEstrellas = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        filaEstrellas.setBackground(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            JLabel estrella = new JLabel("☆");
            estrella.setFont(new Font("Serif", Font.PLAIN, 24));
            estrella.setEnabled(false);
            filaEstrellas.add(estrella);
        }

        //Botón para comentarios
        BotonRedondeado btnComentar = new BotonRedondeado("Ver reseñas");
        btnComentar.setBackground(Color.orange);
        btnComentar.setForeground(new Color(48, 29, 197));
        btnComentar.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Mensaje que sale al usuario si apoya el ratón sobre el botón
        UIManager.put("ToolTip.background", new Color(0, 128, 128));
        UIManager.put("ToolTip.foreground", Color.white);
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 14));
        btnComentar.setToolTipText("Lee las reseñas y añade una si lo deseas");

        panelDerecha.add(filaEstrellas);
        panelDerecha.add(Box.createVerticalStrut(5));
        panelDerecha.add(btnComentar);

        //Montar el panel de registro
        panelRegistroDoctor.add(panelIzquierda, BorderLayout.WEST);
        panelRegistroDoctor.add(panelCentro, BorderLayout.CENTER);
        panelRegistroDoctor.add(panelDerecha, BorderLayout.EAST);

        return panelRegistroDoctor;
    }

    private void iniciarFooter() {

        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdoFooter = new JPanel();
        panelIzquierdoFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdoFooter.setOpaque(false);

        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdoFooter.add(lblTextoAlLado);

        JButton btnLinkUno = new JButton("Política de privacidad ");
        JButton btnLinkDos = new JButton("Contacto");
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

        panelIzquierdoFooter.add(btnLinkUno);
        panelIzquierdoFooter.add(btnLinkDos);

        panelDerechoFooter = new JPanel();
        panelDerechoFooter.setLayout(new BoxLayout(panelDerechoFooter, BoxLayout.Y_AXIS));
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
        textoDesplazandose.add(panelMarquee("Regístrate y obtén una cita. ¡Cambia tu vida con un click!", 200));
        add(textoDesplazandose, BorderLayout.SOUTH);
    }


    private JPanel panelMarquee(String texto, int espacioTiempo) {

        JPanel zonaDelMarquee = new JPanel(new BorderLayout());
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


    private Image imagenEscalada(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        return originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ventana3::new);

    }
}
