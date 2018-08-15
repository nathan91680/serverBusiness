package iAClient.converter;

import java.util.ArrayList;

import enumeration.Direction;
import session.abstr.Action;
import session.impl.ActionMove;
import session.impl.ActionPutArrow;
import session.impl.Game;
import session.impl.Map;
import session.impl.Player;

public class CampaignIaConverter {

	/*
	 * XXXXXXXXXXXX
	 * X......X...X
	 * X..........X
	 * XXXXXXXXXXXX
	 */
	public static ArrayList<String> toClientInit(Map map){

		ArrayList<String> sMap = new ArrayList<String>();

		for(int i = 0; i < map.getWidth(); i++) {
			String line = "";
			for(int j = 0; j < map.getHeight(); j++) {
				if(map.getCell(i, j) != null) {
					line += "X";
				}
				else {
					line += ".";
				}
			}
			sMap.add(line);
		}
		return sMap;
	}

	public static ArrayList<String> toClient(Game game){
		/*
		 * nbfleches 5
		 * 4 3 D 7 1 U ....
		 * nbjoueurs
		 * 4 3 5 6
		 */

		ArrayList<String> positions = new ArrayList<String>();

		int nbArrows = 0;
		String arrowsPosition = "";

		int nbPlayers = 0;
		String playersPosition = "";

		for(int i = 0; i < game.getMap().getWidth(); i++) {
			for(int j = 0; j < game.getMap().getHeight(); j++) {
				if(game.getMap().getCell(i, j) != null && game.getMap().getCell(i, j).getArrow() != null) {
					nbArrows += 1;
					String direction = "";
					switch (game.getMap().getCell(i, j).getArrow().getDirection())
					{
					case DOWN:
						direction = "D";
						break;
					case UP:
						direction = "U";
						break; 
					case RIGHT:
						direction = "R";
						break; 
					case LEFT:
						direction = "L";
						break; 
					default:
						/*Action*/;             
					}
					arrowsPosition += "" + i + " " + j + " " + direction + " ";
				}
			}
		}

		game.getPlayers().size();

		for(int i = 0; i < game.getPlayers().size(); i++) {
			if(game.getPlayers().get(i) != null) {
				nbPlayers += 1;
				playersPosition += "" + game.getPlayers().get(i).getCell().getX() + " " + game.getPlayers().get(i).getCell().getY() + " ";
			}
		}

		positions.add(""+nbArrows);
		positions.add(arrowsPosition);
		positions.add(""+nbPlayers);
		positions.add(playersPosition);

		return positions;

	}

	public static Action getAction(String sAction, Player actor){
		/*

		 * Move posX posY
		 * Arrow posX posY dir
		 */

		Action action = null;

		if(sAction != null) {
			String[] splitAction = sAction.split(" ");
			if(splitAction[0] != null) {
				switch (splitAction[0])
				{
				case "Move":
					ActionMove actionMove = new ActionMove();
					actionMove.setActor(actor);
					actionMove.setChoosed(true);
					int x = Integer.parseInt(splitAction[1]);
					int y = Integer.parseInt(splitAction[2]);
					Direction direction = null;
					if(x > actor.getCell().getX()) {
						direction = Direction.RIGHT;
					}
					else if(x < actor.getCell().getX()) {
						direction = Direction.LEFT;
					}
					else if(y > actor.getCell().getY()) {
						direction = Direction.DOWN;
					}
					else if(y < actor.getCell().getY()) {
						direction = Direction.UP;
					}
					actionMove.setDirection(direction);
					action = actionMove;
					break;
				case "Arrow":
					ActionPutArrow actionPutArrow = new ActionPutArrow();
					actionPutArrow.setActor(actor);
					actionPutArrow.setChoosed(true);
					int posX = Integer.parseInt(splitAction[1]);
					int posY = Integer.parseInt(splitAction[2]);
					String sDirection = splitAction[3];
					Direction putDirection = null;
					Direction arrowDirection = null;
					if(posX > actor.getCell().getX()) {
						putDirection = Direction.RIGHT;
					}
					else if(posX < actor.getCell().getX()) {
						putDirection = Direction.LEFT;
					}
					else if(posY > actor.getCell().getY()) {
						putDirection = Direction.DOWN;
					}
					else if(posY < actor.getCell().getY()) {
						putDirection = Direction.UP;
					}
					
					switch (sDirection)
					{
					case "D":
						arrowDirection = Direction.DOWN;
						break;
					case "U":
						arrowDirection = Direction.UP;
						break; 
					case "R":
						arrowDirection = Direction.RIGHT;
						break; 
					case "L":
						arrowDirection = Direction.LEFT;
						break; 
					default:
						/*Action*/;             
					}
					
					actionPutArrow.setDirection(putDirection);
					actionPutArrow.setArrowDirection(arrowDirection);
					action = actionPutArrow;
					break;
				default:
					/*Action*/;             
				}
			}
		}
		return action;
	}

}
