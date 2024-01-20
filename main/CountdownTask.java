package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class CountdownTask extends Thread {
    private Logger logger;
    private int countdownSeconds;
    private CountdownUI countdownUI; // Interfaz de usuario para la cuenta regresiva

    public CountdownTask(Logger logger, int countdownSeconds, CountdownUI countdownUI) {
        this.logger = logger;
        this.countdownSeconds = countdownSeconds;
        this.countdownUI = countdownUI; // Inicializa la interfaz de usuario para la cuenta regresiva
    }

    @Override
    public void run() {
        try {
            for (int i = countdownSeconds; i >= 0; i--) {
                final int remainingSeconds = i;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        countdownUI.updateCountdown(remainingSeconds); // Actualiza la interfaz de usuario con los segundos restantes
                    }
                });
                logger.info("Tiempo restante: " + i + " segundos");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.severe("La tarea de cuenta regresiva fue interrumpida");
        }
    }

    public State getCurrentState() {
        if (isInterrupted()) {
            return State.BLOCKED;
        } else if (isAlive()) return State.WAITING;
        else {
            return State.TERMINATED;
        }
    }
}