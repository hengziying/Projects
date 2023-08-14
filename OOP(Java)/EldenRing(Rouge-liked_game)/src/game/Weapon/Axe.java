package game.Weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.Status;
import game.Trade.Exchangeable;
import game.Trade.Purchasable;
import game.Trade.Sellable;

/**
 * Axe class represents an Axe
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Exchangeable,Sellable
 */
public class Axe extends WeaponItem implements Sellable, Exchangeable {
    /**
     * Constructor for Axe
     */
    public Axe() {
        super("Axe", 'T', 142, "Godrick", 84);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }
    /**
     * Selling price for Axe
     * @return Selling price for Axe
     */
    @Override
    public int sellPrice() {
        return 100;
    }
}
