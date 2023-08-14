package game.Trade;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.Purchase;
import game.Action.Sell;
import game.Capability.ActorType;
import game.Capability.Status;
import game.Player.Player;
import game.Weapon.*;

/**
 * MerchantKale class that represents Merchant Kale trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Trader
 */
public class MerchantKale extends Trader{

    /**
     * Constructor for MerchantKale trader, parameters extended from super class
     */
    public MerchantKale() {
        super("Trader",'K',0);
        super.addWeaponToInventory(new Uchigatana());
        super.addWeaponToInventory(new Club());
        super.addWeaponToInventory(new GreatKnife());
    }

    /**
     * actions that are allowed by the other actors, sell and purchase
     * @param otherActor the actor that is about to trader with trader
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of actions that are allowed - sell and purchase or either one
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(ActorType.PLAYER)){
            Player player = (Player) otherActor;
            for (WeaponItem weapon : this.getWeaponInventory()) {
                if(weapon.hasCapability(Status.PURCHASEITEM)){
                    actions.add(new Purchase((Purchasable) weapon, this));
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