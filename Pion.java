/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Pion extends Piece {
	public int compteur = 0;
	public Pion(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "P";
		}
		else {
			return "p";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♙";
		}
		else {
			return "♟";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
			boolean est_blanc = this.estBlanc();
			int colonne       = this.getColonne();
			int ligne         = this.getLigne();
			if(est_blanc==false) {
				if(this.getEchiquier().examinePiece(nouvelle_colonne,nouvelle_ligne)==null) {
					if(colonne==1) {
						if((nouvelle_ligne==ligne) && (colonne+1==nouvelle_colonne || nouvelle_colonne==colonne+2)) {
							return true;
						}
					}
					else if(nouvelle_ligne==ligne && nouvelle_colonne==colonne+1) {
						return true;
					}
				}
				else if((this.getEchiquier().examinePiece(nouvelle_colonne, nouvelle_ligne)!=null) && (nouvelle_colonne==colonne+1) && (ligne-1==nouvelle_ligne ||
					nouvelle_ligne==ligne+1)) {
					return true;
				}
				return false;
			}
			else if(est_blanc==true) {
				if(this.getEchiquier().examinePiece(nouvelle_colonne, nouvelle_ligne)==null) {
					if(colonne==6) {
						if(nouvelle_ligne==ligne && (colonne-1==nouvelle_colonne || nouvelle_colonne==colonne-2)) {
							this.compteur++;
							return true;
						}
					}
					else if(nouvelle_ligne==ligne && nouvelle_colonne==colonne-1) {
						return true;
					}
				}
				else if((this.getEchiquier().examinePiece(nouvelle_colonne, nouvelle_ligne)!=null) && (nouvelle_colonne==colonne-1) &&
				(ligne-1==nouvelle_ligne || nouvelle_ligne==ligne+1)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
}