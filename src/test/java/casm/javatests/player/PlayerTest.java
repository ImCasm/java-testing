package casm.javatests.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void playerWins() {
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(3);

        assertTrue(new Player(dice, 3).play());
    }

    @Test
    public void playerLose() {
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(1);

        assertFalse(new Player(dice, 2).play());
    }
}