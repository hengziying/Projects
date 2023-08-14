package game.Environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Capability.EnemyType;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.GiantCrab;
import game.EnemyCharacter.GiantCrayfish;
/**
 * PuddleOfWater class that represents a Puddle of water ground extended from Spawning Grounds
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see SpawningGrounds
 */
public class PuddleOfWater extends SpawningGrounds{

    /**
     * Constructor for Puddle Of Water
     */
    public PuddleOfWater() {
        super('~');
        this.addCapability(EnemyType.WATER);

    }

    /**
     * Getter of west side enemy for this ground
     * @return a new giant crab object
     */
    @Override
    public Enemies getWestEnemy() {
        return new GiantCrab();
    }

    /**
     * Getter of east side enemy for this ground
     * @return a new giant crayfish object
     */
    @Override
    public Enemies getEastEnemy() {
        return new GiantCrayfish();
    }

}
