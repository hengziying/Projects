package game.Runes;

import edu.monash.fit2099.engine.items.Item;
/**
 * Rune class that represents a Rune and extends from Item
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Item
 */

public class Rune extends Item{
    /**
     * Amount of Runes
     */
    private int amount;

    /**
     * Constructor
     * @param amount Amount of Runes
     */
    public Rune(int amount){
        super("Rune", '$', true);
        setAmount(amount);
    }


    /**
     * Setter for amount of runes
     * @param amount New amount of Runes
     */
    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * Getter for amount of Runes
     * @return Amount of Runes
     */
    public int getAmount(){
        return amount;
    }


}

