package iAClient.intelligence;

import java.util.ArrayList;

public class MapTravelerIA extends GenericIA{

	public MapTravelerIA(ArrayList<String> gameData) {
		super(gameData);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String playTurn(ArrayList<String> inputData){

		super.playTurn(inputData);

		int x = posPlayers.get(0).get(0);

		int y = posPlayers.get(0).get(1);

		int size = dimXTerrain;

		String result = "Move "+(x-1)+" "+y;
		
		if(x == 0 && y < size-1) {
			result = "Move "+x+" "+(y+1);
		}

		else if(x < size - 1 && y == size-1) {
			result = "Move "+(x+1)+" "+y;
		}

		else if(x == size-1 && y > 0) {
			result = "Move "+x+" "+(y-1);
		}

		else if(x > 0 && y == 0) {
			result = "Move "+(x-1)+" "+y;
		}

		

		return result;
	}

}
