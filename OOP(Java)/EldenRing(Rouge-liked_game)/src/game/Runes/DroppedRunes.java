package game.Runes;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.Action.RecoverRunes;
import game.Runes.Rune;
/**
 * DroppedRunes class is a class that represents dropped runes extended from Rune
 * @author MA_Lab06_Group2
 * @version 1.0.0
 * @see Rune
 */
public class DroppedRunes extends Rune {
    /**
     * Constructor for DroppedRunes
     * @param amount Amount that represents Rune
     */
    public DroppedRunes(int amount) {
        super(amount);

    }

    /**
     * Create and return an action to pick this Item up.
     *
     * @return a new RecoverRunes action
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new RecoverRunes(this);
    }

    /**
     * Create and return an action to drop this Item.
     * @return a new DropRuneAction
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return new DropRuneAction(this);
    }
}
