// fonctions utiles pour les grilles

import java.util.Arrays;

public class gates {
	
	public static void print(int[][] gate) {
		// affichage d'une grille
		
		System.out.println(display(gate));
		System.out.println();
		
	}
	
	public static void printNoColor(int[][] gate, int nbPlayers) {
		// affichage d'une grille sans les couleurs
		
		String t;
		t = display(gate);
		for (int i = 0; i < nbPlayers + 1; i++) t = t.replace(String.valueOf(i), "X");
		
		System.out.println(t);
		System.out.println();
		
	}

	private static String display(int[][] gateArg) {
		// génère un String d'affichage de grille
		
		int[][] gate = new int[gateArg[0].length][gateArg.length];
		for (int k = 0; k < gateArg.length; k++) {
			for (int i = 0; i < gateArg[k].length; i++) {
				gate[gate.length - 1 - i][k] = gateArg[k][i];
			}
		}
		
		String t = Arrays.deepToString(gate);
		t = t.replace(", [", "\n");
		t = t.replace(", ", "|");
		t = t.replace("[", "");
		t = t.replace("]", "");
		t = t.replace("0", " ");
		
		return t;
	}
	
	public static int[][] inverse(int[][] gateArg) {
		// inversion d'une grille ; basculement des colonnes en lignes et des lignes en colonnes
		
		int[][] gate = new int[gateArg[0].length][gateArg.length];
		
		for (int k = 0; k < gateArg.length; k++) {
			for (int i = 0; i < gateArg[0].length; i++) {
				gate[i][k] = gateArg[k][i];
			}
		}
		
		return gate;
		
	}
	
	public static boolean isFull(int[][] gateArg) {
		
		for (int i = 0; i < gateArg.length; i++) {
			if (token.possible(gateArg, i)) {
				return false;
			}
		}
		
		return true;
		
	}
	
}
