package session.impl;

import session.abstr.Event;

public class HitArrow extends Event{
	
	private Arrow arrow;
	private Player actor;
	
	

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
	
	

}
