package view;

import controller.CountdownController;

import javax.swing.*;
import java.awt.*;

public class CountdownUI extends JFrame {
    // UI components
    private JButton startButton;
    private JButton cancelButton;
    private JTextField inputField;

    private CountdownController controller;

    public CountdownUI(CountdownController controller) {
        this.controller = controller;
        createUIComponents();
        layoutComponents();
        attachEventHandlers();
    }

    private void createUIComponents() {
        startButton = new JButton("Start");
        cancelButton = new JButton("Cancel");
        inputField = new JTextField(20);
    }

    private void layoutComponents() {
        setLayout(new FlowLayout());
        add(inputField);
        add(startButton);
        add(cancelButton);
    }

    private void attachEventHandlers() {
        startButton.addActionListener(controller::startCountdown);
        cancelButton.addActionListener(controller::cancelCountdown);
    }

    public String getInputFieldText() {
        return inputField.getText();
    }

    public void update(int remainingSeconds) {
    }

    public void showCompletionMessage(boolean b) {
    }
}