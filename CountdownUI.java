import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CountdownUI extends JFrame {
    private JButton startButton;
    private JButton cancelButton;
    private JTextField inputField;
    private JLabel countdownLabel;
    private CountdownTask countdownTask;

    public CountdownUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Apollo 11 Launch Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        inputField = new JTextField(5);
        startButton = new JButton("Start");
        cancelButton = new JButton("Cancel");
        countdownLabel = new JLabel("00:00", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 30));

        panel.add(new JLabel("Enter seconds:"));
        panel.add(inputField);
        panel.add(startButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.NORTH);
        add(countdownLabel, BorderLayout.CENTER);

        pack();

        startButton.addActionListener(this::startCountdown);
        cancelButton.addActionListener(this::cancelCountdown);
    }

    private void startCountdown(ActionEvent e) {
        int seconds;
        try {
            seconds = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format");
            return;
        }

        countdownTask = new CountdownTask(seconds, this);
        countdownTask.start();
    }

    private void cancelCountdown(ActionEvent e) {
        if (countdownTask != null) {
            countdownTask.interrupt();
        }
    }

    public void updateCountdownLabel(String time) {
        countdownLabel.setText(time);
    }

    public void showCompletionMessage(boolean isCancelled) {
        String message = isCancelled ? "Launch Cancelled" : "Launch Successful";
        JOptionPane.showMessageDialog(this, message);
    }
}
