package session.impl;

import session.abstr.Event;

public class PlacementPlayer extends Event{
	
	private Player player;
	private Cell cell;
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
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
	
	

}
