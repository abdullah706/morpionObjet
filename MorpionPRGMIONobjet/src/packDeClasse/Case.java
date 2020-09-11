package packDeClasse;

public class Case {
	private boolean comboHorizontale,comboVerticale,comboDiagonaleDescendante,comboDiagonaleMontante;
	private Joueur propriétaire;
	
	public Case() {
		this.comboHorizontale=true;
		comboVerticale=true;
		comboDiagonaleDescendante=true;
		comboDiagonaleMontante=true;
		this.propriétaire=null;
	}
	
	public void joueurProprietaire(Joueur joueur) {
		this.propriétaire=joueur;
	}
	
	public boolean joueurEstPropriétaire(Joueur joueurVerifier) {
		if(this.propriétaire!=null)
			return (this.propriétaire.connaiterId()==joueurVerifier.connaiterId());
		else
			return false;
	}
	
	public boolean estPasConsommer(String direction) {
		switch (direction) {
		case "verticale":
			return comboVerticale;
		case "horizontale":
			return comboHorizontale;
		case "diagonaleDescendante":
			return comboDiagonaleDescendante;
		case "diagonaleMontante":
			return comboDiagonaleMontante;
		default:
			return true;
		}
	}
	
	public char getSignePropriétaire() {
		return this.propriétaire.connaitreSignePion();
	}
	
	public void consommerCombo(String direction) {
		switch (direction) {
		case "verticale":
			comboHorizontale=false;
			break;
		case "horizontale":
			comboVerticale=false;
			break;
		case "diagonaleDescendante":
			comboDiagonaleDescendante=false;
			break;
		case "diagonaleMontante":
			comboDiagonaleMontante=false;
			break;

		default:
			break;
		}
	}
	
	public boolean estLibre() {
		return this.propriétaire==null;
	}

}
