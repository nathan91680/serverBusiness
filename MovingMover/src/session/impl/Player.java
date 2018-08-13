package session.impl;

import session.abstr.IA;

public class Player {
	
	public enum PlayerStatuts {
		ALIVE,DEAD;
	}
	
	private Cell cell;
	private String name;
	private IA iA;
	private Integer Id;
	private PlayerStatuts playerStatuts;
	
	public Player(){
		this.playerStatuts = PlayerStatuts.ALIVE;
	}
	
	/**
	 * @return the playerStatuts
	 */
	public PlayerStatuts getPlayerStatuts() {
		return playerStatuts;
	}
	/**
	 * @param playerStatuts the playerStatuts to set
	 */
	public void setPlayerStatuts(PlayerStatuts playerStatuts) {
		this.playerStatuts = playerStatuts;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		Id = id;
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the iA
	 */
	public IA getiA() {
		return iA;
	}
	/**
	 * @param iA the iA to set
	 */
	public void setiA(IA iA) {
		this.iA = iA;
	}
	
	
}
