package game.EnemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.Capability.EnemyType;
import game.EnemyCharacter.*;
import game.Environments.SpawningGrounds;
import game.Weapon.Grossmesser;
import game.Weapon.Scimitar;

/**
 * EnemyFactory is an abstract class to represent a spawning enemy factory
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public abstract class EnemyFactory {
    /**
     * Spawning Ground that spawns enemy
     */
    SpawningGrounds spawningGround;

    /**
     * EnemyFactory Constructor
     * @param spawningGround Spawning Ground that spawns Enemy
     */
    public EnemyFactory(SpawningGrounds spawningGround){
        this.spawningGround = spawningGround;
    }

    /**
     * Function to spawn enemy based on different characters and settings
     * @param location Location on the map
     */
    public abstract void spawnEnemy(Location location);
}
