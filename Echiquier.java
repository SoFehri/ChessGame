/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

import java.util.*;
import java.io.*;

class Echiquier {
	private Piece[][] tableau_de_jeu = new Piece[8][8];
	private Piece[] blancs_captures  = new Piece[16];
	private Piece[] noirs_captures   = new Piece[16];
	public Piece[] getBlancs_Captures() {
		return this.blancs_captures;
	}
	public Piece[] getNoirs_Captures() {
		return this.noirs_captures;
	}
	public Piece[][] getTableau_De_Jeu() {
		return this.tableau_de_jeu;
	}
	public boolean caseValide(int colonne, int ligne) {
		if(colonne<0 || colonne>=8 || ligne<0 || ligne>=8) {
			return false;
		}
		return true;
	}
	public Piece examinePiece(int colonne, int ligne) {
		return this.tableau_de_jeu[colonne][ligne];
	}
	public Piece prendsPiece(int colonne, int ligne) {
		Piece temp                     = tableau_de_jeu[colonne][ligne];
		tableau_de_jeu[colonne][ligne] = null;
		return temp;
	}
	public void posePiece(Piece p) {
		this.tableau_de_jeu[p.getColonne()][p.getLigne()] = p;
	}
	public void capturePiece(int colonne, int ligne) {
		Piece piece                    = tableau_de_jeu[colonne][ligne];
		tableau_de_jeu[colonne][ligne] = null;
		if(piece.estBlanc()) {
			piece.meSuisFaitCapture();
			int i = trouvePremierNull(blancs_captures);
			blancs_captures[i] = piece;
		}
		else {
			piece.meSuisFaitCapture();
			int j = trouvePremierNull(noirs_captures);
			noirs_captures[j] = piece;
		}
	}
	public int trouvePremierNull(Piece[] tab) {
		int indice = 0;
		for(int i=0; i<tab.length; i++) {
			if(tab[i]==null) {
				indice = i;
				break;
			}
		}
		return indice;
	}
	public int utilisateurColonne(char a) {
		int colonne = 0;
		switch(a) {
			case 'a':
				colonne = 0;
				break;
			case 'b':
				colonne = 1;
				break;
			case 'c':
				colonne = 2;
				break;
			case 'd':
				colonne = 3;
				break;
			case 'e':
				colonne = 4;
				break;
			case 'f':
				colonne = 5;
				break;
			case 'g':
				colonne = 6;
				break;
			case 'h':
				colonne = 7;
				break;
		}
		return colonne;
	}
	public int utilisateurLigne(int x) {
		int ligne = 0;
		switch(x) {
			case 1:
				ligne = 7;
				break;
			case 2:
				ligne = 6;
				break;
			case 3:
				ligne = 5;
				break;
			case 4:
				ligne = 4;
				break;
			case 5:
				ligne = 3;
				break;
			case 6:
				ligne = 2;
				break;
			case 7:
				ligne = 1;
				break;
			case 8:
				ligne = 0;
				break;
		}
		return ligne;
	}
	public void afficheAscii() {
		int id = 8;
		for(int g=0; g<51; g++) {
			System.out.println();
		}
		int x = 0;
		System.out.println("Les noirs ont capturés: ");
		while(blancs_captures[x]!=null) {
			System.out.print(blancs_captures[x].representationAscii()+" ");
			x++;
		}
		System.out.println();
		System.out.println("    a b c d e f g h");
		System.out.println("    - - - - - - - -");
		for(int i=0; i<8; i++) {
			System.out.print(id+" | ");
			for(int j=0; j<8; j++) {
				if(tableau_de_jeu[i][j]==null) {
					System.out.print(". ");
				}
				else {
					System.out.print(tableau_de_jeu[i][j].representationAscii()+" ");
				}
			}
			System.out.print("| "+id);
			id--;
			System.out.println();
		}
		System.out.println("    - - - - - - - -");
		System.out.println("    a b c d e f g h");
		System.out.println();
		int y = 0;
		System.out.println("Les blancs ont capturés: ");
		while(noirs_captures[y]!=null) {
			System.out.print(noirs_captures[y].representationAscii()+" ");
			y++;
		}
		System.out.println();
	}
	public void afficheUnicode() {
		PrintStream sysout = null;
		try { sysout = new PrintStream(System.out, true, "UTF-8"); }
		catch(UnsupportedEncodingException e) {}
		int id = 8;
		for(int g=0; g<51; g++) {
			System.out.println();
		}
		int x = 0;
		System.out.println("Les noirs ont capturés: ");
		while(blancs_captures[x]!=null) {
			sysout.print(blancs_captures[x].representationUnicode()+" ");
			x++;
		}
		System.out.println();
		System.out.println("    a    b    c    d    e    f    g    h");
		sysout.println("  ├───────────────────────────────────────┤");
		for(int i=0; i<8; i++) {
			System.out.print(id+" | ");
			for(int j=0; j<8; j++) {
				if(tableau_de_jeu[i][j]==null) {
					System.out.print(".  | ");
				}
				else {
					sysout.print(tableau_de_jeu[i][j].representationUnicode()+"  │ ");
				}
			}
			System.out.print(id);
			id--;
			System.out.println();
			sysout.println("  ├───────────────────────────────────────┤");
		}
		System.out.println("     a    b    c    d    e    f    g    h");
		System.out.println();
		int y = 0;
		System.out.println("Les blancs ont capturés: ");
		while(noirs_captures[y]!=null) {
			sysout.print(noirs_captures[y].representationUnicode()+" ");
			y++;
		}
		System.out.println();
	}
	public void deplacementsPossiblesAscii(Piece p) {
		for(int x=0; x<50; x++) {
			System.out.println();
		}
		int id = 8;
		System.out.println();
		System.out.println("    a b c d e f g h");
		System.out.println("    - - - - - - - -");
		for(int i=0; i<8; i++) {
			System.out.print(id+" | ");
			for(int j=0; j<8; j++) {
				if(p.deplacementValide(i,j)) {
					if(examinePiece(i,j)==null) {
						System.out.print("x ");
					}
					else {
						System.out.print("X ");
					}
				}
				else if(tableau_de_jeu[i][j]==null) {
					System.out.print(". ");
				}
				else {
					System.out.print(tableau_de_jeu[i][j].representationAscii()+" ");
				}
			}
			System.out.print("| "+id);
			id--;
			System.out.println();
		}
		System.out.println("    - - - - - - - -");
		System.out.println("    a b c d e f g h");
		System.out.println();
		System.out.println("Entrez un mouvement valide.");
	}
	public void deplacementsPossiblesUnicode(Piece p) {
		PrintStream sysout = null;
		try { sysout = new PrintStream(System.out, true, "UTF-8"); }
		catch(UnsupportedEncodingException e) {}
		for(int x=0; x<50; x++) {
			System.out.println();
		}
		int id = 8;
		System.out.println();
		System.out.println("     a    b    c    d    e    f    g    h");
		sysout.println("  ├───────────────────────────────────────┤");
		for(int i=0; i<8; i++) {
			System.out.print(id+" | ");
			for(int j=0; j<8; j++) {
				if(p.deplacementValide(i,j)) {
					if(examinePiece(i,j)!=null) {
						System.out.print("X  | ");
					}
					else {
						System.out.print("x  | ");
					}
				}
				else if(tableau_de_jeu[i][j]==null) {
					System.out.print(".  | ");
				}
				else {
					sysout.print(tableau_de_jeu[i][j].representationUnicode()+"  │ ");
				}
			}
			System.out.print(id);
			id--;
			System.out.println();
			sysout.println("  ├───────────────────────────────────────┤");
		}
		System.out.println("     a    b    c    d    e    f    g    h");
		System.out.println();
		System.out.println("Entrez un mouvement valide.");
	}
	public Echiquier() {
		tableau_de_jeu[0][0] = new Tour(false, 0, 0, this);
		tableau_de_jeu[0][1] = new Cavalier(false, 0, 1, this);
		tableau_de_jeu[0][2] = new Fou(false, 0, 2, this);
		tableau_de_jeu[0][3] = new Dame(false, 0, 3, this);
		tableau_de_jeu[0][4] = new Roi(false, 0, 4, this);
		tableau_de_jeu[0][5] = new Fou(false, 0, 5, this);
		tableau_de_jeu[0][6] = new Cavalier(false, 0, 6, this);
		tableau_de_jeu[0][7] = new Tour(false, 0, 7, this);
		for(int i=0; i<8; i++) {
			tableau_de_jeu[1][i] = new Pion(false, 1, i, this);
			tableau_de_jeu[6][i] = new Pion(true, 6, i, this);
		}
		for(int j=2; j<6; j++){
			for(int k=0; k<8; k++) {
				tableau_de_jeu[j][k] = null;
			}
		}
		tableau_de_jeu[7][0] = new Tour(true, 7, 0, this);
		tableau_de_jeu[7][1] = new Cavalier(true, 7, 1, this);
		tableau_de_jeu[7][2] = new Fou(true, 7, 2, this);
		tableau_de_jeu[7][3] = new Dame(true, 7, 3, this);     
		tableau_de_jeu[7][4] = new Roi(true, 7, 4, this);
		tableau_de_jeu[7][5] = new Fou(true, 7, 5, this);
		tableau_de_jeu[7][6] = new Cavalier(true, 7, 6, this);
		tableau_de_jeu[7][7] = new Tour(true, 7, 7, this);
	}
}