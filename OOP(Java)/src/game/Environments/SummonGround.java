package game.Environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.Rest;
import game.Action.Summon;

/**
 * SummonGround class is a class to represent summon ground extended from Ground
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public class SummonGround extends Ground {
    Location summonLocation;
    /**
     * Constructor for SpawningGrounds
     *
     */
    public SummonGround(Location summonLocation) {
        super('=');
        this.summonLocation = summonLocation;
    }

    /**
     * List of allowable actions to be done at Site of Lost Grace
     * @param actor The actor performing actions
     * @param location Location of Site of Lost Grace
     * @param direction String that represents direction to move to
     * @return actions based on different settings
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new Summon(this));
        return actions;
    }

    /**
     * Getter of location of summon ground location
     * @return the location of the summon ground
     */
    public Location getSummonLocation() {
        return summonLocation;
    }

    /**
     * Setter of location of summon ground location
     * @param summonLocation location of summon ground
     */
    public void setSummonLocation(Location summonLocation) {
        this.summonLocation = summonLocation;
    }
}
