package Vistas;

import Controlador.ControladorAdministrador;
import Controlador.HashUtil;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class VentanaAdmin extends JFrame {

   //Atributos
    private JPanel btnCerrarPanel;
    private JPanel panelIzquierdobtn;
    private JPanel panelIzquierdoContenido;
    private JPanel panelDerecho;
    //Nos permite recordar los datos del login
    private Preferences credenciales = Preferences.userRoot().node("datosLogin");


    public VentanaAdmin() {

        iniciarVentana();
        iniciarUnicoPanel();
        setVisible(true);
    }

    private void iniciarVentana(){

        setName("Ventana_Administrador");
        setTitle("Área de administrador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void iniciarUnicoPanel(){

        //Se cargan las preferencias previas
        boolean recordar = credenciales.getBoolean("recordar", false);
        String prefUsuario = credenciales.get("usuario", "");
        String prefContrasena = credenciales.get("contrasena", "");

        //Panel izquierdo con BorderLayout para el botón y contenido
        panelIzquierdobtn = new JPanel(new BorderLayout());
        panelIzquierdobtn.setBackground(Color.WHITE);
        panelIzquierdobtn.setPreferredSize(new Dimension(750, 750));
        panelIzquierdobtn.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        //Botón volver alineado a la izquierda en NORTH
        btnCerrarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        btnCerrarPanel.setOpaque(false);

        BotonRedondeado btnCerrado = new BotonRedondeado("Cerrar");
        btnCerrado.setBackground(new Color(216, 68,68));
        btnCerrado.setForeground(Color.white);
        btnCerrado.setPreferredSize(new Dimension(100, 30));

        btnCerrarPanel.add(btnCerrado);
        panelIzquierdobtn.add(btnCerrarPanel, BorderLayout.NORTH);
        btnCerrado.addActionListener(e -> {dispose();});

        //Panel de contenido con BoxLayout
        panelIzquierdoContenido = new JPanel();
        panelIzquierdoContenido.setOpaque(false);
        panelIzquierdoContenido.setLayout(new BoxLayout(panelIzquierdoContenido, BoxLayout.Y_AXIS));

        //Logotipo
        ImageIcon icon = new ImageIcon("C:/Users/chica/OneDrive/Desktop/TFGAPHROLO/Aphrolo/src/Imagenes/A-removebg-preview (4).png");
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Mensaje de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, administrador");
        lblBienvenida.setFont(new Font("Georgia", Font.ITALIC, 26));
        lblBienvenida.setForeground(new Color(0, 128, 128));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Campo usuario
        PlaceholderTextField campoUsuario = new PlaceholderTextField("Email");
        campoUsuario.setMaximumSize(new Dimension(300, 30));
        campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (recordar) {
            campoUsuario.setText(prefUsuario);
        }

        //Campo contraseña
        PlaceholderPasswordField campoContrasenia = new PlaceholderPasswordField("Contraseña");
        campoContrasenia.setMaximumSize(new Dimension(300, 30));
        campoContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (recordar) {
            campoContrasenia.setText(prefContrasena);
        }

        //Checkbox
        JCheckBox campoRecuerdame = new JCheckBox("Recordar contraseña");
        campoRecuerdame.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoRecuerdame.setForeground(Color.BLACK);
        campoRecuerdame.setBackground(Color.WHITE);
        campoRecuerdame.setFont(new Font("Arial", Font.BOLD, 14));

        //Botón aceptar
        BotonRedondeado acceptButton = new BotonRedondeado("Aceptar");
        acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setMaximumSize(new Dimension(100, 25));
        acceptButton.setBackground(Color.orange);
        acceptButton.setForeground(Color.WHITE);

        acceptButton.addActionListener(e -> {

            String email = campoUsuario.getText().trim();
            String pswSinHasheo = new String(campoContrasenia.getPassword());

            if (email.isEmpty() || pswSinHasheo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes introducir email y contraseña.", "Error de validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String pswHasheada = HashUtil.md5(pswSinHasheo);
                ControladorAdministrador controlador = new ControladorAdministrador();
                Usuario admin = controlador.iniciarSesionAdmin(email, pswHasheada);

                if (admin != null) {
                    //Guardar o borrar credenciales según el checkbox
                    if (campoRecuerdame.isSelected()) {
                        credenciales.put("usuario", email);
                        credenciales.put("contrasena", pswSinHasheo);
                        credenciales.putBoolean("recordar", true);
                    } else {
                        credenciales.remove("usuario");
                        credenciales.remove("contrasena");
                        credenciales.putBoolean("recordar", false);
                    }

                    new VentanaUsuario().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Email o contraseña incorrectos, o no tienes permisos de administrador.",
                            "Error de autenticación",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error de base de datos: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelIzquierdoContenido.add(imageLabel);
        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 10)));
        panelIzquierdoContenido.add(lblBienvenida);
        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 30)));

        panelIzquierdoContenido.add(campoUsuario);
        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        panelIzquierdoContenido.add(campoContrasenia);
        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 10)));
        panelIzquierdoContenido.add(campoRecuerdame);
        panelIzquierdoContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelIzquierdoContenido.add(acceptButton);

        panelIzquierdobtn.add(panelIzquierdoContenido, BorderLayout.CENTER);

        //Panel derecho
        panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setBackground(new Color(0, 128, 128));
        panelDerecho.setPreferredSize(new Dimension(300, 300));
        JLabel infoLabel = new JLabel("APHROLO ofrece sólo acceso y privilegios en esta sección al administrador de esta plataforma");
        infoLabel.setFont(new Font("Georgia", Font.ITALIC, 17));
        infoLabel.setForeground(Color.WHITE);
        panelDerecho.add(infoLabel);

        add(panelIzquierdobtn, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }


    //Clases internas para editar los placeholders
    class PlaceholderTextField extends JTextField {
        private String placeholder;
        public PlaceholderTextField(String placeholder) { this.placeholder = placeholder; }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setFont(getFont().deriveFont(Font.ITALIC));
                g2.setColor(Color.GRAY);
                Insets insets = getInsets();
                g2.drawString(placeholder, insets.left + 5,
                        getHeight()/2 + getFont().getSize()/2 - 2);
                g2.dispose();
            }
        }
    }

    class PlaceholderPasswordField extends JPasswordField {
        private String placeholder;
        public PlaceholderPasswordField(String placeholder) { this.placeholder = placeholder; }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getPassword().length == 0) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setFont(getFont().deriveFont(Font.ITALIC));
                g2.setColor(Color.GRAY);
                Insets insets = getInsets();
                g2.drawString(placeholder, insets.left + 5,
                        getHeight()/2 + getFont().getSize()/2 - 2);
                g2.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaAdmin::new);
    }
}
