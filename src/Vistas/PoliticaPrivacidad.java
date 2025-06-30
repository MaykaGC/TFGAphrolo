package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PoliticaPrivacidad extends JFrame {

    //Atributos
    private JPanel headerVentana1;
    private JButton btnHeader;
    private JLabel tituloHeader;

    private JPanel panelCentral;
    private JPanel panelIzquierd;
    private JPanel panelDerechoCerrarPol;
    private JPanel panelDerecho;
    private JEditorPane privacidadPane;
    private JScrollPane privacidadScroll;

    private JPanel footerPanel;
    private JPanel panelIzquierdoFooter;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;


    public PoliticaPrivacidad() {

        iniciarVentana();
        iniciarHeader();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }


    private void iniciarVentana(){

        setTitle("POLÍTICA DE PRIVACIDAD ");
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
        btnHeader.setFocusPainted(false);
        btnHeader.setBackground(new Color(102, 205, 170));
        btnHeader.setForeground(Color.WHITE);
        btnHeader.setFont(new Font("Georgia", Font.BOLD, 14));

        btnHeader.addActionListener(e -> new Vistas.VentanaAdmin());
        headerVentana1.add(btnHeader, BorderLayout.WEST);

        tituloHeader = new JLabel("APRHOLO, su política de privacidad", SwingConstants.CENTER);
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
        btnCerrado.addActionListener(e -> {dispose();});
    }

    private void iniciarPanelCentral(){

        panelCentral = new JPanel(new BorderLayout());

        //Panel 1 para margen izquierdo
        panelIzquierd = new JPanel();
        panelIzquierd.setPreferredSize(new Dimension(100, 0)); // ancho ajustable
        panelIzquierd.setOpaque(false);

        //Panel 2 para margen derecho
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(100, 0));
        panelDerecho.setOpaque(false);

        String contenidoPoliPriva = """
        <html>
          <body style='font-family:Arial; font-size:11px; background-color:#F5F5F5; padding:10px;'>
            <p>Esta política de privacidad se aplica a la app APHROLO, comprometiendose a proteger su privacidad y los datos personales proporcionados.
            El acceso a esta sección no es obligatoria pero es necesario para entender el tratamiento de datos y privacidad de la empresa y el usuario de nuestra aplicación. 
            Esta política de privacidad establece los terminos en que usa y protege la información proporcionada. Dicha información se usará sólo de acuerdo a lo aquí establecido. 
           APHROLO se compromete a un trato ajustado a la legalidad, los cuales se encuentran recogidos en
            la ley vigente relativa a la protección de las personas físicas a la libre circulacion de datos (RGPD) y resto de normativa aplicable. </p>
            
            <p><strong>RESPONSABLE DEL TRATAMIENTO DE DATOS</strong></p><br>
            Para cualquier pregunta o solicitud relacionada con sus datos puede contactarnos a través de nuestros datos de contacto: <br>
            <p>M&amp;CCreators S.L.<br>
            Dirección: Córdoba. Calle La Bodega, nº 14, sala B.<br>
            Email: mccreator@gmail.es<br>
            Teléfono: 678 123 52</p>

            <p><strong>DATOS DE NUEVO CLIENTE</strong></p>
            <p>El tratamiento de datos atañe a los usuarios registrados en la aplicación a través del formualrio de información personal, el cual se realiza con consentimiento expreso 
            al aceptar los terminos y condiciones de acuerdo a la RGPD. Los datos serán almacenados hasta que el usuario decida rescindir del registro con el objetivo de mantener un registro
            y mejorar los servicios ofrecidos. </p>

            <p><strong>CONCERTACIÓN DE UNA CITA A TRAVÉS DE LA APP</strong></p>
            <p>La cita será concertada digitalmente a través de la aplicación y quedará recogida en la base de datos de la compañía. Los datos serán trasladados de manera automática 
            a la clínica con el objetivo de facilitar e las clinicas en cuestión la organización de las agendas diarias. Este traspaso de información se realiza con el objetivo de respaldar 
            y refutar lo establecido en el Real Decreto Legislativo 1/2007, de 16 d eNoviembre, por el que se aprueba el texto de la Ley General para la Defensa de los Consumidores y Usuarios 
            y leyes que se complementan con estas. Los datos serán tratados con exclusividad y con el objetivo de poder ofrecer "una calidad acertada del servicio y posterior desarrollo. 
            Para poder darse de baja, el usuario tendrá que contactar con la empresa a través del apartado Contacto, bien por e-mail o enviando un mensaje a través de la cajita disponible 
            para dudas y consultas.</p>

            <p><strong>PUBLICIDAD Y DESCUENTOS</strong></p>
            <p>APHROLO enviará publicidad sólo a aquellos usuarios registrados. Dicha información estará relacionada con descuentos que se considerarán como privilegios exclusivos de 
            los usuarios regristrados a través del formulario de registro y dando consentimiento previo, de acuerdo a lo dispuesto en el ARt.6.1.a) de la RPGD. En este sentido, es posible
            que el usuari reciva correos electrónicos periódicamente. </p>

            <p><strong>PUBLICAR OPINIONES SOBRE LAS CLÍNICAS IMPLICADAS</strong></p>
            <p>No es necesario el uso de información personal para realizar una reseña en la aplicación s por ello que pedimos sinceridad y realizar dicha acción con el mayor respeto
             y veracidad hacia las clínicas y sus doctores, todos ellos títulados, cualificados y expertos en la materia. El objetivo principal de M&CCreators es realizar un estudio de 
             campo exhaustivo para ello, contribuyendo a la tranquilidad de los potenciales clientes. </p>
          </body>
        </html>
        """;

        privacidadPane = new JEditorPane("text/html", contenidoPoliPriva);
        privacidadPane.setEditable(false);
        privacidadPane.setOpaque(true);
        privacidadPane.setBackground(new Color(245, 245, 245));

        privacidadScroll = new JScrollPane(privacidadPane);
        privacidadScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        privacidadScroll.setPreferredSize(new Dimension(800, 200));

        panelCentral.add(privacidadScroll);
        panelCentral.add(panelIzquierd, BorderLayout.WEST);
        panelCentral.add(privacidadScroll, BorderLayout.CENTER);
        panelCentral.add(panelDerecho, BorderLayout.EAST);
        this.add(panelCentral, BorderLayout.CENTER);
    }

    private void iniciarFooter(){
        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdoFooter= new JPanel();
        panelIzquierdoFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdoFooter.setOpaque(false);

        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdoFooter.add(lblTextoAlLado);

        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setOpaque(false);

        JLabel lblSiguenos = new JLabel("SÍGUENOS EN");
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblSiguenos);

        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        ImageIcon imgUno = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\logotipos.png");
        ImageIcon imgDos = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\instagram.png");
        ImageIcon imgTres = new ImageIcon("C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Imagenes\\facebook.png");
        panelIconos.add(new JLabel(new ImageIcon(imgUno.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgDos.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelIconos.add(new JLabel(new ImageIcon(imgTres.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
        panelDerecho.add(panelIconos);

        footerPanel.add(panelIzquierdoFooter, BorderLayout.WEST);
        footerPanel.add(panelDerecho, BorderLayout.EAST);

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

        SwingUtilities.invokeLater(PoliticaPrivacidad::new);
    }

}

