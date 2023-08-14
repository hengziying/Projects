package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Trade.MerchantKale;
import game.Trade.Purchasable;
import game.Trade.PurchaseSell;
import game.Trade.Trader;

/**
 * Purchase class for player to purchase items from trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Purchase extends Action {
    /**
     * Trader that player is purchasing from
     */
    private Trader trader;
    /**
     * Purchasable item that player is purchasing
     */
    private Purchasable weapon;
    /**
     * Constructor for Purchase class
     * @param weapon Purchasable item that player is purchasing
     * @param trader Trader that player is purchasing from
     * @see Purchasable
     */
    public Purchase(Purchasable weapon, Trader trader){
        this.weapon = weapon;
        this.trader = trader;
    }
    /**
     * Execute the action of purchasing
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string of description for player to purchase item,
     * if successful, return a string of description for player to purchase item
     * else, return a string of description for player to know that he/she doesn't have enough money
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        PurchaseSell ps = new PurchaseSell(player, this.trader);
        boolean ret = ps.purchase(this.weapon);
        if (ret == true) {
            return menuDescription(player);
        }
        else {
            return "You don't have enough money to buy this item";
        }

    }
    /**
     * description in menu on console for player to purchase weapon for certain price
     * @param actor The actor performing the action.
     * @return string of description for player to purchase weapon for certain price
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" purchases " + this.weapon + " for " + this.weapon.buyPrice();
    }
}
