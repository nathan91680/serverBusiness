package iAClient.intelligence;

import java.util.ArrayList;


public class MinMaxIA1action extends GenericIA{

	public MinMaxIA1action(ArrayList<String> gameData){

		super(gameData);	
	}

	@Override
	public String playTurn(ArrayList<String> inputData){

		int profArbre = 2;

		super.playTurn(inputData);

		String result = minMax(inputData, profArbre);

		//BufferedReader br = new BufferedReader(new InputStreamReader(null));

		return result;
	}

	// idees elagage :
	// inutile de faire deplacement dans un sens puis fleche derriere (posAnterieure) ?
	// utiliser position relative (ne pas aller dans la direction opposee de l'autre
	// ou encore a appliquer a partir d'une distance de 2 selon HB ou GD)
	// fonction qui determine si on va preferer se deplacer (20=>4) : si distance a l'autre
	// trop elevee, sauf si on est entrain de construire un chemin de fleches..

	// adapter reflexe1 a regle1 ou faire new ?

	// strategie : blinder carte de fleches avec de partout un chemin vers trou
	// entourer l'adversaire
	// on demarre d'un trou(+ roche delui ou moi), on pose des fleches dans l'ordre case par case 
	// (1 depl - 1 fleche ou plusieurs a determiner - en boucle)
	// on se rapproche de lui - on essaye de l'entourer au max - 
	// tout en priorisant l'esquive des fleches qui nous amene sur ce chemin
	// => se fait en 1 action ou 2 actions (a adapter)

	// Algo MinMax
	private String minMax(ArrayList<String> inputData, int profondeur){

		String result = new String();
		int max = -10000;
		int tmp,maxi = 0,maxj = 0, maxk = 0;

		//1: simuler coup
		int newXPlayer = posPlayers.get(0).get(0);
		int newYPlayer = posPlayers.get(0).get(1);

		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(int i=0 ; i<4 ; i++) { //deplacement

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectations deplacements

			//if(p.getTabPionPos(i, j) == Pion.Rien){//revoir if pour verif chaque cas

			if(i==0){

				newXPlayer = posPlayers.get(0).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue() )//x+1, y))					
					posPlayers.get(0).set(0, newXPlayer);
				else
					continue;
			}


			else if(i==1){

				newXPlayer = posPlayers.get(0).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x-1, y))					
					posPlayers.get(0).set(0, newXPlayer);
				else
					continue;
			}                 		


			else if(i==2){

				newYPlayer = posPlayers.get(0).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y+1))					
					posPlayers.get(0).set(1, newYPlayer);
				else
					continue;
			} 

			else if(i==3){

				newYPlayer = posPlayers.get(0).get(1) - 1;                    	
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y-1))					
					posPlayers.get(0).set(1, newYPlayer);
				else
					continue;
			} 			


			// simu 
			tmp = min(profondeur-1);//etatJeu

			if(tmp > max){
				max = tmp;
				maxi = i;
			}

			//restaurer donnees avant simu
			//setTabPionPos(i, j, Pion.Rien);                     


			if(i==0)					
				posPlayers.get(0).set(0, posPlayers.get(0).get(0) - 1);
			else if(i==1)					
				posPlayers.get(0).set(0, posPlayers.get(0).get(0) + 1);
			else if(i==2)					
				posPlayers.get(0).set(1, posPlayers.get(0).get(1) - 1);
			else if(i==3)					
				posPlayers.get(0).set(1, posPlayers.get(0).get(1) + 1);			
		}			


		for(int j=0 ; j<4 ; j++){ //fleche

			//idee elagage
			//if(distancePlayers > 6)
			//break;

			newXPlayer = posPlayers.get(0).get(0);
			newYPlayer = posPlayers.get(0).get(1);

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectation fleches

			if(j==0){

				newXPlayer = posPlayers.get(0).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x+1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==1){

				newXPlayer = posPlayers.get(0).get(0) - 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x-1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==2){

				newYPlayer = posPlayers.get(0).get(1) + 1;
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y+1))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==3){

				newYPlayer = posPlayers.get(0).get(1) - 1;
				temp.set(1, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y-1))					
					posArrows.add(temp);
				else
					continue;
			}

			for(int k=0 ; k<4 ; k++){ //sens fleche

				// affectation sens fleches


				if(k==0)
					dirArrows.add('L');
				if(k==1)
					dirArrows.add('R');
				if(k==2)
					dirArrows.add('U');
				if(k==3)
					dirArrows.add('D');

				// simu 
				tmp = min(profondeur-1);//etatJeu

				if(tmp > max){
					max = tmp;
					maxi = -1;
					maxj = j;
					maxk = k;
				}

				//restaurer donnees avant simu
				//setTabPionPos(i, j, Pion.Rien);                     
				dirArrows.remove(dirArrows.size()-1);					
			}

			posArrows.remove(posArrows.size()-1);					
		}
		//System.out.println(max);
		//setTabPionPos(maxi, maxj);
		if(maxi == 0)
			result = "Move " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		else if(maxi == 1)
			result = "Move " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1));
		else if(maxi == 2)
			result = "Move " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) + 1);
		else if(maxi == 3)
			result = "Move " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) - 1);
		//result += "\nArrow " + String.valueOf(futureEnnemyPosX) + " " + String.valueOf(futureEnnemyPosY + 1) + " " + directionClosestHole;				

		else{
			if(maxj == 0)
				result += "Arrow " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1)) + " " + arrows.get(maxk);				
			else if(maxj == 1)
				result += "Arrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1)) + " " + arrows.get(maxk);				
			else if(maxj == 2)
				result += "Arrow " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) + 1) + " " + arrows.get(maxk);				
			else if(maxj == 3)
				result += "Arrow " + String.valueOf(posPlayers.get(0).get(0)) + " " + String.valueOf(posPlayers.get(0).get(1) - 1) + " " + arrows.get(maxk);	
		}
		/*else if(maxj == 4)
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1) + 1) + " " + dirArrows.get(dirArrows.size() - 1);				
		else if(maxj == 5)
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) - 1) + " " + String.valueOf(posPlayers.get(0).get(1) - 1) + " " + dirArrows.get(dirArrows.size() - 1);				
		else if(maxj == 6)
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1) + 1) + " " + dirArrows.get(dirArrows.size() - 1);				
		else if(maxj == 7)
			result += "\nArrow " + String.valueOf(posPlayers.get(0).get(0) + 1) + " " + String.valueOf(posPlayers.get(0).get(1) - 1) + " " + dirArrows.get(dirArrows.size() - 1);	              
		 */
		//return string move x y - arrow x y dir
		return result;
	}	

	private int max(int profondeur){

		int max = -10000;
		int tmp;

		if(profondeur == 0){// || estGagnant() || matchNul()) {
			return eval();
		}

		//1: simuler coup
		int newXPlayer = posPlayers.get(0).get(0);
		int newYPlayer = posPlayers.get(0).get(1);

		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(int i=0 ; i<4 ; i++) { //deplacement

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectations deplacements

			//if(p.getTabPionPos(i, j) == Pion.Rien){//revoir if pour verif chaque cas

			if(i==0){

				newXPlayer = posPlayers.get(0).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue() )//x+1, y))					
					posPlayers.get(0).set(0, newXPlayer);
				else
					continue;
			}


			else if(i==1){

				newXPlayer = posPlayers.get(0).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x-1, y))					
					posPlayers.get(0).set(0, newXPlayer);
				else
					continue;
			}                 		


			else if(i==2){

				newYPlayer = posPlayers.get(0).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y+1))					
					posPlayers.get(0).set(1, newYPlayer);
				else
					continue;
			} 

			else if(i==3){

				newYPlayer = posPlayers.get(0).get(1) - 1;                    	
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y-1))					
					posPlayers.get(0).set(1, newYPlayer);
				else
					continue;
			} 					


			// simu 
			tmp = min(profondeur-1);//etatJeu

			if(tmp > max)
				max = tmp;

			//restaurer donnees avant simu
			//setTabPionPos(i, j, Pion.Rien);                     


			if(i==0)					
				posPlayers.get(0).set(0, posPlayers.get(0).get(0) - 1);
			else if(i==1)					
				posPlayers.get(0).set(0, posPlayers.get(0).get(0) + 1);
			else if(i==2)					
				posPlayers.get(0).set(1, posPlayers.get(0).get(1) - 1);
			else if(i==3)					
				posPlayers.get(0).set(1, posPlayers.get(0).get(1) + 1);			
		}			


		for(int j=0 ; j<4 ; j++){ //fleche

			newXPlayer = posPlayers.get(0).get(0);
			newYPlayer = posPlayers.get(0).get(1);

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectation fleches

			if(j==0){

				newXPlayer = posPlayers.get(0).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x+1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==1){

				newXPlayer = posPlayers.get(0).get(0) - 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x-1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==2){

				newYPlayer = posPlayers.get(0).get(1) + 1;
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y+1))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==3){

				newYPlayer = posPlayers.get(0).get(1) - 1;
				temp.set(1, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(1).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(1).get(1).intValue())//x, y-1))					
					posArrows.add(temp);
				else
					continue;
			}


			for(int k=0 ; k<4 ; k++){ //sens fleche

				// affectation sens fleches

				if(k==0)
					dirArrows.add('L');
				if(k==1)
					dirArrows.add('R');
				if(k==2)
					dirArrows.add('U');
				if(k==3)
					dirArrows.add('D');

				// simu 
				tmp = min(profondeur-1);//etatJeu

				if(tmp > max)
					max = tmp;

				//restaurer donnees avant simu
				//setTabPionPos(i, j, Pion.Rien);                     
				dirArrows.remove(dirArrows.size()-1);					
			}

			posArrows.remove(posArrows.size()-1);					
		}
		return max;
	}

	private int min(int profondeur){

		int min = 10000;
		int tmp;

		if(profondeur == 0){// || estGagnant() || matchNul()) {
			return eval();
		}

		//1: simuler coup
		int newXPlayer = posPlayers.get(1).get(0);
		int newYPlayer = posPlayers.get(1).get(1);

		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(int i=0 ; i<4 ; i++) { //deplacement

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectations deplacements

			//if(p.getTabPionPos(i, j) == Pion.Rien){//revoir if pour verif chaque cas

			if(i==0){

				newXPlayer = posPlayers.get(1).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x+1, y))					
					posPlayers.get(1).set(0, newXPlayer);
				else
					continue;
			}


			else if(i==1){

				newXPlayer = posPlayers.get(1).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x-1, y))					
					posPlayers.get(1).set(0, newXPlayer);
				else
					continue;
			}                 		


			else if(i==2){

				newYPlayer = posPlayers.get(1).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x, y+1))					
					posPlayers.get(1).set(1, newYPlayer);
				else
					continue;
			} 

			else if(i==3){

				newYPlayer = posPlayers.get(1).get(1) - 1;                    	
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x, y-1))					
					posPlayers.get(1).set(1, newYPlayer);
				else
					continue;
			} 			


			// simu 
			tmp = max(profondeur-1);//etatJeu

			if(tmp < min){
				min = tmp;
			}

			//restaurer donnees avant simu
			//setTabPionPos(i, j, Pion.Rien);                     


			if(i==0)					
				posPlayers.get(1).set(0, posPlayers.get(1).get(0) - 1);
			else if(i==1)					
				posPlayers.get(1).set(0, posPlayers.get(1).get(0) + 1);
			else if(i==2)					
				posPlayers.get(1).set(1, posPlayers.get(1).get(1) - 1);
			else if(i==3)					
				posPlayers.get(1).set(1, posPlayers.get(1).get(1) + 1);			
		}			


		for(int j=0 ; j<4 ; j++){ //fleche

			newXPlayer = posPlayers.get(1).get(0);
			newYPlayer = posPlayers.get(1).get(1);

			temp.add(newXPlayer);
			temp.add(newYPlayer);

			// affectation fleches

			if(j==0){

				newXPlayer = posPlayers.get(1).get(0) + 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x+1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==1){

				newXPlayer = posPlayers.get(1).get(0) - 1;
				temp.set(0, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x-1, y))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==2){

				newYPlayer = posPlayers.get(1).get(1) + 1;
				temp.set(1, newYPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x, y+1))					
					posArrows.add(temp);
				else
					continue;
			}

			else if(j==3){

				newYPlayer = posPlayers.get(1).get(1) - 1;
				temp.set(1, newXPlayer);				

				if(! contain(holes,temp) && temp.get(0).intValue() != posPlayers.get(0).get(0).intValue() && temp.get(1).intValue() != posPlayers.get(0).get(1).intValue())//x, y-1))					
					posArrows.add(temp);
				else
					continue;
			}

			for(int k=0 ; k<4 ; k++){ //sens fleche

				// affectation sens fleches

				if(k==0)
					dirArrows.add('L');
				if(k==1)
					dirArrows.add('R');
				if(k==2)
					dirArrows.add('U');
				if(k==3)
					dirArrows.add('D');

				// simu 
				tmp = max(profondeur-1);//etatJeu

				if(tmp < min){
					min = tmp;
				}

				//restaurer donnees avant simu
				//setTabPionPos(i, j, Pion.Rien);                     
				dirArrows.remove(dirArrows.size()-1);					
			}

			posArrows.remove(posArrows.size()-1);					
		}
		return min;
	}    

	private int eval(){

		int score = 1000 - Math.abs(posPlayers.get(1).get(0) - posPlayers.get(0).get(0)) - Math.abs(posPlayers.get(1).get(1) - posPlayers.get(0).get(1));//but : se rapprocher de l'ennemi

		//idees :

		// si moi je suis sur une fleche qui m'envoie vers un trou alors 
		// -3*puissanceFleche si pas assez puissante (car rapproche)
		// - 10000 si m'envoie dans le trou (si aucun obstacle bloquant du genre une fleche dans une autre direction)
		// si une autre fleche dans une autre direction alors recalculer
		// si je ramasse un booster (ou ennemi proche) alors +10
		// perdre des points aussi si trop près d'une flêche qui rapprocherait d'un trou

		// fait :
		// se rapprocher de l'ennemi
		// si fleche negatif - si vers trou tres negatif

		// pour lui si fleche positif et si trou tres positif

		// ajouter boosters et (estimation deplacement autre joueur ? - pas logique minmax, mais bon argument.. ) 
		// fonction eval pourrait regarder le coup d'avant de l'autre pour l'estimer

		int indice;
		char directionEnemy = 'L', directionClosestHole = 'L', dirArrow = 'L';
		int dist = 0, puissanceFleche = 1;

		//gestion perso
		if (contain(posArrows,posPlayers.get(0))){

			score = -5000;
			ArrayList<Integer> temp = new ArrayList<Integer>();			

			boolean closestObjectIsArrow = true;
			indice = posArrows.indexOf(posPlayers.get(0));
			temp = posPlayers.get(0);

			ArrayList<Integer> posCLosestObjSave = new ArrayList<Integer>();
			posCLosestObjSave = posPlayers.get(0);
			ArrayList<Integer> posCLosestObj = new ArrayList<Integer>();

			while(closestObjectIsArrow){

				if(posCLosestObj.size() != 0){
					indice = posArrows.indexOf(posCLosestObj);
					temp = posCLosestObj;
					posCLosestObjSave = posCLosestObj;
				}

				dirArrow = dirArrows.get(indice);			

				if(dirArrow == 'L'){
					do{//attention limite de 0				)
						temp.set(0, temp.get(0) - 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = posCLosestObjSave.get(0) - temp.get(0);
				}
				if(dirArrow == 'R'){
					do{//attention limite de 0				)
						temp.set(0, temp.get(0) + 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = temp.get(0) - posCLosestObjSave.get(0);				
				}
				if(dirArrow == 'U'){
					do{//attention limite de 0				)
						temp.set(1, temp.get(1) - 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = posCLosestObjSave.get(1) - temp.get(1);				
				}
				if(dirArrow == 'D'){
					do{//attention limite de 0				)
						temp.set(1, temp.get(1) + 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = temp.get(1) - posCLosestObjSave.get(1);								
				}	

				if(dist <= puissanceFleche){//check quel est l'objet rencontre
					if (contain(holes,posCLosestObj))					   
						return -10000;
					else if(contain(posArrows,(posCLosestObj)))
						continue;
					else//ajouter cas autres objets genre boosters
						break;
				}
			}		    		 		    
		}

		// memes cas pour l'ennemi (factoriser dans une fct et ne pas redeclarer les memes var ainsi)

		if (contain(posArrows,posPlayers.get(1))){

			score = 5000;		   

			ArrayList<Integer> temp = new ArrayList<Integer>();			

			boolean closestObjectIsArrow = true;
			indice = posArrows.indexOf(posPlayers.get(1));
			temp = posPlayers.get(1);

			ArrayList<Integer> posCLosestObjSave = new ArrayList<Integer>();
			posCLosestObjSave = posPlayers.get(1);
			ArrayList<Integer> posCLosestObj = new ArrayList<Integer>();

			while(closestObjectIsArrow){

				if(posCLosestObj.size() != 0){
					indice = posArrows.indexOf(posCLosestObj);
					temp = posCLosestObj;
					posCLosestObjSave = posCLosestObj;
				}

				dirArrow = dirArrows.get(indice);			

				if(dirArrow == 'L'){
					do{//attention limite de 0				)
						temp.set(0, temp.get(0) - 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = posCLosestObjSave.get(0) - temp.get(0);
				}
				if(dirArrow == 'R'){
					do{//attention limite de 0				)
						temp.set(0, temp.get(0) + 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = temp.get(0) - posCLosestObjSave.get(0);				
				}
				if(dirArrow == 'U'){
					do{//attention limite de 0				)
						temp.set(1, temp.get(1) - 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );
					posCLosestObj = temp;
					dist = posCLosestObjSave.get(1) - temp.get(1);				
				}
				if(dirArrow == 'D'){
					do{
						temp.set(1, temp.get(1) + 1);
					}while (! (contain(holes,temp) || contain(posArrows,temp)) );//attention limite de 0				)

					posCLosestObj = temp;
					dist = temp.get(1) - posCLosestObjSave.get(1);								
				}	

				if(dist <= puissanceFleche){//check quel est l'objet rencontre
					if (contain(holes, posCLosestObj)) {
						return 10000;
					}


					else if(contain(posArrows,posCLosestObj))
						continue;
					else//ajouter cas autres objets genre boosters
						break;
				}
			}		    		 		    
		}		   

		//il faut que l'adv soit pres du trou, et nous loin
		// voir si une fleche est entrain de nous pousser ou va (moi et adv)

		return score;
	}

}








/*

private int max(int profondeur){

	int max = -10000;
	int tmp;

	ArrayList<Integer> posPlayerBackup = new ArrayList<Integer>();
	posPlayerBackup.add(posPlayers.get(0).get(0));
	posPlayerBackup.add(posPlayers.get(0).get(1));        

	ArrayList<ArrayList<Integer> > posArrowsBackup = new ArrayList<ArrayList<Integer> >();
	posArrowsBackup = posArrows;

	ArrayList<Character> dirArrowsBackup = new ArrayList<Character>();
	dirArrowsBackup = dirArrows;

	if(profondeur == 0){// || estGagnant() || matchNul()) {
		return eval();
	}

	for(int i=0 ; i<5 ; i++) { //deplacement
		for(int j=0 ; j<9 ; j++){ //fleche
			for(int k=0 ; k<4 ; k++){ //sens fleche

				if (j==8 && k != 0) // cas redondants car si pas de fleche ne pas checker tous les sens 
					continue;

				//1: simuler coup
				int newXPlayer = posPlayers.get(0).get(0) + 1;
				int newYPlayer = posPlayers.get(0).get(1);


				ArrayList<Integer> temp = new ArrayList<Integer>();

				temp.add(newXPlayer);
				temp.add(newYPlayer);


				//if(p.getTabPionPos(i, j) == Pion.Rien){//revoir if pour verif chaque cas
				if(i==0 && ! contain(holes,temp))//x+1, y))
					posPlayers.get(0).set(0, newXPlayer);

				newXPlayer = posPlayers.get(0).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(i==1 && ! contain(holes,temp))	//x-1, y))
					posPlayers.get(0).set(0, newXPlayer);                    		

				newXPlayer = posPlayers.get(0).get(0);                    	
				temp.set(0, newXPlayer);
				newYPlayer = posPlayers.get(0).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(i==2 && ! contain(holes,temp))//x, y+1))
					posPlayers.get(0).set(1, newYPlayer);

				newYPlayer = posPlayers.get(0).get(1) - 1;                    	
				temp.set(1, newYPlayer);

				if(i==3 && ! contain(holes,temp))	//x, y-1))
					posPlayers.get(0).set(1, newYPlayer);

				//i==4 : pas de deplacement

				// affectation fleches

				newXPlayer = posPlayers.get(0).get(0) + 1;                    	
				temp.set(0, newXPlayer);

				if(j==0 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newYPlayer = posPlayers.get(0).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(j==1 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}                    	

				newXPlayer = posPlayers.get(0).get(0);                    	
				temp.set(0, newXPlayer);

				if(j==2 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newXPlayer = posPlayers.get(0).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(j==3 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}                    	

				newYPlayer = posPlayers.get(0).get(1);                    	
				temp.set(1, newYPlayer);

				if(j==4 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				newYPlayer = posPlayers.get(0).get(1) - 1;                    	
				temp.set(1, newYPlayer);

				if(j==5 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				newXPlayer = posPlayers.get(0).get(0);                    	
				temp.set(0, newXPlayer);

				if(j==6 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newXPlayer = posPlayers.get(0).get(0) + 1;                    	
				temp.set(0, newXPlayer);

				if(j==7 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				//j==8 signifie pas de fleche 

				// affectation sens fleches

				if(j != 8){

					if(k==0)
						dirArrows.add('L');
					if(k==1)
						dirArrows.add('R');
					if(k==2)
						dirArrows.add('U');
					if(k==3)
						dirArrows.add('D');
				}


				tmp = min(profondeur-1);//etatJeu

				if(tmp > max){
					max = tmp;
				}
				//restaurer donnees avant simu
				//setTabPionPos(i, j, Pion.Rien);                     
				posPlayers.set(0, posPlayerBackup);
				posArrows =  posArrowsBackup;
				dirArrows =  dirArrowsBackup;						
			}
		}
	}
	return max;
}

private int min(int profondeur){

	int min = 10000;
	int tmp;

	ArrayList<Integer> posPlayerBackup = new ArrayList<Integer>();
	posPlayerBackup.add(posPlayers.get(1).get(0));
	posPlayerBackup.add(posPlayers.get(1).get(1));         

	ArrayList<ArrayList<Integer> > posArrowsBackup = new ArrayList<ArrayList<Integer> >();
	posArrowsBackup = posArrows;

	ArrayList<Character> dirArrowsBackup = new ArrayList<Character>();
	dirArrowsBackup = dirArrows;

	if(profondeur == 0){// || estGagnant() || matchNul()) {
		return eval();
	}

	for(int i=0 ; i<5 ; i++) { //deplacement
		for(int j=0 ; j<9 ; j++){ //fleche
			for(int k=0 ; k<4 ; k++){ //sens fleche

				if (j==8 && k != 0) // cas redondants car si pas de fleche ne pas checker tous les sens 
					continue;

				//1: simuler coup
				int newXPlayer = posPlayers.get(1).get(0) + 1;
				int newYPlayer = posPlayers.get(1).get(1);


				ArrayList<Integer> temp = new ArrayList<Integer>();

				temp.add(newXPlayer);
				temp.add(newYPlayer);


				//if(p.getTabPionPos(i, j) == Pion.Rien){//revoir if pour verif chaque cas
				if(i==0 && ! contain(holes,temp))//x+1, y))
					posPlayers.get(1).set(0, newXPlayer);

				newXPlayer = posPlayers.get(1).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(i==1 && ! contain(holes,temp))	//x-1, y))
					posPlayers.get(1).set(0, newXPlayer);                    		

				newXPlayer = posPlayers.get(1).get(0);                    	
				temp.set(0, newXPlayer);
				newYPlayer = posPlayers.get(1).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(i==2 && ! contain(holes,temp))//x, y+1))
					posPlayers.get(1).set(1, newYPlayer);

				newYPlayer = posPlayers.get(1).get(1) - 1;                    	
				temp.set(1, newYPlayer);

				if(i==3 && ! contain(holes,temp))	//x, y-1))
					posPlayers.get(1).set(1, newYPlayer);

				//i==4 : pas de deplacement


				// affectation fleches

				newXPlayer = posPlayers.get(1).get(0) + 1;                    	
				temp.set(0, newXPlayer);

				if(j==0 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newYPlayer = posPlayers.get(1).get(1) + 1;                    	
				temp.set(1, newYPlayer);

				if(j==1 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}                    	

				newXPlayer = posPlayers.get(1).get(0);                    	
				temp.set(0, newXPlayer);

				if(j==2 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newXPlayer = posPlayers.get(1).get(0) - 1;                    	
				temp.set(0, newXPlayer);

				if(j==3 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}                    	

				newYPlayer = posPlayers.get(1).get(1);                    	
				temp.set(1, newYPlayer);

				if(j==4 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				newYPlayer = posPlayers.get(1).get(1) - 1;                    	
				temp.set(1, newYPlayer);

				if(j==5 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				newXPlayer = posPlayers.get(1).get(0);                    	
				temp.set(0, newXPlayer);

				if(j==6 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				}

				newXPlayer = posPlayers.get(1).get(0) + 1;                    	
				temp.set(0, newXPlayer);

				if(j==7 && ! contain(holes,temp)){//x+1, y))
					posArrows.add(temp);
				} 

				//j==8 signifie pas de fleche                     	

				// affectation sens fleches

				if(j != 8){

					if(k==0)
						dirArrows.add('L');
					if(k==1)
						dirArrows.add('R');
					if(k==2)
						dirArrows.add('U');
					if(k==3)
						dirArrows.add('D');
				}

				tmp = max(profondeur-1);//etatJeu

				if(tmp < min){
					min = tmp;
				}
				//restaurer donnees avant simu
				//setTabPionPos(i, j, Pion.Rien);                     
				posPlayers.set(1, posPlayerBackup);
				posArrows =  posArrowsBackup;
				dirArrows =  dirArrowsBackup;						
			}
		}
	}
	return min;
} */