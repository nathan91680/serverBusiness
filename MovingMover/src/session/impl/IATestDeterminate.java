package session.impl;

import enumeration.Direction;
import session.abstr.Action;
import session.abstr.IA;

public class IATestDeterminate extends IA{
	
	private int numbAction;
	public IATestDeterminate() {
		numbAction = 0;
	}
	@Override
	public Action calculNextAction(Player player, Game game) {
		int height = game.getMap().getHeight() - 1;
		int width = game.getMap().getWidth() -1;
		
		ActionMove actionMove = new ActionMove();
		actionMove.setActor(player);
		if(numbAction < width ) {
			actionMove.setDirection(Direction.RIGHT);
		}
		else if(numbAction < width + height){
			actionMove.setDirection(Direction.DOWN);
		}
		else if(numbAction < 2*width + height) {
			actionMove.setDirection(Direction.LEFT);
		}
		else {
			actionMove.setDirection(Direction.UP);
		}
		numbAction ++;
		return actionMove;
	}

}
