package game.Environments;

import edu.monash.fit2099.engine.positions.Location;
import game.EnemyCharacter.Dog;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.GodrickSoldier;
import game.Weapon.HeavyCrossbow;
/**
 * Barrack class that represents a Barrack ground extended from Spawning Grounds
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see SpawningGrounds
 */
public class Barrack extends SpawningGrounds{

    /**
     * Constructor for SpawningGrounds
    */
    public Barrack() {
        super('B');

    }

    /**
     * Getter of west side enemy for this ground
     * @return a new godrick soldier object
     */
    @Override
    public Enemies getWestEnemy() {
        return new GodrickSoldier(new HeavyCrossbow());
    }

    /**
     * Getter of east side enemy for this ground
     * @return a new godrick soldier object
     */
    @Override
    public Enemies getEastEnemy() {
        return new GodrickSoldier(new HeavyCrossbow());
    }


}
