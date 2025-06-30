package Vistas;

import Controlador.ControladorCliente;
import Controlador.HashUtil;
import Modelo.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegristroInicioSesionUsuario extends JFrame {

    //Atributos
    private JPanel panelSuperior;
    private JSplitPane panelDivisor;
    private JPanel panelRegistro;
    private JPanel panelLogin;

    private JPanel footerPanel;
    private JPanel panelIzquierdoFooter;
    private JPanel panelDerechoFooter;
    private JPanel panelIconos;
    private JPanel textoDesplazandose;
    private JPanel volver;

    public RegristroInicioSesionUsuario() {

        iniciarVentana();
        iniciarHeaderConBoton();
        iniciarPanelCentral();
        iniciarFooter();
        setVisible(true);
    }

    private void iniciarVentana() {

        setTitle("Autenticación");
        setSize(300, 200);
        //Cerramos la ventana actual pero no la Ventana 1 con el DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarHeaderConBoton() {

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.white);
        this.add(panelSuperior);

        //Panel con botón volver
        volver = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 35));
        volver.setBackground(Color.white);
        BotonRedondeado btnVolver = new BotonRedondeado("← Volver");
        btnVolver.setBackground(Color.orange);
        btnVolver.setForeground(new Color(48, 29, 197));
        volver.add(btnVolver);
        panelSuperior.add(volver, BorderLayout.NORTH);
        btnVolver.addActionListener(e -> {
            new Vistas.Ventana3();
            this.dispose();
        });
    }

    private void iniciarPanelCentral() {

        //SplitPane me permite dividir la pantalla en 2 áreas
        panelDivisor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panelDivisor.setResizeWeight(0.5);
        //Permite que se mantengan las dos parte del panel si se arrastra la línea divisoria
        panelDivisor.setContinuousLayout(true);
        //Grosor de la línea divisoria que hay entre los paneles
        panelDivisor.setDividerSize(4);
        panelSuperior.add(panelDivisor, BorderLayout.CENTER);
        //Quitar el borde exterior
        panelDivisor.setBorder(null);

        //PANEL DE REGISTRO
        panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBackground(Color.white);
        Border invisible2 = BorderFactory.createEmptyBorder();
        TitledBorder borde2 = BorderFactory.createTitledBorder(invisible2, "Registro");
        borde2.setTitleFont(new Font("Georgia", Font.BOLD, 18));
        borde2.setTitleJustification(TitledBorder.CENTER);
        panelRegistro.setBorder(borde2);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(12, 8, 12, 8);
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.NONE;

        //Campo NOMBRE USUARIO
        gbc2.gridx = 0;
        gbc2.gridy = 0;

        JPanel panelAsterisco1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco1.setOpaque(false);

        JLabel lblNombreUsuario = new JLabel("Nombre");
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco1.add(lblNombreUsuario);

        JLabel aste1 = new JLabel("*");
        aste1.setForeground(Color.RED);
        panelAsterisco1.add(aste1);

        panelRegistro.add(panelAsterisco1, gbc2);

        gbc2.gridx = 1;
        JTextField campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoNombre, gbc2);

        //Campo EMAIL
        gbc2.gridx = 0;
        gbc2.gridy = 1;

        JPanel panelAsterisco2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco2.setOpaque(false);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco2.add(lblEmail);

        JLabel aste2 = new JLabel("*");
        aste2.setForeground(Color.RED);
        panelAsterisco2.add(aste2);

        panelRegistro.add(panelAsterisco2, gbc2);

        gbc2.gridx = 1;
        JTextField campoEmail = new JTextField();
        campoEmail.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoEmail, gbc2);

        //Campo TELÉFONO
        gbc2.gridx = 0;
        gbc2.gridy = 2;

        JPanel panelAsterisco3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco3.setOpaque(false);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco3.add(lblTelefono);

        JLabel aste3 = new JLabel("*");
        aste3.setForeground(Color.RED);
        panelAsterisco3.add(aste3);

        panelRegistro.add(panelAsterisco3, gbc2);

        gbc2.gridx = 1;
        JTextField campoTelefono = new JTextField();
        campoTelefono.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoTelefono, gbc2);

        //Campo DIRECCIÓN
        gbc2.gridx = 0;
        gbc2.gridy = 3;

        JPanel panelAsterisco4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco4.setOpaque(false);

        JLabel lblDireccion = new JLabel("Dirección");
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco4.add(lblDireccion, gbc2);

        JLabel aste4 = new JLabel("*");
        aste4.setForeground(Color.RED);
        panelAsterisco4.add(aste4);

        panelRegistro.add(panelAsterisco4, gbc2);

        gbc2.gridx = 1;
        JTextField campoDireccion = new JTextField();
        campoDireccion.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoDireccion, gbc2);

        //Campo DNI
        gbc2.gridx = 0;
        gbc2.gridy = 4;

        JPanel panelAsterisco5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco5.setOpaque(false);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco5.add(lblDni, gbc2);

        JLabel aste5 = new JLabel("*");
        aste5.setForeground(Color.RED);
        panelAsterisco5.add(aste5);

        panelRegistro.add(panelAsterisco5, gbc2);

        gbc2.gridx = 1;
        JTextField campoDni = new JTextField();
        campoDni.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoDni, gbc2);

        //Campo CONTRASEÑA
        gbc2.gridx = 0;
        gbc2.gridy = 5;

        JPanel panelAsterisco6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAsterisco6.setOpaque(false);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setFont(new Font("Arial", Font.BOLD, 14));
        panelAsterisco6.add(lblContra, gbc2);

        JLabel aste6 = new JLabel("*");
        aste6.setForeground(Color.RED);
        panelAsterisco6.add(aste6);

        panelRegistro.add(panelAsterisco6, gbc2);

        gbc2.gridx = 1;
        JPasswordField campoContra = new JPasswordField();
        campoContra.setPreferredSize(new Dimension(250, 30));
        panelRegistro.add(campoContra, gbc2);

        //Campo TÉRMINOS Y CONDICIONES
        gbc2.gridx = 1;
        gbc2.gridy = 6;
        JCheckBox terminos = new JCheckBox("Acepto términos y condiciones");
        terminos.setFont(new Font("Arial", Font.BOLD, 14));
        terminos.setBackground(Color.white);
        panelRegistro.add(terminos, gbc2);

        //Campo BOTÓN REGISTRAR
        gbc2.gridx = 1;
        gbc2.gridy = 7;
        gbc2.anchor = GridBagConstraints.CENTER;
        BotonRedondeado btnRegistro = new BotonRedondeado("Registrar");
        btnRegistro.setBackground(Color.orange);
        btnRegistro.setForeground(new Color(48, 29, 197));
        btnRegistro.setPreferredSize(new Dimension(100, 30));
        panelRegistro.add(btnRegistro, gbc2);

        //ACCIÓN DE REGISTRO
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre   = campoNombre.getText().trim();
                String email    = campoEmail.getText().trim();
                String telefono = campoTelefono.getText().trim();
                String direccion= campoDireccion.getText().trim();
                String dni      = campoDni.getText().trim();
                String passwordSinHasheo  = new String(campoContra.getPassword()).trim();

                if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || dni.isEmpty() || passwordSinHasheo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                    return;
                }

                if (!terminos.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Debes aceptar términos y condiciones.");
                    return;
                }

                String hashedPassword = HashUtil.md5(passwordSinHasheo);

                int resultado;
                try {
                    resultado = ControladorCliente.insertarClientesApp(nombre, email, telefono, direccion, dni, hashedPassword);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");

                   //Limpiar campos
                    campoNombre.setText("");
                    campoEmail.setText("");
                    campoTelefono.setText("");
                    campoDireccion.setText("");
                    campoDni.setText("");
                    campoContra.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el cliente.");
                }
            }
        });

        //PANEL DE INICIO DE SESIÓN (Lado izquierdo)
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(Color.white);
        Border invisible = BorderFactory.createEmptyBorder();
        TitledBorder borde1 = BorderFactory.createTitledBorder(invisible, "Inicio de sesión");
        borde1.setTitleFont(new Font("Georgia", Font.BOLD, 18));
        //Esto me centra el título
        borde1.setTitleJustification(TitledBorder.CENTER);
        panelLogin.setBorder(borde1);

        GridBagConstraints gbc = new GridBagConstraints();
        //Inset me permite añadir margen
        gbc.insets = new Insets(10, 10, 10, 10);
        //Permite situar los elementos en el lugar que deseemos dentro de la celda si esta es más ancha que dicho componente
        gbc.anchor = GridBagConstraints.CENTER;
        //NONE hace que el botón tome la medida que quiero en el preferredsize y no tome el ancho de la celda por defecto
        //lo cual se conseguiría con GridBagConstraints.HORIZONTAL
        gbc.fill = GridBagConstraints.NONE;

        //Campo USUARIO
        //Indica la columna
        gbc.gridx = 0;
        //Indica la fila
        gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Email");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panelLogin.add(lblUsuario, gbc);
        gbc.gridx = 1;
        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 30));
        panelLogin.add(userField, gbc);

        //Campo CONTRASEÑA
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setFont(new Font("Arial", Font.BOLD, 14));
        panelLogin.add(lblContrasenia, gbc);
        gbc.gridx = 1;
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(250, 30));
        panelLogin.add(passField, gbc);

        //Campo RECORDARME
        gbc.gridx = 1;
        gbc.gridy = 2;
        JCheckBox rememberCheck = new JCheckBox("Recordarme");
        rememberCheck.setFont(new Font("Arial", Font.BOLD, 14));
        rememberCheck.setBackground(Color.white);
        panelLogin.add(rememberCheck, gbc);

        //Botón Entrar centrado
        gbc.gridx = 1;
        gbc.gridy = 3;

        //Esto me permite alinear el botón en el centro
        gbc.anchor = GridBagConstraints.CENTER;
        BotonRedondeado btnLogin = new BotonRedondeado("Entrar");
        btnLogin.setBackground(Color.orange);
        btnLogin.setForeground(new Color(48, 29, 197));
        btnLogin.setPreferredSize(new Dimension(100, 30));
        panelLogin.add(btnLogin, gbc);

        //INICIO DE SESIÓN
        btnLogin.addActionListener(e -> {
            //Obtenemos email y contraseña y se quitan los espacios con trim
            String email = userField.getText().trim();
            String passwordSinHashear = new String(passField.getPassword()).trim();

            String passwordHasheada = HashUtil.md5(passwordSinHashear);
            System.out.println("Intentando iniciar sesión con email: " + email);

            try {

                ControladorCliente controlador = new ControladorCliente();
                Usuario usuario = controlador.iniciarSesionCliente(email, passwordHasheada);


                if (usuario != null && usuario.getId_Usuario() == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Email o contraseña incorrectos.",
                            "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                    System.out.println("No se encontró usuario con esas credenciales."); // Debug
                    return;
                }

                // Si el usuario tiene un id mayor a 0 se pasa a la siguiente pantalla
                if (usuario.getId_Usuario() > 0) {
                    System.out.println("Usuario válido con ID: " + usuario.getId_Usuario()); // Debug

                    // Pasa el usuario a la nueva ventana para que ConfirmacionCita tenga acceso al ID válido
                    new ConfirmacionCita(usuario);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Email o contraseña incorrectos.",
                            "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                    System.out.println("El usuario tiene ID inválido."); // Debug
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        //Se asigna al panel divisor los paneles derecho e izquierdo
        panelDivisor.setLeftComponent(panelLogin);
        panelDivisor.setRightComponent(panelRegistro);
    }


    private void iniciarFooter() {

        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(0, 128, 128));
        footerPanel.setPreferredSize(new Dimension(600, 120));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdoFooter = new JPanel();
        panelIzquierdoFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIzquierdoFooter.setOpaque(false);

        JLabel lblTextoAlLado = new JLabel("© 2025 M&Ccreators S.L - Todos los derechos reservados");
        lblTextoAlLado.setFont(new Font("Georgia", Font.PLAIN, 14));
        lblTextoAlLado.setForeground(Color.white);
        lblTextoAlLado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdoFooter.add(lblTextoAlLado);

        JButton btnLinkUno = new JButton("Política de privacidad ");
        JButton btnLinkDos = new JButton("Contacto");
        for (JButton link : new JButton[]{btnLinkUno, btnLinkDos}) {
            link.setBorderPainted(false);
            link.setFocusPainted(false);
            link.setContentAreaFilled(false);
            link.setFont(new Font("Arial", Font.BOLD, 14));
            link.setForeground(Color.white);
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
        lblSiguenos.setForeground(Color.white);
        lblSiguenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerechoFooter.add(lblSiguenos);

        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelIconos.setBackground(new Color(0, 128, 128));
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
        textoDesplazandose.add(panelMarquee());
        add(textoDesplazandose, BorderLayout.SOUTH);
    }


    private JPanel panelMarquee() {

        JPanel zonaDelMarquee = new JPanel(new BorderLayout());
        zonaDelMarquee.setPreferredSize(new Dimension(600, 30));

        zonaDelMarquee.setOpaque(true);
        zonaDelMarquee.setBackground(Color.WHITE);

        JLabel marqueeLabel = new JLabel(" Regístrate y obtén una cita. ¡Cambia tu vida con un click!");
        marqueeLabel.setFont(new Font("Georgia", Font.BOLD, 12));
        marqueeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        zonaDelMarquee.add(marqueeLabel, BorderLayout.CENTER);

        new Timer(200, e -> {
            String t = marqueeLabel.getText();
            marqueeLabel.setText(t.substring(1) + t.charAt(0));
        }).start();

        return zonaDelMarquee;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(RegristroInicioSesionUsuario::new);
    }
}
