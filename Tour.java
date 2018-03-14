/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Tour extends Piece {
	public Tour(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "T";
		}
		else {
			return "t";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♖";
		}
		else {
			return "♜";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
			if(nouvelle_colonne==this.getColonne() || nouvelle_ligne==this.getLigne()) {
				boolean b = this.trajectoireValide(nouvelle_colonne, nouvelle_ligne);	
				if (b==true) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean trajectoireValide(int nouvelle_ligne, int nouvelle_colonne) {
		int ligne   = this.getColonne();
		int colonne = this.getLigne();
		if(nouvelle_colonne==colonne) {
			if(nouvelle_ligne<ligne) {
				for(int i=ligne-1; i>nouvelle_ligne; i--){
					if(getEchiquier().examinePiece(i, colonne)!=null) {
						return false;
					}
				}
			}
			else if(nouvelle_ligne>ligne) {
				for(int i=ligne+1; i<nouvelle_ligne; i++) {
					if(getEchiquier().examinePiece(i, colonne)!=null){ 
						return false;
					}
				}
			}
		}
		if(nouvelle_ligne==ligne) {
			if(nouvelle_colonne<colonne) {
				for(int i=colonne-1; i>nouvelle_colonne; i--) {
					if(getEchiquier().examinePiece(ligne, i)!=null) {
						return false;
					}
				}
			}
			else if(nouvelle_colonne>colonne) {
				for(int i=colonne+1; i<nouvelle_colonne; i++) {
					if(getEchiquier().examinePiece(ligne, i)!=null) { 
						return false;
					}
				}
			}
		}
		return true;
	}
}