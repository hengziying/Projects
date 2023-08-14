package game.EnemyCharacter;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.*;
import game.Behaviour.UniqueAttackBehaviour;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.Behaviour;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.Capability.HasWeapon;
import game.Player.Player;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.Runes.RuneManager;
import game.SummonCharacter.Ally;
import game.Capability.UniqueSkill;
import game.Runes.Rune;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * Enemies class is an abstract class to represent enemy characters
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Actor
 */
public abstract class Enemies extends Actor implements Resettable {
    /**
     * Weapon Item of the Enemy
     */
    private WeaponItem weaponItem;
    /**
     * Range of rune that could be dropped
     */
    private int[] dropRuneRange = new int[2];
    /**
     * Location of the enemy
     */
    Location location;
    /**
     * Reset Manager that manages if Map needs a Reset
     */
    private ResetManager resetManager = ResetManager.getInstance();
    /**
     * Map to store different behaviours of enemy in game
     */
    public Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Rune Manager that manages the runes
     */
    private RuneManager runeManager= RuneManager.getInstance();
    private int spawningChance;


    /**
     * A constructor for Enemies Class
     *
     * @param name        The name of the EnemyCharacter
     * @param displayChar The character that will represent each of the EnemyCharacter in the display
     * @param hitPoints   The EnemyCharacter's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(ActorType.ENEMY);
        this.resetManager.registerResettable(this);

    }


    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    Collection of possible Actions for this EnemyCharacter
     * @param lastAction The Action this EnemyCharacter took last turn.
     * @param map        The map containing the EnemyCharacter
     * @param display    The I/O object to which messages may be written
     * @return The valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {//loop through the behaviours hashmap to find what is the next behaviour that the enemy should perform
            Action action = behaviour.getAction(this, map);
            if (!(behaviour.getClass().getSimpleName().equals("FollowBehaviour"))){ //if the enemy is not following
                if (rand.nextInt(100) <= 10) {//enemy have the 10% chance to be removed from the map
                    return new Despawn();
                }
            }
            if (action != null)
                return action;
        }
        if (rand.nextInt(100) <= 10) {//enemy have the 10% chance to be removed from the map
            return new Despawn();
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
        //if the actor is a player
        if (otherActor.hasCapability(ActorType.PLAYER)){
            Player player = (Player) otherActor;
            actions.add(new AttackAction(this, direction));//add action for normal attack
            //loop through the weapon of the player to add the attack action using the weapon
            for (WeaponItem weapon : player.getWeaponInventory()) {
                actions.add(new AttackAction(this,direction,weapon));
                actions.add(weapon.getSkill(this,direction));
            }
        }

        //if the actor is an enemy
        else if (otherActor.hasCapability(ActorType.ENEMY)){
            Enemies enemy = (Enemies) otherActor;
            //to make sure the enemy is not the same type
            if (!(enemy.findCapabilitiesByType(EnemyType.class).equals(this.findCapabilitiesByType(EnemyType.class)))) {
                //if the enemy holds a weapon
                if (enemy.hasCapability(HasWeapon.TRUE)) {
                    WeaponItem weapon = enemy.getWeaponInventory().get(0);//get the first weapon in the inventory
                    enemy.behaviours.put(1, new AttackBehaviour(this, direction, weapon));//prioritize the attack behaviour with weapon if the enemy holds a weapon
                    //prioritize the weapon that has unique skills(50% chance)
                    for (UniqueSkill uniqueSkill: uniqueSkills){
                        if (weapon.hasCapability(uniqueSkill)) {
                            if (rand.nextInt(100) <= 50) {
                                enemy.behaviours.put(1, new UniqueAttackBehaviour(this,weapon.getSkill(this,direction)));
                            }
                        }
                    }

                }
                else {//if the enemy does not hold any weapon
                    //prioritize the area attack with intrinsic weapon if the enemy can perform area attack (50% chance)
                    if (otherActor.hasCapability(UniqueSkill.AREA_ATTACK)) {
                        if (rand.nextInt(100) <= 50) {
                            enemy.behaviours.put(1, new UniqueAttackBehaviour(this, new AreaAttack(this, direction)));
                        }
                    }
                }
                enemy.behaviours.put(2, new AttackBehaviour(this, direction));//add normal attack behaviour
            }
        }
        else if (otherActor.hasCapability(ActorType.ALLY)){
            Ally ally = (Ally) otherActor;
            WeaponItem weapon = ally.getWeaponInventory().get(0);
            ally.behaviours.put(1, new AttackBehaviour(this,direction,weapon));
            for (UniqueSkill uniqueSkill: uniqueSkills){
                if (weapon.hasCapability(uniqueSkill)) {
                    if (rand.nextInt(100) <= 50) {
                        ally.behaviours.put(1, new UniqueAttackBehaviour(this,weapon.getSkill(this,direction)));
                    }
                }
            }
            ally.behaviours.put(2, new AttackBehaviour(this, direction));//add normal attack behaviour
        }
        return actions;
    }
    /**
     * The getter for WeaponItem
     * @return weaponItem of the EnemyCharacter
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }
    /**
     * The setter for Weapon Item and add Weapon to inventory
     * @param weaponItem The weaponItem of the EnemyCharacter
     */
    public void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
        this.addWeaponToInventory(weaponItem);
    }
    /**
     * To set the possible range of runes that can be dropped
     * @param x Minimum value of Runes
     * @param y Maximum value of Runes
     */
    public void setDropRunRange(int x, int y){
        this.dropRuneRange[0] = x;
        this.dropRuneRange[1] = y;

    }

    /**
     * Runes generator using range given during setDropRunRange()
     * @return randomly generated Rune value
     */
    public Rune generate_runes(){
        Rune rand_runes = runeManager.randomRunes(this.dropRuneRange[0], this.dropRuneRange[1]);
        return rand_runes;

    }
    /**
     * Reset function to remove EnemyCharacter from map
     * @param map current GameMap
     */
    @Override
    public void reset(GameMap map) {
        System.out.println(new Despawn().execute(this,map));
    }

    public int getSpawningChance() {
        return spawningChance;
    }

    public void setSpawningChance(int spawningChance) {
        this.spawningChance = spawningChance;
    }
}
