package game.Runes;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.Consume;
import game.Item.Consumable;
import game.Player.Player;

/**
 * GoldenRunes class that reperesent the golden runes which scatter in the map
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Item,Consumable
 */

public class GoldenRunes extends Item implements Consumable {
  /**
   * Rune Manager that manages the runes
   */
  private RuneManager runeManager= RuneManager.getInstance();
  /**
   * Minimum amount of rune that can be generated
   */
  private final int MIN_RUNE = 200;
  /**
   * Maximum amount of rune that can be generated
   */
  private final int MAX_RUNE = 10000;

  /**
   * Constructor
   */
  public GoldenRunes(){
    super("Golden Runes",'*',true);
    this.addAllowableAction(new Consume(this));
  }

  /**
   * Check the availability for consume
   */
  @Override
  public boolean isAvailaible() {
    return true;
  }

  /**
   * Get the balance for consuming, since one golden rune can only be consumed onece thus we can just return an empty string
   */
  @Override
  public String getBalance() {
    return "";
  }

  /**
   * Consume golden runes as an player
   * @param player player
   */
  @Override
  public boolean consumed(Player player, GameMap map){
    if (isAvailaible()){
      Rune randRunes = runeManager.randomRunes(MIN_RUNE,MAX_RUNE);
      runeManager.addRune(player,randRunes);
      player.removeItemFromInventory(this);
      map.locationOf(player).removeItem(this);
      return true;
    }
    return false;
  }

  /**
   * Function to add newAction
   * @param newAction newAction to be added
   */
  public void addAllowableAction(Action newAction){
    this.addAction(newAction);
  }






}
