import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * La clase CountdownUI maneja la interfaz de usuario para el simulador de lanzamiento Apollo 11.
 * Proporciona controles para iniciar y cancelar la cuenta atrás y muestra el tiempo restante.
 */
public class CountdownUI extends JFrame {
    private JButton startButton;
    private JButton cancelButton;
    private JTextField inputField;
    private JLabel countdownLabel;
    private CountdownTask countdownTask;
    private Logger logger;

    /**
     * Constructor para CountdownUI.
     *
     * @param logger Logger para registrar eventos.
     */
    public CountdownUI(Logger logger) {
        this.logger = logger;
        createUI();
    }

    /**
     * Inicializa y organiza los componentes de la interfaz de usuario.
     */
    private void createUI() {
        setTitle("Apollo 11 Launch Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        inputField = new JTextField(5);
        startButton = new JButton("Start");
        cancelButton = new JButton("Cancel");
        countdownLabel = new JLabel("00:00", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 30));

        panel.add(new JLabel("Enter seconds:"));
        panel.add(inputField);
        panel.add(startButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.NORTH);
        add(countdownLabel, BorderLayout.CENTER);

        pack();

        startButton.addActionListener(this::startCountdown);
        cancelButton.addActionListener(this::cancelCountdown);
    }

    /**
     * Maneja la acción de inicio de la cuenta atrás.
     *
     * @param e Evento de acción.
     */
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

    /**
     * Maneja la acción de cancelación de la cuenta atrás.
     *
     * @param e Evento de acción.
     */
    private void cancelCountdown(ActionEvent e) {
        if (countdownTask != null) {
            countdownTask.interrupt();
        }
    }

    /**
     * Actualiza la etiqueta del tiempo restante de la cuenta atrás.
     *
     * @param time Tiempo restante en formato String.
     */
    public void updateCountdownLabel(String time) {
        countdownLabel.setText(time);
    }

    /**
     * Muestra un mensaje al finalizar la cuenta atrás.
     *
     * @param isCancelled Indica si la cuenta atrás fue cancelada.
     */
    public void showCompletionMessage(boolean isCancelled) {
        String message = isCancelled ? "Launch Cancelled" : "Launch Successful";
        JOptionPane.showMessageDialog(this, message);
        showFinalMessage(isCancelled);
    }

    /**
     * Actualiza la cuenta atrás en la interfaz de usuario.
     *
     * @param remainingSeconds Segundos restantes.
     */
    public void updateCountdown(int remainingSeconds) {
        SwingUtilities.invokeLater(() -> countdownLabel.setText(formatTime(remainingSeconds)));
    }

    /**
     * Formatea los segundos restantes en un formato de tiempo mm:ss.
     *
     * @param totalSeconds Segundos totales.
     * @return Tiempo formateado como String.
     */
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Muestra una ventana emergente al finalizar la cuenta atrás o cuando se cancela.
     *
     * @param isCancelled Indica si la cuenta atrás fue cancelada.
     */
    private void showFinalMessage(boolean isCancelled) {
        String message = isCancelled ? "Launch Canceled" : "Successful Launch";
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }
}



