/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/
 
class Fou extends Piece {
	public Fou(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "F";
		}
		else {
			return "f";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♗";
		}
		else {
			return "♝";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
			if (this.trajectoireValide(nouvelle_colonne,nouvelle_ligne)) {
				if(nouvelle_colonne-this.getColonne()==nouvelle_ligne-this.getLigne()) {
					return true;
				}
				if(nouvelle_colonne-this.getColonne()==this.getLigne()-nouvelle_ligne) {
					return true;
				}
				if(this.getColonne()-nouvelle_colonne==nouvelle_ligne-this.getLigne()) {
					return true;
				}
				if(nouvelle_colonne-this.getColonne()==nouvelle_ligne-this.getLigne()) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean trajectoireValide(int nouvelle_colonne, int nouvelle_ligne) {
		int ligne        = this.getColonne();
		int colonne      = this.getLigne();
		int temp         = nouvelle_colonne;
		nouvelle_colonne = nouvelle_ligne;
		nouvelle_ligne   = temp;
		int j            = colonne;
        if(ligne>nouvelle_ligne) {
        	if(colonne<nouvelle_colonne) {
        		for(int i=ligne-1; i>nouvelle_ligne; i--) {
        			j+=1;
        			if(j<nouvelle_colonne) {
        				if(getEchiquier().examinePiece(i, j)!=null) {
        					return false;
        				}
        			}
        		}
        	}
        	if(colonne>nouvelle_colonne) {
        		for(int i=ligne-1; i>nouvelle_ligne; i--) {
        			j-=1;
        			if(j>nouvelle_colonne) {
        				if(getEchiquier().examinePiece(i, j)!=null) {
        					return false;
        				}
        			}
        		}
        	}
        }
        else if(ligne<nouvelle_ligne) {
        	if(colonne<nouvelle_colonne) {
        		for(int i=ligne+1; i<nouvelle_ligne; i++) {
        			j+=1;
        			if(j<nouvelle_colonne) {
        				if(getEchiquier().examinePiece(i, j)!=null) {
        					return false;
        				}
        			}
        		}
        	}
        	if(colonne>nouvelle_colonne) {
        		for(int i=ligne+1; i<nouvelle_ligne; i++) {
        			j-=1;
        			if(j>nouvelle_colonne) {
        				if(getEchiquier().examinePiece(i, j)!=null) {
        					return false;
        				}
        			}
        		}
        	}
        }      
        return true;
    }
}