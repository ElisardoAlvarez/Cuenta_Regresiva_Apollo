import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el panel de animación del cohete.
 * Maneja la visualización del cohete y su animación durante la cuenta regresiva.
 */
public class RocketAnimationPanel extends JPanel {

    private int yPosition; // Posición Y del cohete, controla la animación del movimiento hacia arriba.

    public RocketAnimationPanel() {
        yPosition = getHeight() - 50; // Iniciar el cohete en la parte inferior del panel.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja el cohete (puedes mejorar esto con una imagen si lo prefieres).
        g.setColor(Color.RED);
        g.fillOval(50, yPosition, 50, 100);

        // Dibuja la llama del cohete si la cuenta regresiva está en marcha.
        if (yPosition < getHeight()) {
            g.setColor(Color.ORANGE);
            g.fillOval(55, yPosition + 100, 40, 50);
        }
    }

    /**
     * Actualiza la posición del cohete, creando la animación de despegue.
     */
    public void updatePosition() {
        yPosition -= 5; // Mueve el cohete hacia arriba.
        if (yPosition < 0) {
            yPosition = getHeight(); // Reinicia la posición del cohete cuando alcanza el tope.
        }
        repaint(); // Repinta el panel para mostrar la nueva posición del cohete.
    }
}

