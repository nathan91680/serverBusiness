package session.abstr;

import session.IAException;
import session.impl.Game;
import session.impl.Player;

public abstract class Action extends Event{
	
	protected Player actor;
	
	private boolean isChoosed;
	
	public Action() {
		this.isChoosed = true;
	}
	
	/**
	 * @return the isChoosed
	 */
	public boolean isChoosed() {
		return isChoosed;
	}



	/**
	 * @param isChoosed the isChoosed to set
	 */
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}



	public abstract Game doValidatedAction(Game game);
	
	public abstract boolean validateAction(Game game);
	
	public Game executeAction(Game game) throws Exception {
		if(validateAction(game)) {
			return doValidatedAction(game);
		}
		throw new IAException();
	};

	/**
	 * @return the actor
	 */
	public Player getActor() {
		return actor;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(Player actor) {
		this.actor = actor;
	}
	
	

}
