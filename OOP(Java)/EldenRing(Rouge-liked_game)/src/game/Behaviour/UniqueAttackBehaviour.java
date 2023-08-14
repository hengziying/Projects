package game.Behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * UniqueAttackBehaviour class that represents a behaviour that performs an unique attack action.
 * @author MA_Lab06_Group2
 * @version 2.0.0
 * @see Behaviour
 */

public class UniqueAttackBehaviour implements Behaviour {
    /**
     * Target actor of the AreaAttack
     */
    private Actor target;

    /**
     * Unique attack action
     */
    private Action uniqueAttack;

    /**
     * Constructor for AreaAttackBehaviour class
     * @param subject Target of the AreaAttack
     * @param uniqueAttack Action of a unique attack
     */
    public UniqueAttackBehaviour(Actor subject,Action uniqueAttack) {
        this.target = subject;
        this.uniqueAttack = uniqueAttack;
    }

    /**
     * Function that returns an unique attack action
     * @param actor the Enemy acting the unique attack
     * @param map the GameMap containing the Enemy
     * @return an unique attack action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);


        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there) {
                return uniqueAttack;
            }

        }
        return null;
    }

}
