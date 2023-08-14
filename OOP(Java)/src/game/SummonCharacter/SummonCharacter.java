package game.SummonCharacter;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * SummonCharacter interface
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public interface SummonCharacter {
    /**
     * A method that can remove the Summon Character from the map
     */
    void remove(GameMap map);
}
