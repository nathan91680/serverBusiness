package presentation.dto;

public class ActionMoveDto extends ActionDto{
	
	String direction;
	int idPlayer;
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
