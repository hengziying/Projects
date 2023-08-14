package game.Weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.AreaAttack;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;
/**
 * Grossmesser class that represents Grossmesser Weapon item
 * Implements Sellable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Sellable
 */
public class Grossmesser extends WeaponItem implements Sellable {

    /**
     * Constructor for Grossmesser
     */
    public Grossmesser(){
        super("Grossmesser", '?', 115, "hits", 85);
        this.addCapability(Status.SELLITEM);
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
