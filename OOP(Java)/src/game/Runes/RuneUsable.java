package game.Runes;

import edu.monash.fit2099.engine.actors.Actor;
import game.SummonCharacter.SummonCharacter;

/**
 * RunUsable interface that represents the actor can use the runes.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */

public interface RuneUsable {
    /**
     * getter for the rune
     */
    Rune getRune();
    /**
     * Setter for the rune
     * @param rune Rune object
     */
    void setRune(Rune rune);
}
