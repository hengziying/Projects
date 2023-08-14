package game.EnemyCharacter;

import game.Behaviour.WanderBehaviour;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Weapon.Grossmesser;
/**
 * HeavySkeletalSwordsman class that represents a Heavy Skeletal Swordsman character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class HeavySkeletalSwordsman extends Enemies{

    /**
     * Constructor of HeavySkeletalSwordsman initialise HeavySkeletalSwordsman's traits
     * @param grossmesser Grossmesser is HeavySkeletalSwordsman's weapon
     */
    public HeavySkeletalSwordsman(Grossmesser grossmesser) {
        super("HeavySkeletalSwordsman", 'q', 153);
        this.behaviours.put(999, new WanderBehaviour());
        this.setWeaponItem(grossmesser);
        this.setDropRunRange(35,892);
        this.addCapability(HasWeapon.TRUE);
        this.addCapability(EnemyType.BONE);
        this.addCapability(ActorType.REVIVE);
        this.setSpawningChance(27);
    }


}
