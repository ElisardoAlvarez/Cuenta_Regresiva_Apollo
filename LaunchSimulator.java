import javax.swing.*;

public class LaunchSimulator {
    public static void main(String[] args) {
        // Inicia la interfaz de usuario en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            CountdownUI ui = new CountdownUI();
            ui.setVisible(true);
        });
    }
}

