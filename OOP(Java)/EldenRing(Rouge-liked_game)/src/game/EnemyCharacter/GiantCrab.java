package game.EnemyCharacter;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Behaviour.WanderBehaviour;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Capability.UniqueSkill;

/**
 * GiantCrab class that represents a Giant Crab character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class GiantCrab extends Enemies{

    /**
     * Constructor of Giant Crab to initialise GiantCrab traits
     */
    public GiantCrab() {
        super("GiantCrab", 'C', 407);
        this.behaviours.put(999, new WanderBehaviour());
        this.setDropRunRange(318,4961);
        this.addCapability(HasWeapon.FALSE);
        this.addCapability(EnemyType.WATER);
        this.addCapability(UniqueSkill.AREA_ATTACK);
        this.setSpawningChance(2);


        }



    /**
     * Getter for Giant Crab's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
     * @return Giant Crab's IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }
}
