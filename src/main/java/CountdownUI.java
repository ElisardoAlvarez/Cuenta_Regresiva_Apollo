import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * La clase CountdownUI maneja la interfaz de usuario para el simulador de lanzamiento Apollo 11.
 * Proporciona controles para iniciar y cancelar la cuenta atrás, muestra el tiempo restante y visualiza la animación del cohete.
 */
public class CountdownUI extends JFrame {
    private JButton startButton;
    private JButton cancelButton;
    private JTextField inputField;
    private JLabel countdownLabel;
    private CountdownTask countdownTask;
    private RocketAnimationPanel rocketAnimationPanel; // Panel de animación del cohete.
    private Logger logger;

    /**
     * Constructor para CountdownUI.
     * Inicializa la interfaz de usuario y configura los componentes necesarios.
     * @param logger Logger para registrar eventos.
     */
    public CountdownUI(Logger logger) {
        this.logger = logger;
        createUIComponents();
        layoutComponents();
        attachEventHandlers();
    }

    /**
     * Crea e inicializa los componentes de la interfaz de usuario.
     */
    private void createUIComponents() {
        inputField = new JTextField(5);
        startButton = new JButton("Start");
        cancelButton = new JButton("Cancel");
        countdownLabel = new JLabel("00:00", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 30));
        rocketAnimationPanel = new RocketAnimationPanel(); // Inicializa el panel de animación del cohete.
    }

    /**
     * Organiza los componentes en el layout y configura las propiedades básicas de la ventana.
     */
    private void layoutComponents() {
        setTitle("Apollo 11 Launch Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter seconds:"));
        panel.add(inputField);
        panel.add(startButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.NORTH);
        add(countdownLabel, BorderLayout.SOUTH);
        add(rocketAnimationPanel, BorderLayout.CENTER); // Agrega el panel de animación al JFrame.

        pack(); // Ajusta el tamaño de la ventana según los componentes.
    }

    /**
     * Adjunta manejadores de eventos a los botones.
     */
    private void attachEventHandlers() {
        startButton.addActionListener(this::startCountdown);
        cancelButton.addActionListener(this::cancelCountdown);
    }

    private void startCountdown(ActionEvent e) {
        int seconds;
        try {
            seconds = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format");
            return;
        }

        countdownTask = new CountdownTask(logger, seconds, this);
        countdownTask.start();
    }

    private void cancelCountdown(ActionEvent e) {
        if (countdownTask != null) {
            countdownTask.interrupt();
        }
    }

    public void updateCountdownLabel(String time) {
        countdownLabel.setText(time);
    }

    public void showCompletionMessage(boolean isCancelled) {
        String message = isCancelled ? "Launch Cancelled" : "Launch Successful";
        JOptionPane.showMessageDialog(this, message);
        showFinalMessage(isCancelled);
    }

    /**
     * Actualiza la cuenta atrás en la interfaz de usuario y anima el cohete.
     * @param remainingSeconds Segundos restantes hasta el lanzamiento.
     */
    public void updateCountdown(int remainingSeconds) {
        SwingUtilities.invokeLater(() -> {
            countdownLabel.setText(formatTime(remainingSeconds));
            rocketAnimationPanel.updatePosition(); // Actualiza la posición del cohete con cada tick de la cuenta regresiva.
        });
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void showFinalMessage(boolean isCancelled) {
        String message = isCancelled ? "Launch Canceled" : "Successful Launch";
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }
}
