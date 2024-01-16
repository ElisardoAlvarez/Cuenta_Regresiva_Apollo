
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class CountdownTask extends Thread {
    enum State { ACTIVE, CANCELLED, COMPLETED }

    private final int totalSeconds;
    private final CountdownUI ui;
    private final Logger logger;
    private State currentState;

    public CountdownTask(int totalSeconds, CountdownUI ui, Logger logger) {
        this.totalSeconds = totalSeconds;
        this.ui = ui;
        this.logger = logger;
        this.currentState = State.ACTIVE;
        logger.log(Level.INFO, "Countdown task initialized for " + totalSeconds + " seconds.");
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Countdown task started. State: " + currentState);
        try {
            for (int i = totalSeconds; i >= 0; i--) {
                if (Thread.interrupted()) {
                    currentState = State.CANCELLED;
                    logger.log(Level.INFO, "Countdown task interrupted and cancelled. State: " + currentState);
                    break;
                }
                int finalI = i;
                SwingUtilities.invokeLater(() -> ui.updateCountdownLabel(formatTime(finalI)));
                Thread.sleep(1000);
            }
            if (currentState != State.CANCELLED) {
                currentState = State.COMPLETED;
                logger.log(Level.INFO, "Countdown task completed successfully. State: " + currentState);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            currentState = State.CANCELLED;
            logger.log(Level.WARNING, "Countdown task interrupted due to an exception. State: " + currentState, e);
        } finally {
            SwingUtilities.invokeLater(() -> ui.showCompletionMessage(currentState == State.CANCELLED));
        }
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public State getCurrentState() {
        return currentState;
    }
}
