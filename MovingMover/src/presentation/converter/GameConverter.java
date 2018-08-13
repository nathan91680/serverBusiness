package presentation.converter;

import java.util.ArrayList;

import presentation.dto.ActionMoveDto;
import presentation.dto.ActionPutArrowDto;
import presentation.dto.DeathPlayerDto;
import presentation.dto.GameDto;
import presentation.dto.HistoryEventDto;
import presentation.dto.HitArrowDto;
import presentation.dto.MapDto;
import presentation.dto.PlacementPlayerDto;
import session.impl.ActionMove;
import session.impl.ActionPutArrow;
import session.impl.DeathPlayer;
import session.impl.Game;
import session.impl.HitArrow;
import session.impl.PlacementPlayer;

public class GameConverter {
	
	public static GameDto toDto(Game game) throws Exception {
		
		GameDto gameDto = new GameDto();
		
		/** Set de la map */
		MapDto mapDto = new MapDto();
		mapDto.setHeight(game.getMap().getHeight());
		mapDto.setWidth(game.getMap().getWidth());
		gameDto.setMapDto(mapDto);
		
		/** Set des evenements */
		ArrayList<HistoryEventDto> histoEventsDto = new ArrayList<HistoryEventDto>();
		
		for(int i = 0; i < game.getHistory().size();i++) {
			HistoryEventDto historyEventDto = new HistoryEventDto();
			
			if(game.getHistory().get(i).getEvent() instanceof ActionMove) {
				ActionMove actionMove = (ActionMove)game.getHistory().get(i).getEvent();
				ActionMoveDto actionMoveDto = new ActionMoveDto();
				actionMoveDto.setDirection(actionMove.getDirection().toString());
				actionMoveDto.setIdPlayer(actionMove.getActor().getId().intValue());
				actionMoveDto.setChoosed(actionMove.isChoosed());
				historyEventDto.setEventDto(actionMoveDto);
			}
			else if(game.getHistory().get(i).getEvent() instanceof PlacementPlayer) {
				PlacementPlayer placementPlayer = (PlacementPlayer)game.getHistory().get(i).getEvent();
				PlacementPlayerDto placementPlayerDto = new PlacementPlayerDto();
				placementPlayerDto.setIdPlayer(placementPlayer.getPlayer().getId().intValue());
				placementPlayerDto.setX(placementPlayer.getCell().getX());
				placementPlayerDto.setY(placementPlayer.getCell().getY());
				historyEventDto.setEventDto(placementPlayerDto);
			}
			else if(game.getHistory().get(i).getEvent() instanceof DeathPlayer) {
				DeathPlayer deathPlayer = (DeathPlayer)game.getHistory().get(i).getEvent();
				DeathPlayerDto deathPlayerDto = new DeathPlayerDto();
				deathPlayerDto.setIdPlayer(deathPlayer.getVictim().getId().intValue());
				historyEventDto.setEventDto(deathPlayerDto);
			}
			else if(game.getHistory().get(i).getEvent() instanceof ActionPutArrow) {
				ActionPutArrow actionPutArrow = (ActionPutArrow)game.getHistory().get(i).getEvent();
				ActionPutArrowDto actionPutArrowDto = new ActionPutArrowDto();
				actionPutArrowDto.setArrowDirection(actionPutArrow.getArrowDirection().toString());
				actionPutArrowDto.setIdPlayer(actionPutArrow.getActor().getId());
				actionPutArrowDto.setDirection(actionPutArrow.getDirection().toString());
				actionPutArrowDto.setChoosed(actionPutArrow.isChoosed());
				historyEventDto.setEventDto(actionPutArrowDto);
			}
			else if(game.getHistory().get(i).getEvent() instanceof HitArrow) {
				HitArrow hitArrow = (HitArrow)game.getHistory().get(i).getEvent();
				HitArrowDto hitArrowDto = new HitArrowDto();
				hitArrowDto.setState(hitArrow.getArrow().getState().toString());
				hitArrowDto.setX(hitArrow.getArrow().getCell().getX());
				hitArrowDto.setY(hitArrow.getArrow().getCell().getY());
				historyEventDto.setEventDto(hitArrowDto);
			}
			else {
				throw new Exception();
			}
			historyEventDto.setTimeExecution(game.getHistory().get(i).getTimeExecution());
			histoEventsDto.add(historyEventDto);
		}
		gameDto.setHistoEventsDto(histoEventsDto);
		return gameDto;
	}

}
