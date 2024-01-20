package test;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import main.LaunchPhase;

/**
 * Clase de pruebas para las fases del lanzamiento.
 */
public class LaunchPhaseTest {

    // Prueba de Inicio Secuencial de Fases
    @Test
    public void testSequentialPhaseStart() {
        // Inicialización de las fases
        LaunchPhase phase1 = new LaunchPhase(1);
        LaunchPhase phase2 = new LaunchPhase(2);
        LaunchPhase phase3 = new LaunchPhase(3);
        LaunchPhase phase4 = new LaunchPhase(4);

        // Iniciar las fases (simulando la secuencia)
        phase1.start();
        phase1.join();  // Asegura que la fase1 concluya antes de iniciar fase2
        phase2.start();
        phase2.join();  // Asegura que la fase2 concluya antes de iniciar fase3
        phase3.start();
        phase3.join();  // Asegura que la fase3 concluya antes de iniciar fase4
        phase4.start();
        phase4.join();  // Asegura que la fase4 concluya antes de continuar

        // Verificar el estado final de cada fase
        assertTrue(phase1.isCompleted());
        assertTrue(phase2.isCompleted());
        assertTrue(phase3.isCompleted());
        assertTrue(phase4.isCompleted());
    }

    //prueba de Cancelación de Fases
    @Test
    public void testPhaseCancellation() {
        // Inicialización de las fases
        LaunchPhase phase1 = new LaunchPhase(1);
        LaunchPhase phase2 = new LaunchPhase(2); // Supongamos que esta fase se cancelará

        // Iniciar fase1 y luego cancelar fase2 antes de que comience
        phase1.start();
        phase1.join();
        phase2.cancel();

        // Intentar iniciar fase2 (que ha sido cancelada)
        phase2.start();
        phase2.join();

        // Verificar el estado de la fase2 y fase1
        assertTrue(phase1.isCompleted());
        assertFalse(phase2.isStarted());  // phase2 nunca debió iniciar
    }
    //Prueba de Transición de Estado de Fases
    @Test
    public void testPhaseStateTransition() throws InterruptedException {
        // Inicialización de una fase
        LaunchPhase phase = new LaunchPhase(1);

        // Iniciar la fase
        phase.start();
        phase.join();

        // Verificar la transición de estado
        assertEquals(LaunchPhase.State.TERMINATED, phase.getState());
    }

}