package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Trade.Trader;
import game.Trade.Sellable;
import game.Trade.PurchaseSell;
/**
 * Sell class for player to sell items to trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Sell extends Action {
    /**
     * Trader that player is selling to
     */
    private Trader trader;
    /**
     * Sellable weapon that player is selling
     */
    private Sellable weapon;

    /**
     * Sell class constructor
     * @param weapon Sellable weapon that player is selling
     * @param trader Trader that player is selling to
     */
    public Sell(Sellable weapon, Trader trader){
        this.weapon = weapon;
        this.trader = trader;
    }
    /**
     * Execute the action of selling
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menu description specifying that player sells weapon for certain price
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        PurchaseSell ps = new PurchaseSell(player, this.trader);
        ps.sell(this.weapon);

        return menuDescription(player);
    }
    /**
     * Returns a description in menu on console for player to sell weapon for certain price
     * @param actor The actor performing the action.
     * @return string of description for player to sell weapon for certain price
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" sells " + this.weapon + " for " + this.weapon.sellPrice();
    }
}
