package game.Environments;

import edu.monash.fit2099.engine.positions.Location;
import game.EnemyCharacter.Dog;
import game.EnemyCharacter.Enemies;
import game.Environments.SpawningGrounds;
/**
 * Cage class that represents a Cage ground extended from Spawning Grounds
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see SpawningGrounds
 */
public class Cage extends SpawningGrounds {
    /**
     * Constructor for SpawningGrounds
     */
    public Cage() {
        super('<');
    }

    /**
     * Getter of west side enemy for this ground
     * @return a new dog soldier object
     */
    @Override
    public Enemies getWestEnemy() {
        return new Dog();
    }

    /**
     * Getter of east side enemy for this ground
     * @return a new dog object
     */
    @Override
    public Enemies getEastEnemy() {
        return new Dog();
    }




}
