package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Trade.*;
import game.Player.Player;

/**
 * Exchange is a class that represents a exchange action with player and trader extended from Action
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Exchange extends Action {
  /**
   * The weapon that player is going to exchanage using an item
   */
  private Exchangeable weapon;
  /**
   * Trader that player is exchanging with
   * */
  private Trader trader;
  /**
   * The item that player are going to exchange a weapon from the trader
   */
  private Item item;

  /**
   * Constructor for Exchange class
   * @param weapon Exchangeable item that player is going to exchange
   * @param trader Trader that player is exchanging from
   * @see Exchangeable
   */
  public Exchange(Exchangeable weapon, Trader trader, Item item) {
    this.weapon = weapon;
    this.trader = trader;
    this.item = item;

  }

  /**
   * Execute the action of purchasing
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return string of description for player to purchase item,
   * return a string of description for player to exchange item
   */

  @Override
  public String execute(Actor actor, GameMap map) {
    Player player = (Player) actor;
    PurchaseSell ps = new PurchaseSell(player, this.trader);
    ps.exchange(this.weapon,item);
    return menuDescription(player);
  }

  /**
   * description in menu on console for player to exchange weapon with an item
   * @param actor The actor performing the action.
   * @return string of description for player exchange weapon with an item
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " exchanges " + this.item +" for " +this.weapon;
  }
}
