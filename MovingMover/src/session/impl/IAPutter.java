package session.impl;

import enumeration.Direction;
import session.abstr.Action;
import session.abstr.IA;

public class IAPutter extends IA{

	@Override
	public Action calculNextAction(Player player, Game game) {
		return getRandomAction(player, game);
	}

	private Action getRandomAction(Player player, Game game) {
		int aleanumber = (int)(Math.random() * 8);
		if(aleanumber != 7) {
			ActionMove actionMove = new ActionMove();
			actionMove.setActor(player);
			actionMove.setDirection(getRandomDirection(player));
			return actionMove;
		}
		else {
			ActionPutArrow actionPutArrow = new ActionPutArrow();
			actionPutArrow.setActor(player);
			actionPutArrow.setDirection(getRandomDirection(player));
			actionPutArrow.setArrowDirection(getRandomDirection());
			return actionPutArrow;
		}
	}

	private Direction getRandomDirection(Player player) {

		Direction direction = null;

		int aleanumber = (int)(Math.random() * 4);
		Cell targetedCell = new Cell();

		switch (aleanumber)
		{
		case 0:
			direction = Direction.DOWN;
			targetedCell = player.getCell().getDownCell();
			break;
		case 1:
			direction = Direction.UP;
			targetedCell = player.getCell().getUpCell();
			break; 
		case 2:
			direction = Direction.RIGHT;
			targetedCell = player.getCell().getRightCell();
			break; 
		case 3:
			direction = Direction.LEFT;
			targetedCell = player.getCell().getLeftCell();
			break; 
		default:
			/*Action*/;             
		}

		if(targetedCell == null) {
			return getRandomDirection(player);
		}

		else {
			return direction;
		}

	}
	

	private Direction getRandomDirection() {

		Direction direction = null;

		int aleanumber = (int)(Math.random() * 4);

		switch (aleanumber)
		{
		case 0:
			direction = Direction.DOWN;
			break;
		case 1:
			direction = Direction.UP;
			break; 
		case 2:
			direction = Direction.RIGHT;
			break; 
		case 3:
			direction = Direction.LEFT;
			break; 
		default:
			/*Action*/;             
		}

		return direction;

	}


}
