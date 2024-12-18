package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import item.ressources.Bois;

public class RessourceTest {
    static Bois bois;

    @BeforeEach
    void setUp() {
        bois = new Bois();
    }

    @Test
    void testGetName() {
        assertEquals("bois", bois.name, "Erreur nom de la ressource bois");
    }

    @Test
    void testGetQuantity() {
        assertEquals(0, bois.getQuantity(), "Erreur quantité bois");
    }

    @Test
    void testIncreaseQuantity() {
        bois.increaseQuantity(5);
        assertEquals(5, bois.getQuantity(), "Erreur augmentation quantité bois");
    }

    @Test
    void testReduceQuantity() {
        bois.setQuantity(7);
        bois.reduceQuantity(5);
        assertEquals(2, bois.getQuantity(), "Erreur réduction quantité bois");
    }

    @Test
    void testSetQuantity() {
        bois.setQuantity(9);
        assertEquals(9, bois.getQuantity(), "Erreur modication quantité bois");
    }

    @AfterAll
    static void cleanUpAfterAllTests() {
        bois = null; // Libère la référence à l'objet bois (garbage collector s'en occupera)
        System.out.println("Nettoyage après tous les tests");
    }

}
