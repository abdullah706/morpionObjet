package packDeClasse;

import java.util.Scanner;

public class Joueur {
	
	private int score;
	private int identifiantJoueur;
	private char signePionJoueur;
	private Scanner choixPosition;
	
	public Joueur(int identifiant,char signePion) {
		this.identifiantJoueur=identifiant;
		this.signePionJoueur=signePion;
		initialiserScore();
	}

	public void initialiserScore() {
		this.score=0;
	}
	
	public void ajouterScore(int score) {
		this.score+=score;
	}
	
	public int connaitreScore() {
		return this.score;
	}
	
	public int connaiterId() {
		return this.identifiantJoueur;
	}
	
	public char connaitreSignePion() {
		return this.signePionJoueur;
	}
	
	public int[] ouPlacerPion() {
		int propositionPlacementPion[]=new int[2];
		choixPosition=new Scanner(System.in);
		
		System.out.println("Veuillez choisir la position en x");
		propositionPlacementPion[0] = choixPosition.nextInt();
		
		System.out.println("Veuillez choisir la position en y");
		propositionPlacementPion[1] = choixPosition.nextInt();
		
		return propositionPlacementPion;
	}	
	
}
