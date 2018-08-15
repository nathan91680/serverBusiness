package iAClient.intelligence;

import java.util.ArrayList;


public class ReflexIA21action extends GenericIA {
		
	public ReflexIA21action(ArrayList<String> gameData){
		
		super(gameData);
		
	}
	
	ArrayList<ArrayList<Integer> > posPlayersSave = new ArrayList<ArrayList<Integer> >();

	
	/*
	 * inputData : 
	 * nombreFleches
	 * x1 y1 dir1 ...  xn yn dirN
	 * nombreJoueurs
	 * x1 y1 ... xn yn
	 * 
	 * */
	@Override
	public String playTurn(ArrayList<String> inputData){
		
		super.playTurn(inputData);
		
		String result = eval();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(null));
	
		return result;
	}
	
	// strategie : blinder carte de fleches avec de partout un chemin vers trou
	// entourer l'adversaire
	// on demarre d'un trou(+ roche delui ou moi), on pose des fleches dans l'ordre case par case 
	// (1 depl - 1 fleche ou plusieurs a determiner - en boucle)
	// on se rapproche de lui - on essaye de l'entourer au max - 
	// tout en priorisant l'esquive des fleches qui nous amene sur ce chemin
	// => se fait en 1 action ou 2 actions (a adapter)
	
	public String eval(){
		
		
		String result = "";
				
		char directionEnemy = 'L', directionClosestHole = 'L', dirArrow = 'L';
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
	
		int indice = 0;
		
		/*  determination du joueur le plus proche
		for(int i = 1 ; i < posPlayers.size() ; i++){
			
			
		}*/
		
		// stratégie : placer une fleche au plus pres de l'adversaire (devant lui en fonction de la direction ou il va)
		// fleche pointant vers le mur le plus proche
		
		/*  determination du trou le plus proche de l'adversaire en ligne droite */
		for(int i = 1 ; i < max(dimXTerrain, dimYTerrain) ; i++){
			
			temp.add(posPlayers.get(1).get(0) - i);
			temp.add(posPlayers.get(1).get(1));
			
			if(holes.contains(temp)){
				directionClosestHole = 'L';
				break;
			}
			
			temp.clear();
			temp.add(posPlayers.get(1).get(0) + i);
			temp.add(posPlayers.get(1).get(1));
			
			if(holes.contains(temp)){
				directionClosestHole = 'R';			
				break;
			}
			
			temp.clear();
			temp.add(posPlayers.get(1).get(0));
			temp.add(posPlayers.get(1).get(1) - i);			
			
			if(holes.contains(temp)){
				directionClosestHole = 'U';
				break;
			}
			
			temp.clear();
			temp.add(posPlayers.get(1).get(0));
			temp.add(posPlayers.get(1).get(1) + i);			
			
			if(holes.contains(temp)){
				directionClosestHole = 'D';	
				break;
			}					
		}	
		
		if(posPlayers.get(1).get(0) < posPlayersSave.get(1).get(0))
			directionEnemy = 'L';
		if(posPlayers.get(1).get(0) > posPlayersSave.get(1).get(0))
			directionEnemy = 'R';		
		if(posPlayers.get(1).get(1) < posPlayersSave.get(1).get(1))
			directionEnemy = 'U';		
		if(posPlayers.get(1).get(1) > posPlayersSave.get(1).get(1))
			directionEnemy = 'D';			

		
		int futureEnnemyPosX = posPlayers.get(1).get(0) + 1;
		int futureEnnemyPosY = posPlayers.get(1).get(1);		
		//if(directionEnemy == 'R'){, valeurs ci-dessus

		if(directionEnemy == 'L'){			
			futureEnnemyPosX = posPlayers.get(1).get(0) - 1;
		}	
		if(directionEnemy == 'U'){
			
			futureEnnemyPosX = posPlayers.get(1).get(0);
			futureEnnemyPosY = posPlayers.get(1).get(1) - 1;
		}			
		if(directionEnemy == 'D'){
			
			futureEnnemyPosX = posPlayers.get(1).get(0);
			futureEnnemyPosY = posPlayers.get(1).get(1) + 1;			
		}	

		result = "Move " + String.valueOf(futureEnnemyPosX) + " " + String.valueOf(futureEnnemyPosY);	
		
		temp.clear();
		temp.add(futureEnnemyPosX);
		temp.add(futureEnnemyPosY);
		
		//si on est suffisamment pres et s'il n'y a pas de fleche posee, on la pose au niveau de la position future supposee de l'ennemi
		if(Math.abs(futureEnnemyPosY - posPlayers.get(0).get(1)) < 2 &&
				Math.abs(futureEnnemyPosX - posPlayers.get(0).get(0)) < 2
				&& ! posArrows.contains(temp))
				result += "\nArrow " + String.valueOf(futureEnnemyPosX) + " " + String.valueOf(futureEnnemyPosY) + " " + directionClosestHole;
		
		else if(posArrows.contains(temp)){
			
			indice = posArrows.indexOf(temp);
			dirArrow = dirArrows.get(indice);
				
			if(dirArrow == 'L'
					&& Math.abs(futureEnnemyPosY - posPlayers.get(0).get(1)) < 2 &&
					Math.abs(futureEnnemyPosX - 1 - posPlayers.get(0).get(0)) < 2)
				result += "\nArrow " + String.valueOf(futureEnnemyPosX - 1) + " " + String.valueOf(futureEnnemyPosY) + " " + directionClosestHole;			

			if(dirArrow == 'R'
					&& Math.abs(futureEnnemyPosY - posPlayers.get(0).get(1)) < 2 &&
					Math.abs(futureEnnemyPosX + 1 - posPlayers.get(0).get(0)) < 2)
				result += "\nArrow " + String.valueOf(futureEnnemyPosX + 1) + " " + String.valueOf(futureEnnemyPosY) + " " + directionClosestHole;				

			if(dirArrow == 'U'
					&& Math.abs(futureEnnemyPosY - 1 - posPlayers.get(0).get(1)) < 2 &&
					Math.abs(futureEnnemyPosX - posPlayers.get(0).get(0)) < 2)
				result += "\nArrow " + String.valueOf(futureEnnemyPosX) + " " + String.valueOf(futureEnnemyPosY - 1) + " " + directionClosestHole;	
			
			if(dirArrow == 'D'
					&& Math.abs(futureEnnemyPosY + 1 - posPlayers.get(0).get(1)) < 2 &&
					Math.abs(futureEnnemyPosX - posPlayers.get(0).get(0)) < 2)
				result += "\nArrow " + String.valueOf(futureEnnemyPosX) + " " + String.valueOf(futureEnnemyPosY + 1) + " " + directionClosestHole;				
		}
		
		posPlayersSave = posPlayers;
		

		
		return result;		
	}

}
