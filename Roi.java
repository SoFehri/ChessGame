/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Roi extends Piece {
	public Roi(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "R";
		}
		else {
			return "r";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♔";
		}
		else {
			return "♚";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
			if(this.getColonne()-1 <= nouvelle_colonne && nouvelle_colonne <= this.getColonne()+1 && this.getLigne()-1 <= nouvelle_ligne &&
				nouvelle_ligne <= this.getLigne()+1) {
				return true;
			}
		}
		return false;
	}
}