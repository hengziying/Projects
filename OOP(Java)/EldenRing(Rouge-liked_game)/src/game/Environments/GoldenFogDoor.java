package game.Environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.Teleport;

/**
 * Door class that represents a Door to travel between maps
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public class GoldenFogDoor extends Ground {
    /**
     * destination destination that the door will lead
     */
    private Location destination;
    /**
     * destinationMap the map name of destination
     */
    private String destinationMap;

    /**
     * Constructor for Door
     * @param destination destination that the door will lead
     * @param destinationMap the map name of destination
     */
    public GoldenFogDoor(Location destination, String destinationMap) {
        super('D');
        this.destination = destination;
        this.destinationMap = destinationMap;
    }

    /**
     * List of allowable actions to be done at Lake Of Rot
     * @param actor The actor performing actions
     * @param location Location of Site of Lost Grace
     * @param direction String that represents direction to move to
     * @return actions based on different settings
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        actions.add(new Teleport(destination, destinationMap));
        return actions;
    }


}

