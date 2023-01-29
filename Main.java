
/*
 * 
 * PUISSANCE 4
 * 
 * Classes :
 * - Main       | initialisations
 * - Game       | variables de jeu et fonctions principales
 * - Analyzer   | fonctions d'analyse
 * - Analyzer3D | fonctions d'analyse �tendues � la 3D
 * - Token      | v�rifications li�es aux jetons et g�n�ration de grille
 * - Gates      | fonctions utiles de grilles
 * - IA         | utilisation d'une IA
 * - StatsIA    | objet de statistiques pour les calculs de l'IA
 * - Frame      | fen�tre
 * - Canvas     | canvas
 * 
 * Les variables sont modifiables depuis le code-source.
 * Variables int�ressantes :
 * - rap          | Main     | taille des colonnes
 * - nb           | Game     | nombre de jetons � alligner
 * - nbPlayers    | Game     | nombre de joueur
 * - rows         | Game     | longueur de la grille
 * - cols         | Game     | hauteur de la grille
 * - useCol       | Analyzer | prise en compte ou non des colonnes
 * - useRow       | Analyzer | prise en compte ou non des lignes
 * - useDiag      | Analyzer | prise en compte ou non des diagonales
 * - useSquare    | Analyzer | prise en compte ou non des carr�s
 * - memory       | Game     | jouer le jeu en mode M�moire, sans voir les jetons
 * - memoryColors | Game     | jouer le jeu en mode M�moireCouleurs, sans voir les couleurs
 * 
 * Le nombre de joueur est modifiable. Les couleurs de chacun sont pr�d�finies jusqu'au
   huiti�me joueur.
 * 
 * FONCTIONS DES CLASSES
 * 
 * -MAIN-
 * main(String[] args): void // fonction de lancement
 * maxIndex(int[] arrArg): int // trouve l'index de l'�l�ment le plus grand
 * 
 * -GAME-
 * addCanvas(Canvas can): void // ajout de l'attribut Canvas � un jeu
 * verify(int play): void // ajoute, si c'est possible, un jeton, dans la colonne PLAY donn�e
 * historic(): void // r�utilise le dernier historique
 * actualizeHistoric(int[][] lastMove): void // met � jour l'historique
 * playIA(): void // fait jouer l'IA
 * 
 * -ANALYZER-
 * use(int[][] gateArg, int player): boolean // renvoie si une grille contient un puissance 4 (sauf modification de NB)
 * use(int[][] gateArg, int player, int nb): boolean // renvoie si une grille contient un puissance de la longueur NB
 * 
 * -ANALYZER3D-
 * 
 * -TOKEN-
 * add(int[][] gateArg, int row, int player): int[][] // g�n�re une grille avec un nouveau jeton
 * possible(int[][] gate, int row): boolean // renvoie si un jeton peut �tre plac� dans une colonne donn�e
 * posCol(int[][] gate, int row): int // renvoie la ligne � laquelle sera plac� un jeton
 * 
 * -GATES-
 * print(int[][] gate): void // affiche une grille dans la console
 * inverse(int[][] gateArg): int[][] // inverse une grille
 * 
 * -IA-
 * findPlay(int[][] gateArg, int player): int // trouve la meilleure solution pour jouer
 * 
 * -STATSIA-
 * 
 * -FRAME-
 * 
 * -CANVAS-
 * updateGate(int[][] gateArg): void // actualise la grille sauvegard�e
 * 
 * ALGORITHME DE L'ORDINATEUR
 * 
 * Fonctionne par r�cursion. L'ordinateur teste toutes les possibilit�s pour chaque coup et calcule le meilleur emplacement de jeu.
 * 
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	public static void main(String[] args) {
		
		// taille d'une colonne
		final int rap = 75;
		
		// cr�ation des outils principaux
		
		final Game game = new Game();
		
		final Frame win = new Frame(game, rap);
		final Canvas can = new Canvas(win, game, rap);
		final InfosCan infos = new InfosCan(win, can, game, rap);
		
		// activation du bouton UNDO
		
		win.undo.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		game.historic();
	    		
	    	}
	    	
	    });
		
		game.addCanvas(can);

	}
	
	public static int maxIndex(int[] arrArg) {
		
		int v = 0;
		
		for (int i: arrArg) {
			if (i > v) {
				v = i;
			}
		}
		for (int j = 0; j < arrArg.length; j++) {
			if (arrArg[j] == v) {
				return j;
			}
		}
		
		return 0;
		
	}
	
}
