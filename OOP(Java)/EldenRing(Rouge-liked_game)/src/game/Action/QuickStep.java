package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player.Player;
import game.Weapon.GreatKnife;


import java.util.Random;

/**
 * QuickStep class for all the actor to perform quickstep provided by GreatKnife
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class QuickStep extends Action {
    /**
     * The weapon that has this specific action
     */
    GreatKnife greatKnife;
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Actor that is the target
     */
    Actor target;
    /**
     * The direction of incoming attack.
     */
    private String direction;
    /**
     * Constructor for QuickStep class
     * @param target Actor that is to be attacked
     * @param direction direction of incoming attack
     * @param greatKnife weapon used for the attack
     */
    public QuickStep(Actor target, String direction, GreatKnife greatKnife){
        this.greatKnife = greatKnife;
        this.target = target;
        this.direction = direction;
    }
    /**
     * excute the quickstep action on the target actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     * string of the target actor is hurt the by greatKnife with the damage
     * string of description for actor to be removed from the map if actor has not more hitpoints
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (!(rand.nextInt(100) <= greatKnife.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = greatKnife.damage();
        String result = actor + " " + greatKnife.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }
        Location locationOfplayer = map.locationOf(actor);

        for (Exit exit :
                locationOfplayer.getExits()){
            if (!map.isAnActorAt(exit.getDestination())){
                map.moveActor(actor,exit.getDestination());
                break;
            }
        }

        return result;
    }
    /**
     * Returns a description in menu on console for player execute quickstep on target
     * @param actor The actor performing the action.
     * @return string of description for actor performing quickstep
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " quicksteps "+ greatKnife +" on "+ target;
    }
}
