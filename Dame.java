/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Dame extends Piece {
	public Dame(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	}
	public String representationAscii() {
		if(this.estBlanc()) {
			return "D";
		}
		else {
			return "d";
		}
	}
	public String representationUnicode() {
		if(this.estBlanc()) {
			return "♕";
		}
		else {
			return "♛";
		}
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		if(super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
			if(nouvelle_colonne!=this.getColonne() && nouvelle_ligne!=this.getLigne()) {
				boolean a = this.trajectoireValideFou(nouvelle_colonne, nouvelle_ligne);
					if(a==true) {
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
				} return false;
			}
			else if(nouvelle_colonne==this.getColonne() || nouvelle_ligne==this.getLigne()) {
				boolean b = this.trajectoireValideTour(nouvelle_colonne, nouvelle_ligne);	
				if (b==true) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean trajectoireValideTour(int nouvelle_ligne, int nouvelle_colonne) {
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
			else if (nouvelle_colonne>colonne) {
				for(int i=colonne+1; i<nouvelle_colonne; i++) {
					if(getEchiquier().examinePiece(ligne, i)!=null) { 
						return false;
					}
				}
			}
		}
		return true;
	}
	public boolean trajectoireValideFou(int nouvelle_colonne, int nouvelle_ligne) {
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