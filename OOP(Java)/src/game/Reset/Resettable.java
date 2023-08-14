package game.Reset;

import edu.monash.fit2099.engine.positions.GameMap;
import game.Runes.Rune;


/**
 * A resettable interface
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public interface Resettable {
    /**
     * Reset function that takes gameMap as input and reset game when called
     * @param map GameMap object
     */
    void reset(GameMap map);
}
