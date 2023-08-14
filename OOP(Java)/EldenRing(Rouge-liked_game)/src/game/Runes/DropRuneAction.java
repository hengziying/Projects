package game.Runes;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
/**
 * DropRuneAction class that represents an action dropping runes
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see DropItemAction
 */
public class DropRuneAction extends DropItemAction {
    /**
     * Final value of Runes
     */
    private final Rune rune;

    /**
     * Constructor for DropRuneAction
     * @param rune the item to drop
     */
    public DropRuneAction(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Function to execute DropRuneAction, dropping the rune at the previous location of player
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return Menu description for the actor
     */
   @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.setRune(new Rune(0));
        player.getPrevLocation().addItem(this.rune);
        return menuDescription(actor);
    }
}