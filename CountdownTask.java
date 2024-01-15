import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class CountdownTask extends Thread {
    private final int totalSeconds;
    private final CountdownUI ui;
    private final Logger logger;

    public CountdownTask(int totalSeconds, CountdownUI ui, Logger logger) {
        this.totalSeconds = totalSeconds;
        this.ui = ui;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Iniciando la cuenta regresiva de " + totalSeconds + " segundos.");
        try {
            for (int i = totalSeconds; i >= 0; i--) {
                int minutes = i / 60;
                int seconds = i % 60;
                String timeFormatted = String.format("%02d:%02d", minutes, seconds);

                SwingUtilities.invokeLater(() -> ui.updateCountdownLabel(timeFormatted));
                logger.log(Level.FINE, "Tiempo restante: " + timeFormatted);

                Thread.sleep(1000);
            }
            logger.log(Level.INFO, "Lanzamiento exitoso.");
            ui.showCompletionMessage(false);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "Lanzamiento cancelado.");
            SwingUtilities.invokeLater(() -> ui.updateCountdownLabel("00:00"));
            ui.showCompletionMessage(true);
        }
    }
}
