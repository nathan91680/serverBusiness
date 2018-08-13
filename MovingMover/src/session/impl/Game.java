package session.impl;

import java.util.ArrayList;
import java.util.List;

import session.abstr.Event;
import session.impl.Player.PlayerStatuts;

public class Game {
	private List<Player> players;
	private Map map;
	private List<HistoryEvent> history;
	private int cptEvent;
	private int cptTurn;
	private int cptPlayerAction;
	
	public static int NB_MAX_TURN = 200;

	public List<HistoryEvent> lauchAllGame(){
		ArrayList<HistoryEvent> historyEvents = new ArrayList<HistoryEvent>();
		ArrayList<Player> playersInGame = new ArrayList<Player>(players);

		this.cptTurn = 0;
		this.cptPlayerAction=0;
		this.cptEvent = 0;
		
		/** Positionnement des joueurs */
		
		historyEvents.addAll(this.initPositionPlayer());
		//printGame();
		Player currentPlayer = new Player();
		while(!isFinished()) {
			currentPlayer = getNextPlayer(playersInGame);
			Event event = currentPlayer.getiA().doNextAction(currentPlayer,this);
			ArrayList<Event> repercutionEvent = getRepercutedEvents(event);
			//printGame();
			for(int i = 0;i < repercutionEvent.size();i++) {
				HistoryEvent historyEvent = new HistoryEvent();
				historyEvent.setEvent(repercutionEvent.get(i));
				historyEvent.setTimeExecution(cptEvent);
				historyEvents.add(historyEvent);
				
				cptEvent ++;
			}
			cptPlayerAction ++;
			if(cptPlayerAction%players.size() == 0) {
				this.cptTurn ++;
			}
		}
		return historyEvents;
	}
	
	private Player getNextPlayer(ArrayList<Player> players) {
		Player player = players.get(cptPlayerAction%players.size());
		if(PlayerStatuts.DEAD.equals(player.getPlayerStatuts())) {
			cptPlayerAction ++;
			if(cptPlayerAction%players.size() == 0) {
				this.cptTurn ++;
			}
			return getNextPlayer(players);
		}
		return player;
	}
	
	public ArrayList<HistoryEvent> initPositionPlayer() {
		ArrayList<HistoryEvent> historyEvents = new ArrayList<HistoryEvent>();
		for(int i = 0; i < this.getPlayers().size();i++) {
			Player player = this.getPlayers().get(i);
			switch (i)
			{
			  case 0:
				  player.setCell(map.getCell(1, 1));
				  player.getCell().setPlayer(player);
			    break;
			  case 1:
				  player.setCell(map.getCell(map.getWidth()-2, 1));
				  player.getCell().setPlayer(player);
				    break; 
			  case 2:
				  player.setCell(map.getCell(1, map.getHeight()-2));
				  player.getCell().setPlayer(player);
				    break; 
			  case 3:
				  player.setCell(map.getCell(map.getWidth()-2, map.getHeight()-2));
				  player.getCell().setPlayer(player);
				    break; 
			  default:
			    /*lancer une exception ... trop de joueurs*/;             
			}

			PlacementPlayer placementPlayer = new PlacementPlayer();
			placementPlayer.setCell(player.getCell());
			placementPlayer.setPlayer(player);
			HistoryEvent historyEvent = new HistoryEvent();
			historyEvent.setEvent(placementPlayer);
			historyEvent.setTimeExecution(this.cptEvent);
			historyEvents.add(historyEvent);
			this.cptEvent ++;
		}
		
		return historyEvents;
	}

	public boolean isFinished() {
		if(cptTurn >= NB_MAX_TURN || getNbPlayerAlive() == 1) {
			return true;
		}
		return false;
	}
	
	public int getNbPlayerAlive() {
		int cptAlive = 0;
		for(int i = 0; i < players.size(); i++) {
			if(PlayerStatuts.ALIVE.equals(players.get(i).getPlayerStatuts())){
				cptAlive ++;
			}
		}
		return cptAlive;
	}

	public ArrayList<Event> getRepercutedEvents(Event origin){

		/** Détermination des sous événements */
		ArrayList<Event> repercutedEvents = new ArrayList<Event>();
		repercutedEvents = origin.repercutionEvent(this);

		/** Détermination de l'element courrant sous événements */
		ArrayList<Event> results = new ArrayList<Event>();
		results.add(origin);


		for(int i = 0; i < repercutedEvents.size(); i++) {
			results.addAll(getRepercutedEvents(repercutedEvents.get(i)));
		}

		return results;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}
	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
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
	 * @return the history
	 */
	public List<HistoryEvent> getHistory() {
		return history;
	}
	/**
	 * @param history the history to set
	 */
	public void setHistory(List<HistoryEvent> history) {
		this.history = history;
	}
	
	private void printGame() {
		System.out.println("-----------------------------------------");
		int x = 0;
		int y = 0;
		for(int i = y; i < map.getHeight() ; i++) {
			String line = "";
			for(int j = x; j < map.getWidth(); j++) {
				Cell cell = map.getCell(j, i);
				if(cell.getPlayer() != null) {
					line = line + 'P';
				}
				else {
					line = line + 'C';
				}
			}
			System.out.println(line);
		}
		System.out.println("-----------------------------------------");
	}

}
