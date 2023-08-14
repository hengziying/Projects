package game.Item;

import edu.monash.fit2099.engine.items.Item;
import game.Capability.ExcahangeableItem;
import game.Capability.Status;
import game.Trade.Exchangeable;
import game.Trade.Sellable;

/**
 * Remembrance class, an item which can be trade for sale and exchange with the trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Item,Sellable,Exchangeable
 */
public class Remembrance extends Item implements Sellable{

  /**
   * Constructor of Remembrance
   */
  public Remembrance() {
    super("Remembrance of the Grafted", 'O', true);
    this.addCapability(Status.SELLITEM);
    this.addCapability(ExcahangeableItem.REMEMBRANCE);
  }


  /**
   * Return the selling price of Remembrance
   */
  @Override
  public int sellPrice() {
    return 20000;
  }




}
