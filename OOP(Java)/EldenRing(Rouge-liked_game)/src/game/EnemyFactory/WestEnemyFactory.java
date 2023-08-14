package game.EnemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.Capability.EnemyType;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.GiantCrab;
import game.EnemyCharacter.HeavySkeletalSwordsman;
import game.EnemyCharacter.LoneWolf;
import game.Environments.SpawningGrounds;
import game.Weapon.Grossmesser;

/**
 * WestEnemyFactory is a class that represents a factory that spawns enemies on WestSide Spawning Grounds
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see EnemyFactory
 */
public class WestEnemyFactory extends EnemyFactory{

    /**
     * WestEnemyFactory Constructor
     */
    public WestEnemyFactory(SpawningGrounds spawningGround) {
        super(spawningGround);
    }

    /**
     * Function to spawn Enemy based on different characters and settings (West Side)
     * @param location Location on the map
     */
    @Override
    public void spawnEnemy(Location location){
        Enemies enemy = spawningGround.getWestEnemy();
        if(spawningGround.spawnByChance(enemy.getSpawningChance()) && !location.containsAnActor()){
            location.addActor(enemy);
        }

    }
}
