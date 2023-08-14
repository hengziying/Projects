package game.EnemyCharacter;

import game.Behaviour.WanderBehaviour;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Weapon.HeavyCrossbow;
/**
 * GodrickSoldier class that represents a Godrick Soldier character.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class GodrickSoldier extends Enemies{
    /**
     * A constructor for Enemies Class
     */
    public GodrickSoldier(HeavyCrossbow heavyCrossbow) {
        super("GodrickSoldier", 'p', 198);
        this.behaviours.put(999, new WanderBehaviour());
        this.setDropRunRange(38,70);
        this.addCapability(EnemyType.CASTLE);
        this.addCapability(HasWeapon.TRUE);
        this.setWeaponItem(heavyCrossbow);
        this.setSpawningChance(45);
    }
}
