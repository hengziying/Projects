package game.Reset;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Reset.Resettable;
import game.Runes.Rune;

import java.util.ArrayList;
import java.util.List;

/**
 * ResetManager class is a class that manages Reset actions
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public class ResetManager {
    /**
     * List that contains resettables
     */
    private List<Resettable> resettables;
    /**
     * Reset Manager instance
     */
    private static ResetManager instance = null;



    /**
     * Constructor for ResetManager
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();

    }
    /**
     * Function to run reset action when called
     */
    public void run(GameMap map) {
        for (Resettable element:resettables){
            element.reset(map);
        }


    }

    /**
     * Function that adds resettable maps into resettable list
     * @param resettable Resettable
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }
    /**
     * Function that removes resettable maps from resettable list
     * @param resettable Resettable
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }
    /**
     * ResetManager's getInstance is to create a new ResetManager class if instance is null
     * @return instance
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }
}
