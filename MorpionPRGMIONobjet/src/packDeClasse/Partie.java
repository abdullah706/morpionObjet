package packDeClasse;

import java.util.Scanner;

public class Partie {
	public final static String[] DIRECTION_COMBO={"verticale","horizontale","digonaleDescendante","digonaleMontante"}; 
	
	private static Terrain terrain;
	private int nbJoueurs;
	private static Joueur joueursPartiEnCours[];
	private int nbManchesTotal;
	private int nbManchesJoué;
	private static int tourJoueur=-1;
	private Scanner saisie;

	public void initialiserTerrain() {
		int longueur,hauteur;
		System.out.println("Veuillez choisir la longueur du terrain !");
		longueur = saisie.nextInt();
		System.out.println("Veuillez choisir la hauteur du terrain !");
		hauteur = saisie.nextInt();

		terrain= new Terrain(longueur, hauteur);
		determinerNbManches(longueur, hauteur);
	}

	private void determinerNbManches(int longueur, int hauteur) {
		this.nbManchesTotal=longueur*hauteur;
	}

	private void initialiserJoueurs() {
		char signeJoueur;
		saisie = new Scanner(System.in);
		System.out.println("Veuillez choisir le nombre de joueurs");
		nbJoueurs=(saisie.nextInt());
		joueursPartiEnCours=new Joueur[nbJoueurs];
		saisie.nextLine();
		for(int compteurJoueurs=0;compteurJoueurs<nbJoueurs;compteurJoueurs++) {
			System.out.println("Veuillez choisir votre Signe");
			signeJoueur=(saisie.nextLine()).charAt(0);
			joueursPartiEnCours[compteurJoueurs]=new Joueur(compteurJoueurs,signeJoueur);
		}
	}

	private void auTourDe() {
		if(tourJoueur<nbJoueurs-1)
			tourJoueur++;
		else 
			tourJoueur=0;
		System.out.println(tourJoueur);
	}
	
	public static void ajouterScoreJoueur(Joueur joueur,int score) {
		joueur.ajouterScore(score);
	}
	
	public void afficherScore() {
		System.out.print("score : ");
		System.out.print(joueursPartiEnCours[0].connaitreScore());
		for(int compteurJoueur=1;compteurJoueur<nbJoueurs;compteurJoueur++)
			System.out.print(" - "+joueursPartiEnCours[compteurJoueur].connaitreScore());
		System.out.println();
	}
	
	public static int combienJoueurAmarquerPoints(int[] propositionJoueur) {
		int nbPointsMarqué=0;
		for(int comboPossible=0;comboPossible<4;comboPossible++) {
			if(verifierSiComboGagnant(DIRECTION_COMBO[comboPossible],propositionJoueur))
				nbPointsMarqué++;
		}
		return nbPointsMarqué;
	}
	
	public static boolean verifierSiComboGagnant(String direction,int[] propositionJoueur) {
		int vecteurX,vecteurY;
		int décalageEnX,décalageEnY;
		int positionDécaler[]=new int[2];
		int positionPionsRetenus[][]=new int[4][2];
		int nombrePionsretenus=1;
		positionPionsRetenus[0][0]=propositionJoueur[0];
		positionPionsRetenus[0][1]=propositionJoueur[1];	
		
		switch (direction) {
		case "verticale":
			vecteurX=0;
			vecteurY=1;
			break;
		case "horizontale":
			vecteurX=1;
			vecteurY=0;
			break;	
		case "digonaleMontante":
			vecteurX=1;
			vecteurY=-1;
			break;		
		case "digonaleDescendante":
			vecteurX=1;
			vecteurY=1;
			break;
		default:
			vecteurX=0;
			vecteurY=0;
			break;
		}
		
		for(int passage=-1;passage<2;passage++) {
			for(int valeurVecteur=1;valeurVecteur<4;valeurVecteur++) {
				décalageEnX=(vecteurX*valeurVecteur*passage);
				décalageEnY=(vecteurY*valeurVecteur*passage);
				positionDécaler[0]=propositionJoueur[0]+décalageEnX;
				positionDécaler[1]=propositionJoueur[1]+décalageEnY;
				
				if(passage!=0 
						&& propositionJoueur[0]+décalageEnX< terrain.getLongueur() 
						&& propositionJoueur[0]+décalageEnX>=0 
						&& propositionJoueur[1]+décalageEnY<terrain.getHauteur() 
						&& propositionJoueur[1]+décalageEnY>=0 
						&& terrain.caseAppartientJoueur(positionDécaler, joueursPartiEnCours[tourJoueur])
						&& terrain.estPasConsommer(positionDécaler, direction)
						&& nombrePionsretenus<4)
					nombrePionsretenus++;
			}
		}
		if(nombrePionsretenus==4){
			majComboRestant(positionPionsRetenus,direction);
			return true;
		}		
		return false;
	}
	
	public static void majComboRestant(int[][] pionsFormeCombo,String directionComboConsommer){
		int position[]=new int[2];
		for(int pions=0;pions<4;pions++) {
			position[0]=pionsFormeCombo[pions][0];
			position[1]=pionsFormeCombo[pions][1];
			terrain.consommerComboCase(position,directionComboConsommer);		}
			
	}
		
	private boolean partieFini() {
		if(nbManchesJoué>=nbManchesTotal)
			return true;
		else 
			return false;
	}

	public void jouerPartie() {
		int propositionJoueur[]=new int[2];
		initialiserJoueurs();
		initialiserTerrain();
		do {
			auTourDe();
			terrain.afficherTerrain();
			afficherScore();
			do {
				propositionJoueur=joueursPartiEnCours[tourJoueur].ouPlacerPion();
			}
			while(!terrain.verifierCasePriseOuHorsLimit(propositionJoueur));

			terrain.placeLePionEn(propositionJoueur, joueursPartiEnCours[tourJoueur]);	
			joueursPartiEnCours[tourJoueur].ajouterScore(combienJoueurAmarquerPoints(propositionJoueur));
				
			nbManchesJoué++;
		}
		while(!partieFini());

	}


}
