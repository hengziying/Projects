package game.Environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.Rest;
/**
 * LostGrace class that represents a Lost Grace ground extended from Ground.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public class LostGrace extends Ground {
    /**
     * Constructor for LostGrace
     */
    public LostGrace(){

        super('U');
    }
    /**
     * List of allowable actions to be done at Site of Lost Grace
     * @param actor The actor performing actions
     * @param location Location of Site of Lost Grace
     * @param direction String that represents direction to move to
     * @return actions based on different settings
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        actions.add(new Rest());
        return actions;
    }



}
