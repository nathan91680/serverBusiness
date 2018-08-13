package session.impl;

import enumeration.Direction;
import session.abstr.Action;

public class ActionPutArrow extends Action{
	
	
	private Direction direction;
	
	private Direction arrowDirection;

	/**
	 * @return the arrowDirection
	 */
	public Direction getArrowDirection() {
		return arrowDirection;
	}

	/**
	 * @param arrowDirection the arrowDirection to set
	 */
	public void setArrowDirection(Direction arrowDirection) {
		this.arrowDirection = arrowDirection;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	@Override
	public Game doValidatedAction(Game game) {
		Cell targetedCell = new Cell();
		switch (direction)
		{
		  case DOWN:
			  targetedCell = actor.getCell().getDownCell();
		    break;
		  case UP:
			  targetedCell = actor.getCell().getUpCell();
			    break; 
		  case RIGHT:
			  targetedCell = actor.getCell().getRightCell();
			    break; 
		  case LEFT:
			  targetedCell = actor.getCell().getLeftCell();
			    break; 
		  default:
		    /*Action*/;             
		}
		Arrow arrow = new Arrow(arrowDirection);
		arrow.setCell(targetedCell);
		if(targetedCell!=null) {
			targetedCell.setArrow(arrow);
		}
		return game;
	}

	@Override
	public boolean validateAction(Game game) {
		Cell targetedCell = new Cell();
		switch (this.direction)
		{
		  case DOWN:
			  targetedCell = actor.getCell().getDownCell();
		    break;
		  case UP:
			  targetedCell = actor.getCell().getUpCell();
			    break; 
		  case RIGHT:
			  targetedCell = actor.getCell().getRightCell();
			    break; 
		  case LEFT:
			  targetedCell = actor.getCell().getLeftCell();
			    break; 
		  default:
		    /*Action*/;             
		}
		
		if(targetedCell != null) {
			if(targetedCell.getPlayer() != null || targetedCell.getArrow() != null) {
				return false;
			}
		}
		
		return true;
	}

}
