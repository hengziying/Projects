package game.Weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;

/**
 * Club class that represents a Club Weapon Item
 * Implements Purchasable and Sellable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Purchasable,Sellable
 */
public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor for Club
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }

    /**
     * Purchase price set for Traders
     * @return Purchase Price
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


}
