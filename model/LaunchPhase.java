package model;

/**
 * Clase que representa una fase de lanzamiento. Puede ser iniciada, completada o cancelada.
 */
public class LaunchPhase extends Thread {

    private final int phaseNumber;
    private volatile boolean completed = false;
    private volatile boolean cancelled = false;

    public LaunchPhase(int phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    @Override
    public void run() {
        try {
            // Simular trabajo de la fase
            performPhaseWork();
            // Marcar la fase como completada si no se canceló
            if (!cancelled) {
                completed = true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
        }
    }

    private void performPhaseWork() throws InterruptedException {
        // Simular trabajo (debe ser reemplazado por la lógica real de la fase)
        Thread.sleep(1000);
    }

    public void cancel() {
        cancelled = true;
        this.interrupt();
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
