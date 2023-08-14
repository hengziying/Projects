package game.Weapon;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.Status;
import game.Trade.Exchangeable;
import game.Trade.Purchasable;
import game.Trade.Sellable;

/**
 * GraftedDragon class that represents a GraftedDragon
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see WeaponItem,Purchasable,Sellable
 */
public class GraftedDragon extends WeaponItem implements Sellable, Exchangeable {
  /**
   * Constructor for GraftedDragon
   */
  public GraftedDragon() {
    super("GraftedDragon", 'N', 89, "hit", 90);
    this.addCapability(Status.SELLITEM);

  }
  /**
   * Selling price for GraftedDragon
   * @return Selling price for GraftedDragon
   */
  @Override
  public int sellPrice() {
    return 200;
  }

}

