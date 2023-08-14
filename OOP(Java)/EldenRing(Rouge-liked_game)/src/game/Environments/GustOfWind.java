package game.Environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability.EnemyType;
import game.EnemyCharacter.*;
import game.Weapon.Grossmesser;
/**
 * GustOfWind class that represents a Gust of Wind extended from SpawningGrounds.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see SpawningGrounds
 */
public class GustOfWind extends SpawningGrounds{

    /**
     * Constructor for GustOfWind
     */
    public GustOfWind() {
        super('&');
        this.addCapability(EnemyType.GRASS);

    }

    /**
     * Getter of west side enemy for this ground
     * @return a new lone wolf object
     */
    @Override
    public Enemies getWestEnemy() {
        return new LoneWolf();
    }

    /**
     * Getter of east side enemy for this ground
     * @return a new giant dog object
     */
    @Override
    public Enemies getEastEnemy() {
        return new GiantDog();
    }


}
