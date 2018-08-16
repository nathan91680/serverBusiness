package session.abstr;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import session.impl.ActionMove;
import session.impl.Game;
import session.impl.IAActionCalculator;
import session.impl.IARandom;
import session.impl.Player;



public abstract class IA {
	
	private static int maxActionTimer = 100;

	public abstract Action calculNextAction(Player player, Game game);

	public Action doNextAction(Player player, Game game) {
		/** Détermination de l'action imposée */
		Action action = null;
		action = getMandatoryAction(player, game);
		
		try {
			if(action == null) {
				/** Détermination de l'action souhaitée par l'IA */
				Future<Action> future = new IAActionCalculator().calculate(this,player,game);
				/** Lance une exception si trop long **/
				action = future.get(maxActionTimer, TimeUnit.MILLISECONDS);
			}
			else {
				action.setChoosed(false);
			}
			action.executeAction(game);
		} 
		
		catch (Exception e) {
			if(e instanceof TimeoutException) {
				System.out.println("trop tard");
			}
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
