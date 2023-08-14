package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Runes.Rune;
import game.Runes.RuneManager;

/**
 * This class is for the player to recover his lost runes
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class RecoverRunes extends PickUpAction{
    /**
     * Runes to be retrieved
     */
    private final Rune rune;
    private RuneManager runeManager = RuneManager.getInstance();
    /**
     * Constructor for RecoverRunes class
     * @param rune Runes value
     */
    public RecoverRunes(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * pick up rune item from the ground of the map that the player is on
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return execute method from PickUpAction class and return the menu description of the class
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        runeManager.addRune(player,this.rune);
        return super.execute(actor, map);
    }
    /**
     * menu description of the action on the console
     * @param actor The actor performing the action.
     * @return a description in menu on console that the player has retrieved the rune and the value of the rune
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves Runes (value:  " + this.rune.getAmount()+")";
    }
}
