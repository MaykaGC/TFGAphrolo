package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BotonRedondeado extends JButton {

    private int anchoBtn = 20;
    private int altoBtn = 20;

    public BotonRedondeado(String text) {
        super(text);
        //No pintar el fondo por defecto
        setContentAreaFilled(false);
        //Sin borde de foco
        setFocusPainted(false);
        //Sin borde por defecto
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        //Técnica para evitar el pixelado, suavizando formas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Fondo
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), anchoBtn, altoBtn);
        //Texto
        super.paintComponent(g2);
        g2.dispose();
    }

    //Borde del botón
    @Override
    protected void paintBorder(Graphics g) {
        //Borde
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, anchoBtn, altoBtn);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), anchoBtn, altoBtn);
        return shape.contains(x, y);
    }
}
