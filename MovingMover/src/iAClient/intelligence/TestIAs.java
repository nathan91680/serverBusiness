package iAClient.intelligence;

import java.util.ArrayList;


public class TestIAs {
	
	public static void main(){
		
		ArrayList<String> gameData = new ArrayList<String>();
		
		// construire gameData (map)
		// 1 string par ligne (holes + cases accessibles + 2 joueurs a placer)
		
		ReflexIA1 ia1 = new ReflexIA1(gameData);
		RandomIA rdia = new RandomIA(gameData);
		
		// boucle affrontement IAs : joue ici puis la en printant les pos

	}

}

// faire autre ia inspiree de la premiere mais moins forte + ameliorer la premiere + minmax