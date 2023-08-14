package game.SummonCharacter;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Reset.ResetManager;
import game.Reset.Resettable;

import java.util.ArrayList;
import java.util.List;
/**
 * Summon manager that manages the summon characters
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */

public class SummonManager {
    /**
     * List that contains SummonCharacter
     */
    private List<SummonCharacter> summonCharacters;
    /**
     * Reset Manager instance
     */
    private static SummonManager instance = null;

    /**
     * Constructor for SummonManager
     */
    private SummonManager() {
        this.summonCharacters = new ArrayList<>();

    }

    /**
     * Function to run Summon manager action when called
     */
    public void removeAllSummonChar(GameMap map) {
        for (SummonCharacter character:summonCharacters){
            character.remove(map);
        }
        this.resetList();
    }

    /**
     * Function that adds SummonCharacter into summonCharacters list
     * @param summonCharacter SummonCharacter
     */
    public void register(SummonCharacter summonCharacter) {
        this.summonCharacters.add(summonCharacter);
    }

    /**
     * Function that removes SummonCharacter from summonCharacters list
     * @param summonCharacter SummonCharacter
     */
    public void remove(SummonCharacter summonCharacter) {
        this.summonCharacters.remove(summonCharacter);
    }

    /**
     * SummonManager's getInstance is to create a new SummonManager class if instance is null
     * @return instance
     */
    public static SummonManager getInstance() {
        if (instance == null) {
            instance = new SummonManager();
        }
        return instance;
    }

    /**
     * Set the summonCharacters list to an empty list
     */
    public void resetList(){
        this.summonCharacters = new ArrayList<>();
    }
}
