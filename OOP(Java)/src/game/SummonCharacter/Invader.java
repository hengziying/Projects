package game.SummonCharacter;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.Despawn;
import game.Behaviour.Behaviour;
import game.Capability.EnemyType;
import game.EnemyCharacter.Enemies;
import game.Capability.HasWeapon;

/**
 * Invader class that represents an invader that can be summon by player.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Enemies,SummonCharacter
 */

public class Invader extends Enemies implements SummonCharacter {
    /**
     * Summon Manager that manages the summon characters
     */
    private SummonManager summonManager = SummonManager.getInstance();

    /**
     * Constructor.
     *
     * @param hitPoints   the Actor's starting hit points
     * @param hitPoints   Invader's starting number of hitpoints (HP)
     */
    public Invader(int hitPoints, WeaponItem weapon) {
        super("Invader", 'ඞ', hitPoints);
        this.setWeaponItem(weapon);
        this.addCapability(HasWeapon.TRUE);
        this.setDropRunRange(1358,5578);
        this.addCapability(EnemyType.INVADER);
        this.summonManager.register(this);

    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    Collection of possible Actions for this Invader
     * @param lastAction The Action this Invader took last turn.
     * @param map        The map containing the Invader
     * @param display    The I/O object to which messages may be written
     * @return The valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {//loop through the behaviours hashmap to find what is the next behaviour that the enemy should perform
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();

    }

    /**
     * Remove invader object  from the map
     * @param map current GameMap
     */
    @Override
    public void remove(GameMap map) {
        System.out.println(new Despawn().execute(this,map));
    }

    /**
     * Reset function (do nothing when reset)
     * @param map current GameMap
     */
    @Override
    public void reset(GameMap map) {
    }
}
