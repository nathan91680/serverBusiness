package iAClient.intelligence;

import java.util.ArrayList;
import java.util.StringTokenizer;


public abstract class GenericIA {

	public GenericIA(ArrayList<String> gameData){
		
		//ArrayList<String> gameData = new ArrayList<String>();
		
		readMap(gameData);
		
		arrows.add('L');
		arrows.add('R');
		arrows.add('U');
		arrows.add('D');		
	}
	
	ArrayList<ArrayList<Integer> > holes = new ArrayList<ArrayList<Integer> >();
	int dimXTerrain, dimYTerrain;
	int nbPlayers = 2;
	int nbArrows = 0;
	ArrayList<ArrayList<Integer> > posPlayers = new ArrayList<ArrayList<Integer> >();
	ArrayList<ArrayList<Integer> > posArrows = new ArrayList<ArrayList<Integer> >();
	ArrayList<Character> dirArrows = new ArrayList<Character>();
	ArrayList<Character> arrows = new ArrayList<Character>();
	
	public String playTurn(ArrayList<String> inputData){
		
		String result = "";

		handleInputData(inputData);
		
		return result;
	}
	
	public void handleInputData(ArrayList<String> inputData){
		
		StringTokenizer st = null;
		
		String data;

		ArrayList<Integer> pos = new ArrayList<Integer>();
		
		for(int i = 0 ; i < inputData.size() ; i++){
			
			data = inputData.get(i);
			
			if (i == 0){
				nbArrows = Integer.parseInt(data);
			}
			else if(i == 1){
				
				st = new StringTokenizer(data);
				
				while( st.hasMoreTokens() ){
						
					pos = new ArrayList<Integer>();
					pos.add( Integer.parseInt(st.nextToken() ));//x
					pos.add( Integer.parseInt(st.nextToken() ));//y
					dirArrows.add( st.nextToken().charAt(0) );//direction					
					posArrows.add(pos);
				}
				
			}
			if (i == 2){
				nbPlayers = Integer.parseInt(data);
			}
			else if(i == 3){
				
				st = new StringTokenizer(data);
				
				while( st.hasMoreTokens() ){
						
					pos = new ArrayList<Integer>();
					pos.add( Integer.parseInt(st.nextToken() ));//x
					pos.add( Integer.parseInt(st.nextToken() ));//y
					posPlayers.add(pos);
				}
				
			}					
			
		}
	}
	
	
	public void readMap(ArrayList<String> gameData){
				
		ArrayList<Integer> pos = new ArrayList<Integer>();
		
		dimYTerrain = gameData.size();
		
		dimXTerrain = 0;
		
		for (int j = 0 ; j < dimYTerrain ; j++){
			
			String lineData = gameData.get(j);
			
			if (lineData.length() > dimXTerrain)
				dimXTerrain = lineData.length();
			
			for(int i = 0 ; i < lineData.length() ; i++){
				
				if (lineData.charAt(i) == 'X'){
					
					pos = new ArrayList<Integer>();
					pos.add(j);
					pos.add(i);
					holes.add(pos);					
				}
				
				
			}
		}
		
	}
	
	int max(int a, int b){
		return ( (a>b) ? a : b );
	}
	
	boolean contain(ArrayList <ArrayList <Integer>> contener, ArrayList <Integer> temp) {

		for (int i = 0 ; i < contener.size(); i++) {
			if(temp.get(0).intValue() == contener.get(i).get(0).intValue() && temp.get(1).intValue() == contener.get(i).get(1).intValue())
				return true;
		}
		return false;
	}

}
