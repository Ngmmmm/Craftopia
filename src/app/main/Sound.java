package main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe Sound gère la lecture des fichiers audio dans une application
 * Java Swing.
 * 
 * <p>
 * Elle permet de jouer, boucler et arrêter des pistes audio.
 * </p>
 * 
 * <p>
 * Les principales méthodes de cette classe incluent :
 * </p>
 * 
 * <ul>
 * <li><strong>playMusic(int index)</strong> : Joue la piste audio associée à
 * l'index donné, en arrêtant tout autre son en cours.</li>
 * <li><strong>loopMusic(int index)</strong> : Joue la musique associée à
 * l'index donné en boucle.</li>
 * <li><strong>stopMusic()</strong> : Arrête le son en cours de lecture (s'il y
 * en a un).</li>
 * </ul>
 * 
 * <p>
 * Pour que la lecture audio fonctionne correctement, les fichiers audio doivent
 * être situés dans le répertoire "audio" et les chemins doivent être
 * correctement configurés dans la liste des pistes.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 3.1
 */
public class Sound {
    private Clip currentClip; // Instance de Clip pour gérer la lecture audio
    private final ArrayList<String> tracks; // Liste pour les musiques

    /**
     * Constructeur de la classe Sound.
     * Initialise la liste des pistes audio.
     */
    public Sound() {
        tracks = new ArrayList<>();
        // Ajouter des pistes musicales avec leur chemin
        tracks.add("./audio/8bit Persona 4 - Glimpse of a Memory.wav"); // 0
        tracks.add("./audio/8bit Persona 4 - Heartbreak Heartbreak.wav"); // 1
    }

    /**
     * Retourne la liste des pistes audio.
     * 
     * @return Une liste contenant les chemins des fichiers audio.
     */
    public ArrayList<String> getTracks() {
        return this.tracks;
    }

    /**
     * Joue la piste audio associée à l'index donné.
     * Si un autre son est en cours de lecture, il est arrêté avant de jouer la
     * nouvelle piste.
     * 
     * @param index L'index de la piste dans la liste des musiques.
     */
    public void playMusic(int index) {
        stopMusic(); // Arrêter tout son en cours
        try {
            File audioFile = new File(tracks.get(index));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start(); // Démarre la lecture
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

    /**
     * Joue la musique associée à l'index donné en boucle.
     * 
     * @param index L'index de la piste dans la liste des musiques.
     */
    public void loopMusic(int index) {
        stopMusic(); // Arrêter tout son en cours
        try {
            File audioFile = new File(tracks.get(index));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY); // Boucle infinie
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

    /**
     * Arrête la musique en cours de lecture.
     */
    public void stopMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop(); // Arrêter la lecture
            currentClip.close(); // Libérer les ressources
            currentClip = null;
        }
    }
}
