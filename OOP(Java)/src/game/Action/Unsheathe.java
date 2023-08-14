package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Weapon.Uchigatana;

import java.util.Random;

/**
 * Unsheathe class for all the actor to perform unsheathe action provided by Uchigatana
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Unsheathe extends Action {
    /**
     * hitChance for the unsheathe action
     */
    private final int hitChance = 60;
    /**
     * The Uchigatana that has this specific action
     */
    Uchigatana uchigatana;
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * The Actor that is to be attacked
     */
    Actor target;
    /**
     * The direction of incoming attack.
     */
    private String direction;
    /**
     * Constructor for Unsheathe class
     * @param target actor that is to be attacked
     * @param direction direction of incoming attack
     * @param uchigatana Uchigatana that has this Unsheathe action
     */
    public Unsheathe(Actor target, String direction, Uchigatana uchigatana){
        this.uchigatana = uchigatana;
        this.target = target;
        this.direction = direction;
    }
    /**
     * execute the unsheathe action by the acton on the game map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return if missed, return message of missing the attack
     * else if, return the message of the target actor is hurt the by uchigatana with the damage
     * else if the target is not conscious, return the message of the target actor is removed from the map
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) <= hitChance)) {
            return actor + " misses " + target + ".";
        }

        int damage = uchigatana.damage()*2;
        String result = actor + " " + uchigatana.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }

        return result;
    }
    /**
     * return the menu description of the unsheathe action
     * @param actor The actor performing the action.
     * @return string of the actor unsheathes uchigatana on target on console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes "+ uchigatana +" on "+ target;
    }
}
