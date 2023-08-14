package game.Item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Reset.Resettable;
import game.Trade.Exchangeable;

/**
 * Consumable interface that represents a consumable item
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public interface Consumable {
  /**
   * Check the availability for consume
   */
  boolean isAvailaible();
  /**
   * Get the balnce for consume
   */
  String getBalance();
  /**
   * Consume the item as an player
   * @param player player
   */
  boolean consumed(Player player, GameMap map);
}