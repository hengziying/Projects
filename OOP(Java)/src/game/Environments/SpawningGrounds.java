package game.Environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.GiantCrab;
import game.EnemyCharacter.GiantCrayfish;
import game.EnemyFactory.EastEnemyFactory;
import game.EnemyFactory.EnemyFactory;
import game.EnemyFactory.WestEnemyFactory;
import game.Reset.Resettable;
import game.Utils.RandomNumberGenerator;
/**
 * SpawningGround class is an abstract class to represent spawning grounds extended from Ground
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Ground
 */
public abstract class SpawningGrounds extends Ground{
    /**
     * EastEnemyFactory is to spawn specific enemies on the East side of the map
     */
    private EastEnemyFactory eastEnemyFactory;
    /**
     * WestEnemyFactory is to spawn specific enemies on the West side of the map
     */
    private WestEnemyFactory westEnemyFactory;
    /**
     * To get the west side enemy of this ground
     * @return a west side enemy object
     */
    public abstract Enemies getWestEnemy();
    /**
     * To get the east side enemy of this ground
     * @return an east side enemy object
     */
    public abstract Enemies getEastEnemy();

    /**
     * Constructor for SpawningGrounds
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGrounds(char displayChar) {
        super(displayChar);
        this.eastEnemyFactory = new EastEnemyFactory(this);
        this.westEnemyFactory = new WestEnemyFactory(this);
    }

    /**
     * Function to spawn character based on intrinsic chance
     * @return True if chance is within range of intrinsic chance, false otherwise.
     */
    // new method to spawn character by chance
    public boolean spawnByChance(int spawningChance){
        int randomChance ;
        randomChance = RandomNumberGenerator.getRandomInt(0,100);
        if((randomChance - spawningChance)<0){
            return true;
        }
        return false;
    }


    /**
     * Function that ticks over the location to perform Spawning action
     * @param location Location to spawn
     */
    @Override
    public void tick(Location location) {
        if(location.x() <= 32){
            westEnemyFactory.spawnEnemy(location);
        }
        if(location.x() > 32){
            eastEnemyFactory.spawnEnemy(location);
        }

    }


}
