package game.EnemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.Capability.EnemyType;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.GiantCrayfish;
import game.EnemyCharacter.GiantDog;
import game.EnemyCharacter.SkeletalBandit;
import game.Environments.SpawningGrounds;
import game.Weapon.Scimitar;

/**
 * EastEnemyFactory is a class that represents a factory that spawns enemies on EastSide Spawning Grounds
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see EnemyFactory
 */
public class EastEnemyFactory extends EnemyFactory{
    /**
     * EastEnemyFactory constructor
     * @param spawningGround SpawningGround that spawns Enemy
     */
    public EastEnemyFactory(SpawningGrounds spawningGround) {
        super(spawningGround);
    }

    /**
     * Function to spawn Enemy based on different characters and settings (East Side)
     * @param location Location on the map
     */
    @Override
    public void spawnEnemy(Location location){
        Enemies enemy = spawningGround.getEastEnemy();
        if(spawningGround.spawnByChance(enemy.getSpawningChance()) && !location.containsAnActor()){
            location.addActor(enemy);
        }

    }
}
