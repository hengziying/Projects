package game.Trade;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player.Player;
import game.Runes.Rune;
import game.Runes.RuneManager;

import java.util.List;


/**
 * PurchaseSell class for all the actor that are purchasable to trade here
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public class PurchaseSell {
    /**
     * Player purchase items
     */
    private Player player;
    /**
     * TraderK sell items
     */
    private Trader trader;

    private RuneManager runeManager= RuneManager.getInstance();

    /**
     * Constructor for PurchaseSell class to initialize the player and trader's weapons
     * @param player player that can purchase
     * @param trader trader that can sell
     */
    public PurchaseSell(Player player, Trader trader){
        this.player = player;
        this.trader = trader;
    }


    /**
     * Method to purchase items from trader by player
     * @param tW item that are purchasable from trader
     * @return true if player has enough rune to purchase, false otherwise
     */
    public boolean purchase(Purchasable tW) {
        boolean retVal = true;

        if (player.getRune().getAmount() > tW.buyPrice()) {
            player.addWeaponToInventory((WeaponItem)tW);
            runeManager.deductRune(player, new Rune(tW.buyPrice()));


        } else {
            retVal = false;
        }
        return retVal;
    }

    /**
     * method to sell items to trader by player
     * @param tW items that can be sold
     */
    public void sell(Sellable tW)
    {
            player.removeWeaponFromInventory((WeaponItem) tW);
            runeManager.addRune(player, new Rune(tW.sellPrice()));
    }
    /**
     * method to exchange items to trader by player
     * @param tW items that can be exchange
     * @param item the item to exchange
     */
    public void exchange(Exchangeable tW, Item item)
    {
        player.addWeaponToInventory((WeaponItem) tW);
        player.removeItemFromInventory(item);

    }


}






