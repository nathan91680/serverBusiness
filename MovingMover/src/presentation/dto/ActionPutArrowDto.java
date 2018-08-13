package presentation.dto;

public class ActionPutArrowDto extends ActionDto{

	private String direction;
	private String arrowDirection;
	private int idPlayer;
	
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the arrowDirection
	 */
	public String getArrowDirection() {
		return arrowDirection;
	}
	/**
	 * @param arrowDirection the arrowDirection to set
	 */
	public void setArrowDirection(String arrowDirection) {
		this.arrowDirection = arrowDirection;
	}
	/**
	 * @return the idPlayer
	 */
	public int getIdPlayer() {
		return idPlayer;
	}
	/**
	 * @param idPlayer the idPlayer to set
	 */
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	

}
