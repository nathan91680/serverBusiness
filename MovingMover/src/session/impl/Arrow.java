package session.impl;

import enumeration.Direction;

public class Arrow {

	public enum State {
		NORMAL,DAMAGED,DESTROYED;
	}

	public Arrow() {
		this.state = State.NORMAL;
	}

	public Arrow(Direction direction) {
		this.state = State.NORMAL;
		this.direction = direction;
	}

	private Direction direction;

	private State state;

	private Player owner;
	
	private Cell cell;

	/**
	 * @return the cell
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
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

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public void hit() {
		if(State.NORMAL.equals(this.state)) {
			this.state = State.DAMAGED;
		}
		else if(State.DAMAGED.equals(this.state)) {
			this.state = State.DESTROYED;
			this.cell.setArrow(null);
		}
	}



}
