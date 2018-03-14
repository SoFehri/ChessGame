/**
*@author Alexandre Brilhante
*@version 1.0
*@since 12.10.2015
*/

import java.util.*;

class JeuEchec {
	public static String arg;
	public static Echiquier e;
	public static Piece p;
	public static Piece test;
	public static int compteur   = 0;
	public static String gagnant = "";
	public static void main(String[] args) {
		
		if (args.length!=1){
		usage();
		System.exit(0);
	}

	Scanner s = new Scanner(System.in);
	e         = new Echiquier();
	arg       = args[0];
	init(e);

		if(arg.equals("ascii")) {
			jeuAscii();
		}
		if(arg.equals("unicode")) {
			jeuUnicode();
		}
	}



public static void usage(){
	System.out.println("<usage>:<ascii|unicode>");
}
	public static void jeuAscii() {


		Scanner s = new Scanner(System.in);
		e         = new Echiquier();
		String move; int colonne, ligne, nouvelle_colonne, nouvelle_ligne;
		
		do {
			if(arg.equals("ascii")) {
				if(compteur%2!=0) {
					System.out.println("Joueur Noir?");
				}
				else {
					System.out.println("Joueur Blanc?");
				}
				move = s.nextLine();
				if(move.length()!=5 || move.indexOf(" ")!=2){
					System.out.println("Entrez un mouvement valide.");
				}
				if(move.length()==2) {
					colonne = e.utilisateurLigne(Integer.parseInt(move.charAt(1)+""));
					ligne   = e.utilisateurColonne(move.charAt(0));
					p       = e.examinePiece(colonne, ligne);
					if(p!=null) {
						e.deplacementsPossiblesAscii(p);
						move    = s.nextLine();
					}
				}
				if(move.length()==5 && move.indexOf(" ")==2) {
					colonne          = e.utilisateurLigne(Integer.parseInt(move.charAt(1)+""));
					ligne            = e.utilisateurColonne(move.charAt(0));
					nouvelle_ligne   = e.utilisateurColonne(move.charAt(3));
					nouvelle_colonne = e.utilisateurLigne(Integer.parseInt(move.charAt(4)+""));
					p                = e.examinePiece(colonne, ligne);
					if(p==null) {
						System.out.println("Entrez un mouvement valide.");
						move = s.nextLine();
					}
					else if(p.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
						p.deplace(nouvelle_colonne, nouvelle_ligne);
						e.afficheAscii();
						compteur++;
					}
					else {
						System.out.println("Entrez un mouvement valide.");
						move = s.nextLine();
					}
				}
			}
		} while(!roiCapture());
		System.out.println(gagnant);
	}
	public static void jeuUnicode() {
		Scanner s = new Scanner(System.in);
		e         = new Echiquier();
		String move; int colonne, ligne, nouvelle_colonne, nouvelle_ligne;
		do {
			if(arg.equals("unicode")) {
				if(compteur%2!=0) {
					System.out.println("Joueur Noir?");
				}
				else {
					System.out.println("Joueur Blanc?");
				}
				move = s.nextLine();
				if(move.equals("quit")) {
					System.exit(1);
				}
				if(move.length()!=5 || move.indexOf(" ")!=2){
					System.out.println("Entrez un mouvement valide.");
				}
				if(move.length()==2) {
					colonne = e.utilisateurLigne(Integer.parseInt(move.charAt(1)+""));
					ligne   = e.utilisateurColonne(move.charAt(0));
					p       = e.examinePiece(colonne, ligne);
					e.deplacementsPossiblesUnicode(p);
					move    = s.nextLine();
				}
				if(move.length()==5 && move.indexOf(" ")==2) {
					colonne          = e.utilisateurLigne(Integer.parseInt(move.charAt(1)+""));
					ligne            = e.utilisateurColonne(move.charAt(0));
					nouvelle_ligne   = e.utilisateurColonne(move.charAt(3));
					nouvelle_colonne = e.utilisateurLigne(Integer.parseInt(move.charAt(4)+""));
					p                = e.examinePiece(colonne, ligne);
					if(p==null) {
						System.out.println("Entrez un mouvement valide.");
						move = s.nextLine();
					}
					if(p.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
						p.deplace(nouvelle_colonne, nouvelle_ligne);
						e.afficheUnicode();
						compteur++;
					}
					else {
						System.out.println("Entrez un mouvement valide.");
						move = s.nextLine();
					}
				}
			}
		} while(!roiCapture());
		System.out.println(gagnant);
	}
	public static boolean roiCapture() {
		for(int i=0; i<e.getBlancs_Captures().length; i++) {
			if(e.getBlancs_Captures()[i] instanceof Roi) {
				gagnant = "Les noirs ont gagnés!";
				return true;
			}
			else if(e.getNoirs_Captures()[i] instanceof Roi) {
				gagnant = "Les blancs ont gagnés!";
				return true;
			}
		}
		return false;
	}
	public static void init(Echiquier e) {
		if(arg.equals("ascii")) {
			e.afficheAscii();
		}
		else if(arg.equals("unicode")) {
			e.afficheUnicode();
		}
	}
}