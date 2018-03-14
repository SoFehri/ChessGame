/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Cavalier extends Piece {
	public Cavalier(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "C";
		}
		else {
			return "c";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♘";
		}
		else {
			return "♞";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne,nouvelle_ligne)) {
			if(this.getColonne()==nouvelle_colonne+2 && this.getLigne()==nouvelle_ligne+1) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne+2 && this.getLigne()==nouvelle_ligne-1) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne-2 && this.getLigne()==nouvelle_ligne-1) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne-2 && this.getLigne()==nouvelle_ligne+1) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne+1 && this.getLigne()==nouvelle_ligne+2) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne+1 && this.getLigne()==nouvelle_ligne-2) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne-1 && this.getLigne()==nouvelle_ligne-2) {
				return true;
			}
			if(this.getColonne()==nouvelle_colonne-1 && this.getLigne()==nouvelle_ligne+2) {
				return true;
			}
		}
		return false;
	}	
}