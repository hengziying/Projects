package game.Environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Capability.GroundType;
import game.Capability.Status;

/**
 * Lake of Rot class represents a LakeOfRot ground
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public class LakeOfRot extends Ground {

    /**
     * Constructor of a LakeOfRot ground
     */
    public LakeOfRot() {
        super('^');
        this.addCapability(GroundType.FATAL);
    }

    /**
     * Tick function when actor enters the ground
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        int damage ;
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            if (actor.hasCapability(ActorType.PLAYER)) {
                damage = 35;
                actor.hurt(damage);
                System.out.println(actor + " has been decayed by " + damage + " damage");
            }
        }
    }


    /**
     * Function to check if Actor can pass through this block (lake of rot)
     * @param actor Character that can wants to pass through
     * @return  true if the actor is a player or Water type enemy, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return (actor.hasCapability(ActorType.PLAYER) || actor.hasCapability(EnemyType.WATER));
    }

}
