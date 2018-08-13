package session.impl;

public class Cell {
	
	private int x;
	private int y;
	
	private Map map;
	
	private Player player;
	
	private Arrow arrow;
	
	Cell(){
		
	}
	
	Cell(int lX,int lY){
		this.x=lX;
		this.y=lY;
	}
	
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
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
	 * @return the arrow
	 */
	public Arrow getArrow() {
		return arrow;
	}
	/**
	 * @param arrow the arrow to set
	 */
	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public Cell getRightCell() {
		return map.getCell(x+1,y);
	}
	
	public Cell getLeftCell() {
		return map.getCell(x-1,y);
	}
	
	public Cell getUpCell() {
		return map.getCell(x,y-1);
	}
	
	public Cell getDownCell() {
		return map.getCell(x,y+1);
	}
	
	
	
	
	

}
