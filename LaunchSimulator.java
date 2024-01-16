
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * LaunchSimulator es la clase principal para el Simulador de Lanzamiento Apollo 11.
 * Configura el logger y crea la UI. La cuenta regresiva comienza con la entrada del usuario.
 */
public class LaunchSimulator {
    private static final Logger logger = Logger.getLogger(LaunchSimulator.class.getName());

    /**
     * Punto de entrada principal de la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        setupLogger();
        SwingUtilities.invokeLater(() -> {
            CountdownUI ui = new CountdownUI(logger);
            ui.setVisible(true);
        });
    }

    /**
     * Configura el logger para la aplicación.
     * Los logs se escribirán en el archivo 'launch_simulator.log' en la carpeta 'logs'.
     */
    private static void setupLogger() {
        logger.setLevel(Level.ALL);
        try {
            // Crear el directorio 'logs' si no existe
            new File("logs").mkdir();

            // Configurar el logger para escribir en el archivo
            FileHandler fileHandler = new FileHandler("logs/launch_simulator.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al configurar el logger", e);
        }
    }
}
