package game.Trade;
/**
 * common interface for all the items that are sellable from trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public interface Sellable {
    /**
     * common method of getting sell price of item for all sellable items
     * @return price of the item
     */
    int sellPrice();
}
