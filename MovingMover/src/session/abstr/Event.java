package session.abstr;

import java.util.ArrayList;

import session.impl.Game;

public abstract class Event {
	
	public ArrayList<Event> repercutionEvent(Game game){
		ArrayList<Event> repercutionEvent = new ArrayList<Event>();
		return repercutionEvent;
	}
		

}
