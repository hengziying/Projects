package game.Utils;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player.Player;
import game.Runes.Rune;
import game.Weapon.AstrologersStaff;
import game.Weapon.Club;
import game.Weapon.GreatKnife;
import game.Weapon.Uchigatana;

import java.util.Scanner;

/**
 * StartScenario class acts as a starting prompt manager class
 * @author MA_Lab06_Group2
 * @version 1.0.0
 */
public class StartScenario {
    /**
     * Initialize player
     */
    Player player = null;
    /**
     * Initialize weapon
     */
    WeaponItem weapon = null;

    /**
     * Constructor for Start Scenario
     */
    public StartScenario(){
    }

    /**
     * ConsoleSelection is a function that prompts user for input to choose their starting weapon
     * also initializes the player's weapon and hitPoints
     * @return A New player character
     */
    public Player consoleSelection() {
        String selection;
        int startHitPoint = 0;
        System.out.print("Select your role : \na) Astrologer\nb) Bandit\ns) Samurai\nw) Wretch\n");
        while(true){
            try {
                Scanner sel = new Scanner(System.in);
                selection = sel.nextLine();
                switch (selection) {
                    case "b":
                        startHitPoint = 414;
                        weapon = new GreatKnife();
                        break;
                    case "s":
                        startHitPoint = 455;
                        weapon = new Uchigatana();
                        break;
                    case "w":
                        startHitPoint = 414;
                        weapon = new Club();
                        break;
                    case "a":
                        startHitPoint = 396;
                        weapon = new AstrologersStaff();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Option.");
                }
                break; // break out of the while loop if a valid input is given
            } catch (Exception e) {
                // do nothing and loop again
            }
        }
        player = new Player("Tarnished", '@', startHitPoint, weapon);
        return player;
    }
}
