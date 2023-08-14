package game.Weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.Unsheathe;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;
import game.Capability.UniqueSkill;
/**
 * Uchigatana class that represents an Uchigatana Weapon Item
 * Implements Purchasable and Sellable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Purchasable,Sellable
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {


    /**
     * Constructor for Uchigatana with Unsheathe
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        this.addCapability(UniqueSkill.UNSHEATHE);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }
    /**
     * Purchasing price set for Traders
     * @return Purchase Price
     */
    @Override
    public int buyPrice() {
        return 5000;
    }
    /**
     * Selling price set for Traders
     * @return Selling Price
     */
    @Override
    public int sellPrice() {
        return 500;
    }

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill
     * against one targeted Actor (i.e, special attack, heal, stun, etc.).
     * @param target target actor
     * @return a special Action that can be performed by this weapon (perform special attack on the enemy, etc.)
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Unsheathe(target,direction,this);
    }


}
