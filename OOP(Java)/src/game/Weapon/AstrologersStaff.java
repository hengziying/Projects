package game.Weapon;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.Status;
import game.Trade.Purchasable;
import game.Trade.Sellable;


/**
 * AstrologersStaff class represents an AstrologersStaff
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem
 */
public class AstrologersStaff extends WeaponItem implements Purchasable, Sellable {
    /**
     * Constructor for AstrologersStaff
     *
     */
    public AstrologersStaff() {
        super("Astrologer's Staff", 'f', 274, "shoot", 50);
        this.addCapability(Status.SELLITEM);
        this.addCapability(Status.PURCHASEITEM);
    }

    @Override
    public int buyPrice() {
        return 800;
    }

    @Override
    public int sellPrice() {
        return 100;
    }
}
