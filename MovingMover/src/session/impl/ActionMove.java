package session.impl;

import java.util.ArrayList;

import enumeration.Direction;
import session.abstr.Action;
import session.abstr.Event;
import session.impl.Player.PlayerStatuts;

public class ActionMove extends Action{
	
	
	private Direction direction;
	
	private Cell originCell;
	

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
		this.originCell = actor.getCell();
		switch (direction)
		{
		  case DOWN:
			  targetedCell = this.originCell.getDownCell();
		    break;
		  case UP:
			  targetedCell = this.originCell.getUpCell();
			    break; 
		  case RIGHT:
			  targetedCell = this.originCell.getRightCell();
			    break; 
		  case LEFT:
			  targetedCell = this.originCell.getLeftCell();
			    break; 
		  default:
		    /*Action*/;             
		}
		if(actor.getCell() != null) {
			actor.getCell().setPlayer(null);
		}
		if(targetedCell!=null) {
			targetedCell.setPlayer(actor);
		}
		actor.setCell(targetedCell);
		return game;
	}



	@Override
	public boolean validateAction(Game game) {
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
		if(targetedCell != null) {
			if(targetedCell.getPlayer() != null) {
				return false;
			}
		}
		
		return true;
	}



	@Override
	public ArrayList<Event> repercutionEvent(Game game) {
		
		/** mort d'un joueur */
		ArrayList<Event> repercutionEvent = new ArrayList<Event>();
		if(actor.getCell()==null) {
			actor.setPlayerStatuts(PlayerStatuts.DEAD);
			DeathPlayer deathPlayer = new DeathPlayer();
			deathPlayer.setVictim(actor);
			repercutionEvent.add(deathPlayer);
		}
		
		
		/** Endommagement de la flèche */
		
		if(this.originCell != null && this.originCell.getArrow() != null) {
			Arrow arrow = this.originCell.getArrow();
			arrow.hit();
			HitArrow hitArrow = new HitArrow();
			Arrow arrowClone = new Arrow();
			arrowClone.setCell(arrow.getCell());
			arrowClone.setDirection(arrow.getDirection());
			arrowClone.setOwner(arrow.getOwner());
			arrowClone.setState(arrow.getState());
			hitArrow.setArrow(arrowClone);
			hitArrow.setActor(actor);
			repercutionEvent.add(hitArrow);
		}
		return repercutionEvent;
	}

}
