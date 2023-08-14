package game.Behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Action.AttackAction;
/**
 * AttackBehaviour class that represents a behaviour performed by the enemy to attack the player.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour {
    /**
     * Target of the Attack
     */
    private final Actor target;
    /**
     * Direction of the Attack
     */
    private String direction;
    /**
     * Weapon used for the Attack
     */
    private Weapon weapon;

    /**
     * Constructor for AttackBehaviour class
     * @param subject Target of the Attack
     * @param direction Direction of the Attack
     */
    public AttackBehaviour(Actor subject, String direction) {
        this.target = subject;
        this.direction =direction;
    }
    /**
     * Overloaded Constructor for AttackBehaviour class if enemy has a weapon
     * @param subject Target of the Attack
     * @param direction Direction of the Attack
     * @param weapon  Weapon used for the Attack
     */
    public AttackBehaviour(Actor subject, String direction, Weapon weapon) {
        this.target = subject;
        this.direction =direction;
        this.weapon = weapon;
    }
    /**
     * Function that returns an
     * @param actor the Actor acting the attack action
     * @param map the GameMap containing the Actor
     * @return actual Attack action executed by the actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        AttackAction attackAction;
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);


        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there) {
                if (weapon == null) {
                    attackAction = new AttackAction(target,direction);
                }
                else{
                    attackAction = new AttackAction(target,direction,weapon);
                }
                return attackAction;

            }

        }

        return null;


    }



}
