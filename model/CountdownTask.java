package model;

import view.CountdownUI;
import java.util.logging.Logger;

/**
 * La clase CountdownTask gestiona la lógica de la cuenta atrás para el simulador de lanzamiento.
 * Se encarga de realizar la cuenta atrás y de notificar a la interfaz de usuario sobre el progreso.
 */
public class CountdownTask extends Thread {
    private static final State RUNNING = State.RUNNING;
    private final int totalSeconds;
    private final CountdownUI ui;
    private final Logger logger;
    private boolean running;

    /**
     * Constructor de la clase CountdownTask.
     *
     * @param logger       Logger para registrar eventos.
     * @param totalSeconds Total de segundos para la cuenta atrás.
     * @param ui           La interfaz de usuario asociada a esta tarea.
     */
    public CountdownTask(Logger logger, int totalSeconds, CountdownUI ui) {
        this.logger = logger;
        this.totalSeconds = totalSeconds;
        this.ui = ui;
        this.running = true;
    }

    /**
     * El código a ejecutar cuando la tarea se inicia.
     * Gestiona la cuenta atrás y actualiza la interfaz de usuario.
     */
    @Override
    public void run() {
        int remainingSeconds = totalSeconds;
        while (remainingSeconds > 0 && running) {
            // Actualizar la interfaz de usuario con el tiempo restante
            ui.update(remainingSeconds);

            try {
                // Esperar un segundo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warning("CountdownTask interrumpido: " + e.getMessage());
                running = false; // Terminar la cuenta atrás si el hilo es interrumpido
            }

            remainingSeconds--;
        }

        if (running) {
            // La cuenta atrás finalizó correctamente
            ui.showCompletionMessage(false); // false significa que la cuenta atrás no fue cancelada
        } else {
            // La cuenta atrás fue cancelada
            ui.showCompletionMessage(true); // true significa que la cuenta atrás fue cancelada
        }
    }

    /**
     * Cancela la cuenta atrás.
     */
    public void cancel() {
        running = false;
        this.interrupt(); // Interrumpir el hilo si está en un estado de espera (sleep)
    }

    /**
     * Devuelve el estado actual de la tarea.
     *
     * @return el estado actual de la tarea
     */
    public State getCurrentState() {
        if (running) {
            return RUNNING;
        } else {
            return State.BLOCKED;
        }
    }

    /**
     * Enumeración de los posibles estados de la tarea.
     */
    public enum State {
        NEW,
        RUNNING,
        BLOCKED,
        TERMINATED
    }
}