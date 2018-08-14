package servlets.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import metier.service.api.IGameService;
import presentation.converter.GameConverter;
import presentation.dto.GameDto;
import presentation.dto.MapDto;
import servlets.abstr.CustomHttpServlet;
import servlets.api.IGameController;
import session.impl.Game;
import session.impl.GameSession;

@RestController
public class GameController extends CustomHttpServlet implements IGameController{
	
private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGameService gameService;

	@Override
	@RequestMapping(value = "/getGame", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<GameDto> getGame() {
		Game game = gameService.createGame();
		GameDto gameDto = new GameDto();
		try {
			gameDto = GameConverter.toDto(game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<GameDto> response = new ResponseEntity<GameDto>(gameDto,HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/getMap", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MapDto> getMap() {
		Game game = gameService.createGame();
		GameDto gameDto = new GameDto();
		try {
			gameDto = GameConverter.toDto(game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<MapDto> response = new ResponseEntity<MapDto>(gameDto.getMapDto(),HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/getNbConnection", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> getNbConnection(HttpServletRequest request) {
		GameSession gameSession = (GameSession) request.getSession().getAttribute("gameSession");
		if(gameSession == null) {
			gameSession = new GameSession();
			gameSession.setNbConnection(0);
			request.getSession().setAttribute("gameSession", gameSession);
		}
		gameSession.setNbConnection(gameSession.getNbConnection()+1);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(new Integer (gameSession.getNbConnection()),HttpStatus.OK);
		return response;
	}
}
