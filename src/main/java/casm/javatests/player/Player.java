package casm.javatests.player;

import com.sun.istack.internal.NotNull;

public class Player {

    private Dice dice;
    private int minNumberToWin;

    public Player(@NotNull Dice dice, int minNumberToWin) {
        this.dice = dice;
        this.minNumberToWin = minNumberToWin;
    }

    public boolean play() {
        return dice.roll() >= minNumberToWin;
    }
}
