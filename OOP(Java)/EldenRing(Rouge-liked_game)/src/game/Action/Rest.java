package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player.Player;
import game.Reset.ResetManager;

/**
 * Rest class for player to rest at The First Step Lost Site of Grace if player die
 * This class is used to reset the game
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Action
 */
public class Rest extends Action {
    /**
     * @param actor The actor that is to be reset
     * @param map The map the actor is on.
     * @return string of description for player to rest at The First Step Lost Site of Grace
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        String result = "";
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map);
        map.moveActor(player,player.getlLostGrace());
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }
    /**
     * returns a description in menu on console for player to rest at The First Step Lost Site of Grace
     * @param actor The player that is reset
     * @return menu description on console for player to rest at The First Step Lost Site of Grace
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" rest at The First Step Lost Site of Grace";
    }
}
