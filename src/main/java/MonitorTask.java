import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorTask implements Runnable {
    private final CountdownTask countdownTask;
    private final Logger logger;
    private CountdownTask.State lastState;

    public MonitorTask(CountdownTask countdownTask, Logger logger) {
        this.countdownTask = countdownTask;
        this.logger = logger;
        this.lastState = null;
    }

    @Override
    public void run() {
        while (countdownTask.isAlive()) {
            CountdownTask.State currentState = countdownTask.getCurrentState();
            if (currentState != lastState) {
                logger.log(Level.INFO, "Countdown Task State Changed: " + currentState);
                lastState = currentState;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Monitor thread interrupted", e);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
