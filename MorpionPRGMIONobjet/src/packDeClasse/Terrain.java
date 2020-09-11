package packDeClasse;

public class Terrain {
	private Case cases[][];
	private int longeurTerrain,HauteurTerrain;
	
	public Terrain(int longueur,int hauteur) {
		this.longeurTerrain=longueur;
		this.HauteurTerrain=hauteur;
		initialiserTerrain();
	}
	
	public void initialiserTerrain() {
		this.cases=new Case[longeurTerrain][HauteurTerrain];
		for(int positionY=0;positionY<HauteurTerrain;positionY++) 
			for(int positionX=0;positionX<longeurTerrain;positionX++) {
				cases[positionX][positionY]=new Case();
			}
	}
	
	public void afficherTerrain() {
		for(int positionY=0;positionY<HauteurTerrain;positionY++) {
			for(int positionX=0;positionX<longeurTerrain;positionX++) {
				if(cases[positionX][positionY].estLibre()) {
					System.out.print("| ");
				}
				else
					System.out.print("|"+cases[positionX][positionY].getSignePropriétaire());
			}
		System.out.println("|");
		}
	}
	
	public boolean verifierCasePriseOuHorsLimit(int[] proposition) {
		if(cases[proposition[0]][proposition[1]].estLibre() && (proposition[0]<this.longeurTerrain && proposition[0]>=0) && (proposition[1]<this.HauteurTerrain && proposition[1]>=0)) {
			
			return true;
		}
		else
			return false;
	}
	
	public boolean caseAppartientJoueur(int[] positionCase,Joueur joueur) {
		return cases[positionCase[0]][positionCase[1]].joueurEstPropriétaire(joueur);
	}
	
	public boolean estPasConsommer(int[] positionCase, String direction) {
		return cases[positionCase[0]][positionCase[1]].estPasConsommer(direction);
	}
	
	public void consommerComboCase(int[] positionCase,String direction) {
		cases[positionCase[0]][positionCase[1]].consommerCombo(direction);
	}
	
	public int getLongueur(){
		return this.longeurTerrain;
	}
	
	public int getHauteur(){
		return this.HauteurTerrain;
	}
	
	public void placeLePionEn(int[] proposition,Joueur joueur) {
		cases[proposition[0]][proposition[1]].joueurProprietaire(joueur);
	}
}


