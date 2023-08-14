package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.UniqueSkill;

import java.util.Random;
/**
 * AreaAttack class for all the actor to perform area attack
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */

public class AreaAttack extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;
    /**
     * AreaAttack Constructor with target and direction
     * @param target Target actor to be attacked
     * @param direction Direction of the Actor
     */
    public AreaAttack(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }
    /**
     * AreaAttack Constructor with target , direction and weapon
     * @param target Target actor to be attacked
     * @param direction Direction of the Actor
     */
    public AreaAttack(Actor target, String direction,Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }
    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location locationOfActor = map.locationOf(actor);
        String result = "";
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        //perform attack on the target
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            result += actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage with area attack using "+weapon;
        target.hurt(damage);
        if (!target.isConscious()) {
            result += System.lineSeparator()+ new DeathAction(actor).execute(target, map);
        }
        //loop through the exit to check whether there is any actor to perform the attack
        for (Exit exit : locationOfActor.getExits()) {
            Location otherActorLocation = exit.getDestination();
            if (map.isAnActorAt(otherActorLocation) && otherActorLocation != map.locationOf(target)) {
                Actor otherActor = map.getActorAt(otherActorLocation);
                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    result += System.lineSeparator()+ actor + " misses " + otherActor + ".";
                }
                result += System.lineSeparator()+ actor + " " + weapon.verb() + " " + otherActor + " for " + damage + " damage with area attack using "+weapon;
                otherActor.hurt(damage);
                if (!otherActor.isConscious()) {
                    result += System.lineSeparator()+ new DeathAction(actor).execute(otherActor, map);
                }
            }

        }

        return result;
    }
    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu user interface
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " slam area attack on "+ target + " with Scimitar";
    }
}
