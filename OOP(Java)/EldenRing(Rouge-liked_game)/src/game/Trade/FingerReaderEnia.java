package game.Trade;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.Exchange;
import game.Action.Sell;
import game.Capability.ActorType;
import game.Capability.ExcahangeableItem;
import game.Capability.Status;
import game.Player.Player;
import game.Weapon.Axe;
import game.Weapon.GraftedDragon;

/**
 * FingerReaderEnia class that represents Finger Reader Enia trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Trader
 */
public class FingerReaderEnia extends Trader {

  /**
   * Constructor for FingerReaderEnia trader, parameters extended from super class
   */
  public FingerReaderEnia() {
    super("Finger Reader Enia", 'E', 0);
    this.addWeaponToInventory(new Axe());
    this.addWeaponToInventory(new GraftedDragon());
  }

  /**
   *  actions that are allowed by the other actors, sell and exchange
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = super.allowableActions(otherActor, direction, map);
    if(otherActor.hasCapability(ActorType.PLAYER)){
      Player player = (Player) otherActor;
      ExcahangeableItem excahangeableItem[] = ExcahangeableItem.values();
        for(Item item:player.getItemInventory()){
          for(ExcahangeableItem type: excahangeableItem){
            if (item.hasCapability(type)) {
              for (WeaponItem weapon : this.getWeaponInventory()) {
                actions.add(new Exchange((Exchangeable) weapon, this,item));
              }
            }
          }
        }

      for (WeaponItem weapon : player.getWeaponInventory()) {
        if(weapon.hasCapability(Status.SELLITEM)){
          actions.add(new Sell((Sellable) weapon, this));
        }
      }
      for(Item item : player.getItemInventory()){
        if (item.hasCapability(Status.SELLITEM)){
          actions.add(new Sell((Sellable) item, this));
        }
      }
    }
    return actions;
  }

}
