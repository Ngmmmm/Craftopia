package test;

import main.GamePanel;
import main.KeyHandler;
import sprites.Avatar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AvatarTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Avatar avatar;

    // Initialisation avant chaque test
    @BeforeEach
    void setUp() {
        // Crée une instance de GamePanel simplifiée
        gamePanel = new GamePanel() {
            @Override
            public int getWidth() {
                return 480; // Largeur de l'écran simulée
            }

            @Override
            public int getHeight() {
                return 360; // Hauteur de l'écran simulée
            }
        };

        // Crée une instance de KeyHandler sans dépendance à GamePanel
        keyHandler = new KeyHandler(gamePanel); 

        // Crée une instance de Avatar en passant le GamePanel et KeyHandler
        avatar = new Avatar(gamePanel, keyHandler); 
    }

    // Test pour vérifier que l'avatar est bien initialisé
    @Test
    void testAvatarInitialization() {
        // Vérifie que l'avatar a bien été créé
        assertNotNull(avatar); 
        // Vérifie que l'avatar est bien lié au GamePanel
        assertEquals(gamePanel, avatar.getGp()); 
    }

    // Test pour vérifier les mouvements de l'avatar
    @Test
    void testAvatarMoveLeft() {
        // Position initiale de l'avatar
        int initialX = avatar.getScreenX(); 

        // Simuler que la touche "gauche" est enfoncée
        keyHandler.leftPressed = true; 
        avatar.update(); // Met à jour l'avatar avec la simulation de la touche

        // Vérifie que l'avatar a bougé vers la gauche
        assertTrue(avatar.getScreenX() < initialX); 
    }

    @Test
    void testAvatarMoveRight() {
        // Position initiale de l'avatar
        int initialX = avatar.getScreenX(); 

        // Simuler que la touche "droite" est enfoncée
        keyHandler.rightPressed = true; 
        avatar.update(); // Met à jour l'avatar avec la simulation de la touche

        // Vérifie que l'avatar a bougé vers la droite
        assertTrue(avatar.getScreenX() > initialX); 
    }

    @Test
    void testAvatarMoveUp() {
        // Position initiale de l'avatar
        int initialY = avatar.getScreenY(); 

        // Simuler que la touche "haut" est enfoncée
        keyHandler.upPressed = true; 
        avatar.update(); // Met à jour l'avatar avec la simulation de la touche

        // Vérifie que l'avatar a bougé vers le haut
        assertTrue(avatar.getScreenY() < initialY); 
    }

    @Test
    void testAvatarMoveDown() {
        // Position initiale de l'avatar
        int initialY = avatar.getScreenY(); 

        // Simuler que la touche "bas" est enfoncée
        keyHandler.downPressed = true; 
        avatar.update(); // Met à jour l'avatar avec la simulation de la touche

        // Vérifie que l'avatar a bougé vers le bas
        assertTrue(avatar.getScreenY() > initialY); 
    }
}
