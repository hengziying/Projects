package game.Item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.Consume;
import game.Item.Consumable;
import game.Player.Player;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.Trade.Exchangeable;
import game.Trade.Sellable;

/**
 * CrimsonTears class that represents Crimson Tears and extended from Item, implements and Consumable
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Item,Resettable,Exchangeable
 */
public class CrimsonTears extends Item implements Consumable {
    /**
     * Final integer that represent the maximum times the player can consume this item
     */
    private final int MAX_CONSUME_TIME = 2;
    /**
     * Counter that increments when the player consumes Crimson Tears
     */
    private int consumeTime;

    /**
     * Constructor of Crimson Tears
     */
    public CrimsonTears(){
        super("Flask Of Crimson Tears ",'c',false);
        this.consumeTime = 0;
        this.addAllowableAction(new Consume(this));

    }

    /**
     * Consume the item as an player
     * @param player player
     */
    @Override
    public boolean consumed(Player player,GameMap map){
        if (isAvailaible()){
            player.heal(250);
            int consumeTime = this.getConsumeTime();
            this.setConsumeTime(consumeTime+1);


            return true;
        }
        return false;
    }


    /**
     * Getter for Number of times Crimson Tears got consumed
     * @return Number of times Crimson Tears got consumed
     */
    public int getConsumeTime() {
        return consumeTime;
    }
    /**
     * Setter for Consume Times
     * @param consumeTime Integer that represents number of times Crimson Tears gor consumed
     */
    public void setConsumeTime(int consumeTime){
        this.consumeTime = consumeTime;
    }
    /**
     * Function to check if it is still available for Player to consume
     * @return True if it is still available, false otherwise
     */
    public boolean isAvailaible(){
        boolean ret_val = false;
        if (this.consumeTime < MAX_CONSUME_TIME){
            ret_val = true;
        }

        return ret_val;
    }

    /**
     * Get the balnce for consume
     */
    @Override
    public String getBalance() {
        String retStr = "("+ Integer.toString(2-this.getConsumeTime()) + "/" + Integer.toString(MAX_CONSUME_TIME)+")" ;
        return retStr ;
    }

    /**
     * Function to add newAction
     * @param newAction newAction to be added
     */
    public void addAllowableAction(Action newAction){
        this.addAction(newAction);
    }


}