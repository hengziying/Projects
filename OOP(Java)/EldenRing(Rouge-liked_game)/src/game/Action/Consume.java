package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Item.Consumable;
import game.Player.Player;
/**
 * Consume class for player to consume consummable items
 * @author MA_Lab06_Group2
 * @version 2.0.0
 * @see Action
 */
public class Consume extends Action {
    /**
     * The Item to be consumed
     */
    Consumable item;

    /**
     * Constructor
     * @param item item to be consumed
     */
    public Consume(Consumable item){
        this.item = item;
    }

    /**
     * execution of consuming a consumable item by player
     * @param actor The actor performing the consumption
     * @param map The map the actor is on.
     * @return string of description in menu on console for player of consuming the item
     */
    ;
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        boolean retVal = item.consumed(player,map);
        if (!retVal){
            return "The" + item + "is empty (" + item.getBalance()+")";
        }
        

        return menuDescription(player);
    }
    /**
     * Returns a description in menu on console for player to consume the item
     * @param actor The actor performing the action.
     * @return string of description for player to consume the item
     */
    @Override
    public String menuDescription(Actor actor) {
        Player player = (Player) actor;
        return player + " consumes " + item +  item.getBalance();
    }
}
