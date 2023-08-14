package game.Player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Action.AreaAttack;
import game.Action.DeathAction;
import game.Behaviour.UniqueAttackBehaviour;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.FollowBehaviour;
import game.Capability.ActorType;
import game.Capability.Status;
import game.Capability.UniqueSkill;
import game.EnemyCharacter.Enemies;
import game.Capability.HasWeapon;
import game.Item.CrimsonTears;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.Runes.Rune;

import game.Runes.RuneUsable;

import java.util.Random;

/**
 * Player class that represents player
 * Extends from Actor and implements Resettable
 */
public class Player extends Actor implements Resettable, RuneUsable {
	/**
	 * Menu that will be displayed to user and show options
	 */
	private final Menu menu = new Menu();
	/**
	 * Runes that the player will owm
	 */
	private Rune playerRune;
	/**
	 * CrimsonTears that PLayer can consume
	 */
	private CrimsonTears crimsonTears;
	/**
	 * Location of site of Lost Grace
	 */
	private Location lostGrace;
	/**
	 * Reset Manager that manages if the game has to be reset
	 */
	private ResetManager resetManager = ResetManager.getInstance();
	/**
	 * Random number generator
	 */
	private Random rand = new Random();
	/**
	 * The previous location of player
	 */
	private Location prevLocation;
	/**
	 * The current location of player
	 */
	private Location currentLocation = null;

	private int starlightCounter = 0;

	private boolean starlightConsumed = false;
	/**
	 * Constructor for Player
	 *
	 * @param name        Name to call the player in the User Interface
	 * @param displayChar Character to represent the player in the User Interface
	 * @param hitPoints   Player's starting number of hitpoints (HP)
	 */
	public Player(String name, char displayChar, int hitPoints, WeaponItem weapon) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(ActorType.PLAYER);
		this.addWeaponToInventory(weapon);
		this.playerRune = new Rune(0);
		this.crimsonTears = new CrimsonTears();
		this.addItemToInventory(this.crimsonTears);
		this.resetManager.registerResettable(this);

	}
	/**
	 * At each turn, select a valid action to perform.
	 * @param actions Collection of possible Actions for this Player
	 * @param lastAction The Action this Player took last turn
	 * @param map The map containing the Player
	 * @param display The I/O object to which messages may be written
	 * @return Menu Interface for user that contains the actions carried out
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//store the previous location and the current location of the player at every turn
		if (currentLocation != null){
			prevLocation  = currentLocation;
		}
		currentLocation = map.locationOf(this);
		if (currentLocation.getDisplayChar() == 'U'){
			this.setLostGrace(currentLocation);
		}
		if(currentLocation.getGround().getDisplayChar() == '+'){
			return new DeathAction(this);
		}
		if(currentLocation.getGround().getDisplayChar() == '^' && !this.isConscious()){
			return new DeathAction(this);
		}
		if(isStarlightConsumed()){
			starlightCounter +=1;
			this.heal(20);
			if(starlightCounter == 5){
				starlightCounter = 0;
				setStarlightConsumed(false);
			}
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		String retStr = this + "("+ this.hitPoints + "/" +this.maxHitPoints + "), runes:"+ this.playerRune.getAmount();
		System.out.println(retStr);
		return  menu.showMenu(this, actions, display);
	}


	/**
	 * List of allowable actions for the Player
	 * @param otherActor other Actor that represents EnemyCharacter
	 * @param direction String representing the direction of the other Actor
	 * @param map The map containing the Player
	 * @return actions based on different settings
	 */
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		UniqueSkill uniqueSkills[] = UniqueSkill.values();
		//if the actor is an enemy type
		if(otherActor.hasCapability(ActorType.ENEMY)) {
			Enemies enemy = (Enemies) otherActor;
			enemy.behaviours.put(5, new FollowBehaviour(this));//add follow behaviour
			//if the enemy has a weapon
			if (otherActor.hasCapability(HasWeapon.TRUE)) {
				WeaponItem weapon = getWeaponInventory().get(0);//get the first weapon in the inventory
				enemy.behaviours.put(1, new AttackBehaviour(this, direction, weapon));//prioritize the attack behaviour with weapon if the enemy holds a weapon
				//prioritize the weapon that has unique skills(50% chance)
				for (UniqueSkill uniqueSkill : uniqueSkills) {
					if (weapon.hasCapability(uniqueSkill)) {
						if (rand.nextInt(100) <= 50) {
							enemy.behaviours.put(1, new UniqueAttackBehaviour(this, weapon.getSkill(this, direction)));
						}
					}
				}

			} else {//if the enemy has no weapon
				//prioritize the area attack with intrinsic weapon if the enemy can perform area attack (50% chance)
				if (otherActor.hasCapability(UniqueSkill.AREA_ATTACK)) {
					if (rand.nextInt(100) <= 50) {
						enemy.behaviours.put(1, new UniqueAttackBehaviour(this, new AreaAttack(this, direction)));
					}
				}
			}
			enemy.behaviours.put(2, new AttackBehaviour(this, direction));//add normal attack behaviour
		}

		return actions;
	}



	/**
	 * Setter for Player's rune value
	 * @param rune a rune object
	 */
	public void setRune(Rune rune) {
		playerRune = rune;
	}


	/**
	 * Getter for player's rune value
	 * @return PLayer's rune value
	 */
	public Rune getRune(){
		return this.playerRune;
	}
	/**
	 * Setter for Location of Site of Lost Grace
	 * @param lostGrace Locaation of Site of Lost Grace
	 */
	public void setLostGrace(Location lostGrace) {
		this.lostGrace = lostGrace;
	}
	/**
	 * Reset hitpoints back to max when game has been reset
	 * @param map Map that the player is currently on
	 */
	@Override
	public void reset(GameMap map) {
		this.resetMaxHp(maxHitPoints);
		this.crimsonTears.setConsumeTime(0);
	}

	/**
	 * Getter for Location of Site of Lost Grace
	 * @return Location of Site of Lost Grace
	 */
	public Location getlLostGrace() {
		return this.lostGrace;
	}

	/**
	 * Getter for Player's Intrinsic Weapon and also used to declare the new Intrinsic weapon's traits
	 * @return Player's IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}
	/**
	 * Getter for Player's previous location
	 * @return Player's previous location
	 */
	public Location getPrevLocation() {
		return prevLocation;
	}

	/**
	 * Getter for Player's hit points
	 * @return player's hit points
	 */
	public int getHitPoints(){
		return this.hitPoints;
	}

	public boolean isStarlightConsumed() {
		return starlightConsumed;
	}

	public void setStarlightConsumed(boolean starlightConsumed) {
		this.starlightConsumed = starlightConsumed;
	}
}
