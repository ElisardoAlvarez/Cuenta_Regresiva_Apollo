import javax.swing.*;
import java.io.IOException;
import java.util.logging.*;

public class LaunchSimulator {
    private static final Logger logger = Logger.getLogger(LaunchSimulator.class.getName());

    public static void main(String[] args) {
        setupLogger();
        SwingUtilities.invokeLater(() -> {
            CountdownUI ui = new CountdownUI(logger);
            ui.setVisible(true);
        });
    }

    private static void setupLogger() {
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("logs/launch_simulator.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al inicializar el logger", e);
        }
    }
}
