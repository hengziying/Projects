package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Teleport class that can teleport actor to another map
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Teleport extends Action {
    /**
     * Destination to teleport
     */
    private final Location destination;
    /**
     * The map of the destination
     */
    private final String destinationMap;

    public Teleport(Location destination, String destinationMap){
        this.destination = destination;
        this.destinationMap = destinationMap;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        map.moveActor(actor,destination);
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + destinationMap;
    }
}
