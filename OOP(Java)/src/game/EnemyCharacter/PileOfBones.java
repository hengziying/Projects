package game.EnemyCharacter;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.AreaAttack;
import game.Action.AttackAction;
import game.Action.QuickStep;
import game.Action.Unsheathe;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Player.Player;
import game.Capability.UniqueSkill;
import game.Weapon.GreatKnife;
import game.Weapon.Scimitar;
import game.Weapon.Uchigatana;

/**
 * PileOfBones class that represents a PileOfBones status owned by HeavySkeletalSwordsman.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies
 */
public class PileOfBones extends Enemies{
    /**
     * counter that increments
     */
    int counter;
    /**
     * Constructor for PileOfBones
     * @param weapon WeaponItem for PileOfBones
     */
    public PileOfBones(WeaponItem weapon) {
        super("Pile Of Bones", 'X', 1);
        this.setWeaponItem(weapon);
        this.setDropRunRange(35,892);
        counter = 0;
        this.addCapability(EnemyType.PILES_OF_BONE);
        this.setSpawningChance(0);
    }
    /**
     * At each turn, select a valid action to perform.
     * @param actions    Collection of possible Actions for PileOfBones
     * @param lastAction The Action this PileOfBones took last turn.
     * @param map        The map containing the PileOfBones
     * @param display    The I/O object to which messages may be written
     * @return actions based on different settings
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If the Pile of Bones is not hit within the three turns, the Heavy Skeletal Swordsman will be revived with full health.
        if (counter >= 3){
            setCounter(0);
            Location location = map.locationOf(this);
            map.removeActor(this);
            map.addActor(new SkeletalBandit(new Scimitar()),location);
        }

        counter++;
        return new DoNothingAction();
    }


    /**
     * Getter for counter
     * @return counter value
     */
    public int getCounter() {
        return counter;
    }
    /**
     * Setter for counter
     * @param counter value
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
