package game.Trade;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Trader abstract class that represents a Trader
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Actor
 */
public abstract class Trader extends Actor{

  /**
   * Constructor for trader
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Trader(String name, char displayChar, int hitPoints) {
    super("Trader", displayChar, hitPoints);

  }

  /**
   * since trader would not specifically conduct action in every turn, no action is returned
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return Do nothing action
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }


}
