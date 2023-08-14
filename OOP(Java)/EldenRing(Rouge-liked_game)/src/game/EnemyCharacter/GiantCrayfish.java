package game.EnemyCharacter;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Capability.UniqueSkill;
import game.Behaviour.WanderBehaviour;

import java.util.Random;

/**
 * GiantCrayfish class that represents a Giant Crayfish character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class GiantCrayfish extends Enemies{

    /**
     * Random number generator
     */
    private Random rand = new Random();


    /**
     * Constructor of Giant Crayfish to initialise GiantCrayfish's traits
     */
    public GiantCrayfish() {
        super("GiantCrayfish", 'R', 4803);
        this.behaviours.put(999, new WanderBehaviour());
        this.setDropRunRange(500,2374);
        this.addCapability(HasWeapon.FALSE);
        this.addCapability(EnemyType.WATER);
        this.addCapability(UniqueSkill.AREA_ATTACK);
        this.setSpawningChance(1);
    }
    /**
     * Getter for Giant Crayfish's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
     * @return Giant Crayfish's IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }


}
