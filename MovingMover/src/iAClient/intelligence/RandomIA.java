package iAClient.intelligence;

import java.util.ArrayList;
import java.util.Random;


public class RandomIA extends GenericIA {
		
	public RandomIA(ArrayList<String> gameData){
		
		super(gameData);
		
	}
	


	@Override
	public String playTurn(ArrayList<String> inputData){
		
		super.playTurn(inputData);
				
		Random rand = new Random();
		int r = rand.nextInt(32);
		String result = new String();
		
		if(r/4 == 1){
			result = "Move " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) - 1);;
		}
		if(r/4 == 2){
			result = "Move " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) + 1);;
		}
		if(r/4 == 3){
			result = "Move " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		}
		if(r/4 == 0){
			result = "Move " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		}
		
		if(r%4 == 0){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) - 1);
		}
		if(r%4 == 1){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) + 1);
		}	
		if(r%4 == 2){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1) - 1);
		}
		if(r%4 == 3){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1) + 1);
		}
		if(r%4 == 4){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1) - 1);
		}
		if(r%4 == 5){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1) + 1);
		}
		if(r%4 == 6){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		}
		if(r%4 == 7){
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		}	
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(null));
	
		return result;
	}
}