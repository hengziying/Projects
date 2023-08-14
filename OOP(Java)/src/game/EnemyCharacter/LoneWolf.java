package game.EnemyCharacter;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Behaviour.WanderBehaviour;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;

/**
 * LoneWolf class that represents a LoneWolf character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class LoneWolf extends Enemies {
    /**
     * Constructor of LoneWolf initialise LoneWolf's traits
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        this.behaviours.put(999, new WanderBehaviour());
        this.setDropRunRange(55,1470);
        this.addCapability(HasWeapon.FALSE);
        this.addCapability(EnemyType.GRASS);
        this.setSpawningChance(33);
    }


    /**
     * Getter for LoneWolf's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
     * @return LoneWolf 's IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
