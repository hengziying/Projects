package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Despawn class for all the actor to despawn
 * This class is used to remove actor from the map
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 *
 */


public class Despawn extends Action {
    /**
     * @param actor The actor removed from the map
     * @param map The map the actor is on.
     * @return string of description for actor to be removed from the map
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }
    /**
     * descriptive string of actor to be removed from the map
     * @param actor The actor performing the action.
     * @return string of description for actor to be removed from the map
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map.";
    }
}
