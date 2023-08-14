package game.EnemyCharacter;

import game.Behaviour.WanderBehaviour;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Weapon.Scimitar;

import java.util.Random;

/**
 * SkeletalBandit class that represents a Skeletal Bandit character
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class SkeletalBandit extends Enemies{
    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor for SkeletalBandit
     * @param scimitar Scimitar is SkeletalBandit's weapon
     */
    public SkeletalBandit(Scimitar scimitar) {
        super("SkeletalBandit", 'b', 184);
        this.behaviours.put(999, new WanderBehaviour());
        this.setWeaponItem(scimitar);
        this.setDropRunRange(35,892);
        this.addCapability(HasWeapon.TRUE);
        this.addCapability(EnemyType.BONE);
        this.addCapability(ActorType.REVIVE);
        this.setSpawningChance(27);


    }


}
