package game.SummonCharacter;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.AreaAttack;
import game.Action.Despawn;
import game.Behaviour.UniqueAttackBehaviour;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.Behaviour;
import game.Behaviour.WanderBehaviour;
import game.Capability.ActorType;
import game.Capability.UniqueSkill;
import game.EnemyCharacter.Enemies;
import game.Capability.HasWeapon;
import game.Utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;
/**
 * Ally class that represents an ally that can be summon by player.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Actor,SummonCharacter
 */

public class Ally extends Actor implements SummonCharacter{
    /**
     * Map to store different behaviours of summon character in game
     */
    public Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Summon Manager that manages the summon characters
     */
    private SummonManager summonManager = SummonManager.getInstance();

    /**
     * Constructor.
     *
     * @param hitPoints   the Actor's starting hit points
     * @param hitPoints   Ally's starting number of hitpoints (HP)
     */
    public Ally(int hitPoints, WeaponItem weapon) {
        super("Ally", 'A', hitPoints);
        this.behaviours.put(999,new WanderBehaviour());
        this.addWeaponToInventory(weapon);
        this.addCapability(HasWeapon.TRUE);
        this.addCapability(ActorType.ALLY);
        this.summonManager.register(this);
    }




    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    Collection of possible Actions for this Ally
     * @param lastAction The Action this Ally took last turn.
     * @param map        The map containing the Ally
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
     * The list of allowable actions to be performed based on multiple conditions
     *
     * @param otherActor the EnemyCharacter that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        UniqueSkill uniqueSkills[] = UniqueSkill.values();
        //if the actor is an enemy
        if (otherActor.hasCapability(ActorType.ENEMY)){
            Enemies enemy = (Enemies) otherActor;
            //if the enemy holds a weapon
            if (enemy.hasCapability(HasWeapon.TRUE)) {
                WeaponItem weapon = enemy.getWeaponInventory().get(0);//get the first weapon in the inventory
                enemy.behaviours.put(1, new AttackBehaviour(this, direction, weapon));//prioritize the attack behaviour with weapon if the enemy holds a weapon
                //prioritize the weapon that has unique skills(50% chance)
                for (UniqueSkill uniqueSkill: uniqueSkills){
                    if (weapon.hasCapability(uniqueSkill)) {
                        if (RandomNumberGenerator.getRandomInt(100) <= 50) {
                            enemy.behaviours.put(1, new UniqueAttackBehaviour(this,weapon.getSkill(this,direction)));
                        }
                    }
                }

            }
            else {//if the enemy does not hold any weapon
                //prioritize the area attack with intrinsic weapon if the enemy can perform area attack (50% chance)
                if (otherActor.hasCapability(UniqueSkill.AREA_ATTACK)) {
                    if (RandomNumberGenerator.getRandomInt(100) <= 50) {
                        enemy.behaviours.put(1, new UniqueAttackBehaviour(this, new AreaAttack(this, direction)));
                    }
                }
            }
            enemy.behaviours.put(2, new AttackBehaviour(this, direction));//add normal attack behaviour
        }

        return actions;
    }


    /**
     * Remove Ally object  from the map
     * @param map current GameMap
     */
    @Override
    public void remove(GameMap map) {
        System.out.println(new Despawn().execute(this,map));
    }
}
