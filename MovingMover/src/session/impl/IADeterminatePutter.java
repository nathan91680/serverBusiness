package session.impl;

import enumeration.Direction;
import session.abstr.Action;
import session.abstr.IA;

public class IADeterminatePutter extends IA {
	
	private int numbAction;
	public IADeterminatePutter() {
		numbAction = 0;
	}
	@Override
	public Action calculNextAction(Player player, Game game) {
		
		if(numbAction % 3 == 0 ) {
			ActionPutArrow actionPutArrow = new ActionPutArrow();
			actionPutArrow.setActor(player);
			actionPutArrow.setDirection(Direction.RIGHT);
			actionPutArrow.setArrowDirection(Direction.LEFT);
			numbAction ++;
			return actionPutArrow;
		}
		else if(numbAction % 3 == 1){
			ActionMove actionMove = new ActionMove();
			actionMove.setActor(player);
			actionMove.setDirection(Direction.RIGHT);
			numbAction ++;
			return actionMove;
		}
		else if(numbAction % 3 == 2) {
			ActionMove actionMove = new ActionMove();
			actionMove.setActor(player);
			actionMove.setDirection(Direction.RIGHT);
			numbAction ++;
			return actionMove;
		}
		
		return null;
	}

}
