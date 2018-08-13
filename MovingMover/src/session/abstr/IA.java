package session.abstr;

import session.impl.ActionMove;
import session.impl.Game;
import session.impl.IARandom;
import session.impl.Player;

public abstract class IA {
	
	public abstract Action calculNextAction(Player player, Game game);
	
	public Action doNextAction(Player player, Game game) {
		/** Détermination de l'action imposée */
		Action action = getMandatoryAction(player, game);
		if(action == null) {
			/** Détermination de l'action souhaitée par l'IA */
			action = calculNextAction(player,game);
		}
		else {
			action.setChoosed(false);
		}
		try {
			action.executeAction(game);
		} catch (Exception e) {
			IARandom iARandom = new IARandom();
			action = iARandom.calculNextAction(player,game);
			action.setChoosed(false);
			action.doValidatedAction(game);
		}
		return action;
	}

	private Action getMandatoryAction(Player player, Game game) {
		if(player.getCell().getArrow() != null) {
			ActionMove actionMove = new ActionMove();
			actionMove.setActor(player);
			actionMove.setDirection(player.getCell().getArrow().getDirection());
			return actionMove;
		}
		return null;
	}
}
