import javax.swing.*;

public class CountdownTask extends Thread {
    private final int totalSeconds;
    private final CountdownUI ui;

    public CountdownTask(int totalSeconds, CountdownUI ui) {
        this.totalSeconds = totalSeconds;
        this.ui = ui;
    }

    @Override
    public void run() {
        try {
            for (int i = totalSeconds; i >= 0; i--) {
                int minutes = i / 60;
                int seconds = i % 60;
                String timeFormatted = String.format("%02d:%02d", minutes, seconds);

                SwingUtilities.invokeLater(() -> ui.updateCountdownLabel(timeFormatted));

                Thread.sleep(1000);
            }
            ui.showCompletionMessage(false);
        } catch (InterruptedException e) {
            SwingUtilities.invokeLater(() -> ui.updateCountdownLabel("00:00"));
            ui.showCompletionMessage(true);
        }
    }
}
