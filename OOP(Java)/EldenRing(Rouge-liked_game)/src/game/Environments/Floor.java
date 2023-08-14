package game.Environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Capability.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor for floor
	 */
	public Floor() {
		super('_');
	}
	/**
	 * Function to check if Actor can pass through this block (floor)
	 * @param actor Character that can wants to pass through
	 * @return  true if the actor is hostile to the enemy, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
	}
}
