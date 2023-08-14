package game.Environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability.EnemyType;
import game.EnemyCharacter.*;
import game.Weapon.Grossmesser;
import game.Weapon.Scimitar;
/**
 * Graveyard class that represents a graveyard extended from SpawningGrounds.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see SpawningGrounds
 */
public class Graveyard extends SpawningGrounds{
    /**
     * Constructor for Graveyard
     */
    public Graveyard() {
        super('n');
        this.addCapability(EnemyType.BONE);

    }

    /**
     * Getter of west side enemy for this ground
     * @return a new heavy skeletal swordsman object
     */
    @Override
    public Enemies getWestEnemy() {
        return new HeavySkeletalSwordsman(new Grossmesser());
    }

    /**
     * Getter of east side enemy for this ground
     * @return a new skeletal bandit object
     */
    @Override
    public Enemies getEastEnemy() {
        return new SkeletalBandit(new Scimitar());
    }


}
