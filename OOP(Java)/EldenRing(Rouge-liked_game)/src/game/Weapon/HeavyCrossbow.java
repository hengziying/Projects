package game.Weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;

/**
 * HeavyCrossbow class that represents a Heavy Crossbow
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Purchasable,Sellable
 */
public class HeavyCrossbow  extends WeaponItem implements Purchasable,Sellable {

    /**
     * Constructor for HeavyCrossbow
     */
    public HeavyCrossbow() {
        super("Heavy Crossbow", '}', 64, " shoots ", 57);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }

    /**
     * Selling price for HeavyCrossbow
     * @return Selling price for HeavyCrossbow
     */
    @Override
    public int sellPrice() {
        return 100;
    }
    /**
     * Buying price for HeavyCrossbow
     * @return Buying price for HeavyCrossbow
     */
    @Override
    public int buyPrice() {
        return 1500;
    }


}
