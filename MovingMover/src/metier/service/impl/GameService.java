package metier.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import metier.service.api.IGameService;
import session.impl.Game;
import session.impl.IAPutter;
import session.impl.Map;
import session.impl.Player;

@Service("gameService")
public class GameService implements IGameService{
	
	public static int HEIGHT = 5;
	public static int WIDTH = 5;

	@Override
	public Game createGame() {
		Game game = new Game();
		
		/** Initialisation de la carte */
		
		Map map = new Map(WIDTH,HEIGHT);
		
		game.setMap(map);
		
		/** Initialisation des joueurs */
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		/** joueur 1 */
		Player player1 = new Player();
		player1.setId(1);
		player1.setName("player1");
		player1.setiA(new IAPutter());
		players.add(player1);
		
		/** joueur 2 */
		Player player2 = new Player();
		player2.setId(2);
		player2.setName("player2");
		player2.setiA(new IAPutter());
		players.add(player2);
		
		/** joueur 3 */
		Player player3 = new Player();
		player3.setId(3);
		player3.setName("player3");
		player3.setiA(new IAPutter());
		players.add(player3);
		
		/** joueur 4 */
		Player player4 = new Player();
		player4.setId(4);
		player4.setName("player2");
		player4.setiA(new IAPutter());
		players.add(player4);
		
		game.setPlayers(players);
		
		game.setHistory(game.lauchAllGame());
		
		
		return game;
	}

}
