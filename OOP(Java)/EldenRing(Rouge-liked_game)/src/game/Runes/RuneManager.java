package game.Runes;

import edu.monash.fit2099.engine.actors.Actor;
import game.Player.Player;
import game.Reset.ResetManager;
import game.SummonCharacter.SummonCharacter;
import game.Utils.RandomNumberGenerator;
/**
 * Runemanager class that manages the trading of runes.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public class RuneManager {
    /**
     * Rune Manager instance
     */
    private static RuneManager instance = null;

    /**
     * RuneManager's getInstance is to create a new RuneManager class if instance is null
     * @return instance
     */
    public static RuneManager getInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Random runes amount generator
     * @return randomly generated Rune value
     */
    public Rune  randomRunes(int low,int high){
        int randRunes = RandomNumberGenerator.getRandomInt(low, high);
        return new Rune(randRunes);
    }

    /**
     * Function to add Runes from player's Rune account
     * @param actor a Runeusable actor
     * @param rune rune to add
     */
    public void addRune(RuneUsable actor, Rune rune){
        Rune newRune =  new Rune(rune.getAmount() + actor.getRune().getAmount());
        actor.setRune(newRune);
    }

    /**
     * Function to deduct Runes from player's Rune account
     * @param actor a Runeusable actor
     * @param rune rune to deduct
     */
    public void deductRune(RuneUsable actor, Rune rune){
        Rune newRune =  new Rune(actor.getRune().getAmount()-rune.getAmount());
        actor.setRune(newRune);
    }
}
