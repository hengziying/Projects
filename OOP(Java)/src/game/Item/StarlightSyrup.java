package game.Item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.Consume;
import game.Player.Player;
import game.Reset.Resettable;
import game.Trade.Exchangeable;

/**
 * StarlightSyrup class that represents Starlight Syrup and extended from Item implements Consumable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Item, Consumable
 */
public class StarlightSyrup extends Item implements Consumable {

    /***
     * Constructor for StarLightSyrup.
     */
    public StarlightSyrup() {
        super("Starlight Syrup", '8', true);
        this.addAllowableAction(new Consume(this));
    }

    /**
     * Check the availability for consume
     */
    @Override
    public boolean isAvailaible() {
        return true;
    }

    /**
     * Get the balnce for consume
     */
    @Override
    public String getBalance() {
        return "";
    }

    /**
     * Consume the item as an player
     * @param player player
     */
    @Override
    public boolean consumed(Player player, GameMap map) {
        if(isAvailaible()){
            player.setStarlightConsumed(true);
            player.removeItemFromInventory(this);
            map.locationOf(player).removeItem(this);
            return true;
        }
        return false;
    }

    /**
     * Function to add newAction
     * @param newAction newAction to be added
     */
    public void addAllowableAction(Action newAction){
        this.addAction(newAction);
    }

}
