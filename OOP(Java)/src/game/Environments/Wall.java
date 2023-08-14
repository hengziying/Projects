package game.Environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Wall class that represents a Wall ground extended from Spawning Grounds
 */
public class Wall extends Ground{
	/**
	 * Constructor for Wall
	 */
	public Wall() {
		super('#');
	}
	/**
	 * Function to check if Actor can pass through this block (Wall)
	 * @param actor EnemyCharacter that can wants to pass through
	 * @return  true if the actor is hostile to the enemy, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	/**
	 * Function to check if ThrownObjects can pass through this block (Wall)
	 * @return  true if objects are throwable through wall, false otherwise.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
