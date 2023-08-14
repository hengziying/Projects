package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability.ActorType;
import game.Capability.EnemyType;
import game.EnemyCharacter.Enemies;
import game.EnemyCharacter.PileOfBones;
import game.Player.Player;
import game.Reset.ResetManager;
import game.Runes.DropRuneAction;
import game.Runes.DroppedRunes;
import game.Runes.RuneManager;
import game.SummonCharacter.SummonManager;
import game.Utils.FancyMessage;
import game.Runes.Rune;

/**
 * DeathAction is a class that represents a death action extended from Action
 * Modified by: MA_Lab06_Group2
 * @version 2.0.0
 * @see Action
 */
public class DeathAction extends Action {
    /**
     * an Actor that is classified as attacker
     */
    private Actor attacker;
    /**
     * Location of previous Death
     */
    private Location prevDeathLocation = null;
    /**
     * Previous Rune value
     */
    private Rune prevRune;
    /**
     * Rune Manager that manages the runes
     */
    private RuneManager runeManager = RuneManager.getInstance();
    /**
     * Summon Manager that manages the summon characters
     */
    private SummonManager summonManager = SummonManager.getInstance();
    /**
     * Reset Manager that manages if the game has to be reset
     */
    private ResetManager resetManager = ResetManager.getInstance();

    /**
     * Constructor for Death Action
     * @param actor
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        ActionList dropActions = new ActionList();
        //if the actor type is player
        if (target.hasCapability(ActorType.PLAYER)){
            Player player = (Player)target;
            DroppedRunes dropRune = new DroppedRunes(player.getRune().getAmount());
            DropRuneAction drop = new DropRuneAction(dropRune);
            //if the dropped rune from the previous map still in the map, remove it
            if (prevDeathLocation != null){
                prevDeathLocation.removeItem(prevRune);
            }
            this.prevDeathLocation = player.getPrevLocation();
            this.prevRune = dropRune;
            //drop runes
            drop.execute(player,map);
            //reset the game
            resetManager.run(map);
            //remove summon character
            summonManager.removeAllSummonChar(map);
            //move player to the lost grace
            map.moveActor(player,player.getlLostGrace());
        }
        //if the actor can revive (heavyskeletalswordsman & skeletalbandit)
        else if(target.hasCapability(ActorType.REVIVE)){
            Location l = map.locationOf(target);
            map.removeActor(target);
            map.addActor((new PileOfBones(target.getWeaponInventory().get(0))),l);
        }
        //if the actor type is an enemy
        else {
            // drop all weapon
            for (WeaponItem weapon : target.getWeaponInventory()) {
                dropActions.add(weapon.getDropAction(target));
            }
            for (Action drop : dropActions)
                drop.execute(target, map);
            if (this.attacker.hasCapability(ActorType.PLAYER) && (target.hasCapability(ActorType.ENEMY))) {
                Enemies enemy = (Enemies) target;
                Player player = (Player) this.attacker;
                Rune enemy_rune = enemy.generate_runes();
                runeManager.addRune(player,enemy_rune);
            }
            map.removeActor(target);
        }

        result += System.lineSeparator() + menuDescription(target);
        return result;
    }
    /**
     * Describes which Actor has died
     *
     * @param actor The actor affected by the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        String retStr = "";
        if (actor.hasCapability(ActorType.PLAYER)){
            retStr += FancyMessage.YOU_DIED;
        }
        else if(actor.hasCapability(ActorType.REVIVE)){
            retStr += actor +" turned to a pile of bones";
        }
        else if(actor.hasCapability(EnemyType.PILES_OF_BONE)){
            retStr += actor + " is removed";
        }
        else{
            retStr += actor + " is killed.";
        }
        return retStr;
    }
}
