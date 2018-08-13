package session.impl;

import enumeration.Direction;
import session.abstr.Action;
import session.abstr.IA;

public class IARandom extends IA{

	@Override
	public Action calculNextAction(Player player, Game game) {

		ActionMove actionMove = new ActionMove();
		actionMove.setActor(player);
		actionMove.setDirection(getRandomDirection(player));
		return actionMove;
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
		
		if(targetedCell != null && targetedCell.getPlayer() != null) {
			return getRandomDirection(player);
		}
		
		else {
			return direction;
		}
		
	}

}
