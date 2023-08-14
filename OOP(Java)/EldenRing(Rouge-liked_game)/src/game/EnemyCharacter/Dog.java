package game.EnemyCharacter;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Capability.EnemyType;
/**
 * Dog class that represents a Dog character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class Dog extends Enemies{

    /**
     * A constructor for Enemies Class
     */
    public Dog() {
        super("Dog", 'a', 104);
        this.addCapability(EnemyType.CASTLE);
        this.setDropRunRange(52,1390);
        this.setSpawningChance(37);
    }

    /**
     * Getter for Giant Dog's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
     * @return Dog's IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return (new IntrinsicWeapon(101,"bites",93));
    }
}
