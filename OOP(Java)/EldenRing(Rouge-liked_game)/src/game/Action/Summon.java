package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Environments.SummonGround;
import game.SummonCharacter.Ally;
import game.SummonCharacter.Invader;
import game.Utils.RandomNumberGenerator;
import game.Weapon.AstrologersStaff;
import game.Weapon.Club;
import game.Weapon.GreatKnife;
import game.Weapon.Uchigatana;

import java.util.Random;
/**
 * Summon class which is a action that provides player to summon an ally or in vader.
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Summon extends Action {
    /**
     * Summon Ground
     */
    private SummonGround summonGround;
    /**
     * Constructor
     * @param summondGround summon ground
     */
    public Summon(SummonGround summondGround){
        this.summonGround = summondGround;
    }

    /**
     * execute the summon action by the acton on the game map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return return a message showing an ally or an invader has been summoned
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor spawnActor;
        int startHitPoint = 0;
        WeaponItem weapon = null;
        //randomly choose the typr of role and set the starting hitpoint and weapon
        int roleChoice = RandomNumberGenerator.getRandomInt(1,4);
        switch (roleChoice){
            case 1:
                startHitPoint = 414;
                weapon = new GreatKnife();
                break;
            case 2:
                startHitPoint = 455;
                weapon = new Uchigatana();
                break;
            case 3:
                startHitPoint = 414;
                weapon = new Club();
                break;
            case 4:
                startHitPoint = 396;
                weapon = new AstrologersStaff();
        }
        //50% chance to choose an ally or an invader should be spawn
        if (RandomNumberGenerator.getRandomInt(100) <= 50) {
            spawnActor = new Ally(startHitPoint,weapon);
        }
        else {
            spawnActor = new Invader(startHitPoint,weapon);
        }
        //if the location of ground does not contains any actor, spawn the summon charater there
        //else loop through the exits of the summon ground and spawn it in the exits where does not contains any actors
        Location summonLocation = summonGround.getSummonLocation();
        if (!summonLocation.containsAnActor()){
            summonLocation.addActor(spawnActor);
        }
        else{
            for (Exit exit:summonLocation.getExits()){
                Location locationToAdd = exit.getDestination();
                if(!locationToAdd.containsAnActor()){
                    locationToAdd.addActor(spawnActor);
                    break;
                }
            }
        }

        return actor + " summons a "+ spawnActor + " from another realm.";
    }

    /**
     * return the menu description of the summon action
     * @param actor The actor performing the action.
     * @return string of the actor summons a guest on console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm.";
    }
}
