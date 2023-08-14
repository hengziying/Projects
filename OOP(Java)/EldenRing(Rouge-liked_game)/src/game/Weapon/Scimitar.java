package game.Weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.AreaAttack;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;
import game.Capability.UniqueSkill;
/**
 * Scimitar class that represents Scimitar Weapon Item
 * Implements Purchasable and Sellable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Purchasable,Sellable
 */
public class Scimitar extends WeaponItem implements Purchasable, Sellable {

    private int damage;
    /**
     * Constructor for Scimitar
     */
    public Scimitar(){
        super("Scimitar", 's', 118, "spin", 88);
        this.addCapability(UniqueSkill.AREA_ATTACK);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }
    /**
     * Purchasing price set for Traders
     * @return Purchasing Price
     */
    @Override
    public int buyPrice() {
        return 600;
    }
    /**
     * Selling price set for Traders
     * @return Selling Price
     */
    @Override
    public int sellPrice() {
        return 100;
    }

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill
     * against one targeted Actor (i.e, special attack, heal, stun, etc.).
     * @param target target actor
     * @return a special Action that can be performed by this weapon (perform special attack on the enemy, etc.)
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttack(target,direction,this);
    }

}
