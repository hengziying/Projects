package game.Environments;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.DeathAction;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Capability.GroundType;
import game.EnemyFactory.EnemyFactory;
import game.Player.Player;

/**
 * Cliff class represents a Cliff ground that kills player when stepping on it.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public class Cliff extends Ground {
    /**
     * Constructor for Cliff
     *
     */
    public Cliff() {
        super('+');
        this.addCapability(GroundType.FATAL);
//        this.player = null;
    }
}