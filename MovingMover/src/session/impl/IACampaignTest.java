package session.impl;

import java.util.ArrayList;

import iAClient.converter.CampaignIaConverter;
import iAClient.intelligence.MinMaxIA1action;
import session.abstr.Action;
import session.abstr.IA;

public class IACampaignTest extends IA{

	@Override
	public Action calculNextAction(Player player, Game game) {
		
		ArrayList<String> sMap = CampaignIaConverter.toClientInit(game.getMap());
		ArrayList<String> positions = CampaignIaConverter.toClient(game);
		
		MinMaxIA1action clientIA = new MinMaxIA1action(sMap);
		String sAction = clientIA.playTurn(positions);
		
		Action action = CampaignIaConverter.getAction(sAction, player);
		
		return action;
	}

}
