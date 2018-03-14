/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

class Piece
{
	private boolean est_blanc;
	private boolean est_capture;
	private int colonne;
	private int ligne;
	private Echiquier echiquier;
	public static int compteur = 0;
	public boolean estBlanc() {
		return this.est_blanc;
	}
	public boolean estNoir() {
		return !this.est_blanc;
	}
	public boolean estCapture() {
		return this.est_capture;
	}
	public int getLigne() {
		return this.ligne;
	}
	public int getColonne() {
		return this.colonne;
	}
	public boolean getEstCapture() {
		return this.est_capture;
	}
	public Echiquier getEchiquier() {
		return this.echiquier;
	}
	public Piece(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		this.est_blanc   = est_blanc;
		this.colonne     = colonne;
		this.ligne       = ligne;
		this.echiquier   = echiquier;
		this.est_capture = false;
	}
	void meSuisFaitCapture() {
		this.est_capture = true;
	}
	boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
		Piece depart  = echiquier.examinePiece(this.colonne,this.ligne);
		Piece arrivee = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);
		if(this.est_capture== false && echiquier.caseValide(nouvelle_colonne, nouvelle_ligne)== true &&  depart != null) {
			if(arrivee!=null) {
				if (this.est_blanc!=arrivee.est_blanc && this.tourValide()) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (arrivee==null && this.tourValide()) {
				return true;
			}
		}
		return false;
	}
	public boolean tourValide() {		    
		if((compteur%2==0) && (this.est_blanc)) {
			return true;
		}
		else if((compteur%2!=0) && (!this.est_blanc)) {
			return true;
		}
		return false;
	}
	void deplace(int nouvelle_colonne, int nouvelle_ligne) {
		Piece p   = echiquier.prendsPiece(this.colonne, this.ligne);
		p.colonne = nouvelle_colonne;
		p.ligne   = nouvelle_ligne;
		if(echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)!=null) {
			echiquier.capturePiece(nouvelle_colonne, nouvelle_ligne);
		}
		echiquier.posePiece(p);
		compteur++;
	}
	public String toString() {
		return "est_blanc: "+est_blanc+" est_capture: "+est_capture+" colonne: "+colonne+" ligne: "+ligne;
	}
	public String representationAscii() {
		if(est_blanc) {
			return "B";
		}
		else {
			return "N";
		}
	}
	public String representationUnicode() {
		if(est_blanc) {
			return "B";
		}
		else {
			return "N";
		}
	}
}