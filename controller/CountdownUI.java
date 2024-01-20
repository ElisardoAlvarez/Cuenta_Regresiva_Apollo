package controller;

import model.CountdownTask;
import view.CountdownUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Esta clase representa el controlador en el patrón MVC para la interfaz de usuario de la cuenta atrás.
 * Se encarga de procesar las acciones del usuario y actualizar la vista en consecuencia.
 */
class CountdownController {
    private CountdownUI view;
    private CountdownTask model;
    private Logger logger;

    /**
     * Constructor de la clase CountdownController.
     *
     * @param view la vista asociada a este controlador
     * @param logger el logger para registrar eventos
     */
    public CountdownController(CountdownUI view, Logger logger) {
        this.view = view;
        this.logger = logger;
    }

    /**
     * Inicia la cuenta atrás cuando se presiona el botón de inicio.
     *
     * @param e el evento de acción
     */
    public void startCountdown(ActionEvent e) {
        int seconds;

        try {
            seconds = Integer.parseInt(view.getInputFieldText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid number format");
            return;
        }

        model = new CountdownTask(logger, seconds, view);
        model.start();
    }

    /**
     * Cancela la cuenta atrás cuando se presiona el botón de cancelar.
     *
     * @param e el evento de acción
     */
    public void cancelCountdown(ActionEvent e) {
        if (model != null) {
            model.cancel();
        }
    }
}
