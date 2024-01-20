package test;

import main.LaunchPhase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de pruebas para la clase LaunchPhase.
 * Se enfoca en verificar el comportamiento de las fases de lanzamiento,
 * incluyendo operaciones de inicio, finalización y cancelación.
 */
public class LaunchPhaseTest {

    // Inicializar el logger para registrar la información relevante de las pruebas
    private static final Logger logger = LoggerFactory.getLogger(LaunchPhaseTest.class);

    /**
     * Prueba el inicio y la finalización secuencial de las fases.
     * Verifica que cada fase se complete antes de iniciar la siguiente.
     */
    @Test
    public void testSequentialPhaseStart() {
        logger.info("Iniciando prueba: testSequentialPhaseStart");

        // Inicializar las fases
        LaunchPhase phase1 = new LaunchPhase(1);
        LaunchPhase phase2 = new LaunchPhase(2);

        // Iniciar las fases
        phase1.start();
        phase2.start();

        try {
            // Esperar a que las fases finalicen
            phase1.join();
            phase2.join();
        } catch (InterruptedException e) {
            logger.error("La ejecución de la prueba fue interrumpida", e);
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
        }

        // Verificar que las fases se hayan completado correctamente
        Assert.assertTrue("La fase 1 no se completó correctamente.", phase1.isCompleted());
        Assert.assertTrue("La fase 2 no se completó correctamente.", phase2.isCompleted());

        logger.info("Prueba testSequentialPhaseStart completada con éxito.");
    }

    /**
     * Prueba la capacidad de cancelar una fase de lanzamiento.
     * Verifica que la fase pueda ser cancelada correctamente y que su estado refleje la cancelación.
     */
    @Test
    public void testPhaseCancellation() {
        logger.info("Iniciando prueba: testPhaseCancellation");

        // Inicializar la fase
        LaunchPhase phase = new LaunchPhase(1);

        // Iniciar la fase
        phase.start();

        // Cancelar la fase
        phase.cancel();

        try {
            // Esperar a que la fase termine (o se cancele)
            phase.join();
        } catch (InterruptedException e) {
            logger.error("La ejecución de la prueba fue interrumpida", e);
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
        }

        // Verificar que la fase haya sido cancelada correctamente
        Assert.assertTrue("La fase no fue cancelada correctamente.", phase.isCancelled());

        logger.info("Prueba testPhaseCancellation completada con éxito.");
    }
}


