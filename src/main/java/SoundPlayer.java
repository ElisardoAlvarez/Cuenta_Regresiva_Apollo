import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Clase para reproducir sonidos.
 */
public class SoundPlayer {

    /**
     * Reproduce un sonido desde un archivo.
     *
     * @param soundFileName Nombre del archivo de sonido a reproducir.
     */
    public void playSound(String soundFileName) {
        URL soundFileUrl = this.getClass().getResource(soundFileName);
        if (soundFileUrl == null) {
            System.err.println("Archivo de sonido no encontrado: " + soundFileName);
            return;
        }

        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFileUrl)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }
}

