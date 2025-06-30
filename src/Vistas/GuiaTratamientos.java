package Vistas;

import javax.swing.*;
import java.awt.*;

public class GuiaTratamientos extends JFrame {


    //Atributos
    private JPanel headerVentana1;
    private JButton btnHeader;
    private JLabel tituloHeader;

    private JPanel panelDerechoCerrarGuia;
    private JPanel panelCentral;
    private JPanel panelIzquierdSeparador;
    private JPanel panelDerechoSeparador;
    private JEditorPane privacidadPane;
    private JScrollPane privacidadScroll;

    private JPanel footerPanel;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;

    public GuiaTratamientos() {

      iniciarVentana();
      iniciarHeader();
      iniciarPanelCentral();
      iniciarFooter();
      setVisible(true);
    }

    //MÉTOD0S
    private void iniciarVentana(){

        setTitle("Guía de tratamientos ");
        setSize(600, 500);
        //Disponse on close solo cierra la pantalla actual y solo libera los recursos de esta
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


        tituloHeader = new JLabel("APRHOLO, guía completa de tratamientos", SwingConstants.CENTER);
        tituloHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        tituloHeader.setForeground(Color.WHITE);

        headerVentana1.add(tituloHeader, BorderLayout.CENTER);
        add(headerVentana1, BorderLayout.NORTH);

        panelDerechoCerrarGuia= new JPanel(new FlowLayout(FlowLayout.CENTER,30,35));
        panelDerechoCerrarGuia.setOpaque(false);

        BotonRedondeado btnCerrado = new BotonRedondeado("Cerrar");
        btnCerrado.setBackground(new Color(216,68,68));
        btnCerrado.setForeground(Color.white);
        btnCerrado.setPreferredSize(new Dimension(100, 35));
        panelDerechoCerrarGuia.add(btnCerrado);
        headerVentana1.add(panelDerechoCerrarGuia, BorderLayout.EAST);

        btnCerrado.addActionListener(e -> {dispose();});
    }


    private void iniciarPanelCentral(){

        panelCentral = new JPanel(new BorderLayout());

        //Panel 1 para margen izquierdo
        panelIzquierdSeparador = new JPanel();
        panelIzquierdSeparador.setPreferredSize(new Dimension(100, 0));
        panelIzquierdSeparador.setOpaque(false);

        //Panel 2 para margen derecho
        panelDerechoSeparador = new JPanel();
        panelDerechoSeparador.setPreferredSize(new Dimension(100, 0));
        panelDerechoSeparador.setOpaque(false);

        String contenidoPoliPriva = """
          <<html>
          <body style='font-family:Arial; font-size:11px; background-color:#F5F5F5; padding:10px;'>
          <p><strong>A continuación podrás leer una descripción básica de los diversos tratamientos faciales ofertados y realizados por nuestros especialistas. Ten en 
          cuenta que se trata de una guía. Si deseas obtener información directa y personalizada, concerta una cita con el doctor de tu preferencia y obtén asesoramiento
          sin compromiso de permanencia. De igual modo, cabe destacar que toda la información aquí contenido esta RESPALDADA Y ACREDITADA POR LOS PROPIOS DOCTORES, por lo que
          cuenta con un sello de calidad y veracidad. </p><br>
           <p><strong>💉BÓTOX (TOXINA BOTULÍMICA)</strong><br>
            <br><strong>¿Qué es?</strong><br>
           <br>El bótox es una proteína purificada que se inyecta para relajar temporalmente los músculos faciales responsables de las arrugas de expresión.<br>
           <br><strong> ✅ Usos comunes:</strong><br>
           <br> - Líneas de expresión en frente, entrecejo y patas de gallo.<br>
          - Bruxismo (rechinar de dientes).<br>
          - Sudoración excesiva (hiperhidrosis).<br>
          <br><strong>⏱ Duración del procedimiento:</strong><br>
          <br>- De 10 a 15 minutos.<br>
          <br><strong>📆 Efectos visibles:</strong><br>
          <br>- Entre 3 a 7 días después de la aplicación.<br>
          <br><strong>⏳ Duración del efecto:</strong><br>
          <br> - 3 a 6 meses.<br>
          <br><strong>⚠️ Riesgos o efectos secundarios:</strong><br>
          <br>- Hinchazón o enrojecimiento en el punto de inyección.<br>
           - Dolor de cabeza leve.<br>
           - Asimetría facial (poco común y generalmente temporal).<br>
           - Caída del párpado (muy rara, generalmente reversible).</p>
           <p>💉<strong>ÁCIDO HIALURÓNICO</strong><br>
           <br><strong>¿Qué es?</strong><br>
           <br>El ácido hialurónico es una sustancia natural presente en el cuerpo que se inyecta para hidratar, dar volumen y rellenar arrugas o zonas específicas del rostro.<br>
           <br><strong> ✅ Usos comunes:</strong><br>
           <br>- Relleno de labios.<br>
           - Surcos nasogenianos (líneas entre nariz y boca).<br>
           - Ojeras hundidas.<br>
           - Pómulos y contorno facial.<br>
           - Hidratación profunda de la piel.<br>
           <br><strong>⏱ Duración del procedimiento:</strong><br>
           <br> De 15 a 30 minutos.<br>
           <br><strong>📆 Efectos visibles:</strong><br>
           <br>Inmediatos, aunque puede haber algo de inflamación los primeros días.<br>
           <br><strong>⏳ Duración del efecto:</strong><br>
           <br> De 6 a 18 meses, dependiendo del tipo de ácido y la zona tratada.<br>
           <br><strong>⚠️ Riesgos o efectos secundarios:</strong><br>
           <br>- Enrojecimiento, hinchazón o hematomas en el punto de inyección.<br>
           - Sensibilidad o molestia temporal.<br>
           - Formación de pequeños bultos (normalmente transitorios).<br>
           - Reacciones alérgicas (muy poco frecuentes).
           </p>
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
        panelCentral.add(panelIzquierdSeparador, BorderLayout.WEST);
        panelCentral.add(privacidadScroll, BorderLayout.CENTER);
        panelCentral.add(panelDerechoSeparador, BorderLayout.EAST);
        this.add(panelCentral, BorderLayout.CENTER);
    }

    private void iniciarFooter(){

        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //PANEL DE LA IZQUIERDA
        panelIzquierdoFooter = new JPanel();
        panelIzquierdoFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdoFooter.setOpaque(false);


        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.BLACK);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdoFooter.add(lblTextoAlLado);

        //PANEL DERECHO
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
        textoDesplazandose.add(panelMarquee(" Registrate y obtén una cita. ¡Cambia tu vida con un click!", 200));
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

        SwingUtilities.invokeLater(GuiaTratamientos::new);
    }



}
