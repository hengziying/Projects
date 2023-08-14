package game.EnemyCharacter;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Capability.UniqueSkill;
import game.Behaviour.WanderBehaviour;

import java.util.Random;

/**
 * GiantDog class that represents a Giant Dog character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class GiantDog extends Enemies{

    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Constructor of GiantDog initialise GiantDog's traits
     */
    public GiantDog() {
        super("GiantDog", 'G', 693);
        this.behaviours.put(999, new WanderBehaviour());
        this.setDropRunRange(313,1808);
        this.addCapability(HasWeapon.FALSE);
        this.addCapability(EnemyType.GRASS);
        this.addCapability(UniqueSkill.AREA_ATTACK);
        this.setSpawningChance(4);
    }
    /**
     * Getter for Giant Dog's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
     * @return Giant Dog's IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }


}
