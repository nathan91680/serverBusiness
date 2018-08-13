package session.impl;

import session.abstr.Event;

public class DeathPlayer extends Event{
	
	private Player victim;

	/**
	 * @return the victim
	 */
	public Player getVictim() {
		return victim;
	}

	/**
	 * @param victim the victim to set
	 */
	public void setVictim(Player victim) {
		this.victim = victim;
	}
	
	

}
